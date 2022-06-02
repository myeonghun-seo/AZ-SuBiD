package az.subid.persistance.mongodb.impl;


import az.subid.dto.SequenceDTO;
import az.subid.persistance.mongodb.AbstractMongoDBComon;
import az.subid.persistance.mongodb.ISequenceMapper;
import az.subid.util.CmmUtil;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.stereotype.Component;

/**
 * 시퀸스 필터
 *
 * 각 컬렉션의 시퀸스를 저장 밎 관리를 합니다.
 */
@Slf4j
@Component("SequenceMapper")
public class SequenceMapper extends AbstractMongoDBComon implements ISequenceMapper {

    /**
     * 흐름 :
     * if시퀸스 존재 유무 검사
     * else 시퀸스 검색 후 증가 >> 시퀸스 검색
     * 시퀸스 값 넣기
     * 반환
     */
    @Override
    public SequenceDTO getSequence(String colNm) {

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".getSequence Start!");

        // 조회 결과를 전달하기 위한 객체 생성하기
        SequenceDTO rDTO = new SequenceDTO();

        // MongoDB 컬렉션 지정하기
        MongoCollection<Document> col = mongodb.getCollection("Sequence");

        // 컬랙션의 시퀸시 넣을 값
        Document doc = new Document();

        // 시퀸스 존재 유무 검사
        // 존재하지 않으면 생성하기
        if(col.find(new Document("_id", colNm)) == null) {

            // 컬랙션의 시퀸스 값 생성
            doc.append("_id", colNm);
            doc.append("seq", 1);
            
            // 입력한 값 넣기
            col.insertOne(doc);

        } else { // 존재한다면, 검색하기

            // 시퀸스 검색 후 증가
            col.findOneAndUpdate(
                    Filters.eq("_id", colNm),
                    Filters.eq("$inc", Filters.eq("seq", 1)),
                    new FindOneAndUpdateOptions().upsert(true)
            );

            // 조회 결과 중 출력할 컬럼들(SQL의 SELECT절과 FROM절 가운데 컬럼들과 유사함)
            Document projection = new Document();
            projection.append("_id", "$_id");
            projection.append("seq", "$seq");

            // MongoDB의 find 명령어를 통해 조회할 경우 사용함
            // 조회하는 데이터의 양이 적은 경우, find를 사용하고, 데이터양이 많은 경우 무조건 Aggregate 사용한다.
            FindIterable<Document> rs = col.find(new Document("_id", colNm)).projection(projection);

            // 결과값은 하나니까 첫번째 값만 가져옴.
            doc = rs.first();

        }

        // 조회 테스트
        String _id = CmmUtil.nvl(doc.getString("_id"));
        String seq = CmmUtil.nvl(doc.getLong("seq").toString());

        log.info("collection : " + _id);
        log.info("sequence : " + seq);

        rDTO.setCol_id(_id);
        rDTO.setSeq_nl(seq);

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".getSequence End!");

        return rDTO;
    }

}

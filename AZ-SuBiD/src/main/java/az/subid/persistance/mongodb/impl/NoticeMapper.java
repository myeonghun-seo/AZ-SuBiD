package az.subid.persistance.mongodb.impl;

import az.subid.dto.NoticeDTO;
import az.subid.persistance.mongodb.AbstractMongoDBComon;
import az.subid.persistance.mongodb.INoticeMapper;
import az.subid.util.CmmUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Indexes;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static com.mongodb.client.model.Updates.set;

@Slf4j
@Component("NoticeMapper")
public class NoticeMapper extends AbstractMongoDBComon implements INoticeMapper {

    // MongoDB 컬렉션 이름
    private final String colNm = new NoticeDTO().colNm();

    @Override
    public List<NoticeDTO> getNoticeList() throws Exception {

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".getNoticeList Start!");

        // 조회 결과를 전달하기 위한 객체 생성하기
        List<NoticeDTO> rList = new LinkedList<>();

        // 컬렉션이 없다면 생성하기
        if(!mongodb.collectionExists(colNm)) {

            // 컬렉션 생성
            super.createCollection(colNm);
            // 인덱스 생성
            mongodb.getCollection(colNm).createIndex(Indexes.ascending("sequence"));

        }

        // MongoDB 컬렉션 지정하기
        MongoCollection<Document> col = mongodb.getCollection(colNm);

        // 조회 결과 중 출력할 컬럼들(SQL의 SELECT절과 FROM절 가운데 컬럼들과 유사함)
        Document projection = new Document();
        projection.append("sequence", "$sequence");
        projection.append("title", "$title");
        projection.append("read_cnt", "$read_cnt");
        projection.append("user_id", "$user_id");
        projection.append("reg_id", "$reg_id");
        projection.append("reg_dt", "$reg_dt");

        // MongoDB는 무조건 ObjectID가 자동생성되며, ObjectID는 사용하지 않을 때, 조회할 필요가 없음.
        projection.append("_id", 0);

        // MongoDB의 find 명령어를 통해 조회할 경우 사용함
        // 조회하는 데이터의 양이 적은 경우, find를 사용하고, 데이터양이 많은 경우 무조건 Aggregate 사용한다.
        FindIterable<Document> rs = col.find(new Document()).projection(projection);

        for (Document doc : rs) {

            if (doc == null) {

                doc = new Document();

            }

            // 조회 테스트
            String sequence = CmmUtil.nvl(doc.getString("sequence"));
            String title = CmmUtil.nvl(doc.getString("title"));
            String read_cnt = CmmUtil.nvl(doc.getString("read_cnt"));
            String user_id = CmmUtil.nvl(doc.getString("user_id"));
            String reg_id = CmmUtil.nvl(doc.getString("reg_id"));
            String reg_dt = CmmUtil.nvl(doc.getString("reg_dt"));

            log.info("sequence : " + sequence);
            log.info("title : " + title);
            log.info("read_cnt : " + read_cnt);
            log.info("user_id : " + user_id);
            log.info("reg_id : " + reg_id);
            log.info("reg_dt : " + reg_dt);

            NoticeDTO rDTO = new NoticeDTO();

            rDTO.setSequence(sequence);
            rDTO.setTitle(title);
            rDTO.setRead_cnt(read_cnt);
            rDTO.setUser_id(user_id);
            rDTO.setReg_id(reg_id);
            rDTO.setReg_dt(reg_dt);

            // 레코드 결과를 List에 저장하기
            rList.add(rDTO);

        }

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".getNoticeList End!");

        return rList;
    }

    @Override
    public int insertNoticeInfo(NoticeDTO pDTO) throws Exception {

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".insertNoticeInfo Start!");

        int res = 0;

        // MongoDB 컬렉션 지정하기
        MongoCollection<Document> col = mongodb.getCollection(colNm);

        // DTO를 Map 데이터타입으로 변경한 뒤, 변경된 Map 데이터타입을 Document로 변경하기
        col.insertOne(new Document(new ObjectMapper().convertValue(pDTO, Map.class)));

        res = 1;

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".insertNoticeInfo End!");

        return res;
    }

    @Override
    public NoticeDTO getNoticeInfo(NoticeDTO pDTO) throws Exception {

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".getNoticeInfo Start!");

        // 조회 결과를 전달하기 위한 객체 생성하기
        NoticeDTO rDTO = new NoticeDTO();

    // MongoDB 컬렉션 지정하기
        MongoCollection<Document> col = mongodb.getCollection(colNm);

        // 조회 결과 중 출력할 컬럼들(SQL의 SELECT절과 FROM절 가운데 컬럼들과 유사함)
        Document projection = new Document();
        projection.append("sequence", "$sequence");
        projection.append("title", "$title");
        projection.append("notice_yn", "$notice_yn");
        projection.append("contents", "$contents");
        projection.append("user_id", "$user_id");
        projection.append("read_cnt", "$read_cnt");
        projection.append("reg_id", "$reg_id");
        projection.append("reg_dt", "$reg_dt");
        projection.append("chg_id", "$chg_id");
        projection.append("chg_dt", "$chg_dt");

        // MongoDB는 무조건 ObjectID가 자동생성되며, ObjectID는 사용하지 않을 때, 조회할 필요가 없음.
        projection.append("_id", 0);

        // MongoDB의 find 명령어를 통해 조회할 경우 사용함
        // 조회하는 데이터의 양이 적은 경우, find를 사용하고, 데이터양이 많은 경우 무조건 Aggregate 사용한다.
        FindIterable<Document> rs = col.find(new Document("sequence", pDTO.getSequence())).projection(projection);

        for (Document doc : rs) {

            if (doc == null) {

                doc = new Document();

            }

            // 조회 테스트
            String sequence = CmmUtil.nvl(doc.getString("sequence"));
            String title = CmmUtil.nvl(doc.getString("title"));
            String notice_yn = CmmUtil.nvl(doc.getString("notice_yn"));
            String contents = CmmUtil.nvl(doc.getString("contents"));
            String user_id = CmmUtil.nvl(doc.getString("user_id"));
            String read_cnt = CmmUtil.nvl(doc.getString("read_cnt"));
            String reg_id = CmmUtil.nvl(doc.getString("reg_id"));
            String reg_dt = CmmUtil.nvl(doc.getString("reg_dt"));
            String chg_id = CmmUtil.nvl(doc.getString("chg_id"));
            String chg_dt = CmmUtil.nvl(doc.getString("chg_dt"));

            log.info("sequence : " + sequence);
            log.info("title : " + title);
            log.info("read_cnt : " + read_cnt);
            log.info("reg_id : " + reg_id);
            log.info("reg_dt : " + reg_dt);

            // rDTO에 값 집어넣기
            rDTO.setSequence(sequence);
            rDTO.setTitle(title);
            rDTO.setNotice_yn(notice_yn);
            rDTO.setContents(contents);
            rDTO.setUser_id(user_id);
            rDTO.setRead_cnt(read_cnt);
            rDTO.setReg_id(reg_id);
            rDTO.setReg_dt(reg_dt);
            rDTO.setChg_id(chg_id);
            rDTO.setChg_dt(chg_dt);

        }

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".getNoticeInfo End!");

        return rDTO;
    }

    @Override
    public int updateNoticeReadCnt(NoticeDTO pDTO) throws Exception {

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".updateNoticeReadCnt Start!");

        int res = 0;

        // MongoDB 컬렉션 지정하기
        MongoCollection<Document> col = mongodb.getCollection(colNm);

        // 조회 결과 중 출력할 컬럼들(SQL의 SELECT절과 FROM절 가운데 컬럼들과 유사함)
        Document projection = new Document();

        projection.append("read_cnt", "$read_cnt");

        // MongoDB의 find 명령어를 통해 조회할 경우 사용함
        // 조회하는 데이터의 양이 적은 경우, find를 사용하고, 데이터양이 많은 경우 무조건 Aggregate 사용한다.
        FindIterable<Document> rs = col.find(new Document("sequence", pDTO.getSequence())).projection(projection);

        Document doc = rs.first();

        if (doc == null) {

            doc = new Document();

        }

        // 조회 테스트
        String read_cnt = CmmUtil.nvl(doc.getString("read_cnt"));

        log.info("read_cnt : " + read_cnt);

        // rDTO에 값 집어넣기
        pDTO.setRead_cnt(Integer.toString(Integer.parseInt(read_cnt) + 1));

        log.info(pDTO.getRead_cnt());

        // 람다식 활용하여 nickname 필드 추가하기
        // 전체 컬랙션에 있는 데이터를 삭제하기
        rs.forEach(ddoc -> col.updateOne(ddoc, set("read_cnt", pDTO.getRead_cnt())));

        res = 1;

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".updateNoticeReadCnt End!");

        return res;
    }

    @Override
    public int updateNoticeInfo(NoticeDTO pDTO) throws Exception {

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".updateNoticeInfo Start!");

        int res = 0;

        // 조회 결과를 전달하기 위한 객체 생성하기
        NoticeDTO rDTO = new NoticeDTO();

        // MongoDB 컬렉션 지정하기
        MongoCollection<Document> col = mongodb.getCollection(colNm);

        // 조회 결과 중 출력할 컬럼들(SQL의 SELECT절과 FROM절 가운데 컬럼들과 유사함)
        Document projection = new Document();
        projection.append("sequence", "$sequence");

        // MongoDB의 find 명령어를 통해 조회할 경우 사용함
        // 조회하는 데이터의 양이 적은 경우, find를 사용하고, 데이터양이 많은 경우 무조건 Aggregate 사용한다.
        FindIterable<Document> rs = col.find(new Document("sequence", pDTO.getSequence())).projection(projection);

        // 한줄로 append해서 수정할 필드 추가해도 되지만, 가독성이 떨어져 줄마다 append 함
        Document updateDoc = new Document();
        updateDoc.append("title", CmmUtil.nvl(pDTO.getTitle())); // 기존 필드 수정
        updateDoc.append("contents", CmmUtil.nvl(pDTO.getContents())); // 기존 필드 수정
        updateDoc.append("chg_id", CmmUtil.nvl(pDTO.getChg_id())); // 기존 필드 수정
        updateDoc.append("chg_dt", CmmUtil.nvl(pDTO.getChg_dt())); // 기존 필드 수정

        rs.forEach(doc -> col.updateOne(doc, new Document("$set", updateDoc)));

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".updateNoticeInfo End!");

        return res;

    }

    @Override
    public int deleteNoticeInfo(NoticeDTO pDTO) throws Exception {

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".deleteNoticeInfo Start!");

        int res = 0;

        // 조회 결과를 전달하기 위한 객체 생성하기
        NoticeDTO rDTO = new NoticeDTO();

        // MongoDB 컬렉션 지정하기
        MongoCollection<Document> col = mongodb.getCollection(colNm);

        // 조회할 조건
        Document query = new Document();
        query.append("sequence", CmmUtil.nvl(pDTO.getSequence()));

        // MongoDB 데이터 삭제는 반드시 컬렉션으 조회하고, 조회된 ObjectID를 기반으로 데이터를 삭제함
        // MongoDB 환경은 분산환경(Sharding)으로 구성될 수 있기 때문에 정확한 PX에 매핑하기 위해서임
        FindIterable<Document> rs = col.find(query);

        // 전체 컬랙션에 있는 데이터를 삭제하기
        rs.forEach(doc -> col.deleteOne(doc));

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".deleteNoticeInfo End!");

        return res;

    }
}

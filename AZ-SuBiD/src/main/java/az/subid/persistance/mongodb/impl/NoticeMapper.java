package az.subid.persistance.mongodb.impl;


import az.subid.dto.NoticeDTO;
import az.subid.persistance.mongodb.AbstractMongoDBComon;
import az.subid.persistance.mongodb.INoticeMapper;
import az.subid.util.CmmUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Filter;

@Slf4j
@Component("NoticeMapper")
public class NoticeMapper extends AbstractMongoDBComon implements INoticeMapper {

    @Override
    public List<NoticeDTO> getNoticeList(String colNm) throws Exception {

        log.info(this.getClass().getName() + ".getNoticeList Start!");

        // 조회 결과를 전달하기 위한 객체 생성하기
        List<NoticeDTO> rList = new LinkedList<>();

        // MongoDB 컬렉션 지정하기
        MongoCollection<Document> col = mongodb.getCollection(colNm);

        // 조회 결과 중 출력할 컬럼들(SQL의 SELECT절과 FROM절 가운데 컬럼들과 유사함)
        Document projection = new Document();
        projection.append("notice_seq", "$notice_seq");
        projection.append("title", "$title");
        projection.append("read_cnt", "$read_cnt");
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
            String notice_seq = CmmUtil.nvl(doc.getString("notice_seq"));
            String title = CmmUtil.nvl(doc.getString("title"));
            String read_cnt = CmmUtil.nvl(doc.getString("read_cnt"));
            String reg_id = CmmUtil.nvl(doc.getString("reg_id"));
            String reg_dt = CmmUtil.nvl(doc.getString("reg_dt"));

            log.info("notice_seq : " + notice_seq);
            log.info("title : " + title);
            log.info("read_cnt : " + read_cnt);
            log.info("reg_id : " + reg_id);
            log.info("reg_dt : " + reg_dt);

            NoticeDTO rDTO = new NoticeDTO();

            rDTO.setNotice_seq(notice_seq);
            rDTO.setTitle(title);
            rDTO.setRead_cnt(read_cnt);
            rDTO.setReg_id(reg_id);
            rDTO.setReg_dt(reg_dt);

            // 레코드 결과를 List에 저장하기
            rList.add(rDTO);

        }

        log.info(this.getClass().getName() + ".getNoticeList End!");

        return rList;
    }

    @Override
    public int insertNoticeInfo(NoticeDTO pDTO, String colNm) throws Exception {

        log.info(this.getClass().getName() + ".insertNoticeInfo Start!");

        int res = 0;

        // MongoDB 컬렉션 지정하기
        MongoCollection<Document> col = mongodb.getCollection(colNm);

        // DTO를 Map 데이터타입으로 변경한 뒤, 변경된 Map 데이터타입을 Document로 변경하기
        col.insertOne(new Document(new ObjectMapper().convertValue(pDTO, Map.class)));

        res = 1;

        log.info(this.getClass().getName() + ".insertNoticeInfo End!");

        return res;
    }

    @Override
    public NoticeDTO getNoticeInfo(NoticeDTO pDTO, String colNm) throws Exception {

        log.info(this.getClass().getName() + ".getNoticeInfo Start!");

        // 조회 결과를 전달하기 위한 객체 생성하기
        NoticeDTO rDTO = new NoticeDTO();

        // MongoDB 컬렉션 지정하기
        MongoCollection<Document> col = mongodb.getCollection(colNm);

        // 조회 결과 중 출력할 컬럼들(SQL의 SELECT절과 FROM절 가운데 컬럼들과 유사함)
        Document projection = new Document();
        projection.append("notice_seq", "$notice_seq");
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
        FindIterable<Document> rs = col.find(new Document("notice_seq", pDTO.getNotice_seq())).projection(projection);

        for (Document doc : rs) {

            if (doc == null) {

                doc = new Document();

            }

            // 조회 테스트
            String notice_seq = CmmUtil.nvl(doc.getString("notice_seq"));
            String title = CmmUtil.nvl(doc.getString("title"));
            String notice_yn = CmmUtil.nvl(doc.getString("notice_yn"));
            String contents = CmmUtil.nvl(doc.getString("contents"));
            String user_id = CmmUtil.nvl(doc.getString("user_id"));
            String read_cnt = CmmUtil.nvl(doc.getString("read_cnt"));
            String reg_id = CmmUtil.nvl(doc.getString("reg_id"));
            String reg_dt = CmmUtil.nvl(doc.getString("reg_dt"));
            String chg_id = CmmUtil.nvl(doc.getString("chg_id"));
            String chg_dt = CmmUtil.nvl(doc.getString("chg_dt"));

            log.info("notice_seq : " + notice_seq);
            log.info("title : " + title);
            log.info("read_cnt : " + read_cnt);
            log.info("reg_id : " + reg_id);
            log.info("reg_dt : " + reg_dt);

            // rDTO에 값 집어넣기
            rDTO.setNotice_seq(notice_seq);
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

        log.info(this.getClass().getName() + ".getNoticeInfo End!");

        return rDTO;
    }

    @Override
    public int updateNoticeReadCnt(NoticeDTO pDTO, String colNm) throws Exception {

        log.info(this.getClass().getName() + ".updateNoticeReadCnt Start!");

        int res = 0;

        // MongoDB 컬렉션 지정하기
        MongoCollection<Document> col = mongodb.getCollection(colNm);

        // 조회 결과 중 출력할 컬럼들(SQL의 SELECT절과 FROM절 가운데 컬럼들과 유사함)
        Document projection = new Document();

        projection.append("read_cnt", "$read_cnt");

        // MongoDB의 find 명령어를 통해 조회할 경우 사용함
        // 조회하는 데이터의 양이 적은 경우, find를 사용하고, 데이터양이 많은 경우 무조건 Aggregate 사용한다.
        FindIterable<Document> rs = col.find(projection);

        for (Document doc : rs) {

            if (doc == null) {

                doc = new Document();

            }

            // 조회 테스트
            String read_cnt = CmmUtil.nvl(doc.getString("read_cnt"));

            log.info("read_cnt : " + read_cnt);

            // rDTO에 값 집어넣기
            pDTO.setRead_cnt(read_cnt);

        }

        log.info(pDTO.getRead_cnt());

//        rs.forEach(doc -> col.updateOne(doc, new Document("$set", new Document("read_cnt", Integer.toString(Integer.getInteger(pDTO.getRead_cnt()) + 1)))));

        // DTO를 Map 데이터타입으로 변경한 뒤, 변경된 Map 데이터타입을 Document로 변경하기
        col.findOneAndUpdate(
                Filters.eq("notice_seq",pDTO.getNotice_seq()),
                Filters.eq("read_cnt", Integer.toString(Integer.parseInt(pDTO.getRead_cnt())+1)),
                new FindOneAndUpdateOptions().upsert(true)
                );

        log.info(this.getClass().getName() + ".updateNoticeReadCnt End!");

        return res;
    }

    @Override
    public int updateNoticeInfo(NoticeDTO pDTO, String colNm) throws Exception {

        log.info(this.getClass().getName() + ".updateNoticeInfo Start!");

        int res = 0;

        // 조회 결과를 전달하기 위한 객체 생성하기
        NoticeDTO rDTO = new NoticeDTO();

        // MongoDB 컬렉션 지정하기
        MongoCollection<Document> col = mongodb.getCollection(colNm);

        log.info(this.getClass().getName() + ".updateNoticeInfo End!");

        return res;

    }

    @Override
    public int deleteNoticeInfo(NoticeDTO pDTO, String colNm) throws Exception {

        log.info(this.getClass().getName() + ".deleteNoticeInfo Start!");

        int res = 0;

        // 조회 결과를 전달하기 위한 객체 생성하기
        NoticeDTO rDTO = new NoticeDTO();

        // MongoDB 컬렉션 지정하기
        MongoCollection<Document> col = mongodb.getCollection(colNm);

        log.info(this.getClass().getName() + ".deleteNoticeInfo End!");

        return res;

    }
}

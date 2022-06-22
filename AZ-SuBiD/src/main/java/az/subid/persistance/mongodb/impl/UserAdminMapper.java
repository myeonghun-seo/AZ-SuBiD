package az.subid.persistance.mongodb.impl;

import az.subid.dto.UserAdminDTO;
import az.subid.dto.UserInfoDTO;
import az.subid.persistance.mongodb.AbstractMongoDBComon;
import az.subid.persistance.mongodb.IUserAdminMapper;
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

@Slf4j
@Component("UserAdminMapper")
public class UserAdminMapper extends AbstractMongoDBComon implements IUserAdminMapper {

    // MongoDB 컬렉션 이름
    private final String colNm = new UserAdminDTO().colNm();

    // MongoDB 사용자 컬렉션 이름
    private final String infoColNm = "UserInfoCollection";


    @Override
    public List<UserInfoDTO> getListAdminUser() throws Exception {

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".getListAdminUser Start!");

        // 리턴할 리스트 값 준비
        List<UserInfoDTO> rList = new LinkedList<>();

        // MongoDB 컬렉션 지정하기
        MongoCollection<Document> col = mongodb.getCollection(infoColNm);

        // 조회 결과 중 출력할 컬럼들(SQL의 SELECT절과 FROM절 가운데 컬럼들과 유사함)
        Document projection = new Document();
        projection.append("sequence", "$sequence");
        projection.append("reg_id", "$reg_id");
        projection.append("reg_dt", "$reg_dt");
        projection.append("chg_id", "$chg_id");
        projection.append("chg_dt", "$chg_dt");

        projection.append("user_id", "$user_id");
        projection.append("user_nm", "$user_nm");
        projection.append("user_auth", "$user_auth");
        projection.append("addr1", "$addr1");
        projection.append("addr2", "$addr2");

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
            String reg_id = CmmUtil.nvl(doc.getString("reg_id"));
            String reg_dt = CmmUtil.nvl(doc.getString("reg_dt"));
            String chg_id = CmmUtil.nvl(doc.getString("chg_id"));
            String chg_dt = CmmUtil.nvl(doc.getString("chg_dt"));

            String user_id = CmmUtil.nvl(doc.getString("user_id"));
            String user_nm = CmmUtil.nvl(doc.getString("user_nm"));
            String user_auth = CmmUtil.nvl(doc.getString("user_auth"));
            String addr1 = CmmUtil.nvl(doc.getString("addr1"));
            String addr2 = CmmUtil.nvl(doc.getString("addr2"));

            // 많아서 3개의 값만 노출
            log.info("sequence : " + sequence);
            log.info("user_id : " + user_id);
            log.info("user_auth : " + user_auth);

            UserInfoDTO rDTO = new UserInfoDTO();

            rDTO.setSequence(sequence);
            rDTO.setReg_id(reg_id);
            rDTO.setReg_dt(reg_dt);
            rDTO.setChg_id(chg_id);
            rDTO.setChg_dt(chg_dt);
            
            rDTO.setUser_id(user_id);
            rDTO.setUser_nm(user_nm);
            rDTO.setUser_auth(user_auth);
            rDTO.setEmail(reg_id);
            rDTO.setAddr1(addr1);
            rDTO.setAddr2(addr2);

            // 레코드 결과를 List에 저장하기
            rList.add(rDTO);

        }

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".getListAdminUser End!");

        return rList;
    }

    @Override
    public List<UserAdminDTO> getListAdminInfo(UserAdminDTO pDTO) throws Exception {

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".getListAdminInfo Start!");

        // 리턴하기 위한 리스트 값
        List<UserAdminDTO> rList = new LinkedList<>();

        // MongoDB 컬렉션 지정하기
        MongoCollection<Document> col = mongodb.getCollection(colNm);

        // 찾아야 할 쿼리값 생성
        Document query = new Document();
        if(!CmmUtil.nvl(pDTO.getUser_seq()).equals("")) {
            query.append("user_seq", CmmUtil.nvl(pDTO.getUser_seq()));
        }
        if(!CmmUtil.nvl(pDTO.getLog_name()).equals("")) {
            query.append("log_name", CmmUtil.nvl(pDTO.getLog_name()));
        }


        // 조회 결과 중 출력할 컬럼들(SQL의 SELECT절과 FROM절 가운데 컬럼들과 유사함)
        Document projection = new Document();
        projection.append("sequence", "$sequence");
        projection.append("reg_id", "$reg_id");
        projection.append("reg_dt", "$reg_dt");
        projection.append("chg_id", "$chg_id");
        projection.append("chg_dt", "$chg_dt");

        projection.append("user_seq", "$user_seq");
        projection.append("user_id", "$user_id");
        projection.append("log_name", "$log_name");
        projection.append("log_info", "$log_info");

        // MongoDB는 무조건 ObjectID가 자동생성되며, ObjectID는 사용하지 않을 때, 조회할 필요가 없음.
        projection.append("_id", 0);

        // MongoDB의 find 명령어를 통해 조회할 경우 사용함
        // 조회하는 데이터의 양이 적은 경우, find를 사용하고, 데이터양이 많은 경우 무조건 Aggregate 사용한다.
        FindIterable<Document> rs = col.find(query).projection(projection);

        for (Document doc : rs) {

            if (doc == null) {

                doc = new Document();

            }

            // 조회 테스트
            String sequence = CmmUtil.nvl(doc.getString("sequence"));
            String reg_id = CmmUtil.nvl(doc.getString("reg_id"));
            String reg_dt = CmmUtil.nvl(doc.getString("reg_dt"));
            String chg_id = CmmUtil.nvl(doc.getString("chg_id"));
            String chg_dt = CmmUtil.nvl(doc.getString("chg_dt"));

            String user_seq = CmmUtil.nvl(doc.getString("user_seq"));
            String user_id = CmmUtil.nvl(doc.getString("user_id"));
            String log_name = CmmUtil.nvl(doc.getString("log_name"));
            String log_info = CmmUtil.nvl(doc.getString("log_info"));

            // 많아서 3개의 값만 노출
            log.info("user_seq : " + user_seq);
            log.info("user_id : " + user_id);
            log.info("log_name : " + log_name);
            log.info("log_info : " + log_info);

            UserAdminDTO rDTO = new UserAdminDTO();

            rDTO.setSequence(sequence);
            rDTO.setReg_id(reg_id);
            rDTO.setReg_dt(reg_dt);
            rDTO.setChg_id(chg_id);
            rDTO.setChg_dt(chg_dt);

            rDTO.setUser_seq(user_seq);
            rDTO.setUser_id(user_id);
            rDTO.setLog_name(log_name);
            rDTO.setLog_info(log_info);

            // 레코드 결과를 List에 저장하기
            rList.add(rDTO);

        }

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".getListAdminInfo End!");

        return rList;
    }

    @Override
    public int insertUserAdmin(UserAdminDTO pDTO) throws Exception {

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".insertUserAdmin Start!");

        int res = 0;

        // 컬렉션이 없다면 생성하기
        if(!mongodb.collectionExists(colNm)) {

            // 컬렉션 생성
            super.createCollection(colNm);
            // 인덱스 생성
            mongodb.getCollection(colNm).createIndex(Indexes.ascending("sequence"));

        }

        // MongoDB 컬렉션 지정하기
        MongoCollection<Document> col = mongodb.getCollection(colNm);

        log.info(pDTO.getLog_name());

        // DTO를 Map 데이터타입으로 변경한 뒤, 변경된 Map 데이터 타입을 Document로 변경하기
        col.insertOne(new Document(new ObjectMapper().convertValue(pDTO, Map.class)));

        res = 1;

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".insertUserAdmin End!");

        return res;
    }

}

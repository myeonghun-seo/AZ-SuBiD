package az.subid.persistance.mongodb.impl;

import az.subid.dto.UserInfoDTO;
import az.subid.persistance.mongodb.AbstractMongoDBComon;
import az.subid.persistance.mongodb.IUserInfoMapper;
import az.subid.util.CmmUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component("UserInfoMapper")
public class UserInfoMapper extends AbstractMongoDBComon implements IUserInfoMapper {

    // MongoDB 사용자 로그 컬렉션 이름
    private final String colNm = new UserInfoDTO().colNm();

    @Override
    public int insertUserInfo(UserInfoDTO pDTO) throws Exception {

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".insertUserInfo Start!");

        int res = 0;

        // 컬렉션이 없다면 생성하기
        if(!mongodb.collectionExists(colNm)) {

            // 컬렉션 생성
            super.createCollection(colNm);
            // 인덱스 생성
//            mongodb.getCollection(colNm).createIndex(Indexes.ascending("sequence"));

        }
        
        // MongoDB 컬렉션 지정하기
        MongoCollection<Document> col = mongodb.getCollection(colNm);

        log.info(pDTO.getUser_nm());

        // DTO를 Map 데이터타입으로 변경한 뒤, 변경된 Map 데이터 타입을 Document로 변경하기
        col.insertOne(new Document(new ObjectMapper().convertValue(pDTO, Map.class)));

        res = 1;

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".insertUserInfo End!");

        return res;
    }

    @Override
    public UserInfoDTO getUserExists(UserInfoDTO pDTO) throws Exception {

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".getUserExists Start!");

        // 조회 결과를 전달하기 위한 객체 생성하기
        UserInfoDTO rDTO = new UserInfoDTO();

        // MongoDB 컬렉션 지정하기
        MongoCollection<Document> col = mongodb.getCollection(colNm);

        // 조회 결과 중 출력할 컬럼들(SQL의 SELECT절과 FROM절 가운데 컬럼들과 유사함)
        Document projection = new Document();
        projection.append("user_id", CmmUtil.nvl(pDTO.getUser_id()));
        projection.append("email", CmmUtil.nvl(pDTO.getEmail()));

        // 결과 값을 카운트한다.
        long ll = col.countDocuments(projection);

        // 비교 시작
        rDTO.setExists_yn(ll > 0 ? "Y" : "N");

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".getUserExists End!");

        return rDTO;
    }

    @Override
    public UserInfoDTO getUserLoginCheck(UserInfoDTO pDTO) throws Exception {

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".getUserLoginCheck Start!");

        // 조회 결과를 전달하기 위한 객체 생성하기
        UserInfoDTO rDTO = new UserInfoDTO();

        // MongoDB 컬렉션 지정하기
        MongoCollection<Document> col = mongodb.getCollection(colNm);

        // 찾아야 할 쿼리값 생성
        Document query = new Document();
        query.append("user_id", CmmUtil.nvl(pDTO.getUser_id()));
        query.append("user_pw", CmmUtil.nvl(pDTO.getUser_pw()));

        // 조회 결과 중 출력할 컬럼들(SQL의 SELECT절과 FROM절 가운데 컬럼들과 유사함)
        Document projection = new Document();
        projection.append("user_id", "$user_id");
        projection.append("user_nm", "$user_nm");
        projection.append("email", "$email");

        // 조건에 맞는 값을 검색
        FindIterable<Document> rs = col.find(query).projection(projection);

        // 결과값은 하나니까 첫번째 값만 가져옴.
        Document doc = rs.first();

        if (doc != null) {

            // 조회 테스트
            String user_id = CmmUtil.nvl(doc.getString("user_id"));
            String user_nm = CmmUtil.nvl(doc.getString("user_nm"));
            String email = CmmUtil.nvl(doc.getString("email"));

            log.info("user_id : " + user_id);
            log.info("user_nm : " + user_nm);
            log.info("email : " + email);

            // rDTO에 값 집어넣기
            rDTO.setUser_id(user_id);
            rDTO.setUser_nm(user_nm);
            rDTO.setEmail(email);

        }

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".getUserLoginCheck End!");

        return rDTO;
    }

    @Override
    public int initUserInfoPasswd(UserInfoDTO pDTO) throws Exception {

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".initUserPasswd Start!");

        int res = 0;

        // MongoDB 컬렉션 지정하기
        MongoCollection<Document> col = mongodb.getCollection(colNm);

        // 조회 결과 중 출력할 컬럼들(SQL의 SELECT절과 FROM절 가운데 컬럼들과 유사함)
        Document projection = new Document();
        projection.append("user_id", "$user_id");

        // MongoDB의 find 명령어를 통해 조회할 경우 사용함
        // 조회하는 데이터의 양이 적은 경우, find를 사용하고, 데이터양이 많은 경우 무조건 Aggregate 사용한다.
        FindIterable<Document> rs = col.find(new Document("email", pDTO.getEmail())).projection(projection);

        // 한줄로 append해서 수정할 필드 추가해도 되지만, 가독성이 떨어져 줄마다 append 함
        Document updateDoc = new Document();
        updateDoc.append("user_pw", CmmUtil.nvl(pDTO.getUser_pw())); // 기존 필드 수정

        // DTO를 Map 데이터타입으로 변경한 뒤, 변경된 Map 데이터 타입을 Document로 변경하기
        rs.forEach(doc -> col.updateOne(doc, new Document("$set", updateDoc)));

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".initUserPasswd Start!");

        return res;
    }

    @Override
    public UserInfoDTO getUserInfo(UserInfoDTO pDTO) throws Exception {

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".getUserInfo Start!");

        // 조회 결과를 전달하기 위한 객체 생성하기
        UserInfoDTO rDTO = new UserInfoDTO();

        // MongoDB 컬렉션 지정하기
        MongoCollection<Document> col = mongodb.getCollection(colNm);

        // 찾아야 할 쿼리값 생성
        Document query = new Document();
        if(!CmmUtil.nvl(pDTO.getUser_id()).equals("")) {
            query.append("user_id", CmmUtil.nvl(pDTO.getUser_id()));
        }
        if(!CmmUtil.nvl(pDTO.getUser_nm()).equals("")) {
            query.append("user_nm", CmmUtil.nvl(pDTO.getUser_nm()));
        }
        if(!CmmUtil.nvl(pDTO.getUser_pw()).equals("")) {
            query.append("user_pw", CmmUtil.nvl(pDTO.getUser_pw()));
        }
        if(!CmmUtil.nvl(pDTO.getUser_auth()).equals("")) {
            query.append("user_auth", CmmUtil.nvl(pDTO.getUser_auth()));
        }
        if(!CmmUtil.nvl(pDTO.getEmail()).equals("")) {
            query.append("email", CmmUtil.nvl(pDTO.getEmail()));
        }
        if(!CmmUtil.nvl(pDTO.getAddr1()).equals("")) {
            query.append("addr1", CmmUtil.nvl(pDTO.getAddr1()));
        }
        if(!CmmUtil.nvl(pDTO.getAddr2()).equals("")) {
            query.append("addr2", CmmUtil.nvl(pDTO.getAddr2()));
        }

        // 조회 결과 중 출력할 컬럼들(SQL의 SELECT절과 FROM절 가운데 컬럼들과 유사함)
        Document projection = new Document();
        projection.append("user_id", "$user_id");
        projection.append("user_nm", "$user_nm");
        projection.append("user_pw", "$user_pw");
        projection.append("user_auth", "$user_auth");
        projection.append("email", "$email");
        projection.append("addr1", "$addr1");
        projection.append("addr2", "$addr2");

        // 조건에 맞는 값을 검색
        FindIterable<Document> rs = col.find(query).projection(projection);

        // 결과값은 하나니까 첫번째 값만 가져옴.
        Document doc = rs.first();

        if (doc != null) {

            // 조회 테스트
            String user_id = CmmUtil.nvl(doc.getString("user_id"));
            String user_nm = CmmUtil.nvl(doc.getString("user_nm"));
            String user_pw = CmmUtil.nvl(doc.getString("user_pw"));
            String user_auth = CmmUtil.nvl(doc.getString("user_auth"));
            String email = CmmUtil.nvl(doc.getString("email"));
            String addr1 = CmmUtil.nvl(doc.getString("addr1"));
            String addr2 = CmmUtil.nvl(doc.getString("addr2"));

            log.info("user_id : " + user_id);
            log.info("user_nm : " + user_nm);
            log.info("user_pw : " + user_pw);
            log.info("user_auth : " + user_auth);
            log.info("email : " + email);
            log.info("addr1 : " + addr1);
            log.info("addr2 : " + addr2);

            // rDTO에 값 집어넣기
            rDTO.setUser_id(user_id);
            rDTO.setUser_nm(user_nm);
            rDTO.setUser_pw(user_pw);
            rDTO.setUser_auth(user_auth);
            rDTO.setEmail(email);
            rDTO.setAddr1(addr1);
            rDTO.setAddr2(addr2);

        }

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".getUserInfo End!");

        return rDTO;
    }

    @Override
    public int getUpdatePasswd(UserInfoDTO pDTO) throws Exception {

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".getUpdatePasswd Start!");

        int res = 0;

        // MongoDB 컬렉션 지정하기
        MongoCollection<Document> col = mongodb.getCollection(colNm);

        // 조회 결과 중 출력할 컬럼들(SQL의 SELECT절과 FROM절 가운데 컬럼들과 유사함)
        Document projection = new Document();
        projection.append("user_id", "$user_id");

        // MongoDB의 find 명령어를 통해 조회할 경우 사용함
        // 조회하는 데이터의 양이 적은 경우, find를 사용하고, 데이터양이 많은 경우 무조건 Aggregate 사용한다.
        FindIterable<Document> rs = col.find(new Document("user_id", pDTO.getUser_id())).projection(projection);

        // 한줄로 append해서 수정할 필드 추가해도 되지만, 가독성이 떨어져 줄마다 append 함
        Document updateDoc = new Document();
        updateDoc.append("user_pw", CmmUtil.nvl(pDTO.getUser_pw())); // 기존 필드 수정

        // DTO를 Map 데이터타입으로 변경한 뒤, 변경된 Map 데이터 타입을 Document로 변경하기
        rs.forEach(doc -> col.updateOne(doc, new Document("$set", updateDoc)));

        res = 1;

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".getUpdatePasswd End!");

        return res;
    }

    public int updateUserInfo(UserInfoDTO pDTO) throws Exception {

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".updateUserInfo Start!");

        int res = 0;

        // MongoDB 컬렉션 지정하기
        MongoCollection<Document> col = mongodb.getCollection(colNm);

        // 조회 결과 중 출력할 컬럼들(SQL의 SELECT절과 FROM절 가운데 컬럼들과 유사함)
        Document projection = new Document();
        projection.append("user_id", "$user_id");

        // MongoDB의 find 명령어를 통해 조회할 경우 사용함
        // 조회하는 데이터의 양이 적은 경우, find를 사용하고, 데이터양이 많은 경우 무조건 Aggregate 사용한다.
        FindIterable<Document> rs = col.find(new Document("user_id", pDTO.getUser_id())).projection(projection);

        log.info("user_id : " + pDTO.getUser_nm());

        // 한줄로 append해서 수정할 필드 추가해도 되지만, 가독성이 떨어져 줄마다 append 함
        Document updateDoc = new Document();
        updateDoc.append("user_nm", CmmUtil.nvl(pDTO.getUser_nm())); // 기존 필드 수정
        updateDoc.append("email", CmmUtil.nvl(pDTO.getEmail())); // 기존 필드 수정
        updateDoc.append("addr1", CmmUtil.nvl(pDTO.getAddr1())); // 기존 필드 수정
        updateDoc.append("addr2", CmmUtil.nvl(pDTO.getAddr2())); // 기존 필드 수정
        updateDoc.append("chg_id", CmmUtil.nvl(pDTO.getChg_id())); // 기존 필드 수정
        updateDoc.append("chg_dt", CmmUtil.nvl(pDTO.getChg_dt())); // 기존 필드 수정

        // DTO를 Map 데이터타입으로 변경한 뒤, 변경된 Map 데이터 타입을 Document로 변경하기
        rs.forEach(doc -> col.updateOne(doc, new Document("$set", updateDoc)));

        res = 1;

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".updateUserInfo End!");

        return res;
    }

    public int deleteUserInfo(UserInfoDTO pDTO) throws Exception {

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".deleteUserInfo Start!");

        int res = 0;

        // MongoDB 컬렉션 지정하기
        MongoCollection<Document> col = mongodb.getCollection(colNm);

        // 조회할 조건
        Document query = new Document();
        query.append("user_id", CmmUtil.nvl(pDTO.getUser_id()));

        // MongoDB 데이터 삭제는 반드시 컬렉션으 조회하고, 조회된 ObjectID를 기반으로 데이터를 삭제함
        // MongoDB 환경은 분산환경(Sharding)으로 구성될 수 있기 때문에 정확한 PX에 매핑하기 위해서임
        FindIterable<Document> rs = col.find(query);

        // 전체 컬랙션에 있는 데이터를 삭제하기
        rs.forEach(doc -> col.deleteOne(doc));

        res = 1;

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".deleteUserInfo End!");

        return res;
    }

}

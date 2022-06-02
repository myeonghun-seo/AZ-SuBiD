package az.subid.persistance.mongodb.impl;

import az.subid.dto.UserInfoDTO;
import az.subid.persistance.mongodb.AbstractMongoDBComon;
import az.subid.persistance.mongodb.IUserInfoMapper;
import az.subid.util.CmmUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.bson.Document;
import org.springframework.stereotype.Component;

import javax.print.Doc;
import java.util.Map;

@Slf4j
@Component("UserInfoMapper")
public class UserInfoMapper extends AbstractMongoDBComon implements IUserInfoMapper {

        // 회원 가입하기
    @Override
    public int insertUserInfo(UserInfoDTO pDTO, String colNm) throws Exception {

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".insertUserInfo Start!");

        int res = 0;

        // 컬렉션이 없다면 생성하기
        if(!mongodb.collectionExists(colNm)) {
            super.createCollection(colNm);
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
    public UserInfoDTO getUserExists(UserInfoDTO pDTO, String colNm) throws Exception {

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".getUserExists Start!");

        // 조회 결과를 전달하기 위한 객체 생성하기
        UserInfoDTO rDTO = new UserInfoDTO();

        // MongoDB 컬렉션 지정하기
        MongoCollection<Document> col = mongodb.getCollection(colNm);

        // 조회 결과 중 출력할 컬럼들(SQL의 SELECT절과 FROM절 가운데 컬럼들과 유사함)
        Document projection = new Document();
        projection.append("user_id", pDTO.getUser_id());
        projection.append("email", pDTO.getEmail());

        // 결과 값을 카운트한다.
        long ll = col.countDocuments(projection);

        // 비교 시작
        rDTO.setExists_yn(ll > 0 ? "Y" : "N");

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".getUserExists End!");

        return rDTO;
    }

    @Override
    public UserInfoDTO getUserLoginCheck(UserInfoDTO pDTO, String colNm) throws Exception {

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".getUserLoginCheck Start!");

        // 조회 결과를 전달하기 위한 객체 생성하기
        UserInfoDTO rDTO = new UserInfoDTO();

        // MongoDB 컬렉션 지정하기
        MongoCollection<Document> col = mongodb.getCollection(colNm);

        // 찾아야 할 쿼리값 생성
        Document query = new Document();
        query.append("user_id", pDTO.getUser_id());
        query.append("user_pw", pDTO.getUser_pw());

        // 조회 결과 중 출력할 컬럼들(SQL의 SELECT절과 FROM절 가운데 컬럼들과 유사함)
        Document projection = new Document();
        projection.append("user_id", "$user_id");
        projection.append("user_nm", "$user_nm");
        projection.append("email", "$email");

        // 조건에 맞는 값을 검색
        FindIterable<Document> rs = col.find(new Document(query)).projection(projection);

        // 결과값은 하나니까 첫번째 값만 가져옴.
        Document doc = rs.first();

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

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".getUserLoginCheck End!");

        return rDTO;
    }

}

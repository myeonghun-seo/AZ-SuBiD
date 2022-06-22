package az.subid.persistance.mongodb.impl;

import az.subid.dto.UserMyPageDTO;
import az.subid.persistance.mongodb.AbstractMongoDBComon;
import az.subid.persistance.mongodb.IUserMyPageMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component("UserMyPageMapper")
public class UserMyPageMapper extends AbstractMongoDBComon implements IUserMyPageMapper {

    // MongoDB 사용자 로그 컬렉션 이름
    private final String colNm = new UserMyPageDTO().colNm();

}

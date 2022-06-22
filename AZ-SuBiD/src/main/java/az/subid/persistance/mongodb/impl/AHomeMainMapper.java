package az.subid.persistance.mongodb.impl;

import az.subid.dto.AHomeMainDTO;
import az.subid.persistance.mongodb.AbstractMongoDBComon;
import az.subid.persistance.mongodb.IAHomeMainMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component("AHomeMainMapper")
public class AHomeMainMapper extends AbstractMongoDBComon implements IAHomeMainMapper {

    // MongoDB 컬렉션 이름
    private final String colNm = new AHomeMainDTO().colNm();

}

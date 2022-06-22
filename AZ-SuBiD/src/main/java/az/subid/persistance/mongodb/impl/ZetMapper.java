package az.subid.persistance.mongodb.impl;

import az.subid.dto.ZetDTO;
import az.subid.persistance.mongodb.AbstractMongoDBComon;
import az.subid.persistance.mongodb.IZetMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component("ZetMapper")
public class ZetMapper extends AbstractMongoDBComon implements IZetMapper {

    // MongoDB 컬렉션 이름
    private final String colNm = new ZetDTO().colNm();

}

package az.subid.persistance.mongodb.impl;

import az.subid.persistance.mongodb.AbstractMongoDBComon;
import az.subid.persistance.mongodb.IRankingMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component("RankingMapper")
public class RankingMapper extends AbstractMongoDBComon implements IRankingMapper {
}

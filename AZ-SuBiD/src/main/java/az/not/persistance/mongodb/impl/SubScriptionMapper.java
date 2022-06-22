package az.subid.persistance.mongodb.impl;

import az.subid.persistance.mongodb.AbstractMongoDBComon;
import az.subid.persistance.mongodb.ISubScriptionMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component("SubScriptionMapper")
public class SubScriptionMapper extends AbstractMongoDBComon implements ISubScriptionMapper {
}

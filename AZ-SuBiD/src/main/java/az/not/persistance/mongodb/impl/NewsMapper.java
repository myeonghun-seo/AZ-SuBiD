package az.subid.persistance.mongodb.impl;

import az.subid.persistance.mongodb.AbstractMongoDBComon;
import az.subid.persistance.mongodb.INewsMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component("NewsMapper")
public class NewsMapper extends AbstractMongoDBComon implements INewsMapper {
}

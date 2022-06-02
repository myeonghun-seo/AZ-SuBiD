package az.subid.persistance.mongodb.impl;

import com.subid.market.persistance.mongodb.AbstractMongoDBComon;
import com.subid.market.persistance.mongodb.ISubScriptionMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component("SubScriptionMapper")
public class SubScriptionMapper extends AbstractMongoDBComon implements ISubScriptionMapper {
}

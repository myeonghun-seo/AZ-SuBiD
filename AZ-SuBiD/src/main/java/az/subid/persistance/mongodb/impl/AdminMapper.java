package az.subid.persistance.mongodb.impl;

import com.subid.market.persistance.mongodb.AbstractMongoDBComon;
import com.subid.market.persistance.mongodb.IAdminMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component("AdminMapper")
public class AdminMapper extends AbstractMongoDBComon implements IAdminMapper {
}

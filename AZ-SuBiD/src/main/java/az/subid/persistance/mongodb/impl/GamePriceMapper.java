package az.subid.persistance.mongodb.impl;

import com.subid.market.persistance.mongodb.AbstractMongoDBComon;
import com.subid.market.persistance.mongodb.IGamePriceMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component("GamePriceMapper")
public class GamePriceMapper extends AbstractMongoDBComon implements IGamePriceMapper {
}

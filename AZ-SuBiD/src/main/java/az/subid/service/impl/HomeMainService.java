package az.subid.service.impl;

import com.subid.market.persistance.mongodb.IHomeMainMapper;
import com.subid.market.service.IHomeMainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service("HomeMainService")
public class HomeMainService implements IHomeMainService {

    @Resource(name = "HomeMainMapper")
    private IHomeMainMapper homeMainMapper; // MongoDB에 저장할 Mapper

}

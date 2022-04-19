package com.subid.market.persistance.mongodb.impl;

import com.subid.market.persistance.mongodb.AbstractMongoDBComon;
import com.subid.market.persistance.mongodb.IMyPageMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component("MyPageMapper")
public class MyPageMapper extends AbstractMongoDBComon implements IMyPageMapper {
}

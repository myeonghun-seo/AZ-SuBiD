package com.subid.market.persistance.mongodb.impl;

import com.subid.market.persistance.mongodb.AbstractMongoDBComon;
import com.subid.market.persistance.mongodb.IZetMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component("ZetController")
public class ZetController extends AbstractMongoDBComon implements IZetMapper {
}

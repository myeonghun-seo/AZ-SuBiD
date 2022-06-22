package az.subid.service.impl;

import az.subid.persistance.mongodb.ISequenceMapper;
import az.subid.persistance.mongodb.IZetMapper;
import az.subid.service.IZetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service("ZetService")
public class ZetService implements IZetService {

    // MongoDB에 저장할 Mapper
    @Resource(name = "ZetMapper")
    private IZetMapper zetMapper;

    // MongoDB에 시퀸스 검색 Mapper
    @Resource(name="SequenceMapper")
    private ISequenceMapper sequenceMapper;

}

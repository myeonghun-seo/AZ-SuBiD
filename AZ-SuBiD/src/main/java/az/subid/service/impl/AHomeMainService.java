package az.subid.service.impl;

import az.subid.persistance.mongodb.IAHomeMainMapper;
import az.subid.persistance.mongodb.ISequenceMapper;
import az.subid.service.IAHomeMainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service("AHomeMainService")
public class AHomeMainService implements IAHomeMainService {

    // MongoDB에 저장할 Mapper
    @Resource(name = "AHomeMainMapper")
    private IAHomeMainMapper aHomeMainMapper;

    // MongoDB에 시퀸스 검색 Mapper
    @Resource(name="SequenceMapper")
    private ISequenceMapper sequenceMapper;

}

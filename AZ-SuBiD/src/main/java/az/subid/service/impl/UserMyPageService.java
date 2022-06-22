package az.subid.service.impl;

import az.subid.persistance.mongodb.ISequenceMapper;
import az.subid.persistance.mongodb.IUserMyPageMapper;
import az.subid.service.IUserMyPageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service("UserMyPageService")
public class UserMyPageService implements IUserMyPageService {

    // MongoDB에 저장할 Mapper
    @Resource(name = "UserMyPageMapper")
    private IUserMyPageMapper userMyPageMapper;

    // MongoDB에 시퀸스 검색 Mapper
    @Resource(name="SequenceMapper")
    private ISequenceMapper sequenceMapper;

}

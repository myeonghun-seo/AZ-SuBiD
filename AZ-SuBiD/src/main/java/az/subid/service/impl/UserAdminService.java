package az.subid.service.impl;

import az.subid.dto.UserAdminDTO;
import az.subid.dto.UserInfoDTO;
import az.subid.persistance.mongodb.ISequenceMapper;
import az.subid.persistance.mongodb.IUserAdminMapper;
import az.subid.service.IUserAdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@Service("UserAdminService")
public class UserAdminService implements IUserAdminService {

    // MongoDB에 저장할 Mapper
    @Resource(name = "UserAdminMapper")
    private IUserAdminMapper userAdminMapper;

    // MongoDB에 시퀸스 검색 Mapper
    @Resource(name = "SequenceMapper")
    private ISequenceMapper sequenceMapper;

    @Override
    public List<UserInfoDTO> getListAdminUser() throws Exception{

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".getListAdminUser Start!");

        // 리턴하기 위한 리스트 값
        List<UserInfoDTO> rList = new LinkedList<>();

        rList = userAdminMapper.getListAdminUser();

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".getListAdminUser End!");

        return rList;
    }

    @Override
    public List<UserAdminDTO> getListAdminInfo(UserAdminDTO pDTO) throws Exception {

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".getListAdminInfo Start!");

        // 리턴하기 위한 리스트 값
        List<UserAdminDTO> rList = new LinkedList<>();

        rList = userAdminMapper.getListAdminInfo(pDTO);

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".getListAdminInfo End!");

        return rList;
    }

    @Override
    public int insertUserAdmin(UserAdminDTO pDTO) throws Exception {

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".insertUserAdmin Start!");

        int res = 0;

        // 시퀸스 값 넣기
        pDTO.setSequence(sequenceMapper.getSequence(pDTO.colNm()).getCol_seq());

        pDTO.setReg_dt(SimpleDateFormat.getDateInstance().format(new Date()));

        res = userAdminMapper.insertUserAdmin(pDTO);

        // 값이 들어갔을 때, 증가
        if (res == 1) {
            sequenceMapper.updateSequence(pDTO.colNm());
        }

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".insertUserAdmin End!");

        return res;
    }

    @Override
    public int insertUserAdmin(UserInfoDTO pDTO, String log_name, String log_info) throws Exception {
        UserAdminDTO rDTO = new UserAdminDTO(pDTO, log_name, log_info);
        return insertUserAdmin(rDTO);
    }

}

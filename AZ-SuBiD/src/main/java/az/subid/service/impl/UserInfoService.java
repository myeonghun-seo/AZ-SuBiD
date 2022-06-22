package az.subid.service.impl;

import az.subid.dto.MailDTO;
import az.subid.dto.UserInfoDTO;
import az.subid.persistance.mongodb.ISequenceMapper;
import az.subid.persistance.mongodb.IUserInfoMapper;
import az.subid.service.IMailService;
import az.subid.service.IUserInfoService;
import az.subid.util.CmmUtil;
import az.subid.util.DateUtil;
import az.subid.util.EncryptUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Service("UserInfoService")
public class UserInfoService implements IUserInfoService {

    // MongoDB에 저장할 Mapper
    @Resource(name = "UserInfoMapper")
    private IUserInfoMapper userInfoMapper;

    //메일 발송을 위한 MailService 자바 객체 가져오기
    @Resource(name = "MailService")
    private IMailService mailService;

    // MongoDB에 시퀸스 검색 Mapper
    @Resource(name = "SequenceMapper")
    private ISequenceMapper sequenceMapper;

    @Override
    public int insertUserInfo(UserInfoDTO pDTO) throws Exception {

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".insertUserInfo start!");

        // 회원가입 성공 : 1, 아이디 중복으로인한 가입 취소 : 2, 기타 에러 발생 : 0
        int res = 0;

        // 시퀸스 값 넣기
        pDTO.setSequence(sequenceMapper.getSequence(pDTO.colNm()).getCol_seq());

        // 권한 넣기
        pDTO.setUser_auth("user");

        // 수정자 넣기
        pDTO.setReg_id(pDTO.getUser_id());
        pDTO.setChg_id(pDTO.getUser_id());

        // 날짜 넣기
        pDTO.setReg_dt(SimpleDateFormat.getDateInstance().format(new Date()));
        pDTO.setChg_dt(SimpleDateFormat.getDateInstance().format(new Date()));


        // controller에서 값이 정상적으로 못 넘어오는 경우를 대비하기 위해 사용함
        if (pDTO == null) {
            pDTO = new UserInfoDTO();
        }

        // 회원 가입 중복 방지를 위해 DB에서 데이터 조회
        UserInfoDTO rDTO = userInfoMapper.getUserExists(pDTO);

        // mapper에서 값이 정상적으로 못 넘어오는 경우를 대비하기 위해 사용함
        if (rDTO == null) {
            rDTO = new UserInfoDTO();
        }

        // 중복된 회원정보가 있는 경우, 결과값을 2로 변경하고, 더 이상 작업을 진행하지 않음
        if (CmmUtil.nvl(rDTO.getExists_yn()).equals("Y")) {
            res = 2;

            // 회원가입이 중복이 아니므로, 회원가입 진행함
        } else {

            // 회원가입
            int success = userInfoMapper.insertUserInfo(pDTO);

            // db에 데이터가 등록되었다면(회원가입 성공했다면....
            if (success > 0) {

                res = 1;

                // 시퀸스 값 증가
                sequenceMapper.updateSequence(pDTO.colNm());

                /*
                 * #######################################################
                 *        				메일 발송 로직 추가 시작!!
                 * #######################################################
                 */

                MailDTO mDTO = new MailDTO();

                System.out.println(EncryptUtil.decAES128CBC(CmmUtil.nvl(pDTO.getEmail())));
                //회원정보화면에서 입력받은 이메일 변수(아직 암호화되어 넘어오기 때문에 복호화 수행함)
                mDTO.setToMail(EncryptUtil.decAES128CBC(CmmUtil.nvl(pDTO.getEmail())));

                mDTO.setTitle("회원가입을 축하드립니다."); //제목

                //메일 내용에 가입자 이름넣어서 내용 발송
                mDTO.setContents(CmmUtil.nvl(pDTO.getUser_nm()) +"님의 회원가입을 진심으로 축하드립니다.");

                //회원 가입이 성공했기 때문에 메일을 발송함
                mailService.doSendMail(mDTO);

                /*
                 * #######################################################
                 *        				메일 발송 로직 추가 끝!!
                 * #######################################################
                 */

            } else {
                res = 0;

            }

        }

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".insertUserInfo End!");

        return res;
    }

    @Override
    public int getUserLoginCheck(UserInfoDTO pDTO) throws Exception {

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".getUserLoginCheck start!");

        // 로그인 성공 : 1, 실패 : 0
        int res = 0;

        // 로그인을 위해 아이디와 비밀번호가 일치하는지 확인하기 위한 mapper 호출하기
        UserInfoDTO rDTO = userInfoMapper.getUserLoginCheck(pDTO);

        if (rDTO == null) {
            rDTO = new UserInfoDTO();

        }

        // 로그인 성공시, 메일 전송
        if (CmmUtil.nvl(rDTO.getUser_id()).length()>0) {
            res = 1;
            

            MailDTO mDTO = new MailDTO();

            //아이디, 패스워드 일치하는지 체크하는 쿼리에서 이메일 값 받아오기(아직 암호화되어 넘어오기 때문에 복호화 수행함)
            mDTO.setToMail(EncryptUtil.decAES128CBC(CmmUtil.nvl(rDTO.getEmail())));

            mDTO.setTitle("로그인 알림!"); //제목

            //메일 내용에 가입자 이름넣어서 내용 발송
            mDTO.setContents(DateUtil.getDateTime("yyyy.MM.dd 24h:mm:ss") +"에 "+ CmmUtil.nvl(rDTO.getUser_nm()) +"님이 로그인하였습니다.");

            //회원 가입이 성공했기 때문에 메일을 발송함
            mailService.doSendMail(mDTO);
            
        }

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".getUserLoginCheck End!");

        return res;
    }

    @Override
    public String initUserInfoPasswd(UserInfoDTO pDTO) throws Exception {

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".initUserPasswd Start!");

        char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

        String rstr = "";

        // 문자 배열 길이의 값을 랜덤으로 10개를 뽑아 구문을 작성함
        int idx = 0;
        for (int i = 0; i < 10; i++) {
            idx = (int) (charSet.length * Math.random());
            rstr += charSet[idx];
        }

        pDTO.setUser_pw(EncryptUtil.encHashSHA256(rstr));

        int res = userInfoMapper.initUserInfoPasswd(pDTO);

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".initUserPasswd Start!");

        return rstr;
    }

    @Override
    public UserInfoDTO getUserInfo(UserInfoDTO pDTO) throws Exception {

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".getUserInfo Start!");

        UserInfoDTO rDTO = userInfoMapper.getUserInfo(pDTO);

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".getUserInfo Start!");

        return rDTO;
    }

    @Override
    public int getUpdatePasswd(UserInfoDTO pDTO) throws Exception {

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".getUpdatePasswd Start!");

        int res = userInfoMapper.getUpdatePasswd(pDTO);

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".getUpdatePasswd End!");

        return res;
    }

    @Override
    public int updateUserInfo(UserInfoDTO pDTO) throws Exception {

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".updateUserInfo Start!");

        int res = 0;

        // 수정자 넣기
        pDTO.setChg_id(pDTO.getUser_id());

        // 날짜 넣기
        pDTO.setChg_dt(SimpleDateFormat.getDateInstance().format(new Date()));

        // controller에서 값이 정상적으로 못 넘어오는 경우를 대비하기 위해 사용함
        if (pDTO == null) {
            pDTO = new UserInfoDTO();
        }

        // 회원 가입 중복 방지를 위해 DB에서 데이터 조회
//        UserInfoDTO rDTO = userInfoMapper.getUserExists(pDTO, colNm);
//
//        // mapper에서 값이 정상적으로 못 넘어오는 경우를 대비하기 위해 사용함
//        if (rDTO == null) {
//            rDTO = new UserInfoDTO();
//        }
//
//        // 중복된 회원정보가 있는 경우, 결과값을 2로 변경하고, 더 이상 작업을 진행하지 않음
//        if (CmmUtil.nvl(rDTO.getExists_yn()).equals("Y")) {
//            res = 2;
//
//            // 회원가입이 중복이 아니므로, 회원가입 진행함
//        } else {

            // 회원가입
            int success = userInfoMapper.updateUserInfo(pDTO);

            // db에 데이터가 등록되었다면(회원가입 성공했다면....
            if (success > 0) {

                res = 1;

                // 메일 전송
                MailDTO mDTO = new MailDTO();

                System.out.println(EncryptUtil.decAES128CBC(CmmUtil.nvl(pDTO.getEmail())));
                //회원정보화면에서 입력받은 이메일 변수(아직 암호화되어 넘어오기 때문에 복호화 수행함)
                mDTO.setToMail(EncryptUtil.decAES128CBC(CmmUtil.nvl(pDTO.getEmail())));

                mDTO.setTitle("회원 정보가 변경되었습니다."); //제목

                //메일 내용에 가입자 이름넣어서 내용 발송
                mDTO.setContents(CmmUtil.nvl(pDTO.getUser_nm()) +"님의 회원가입을 진심으로 축하드립니다.");

                //회원 가입이 성공했기 때문에 메일을 발송함
                mailService.doSendMail(mDTO);

            } else {
                res = 0;

            }

//        }
        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".updateUserInfo End!");

        return res;
    }

    @Override
    public int deleteUserInfo(UserInfoDTO pDTO) throws Exception {

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".deleteUserInfo Start!");

        int res = userInfoMapper.deleteUserInfo(pDTO);

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".deleteUserInfo End!");

        return res;
    }
}

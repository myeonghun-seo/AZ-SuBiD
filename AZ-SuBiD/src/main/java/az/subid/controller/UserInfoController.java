package az.subid.controller;

import az.subid.dto.MailDTO;
import az.subid.dto.UserInfoDTO;
import az.subid.service.IMailService;
import az.subid.service.IUserAdminService;
import az.subid.service.IUserInfoService;
import az.subid.util.CmmUtil;
import az.subid.util.EncryptUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * UserInfo는 사용자의 정보를 관리하며, 사용자 정보를 담당하는 페이지입니다.
 */
@Slf4j
@Controller
public class UserInfoController {

    /**
     *
     * 비즈니스 로직(중요 로직을 수행하기 위해 사용되는 서비스를 메모리에 적재(싱글톤패턴 적용됨)
     */
    @Resource(name = "UserInfoService")
    private IUserInfoService userInfoService;
    @Resource(name = "UserAdminService")
    private IUserAdminService userAdminService;

    //메일 발송을 위한 MailService 자바 객체 가져오기
    @Resource(name = "MailService")
    private IMailService mailService;

    /*
     * GetMapping은 GET방식을 통해 접속되는 URL 호출에 대해 실행되는 함수로 설정함을 의미함
     * PostMapping은 POST방식을 통해 접속되는 URL 호출에 대해 실행되는 함수로 설정함을 의미함
     * GetMapping(value = "index") =>  GET방식을 통해 접속되는 URL이 index인 경우 아래 함수를 실행함
     */

    /**
     * 회원가입 화면으로 이동
     */
    @GetMapping(value = "register")
    public String userRegForm() {

        log.info(this.getClass().getName() + ".userRegForm ok!");

        return "user/userInfoRegister";
    }

    /**
     * 회원가입 로직 처리
     */
    @PostMapping(value="user/insertUserInfo")
    public String insertUserInfo(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {

        log.info(this.getClass().getName() + ".insertUserInfo start!");

        // 회원가입 결과에 대한 메시지를 전달할 변수
        String msg = "";

        // 웹(회원정보 입력화면)에서 받는 정보를 저장할 변수
        UserInfoDTO pDTO = null;

        try{

            // 웹에서 받는 정보를 String 변수로 저장
            String user_id = CmmUtil.nvl(request.getParameter("user_id")); //아이디
            String user_nm = CmmUtil.nvl(request.getParameter("user_nm")); //이름
            String user_pw = CmmUtil.nvl(request.getParameter("user_pw")); //비밀번호
            String email = CmmUtil.nvl(request.getParameter("email")); //이메일
            String addr1 = CmmUtil.nvl(request.getParameter("addr1")); //주소
            String addr2 = CmmUtil.nvl(request.getParameter("addr2")); //상세주소

            // 로그 테스트
            log.info("user_id : "+ user_id);
            log.info("user_name : "+ user_nm);
            log.info("password : "+ user_pw);
            log.info("email : "+ email);
            log.info("addr1 : "+ addr1);
            log.info("addr2 : "+ addr2);

            // DTO 값 저장하기
            // 웹(회원정보 입력화면)에서 받는 정보를 저장할 변수를 메모리에 올리기
            pDTO = new UserInfoDTO();

            pDTO.setUser_id(user_id);
            pDTO.setUser_nm(user_nm);

            // 비밀번호는 절대로 복호화되지 않도록 해시 알고리즘으로 암호화함
            pDTO.setUser_pw(EncryptUtil.encHashSHA256(user_pw));

            // 민감 정보인 이메일은 AES128-CBC로 암호화함
            pDTO.setEmail(EncryptUtil.encAES128CBC(email));
            pDTO.setAddr1(addr1);
            pDTO.setAddr2(addr2);

            // 회원가입하기
            int res = userInfoService.insertUserInfo(pDTO);

            log.info("회원가입 결과(res) : "+ res);

            if (res==1) {
                // 성공 로그 저장
                userAdminService.insertUserAdmin(pDTO, "insert", "user_inserted");

                model.addAttribute("msg", "회원 가입 성공!");
                model.addAttribute("url", "/login"); // 나중에 로그인 창으로 변경하기

                //추후 회원가입 입력화면에서 ajax를 활용해서 아이디 중복, 이메일 중복을 체크하길 바람
            }else if (res==2){
                
                model.addAttribute("msg", "이미 가입된 이메일 주소입니다.");
                model.addAttribute("url", "/register"); // 나중에 회원가입 창으로 변경하기

            }else {
                
                model.addAttribute("msg", "오류로 인해 회원가입이 실패하였습니다.");
                model.addAttribute("url", "/register"); // 나중에 회원가입 창으로 변경하기

            }

        }catch(Exception e){
            //저장이 실패되면 사용자에게 보여줄 메시지
            msg = "실패하였습니다. : "+ e.toString();
            log.info(e.toString());
            e.printStackTrace();

        }finally{
            log.info(this.getClass().getName() + ".insertUserInfo end!");

            //변수 초기화(메모리 효율화 시키기 위해 사용함)
            pDTO = null;

        }

        return "/user/userInfoRegisterResult";
    }


    /**
     * 로그인을 위한 입력 화면으로 이동
     * */
    @GetMapping(value="login")
    public String loginForm() {

        log.info(this.getClass().getName() + ".loginForm ok!");

        return "/user/userInfoLogin";
    }

    /**
     * 로그인 처리 및 결과 알려주는 화면으로 이동
     * */
    @PostMapping(value="user/getUserLoginCheck")
    public String getUserLoginCheck(HttpSession session, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {

        log.info(this.getClass().getName() + ".getUserLoginCheck start!");

        //로그인 처리 결과를 저장할 변수 (로그인 성공 : 1, 아이디, 비밀번호 불일치로인한 실패 : 0, 시스템 에러 : 2)
        int res = 0;

        //웹(회원정보 입력화면)에서 받는 정보를 저장할 변수
        UserInfoDTO pDTO = null;

        try{

            // 웹에서 받는 정보를 String 변수로 저장
            String user_id = CmmUtil.nvl(request.getParameter("user_id")); //아이디
            String user_pw = CmmUtil.nvl(request.getParameter("user_pw")); //비밀번호

            // 로그 테스트
            log.info("user_id : "+ user_id);
            log.info("user_pw : "+ user_pw);

            //웹(회원정보 입력화면)에서 받는 정보를 저장할 변수를 메모리에 올리기
            pDTO = new UserInfoDTO();

            pDTO.setUser_id(user_id);

            //비밀번호는 절대로 복호화되지 않도록 해시 알고리즘으로 암호화함
            pDTO.setUser_pw(EncryptUtil.encHashSHA256(user_pw));

            // 로그인을 위해 아이디와 비밀번호가 일치하는지 확인하기 위한 userInfoService 호출하기
            res = userInfoService.getUserLoginCheck(pDTO);

            log.info("res : "+ res);

            // 로그인 성공 시, 세션 값 집어넣기
            if (res == 1) {

                /*
                 * 세션에 회원아이디 저장하기, 추후 로그인여부를 체크하기 위해 세션에 값이 존재하는지 체크한다.
                 * 일반적으로 세션에 저장되는 키는 대문자로 입력하며, 앞에 SS를 붙인다.
                 *
                 * Session 단어에서 SS를 가져온 것이다.
                 */
                session.setAttribute("SS_USER_ID", user_id);
                session.setAttribute("SS_USER_AUTH", userInfoService.getUserInfo(pDTO).getUser_auth());

                model.addAttribute("msg", user_id + "님이 로그인 되었습니다.");
                model.addAttribute("url", "/");

            } else {

                model.addAttribute("msg", user_id + "아이디, 비밀번호가 일치하지 않습니다.");
                model.addAttribute("url", "/login");

            }

        }catch(Exception e){

            //저장이 실패되면 사용자에게 보여줄 메시지
            res = 2;
            log.info(e.toString());
            e.printStackTrace();

            model.addAttribute("msg", "시스템에 문제가 발생하였습니다. 잠시 후 다시 시도하여 주시길 바랍니다.");
            model.addAttribute("url", "/login");

        }finally{
            log.info(this.getClass().getName() + ".insertUserInfo end!");

            /* 로그인 처리 결과를 jsp에 전달하기 위해 변수 사용
             * 숫자 유형의 데이터 타입은 값을 전달하고 받는데 불편함이  있어
             * 문자 유형(String)으로 강제 형변환하여 jsp에 전달한다.
             * */
            model.addAttribute("res", String.valueOf(res));

            //변수 초기화(메모리 효율화 시키기 위해 사용함)
            pDTO = null;

        }

        return "/user/userInfoLoginResult";
    }

    /**
     * 로그아웃
     */
    @GetMapping("logout")
    public String UserLogout(HttpSession session) throws Exception {
        
        // 세선 데이터 초기화
        session.invalidate();

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".UserLogout OK!");

        return "redirect:/index";
    }

    /**
     * 비밀번호 찾기 화면
     */
    @GetMapping(value = "findpasswd")
    public String FindPasswd() throws Exception {

        log.info(this.getClass().getName() + ".FindPasswd OK!");

        return "/user/userInfoFindPasswd";
    }
    /**
     * 로그인 처리 및 결과 알려주는 화면으로 이동
     * */
    @PostMapping(value="user/getfindpasswd")
    public String getFindPasswd(HttpSession session, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {

        log.info(this.getClass().getName() + ".getFindPasswd start!");

        //로그인 처리 결과를 저장할 변수 (로그인 성공 : 1, 아이디, 비밀번호 불일치로인한 실패 : 0, 시스템 에러 : 2)
        int res = 0;

        //웹(회원정보 입력화면)에서 받는 정보를 저장할 변수
        UserInfoDTO pDTO = null;

        try{

            // 웹에서 받는 정보를 String 변수로 저장
            String email = CmmUtil.nvl(request.getParameter("email")); //이메일

            // 로그 테스트
            log.info("email : "+ email);

            //웹(회원정보 입력화면)에서 받는 정보를 저장할 변수를 메모리에 올리기
            pDTO = new UserInfoDTO();

            pDTO.setEmail(email);

            //이메일은 복호화 암호화함
            pDTO.setEmail(EncryptUtil.encAES128CBC(email));

            UserInfoDTO rDTO = userInfoService.getUserInfo(pDTO);

            // 해당되는 이메일이 DB에 있으면 1을 넣어준다.
            res = rDTO.getEmail().equals(pDTO.getEmail()) ? 1 : 0;

            log.info("res : "+ res);

            // 값이 있다면, 메일 보내기
            if (res == 1) {

                String rstr = userInfoService.initUserInfoPasswd(pDTO);

                log.info(rstr);

                // 메일 DTO
                MailDTO mDTO = new MailDTO();

                System.out.println(EncryptUtil.decAES128CBC(CmmUtil.nvl(pDTO.getEmail())));
                // 회원정보화면에서 입력받은 이메일 변수(아직 암호화되어 넘어오기 때문에 복호화 수행함)
                mDTO.setToMail(EncryptUtil.decAES128CBC(CmmUtil.nvl(pDTO.getEmail())));

                mDTO.setTitle("비밀번호가 초기화 되었습니다."); // 제목

                // 메일 내용에 가입자 이름넣어서 내용 발송
                mDTO.setContents(CmmUtil.nvl(email) + "님, " + CmmUtil.nvl(rstr) + "로 변경하였습니다.");

                // 회원 가입이 성공했기 때문에 메일을 발송함
                mailService.doSendMail(mDTO);

                model.addAttribute("msg", email + "로 메일을 보냈습니다.");
                model.addAttribute("url", "/");

            } else {

                model.addAttribute("msg", "가입한 정보가 존재하지 않습니다.");
                model.addAttribute("url", "/findpasswd");

            }

        }catch(Exception e){

            //저장이 실패되면 사용자에게 보여줄 메시지
            res = 2;
            log.info(e.toString());
            e.printStackTrace();

            model.addAttribute("msg", "시스템에 문제가 발생하였습니다. 잠시 후 다시 시도하여 주시길 바랍니다.");
            model.addAttribute("url", "/");

        }finally{
            log.info(this.getClass().getName() + ".getFindPasswd end!");

            /* 로그인 처리 결과를 jsp에 전달하기 위해 변수 사용
             * 숫자 유형의 데이터 타입은 값을 전달하고 받는데 불편함이  있어
             * 문자 유형(String)으로 강제 형변환하여 jsp에 전달한다.
             * */
            model.addAttribute("res", String.valueOf(res));

            //변수 초기화(메모리 효율화 시키기 위해 사용함)
            pDTO = null;

        }

        return "/user/userInfoFindPasswdResult";
    }

    /**
     * 비밀번호 바꾸기 화면
     */
    @GetMapping(value = "updatepasswd")
    public String updatePasswd() throws Exception {

        log.info(this.getClass().getName() + ".UpdatePasswd Start!");

        log.info(this.getClass().getName() + ".UpdatePasswd End!");

        return "/user/userInfoUpdatePasswd";
    }

    @PostMapping(value = "/user/getUpdatePasswd")
    public String getUpdatePasswd(HttpSession session, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {

        log.info(this.getClass().getName() + ".getUpdatePasswd Start!");

        // 웹에서 받는 정보를 String 변수로 저장
        String user_id = CmmUtil.nvl((String) session.getAttribute("SS_USER_ID")); //아이디
        String user_pw = CmmUtil.nvl(request.getParameter("user_pw")); //비밀번호

        UserInfoDTO pDTO = new UserInfoDTO();

        pDTO.setUser_id(user_id);
        pDTO.setUser_pw(EncryptUtil.encHashSHA256(user_pw));

        int res = userInfoService.getUpdatePasswd(pDTO);
        
        // 성공시 안내 문구
        if (res == 1) {

            model.addAttribute("msg", "비밀번호를 변경하였습니다.");
            model.addAttribute("url", "/mypage");

        } else {

            model.addAttribute("msg", "가입한 정보가 존재하지 않습니다.");
            model.addAttribute("url", "/mypage");

        }
        
        log.info(this.getClass().getName() + ".getUpdatePasswd End!");

        return "/user/userInfoUpdatePasswdResult";
    }

    /**
     * 회원정보 수정 창
     */
    @PostMapping(value="user/userAdjust")
    public String adjustForm(HttpSession session, HttpServletRequest request, HttpServletResponse response, ModelMap model) {

        log.info(this.getClass().getName() + ".adjustForm Start!");

        // 세션값 조회할 DTO
        UserInfoDTO pDTO = null;

        try {

            // 아이디 세션값 가져오기
            String user_id = CmmUtil.nvl((String) session.getAttribute("SS_USER_ID"));

            pDTO = new UserInfoDTO();
            pDTO.setUser_id(user_id);

            // 조회하기
            UserInfoDTO rDTO = userInfoService.getUserInfo(pDTO);

            // View로 쏴주기
            model.addAttribute("rDTO", rDTO);

        } catch (Exception e) {

            throw new RuntimeException(e);

        } finally {

            // DTO 초기화
            pDTO = null;

            log.info(this.getClass().getName() + ".adjustForm End!");
        }

        return "/user/userAdjust";
    }

    /**
     * 회원수정 로직 처리
     * */
    @PostMapping(value="user/updateUserInfo")
    public String updateUserInfo(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {

        log.info(this.getClass().getName() + ".updateUserInfo start!");

        int res = 0;

        //웹(회원정보 입력화면)에서 받는 정보를 저장할 변수
        UserInfoDTO pDTO = null;

        try{

            // 웹에서 받는 정보를 String 변수로 저장
            String user_id = CmmUtil.nvl(request.getParameter("user_id")); // 아이디
            String user_nm = CmmUtil.nvl(request.getParameter("user_nm")); // 이름
            String user_pw = CmmUtil.nvl(request.getParameter("user_pw")); // 비밀번호
            String email = CmmUtil.nvl(request.getParameter("email")); // 이메일
            String addr1 = CmmUtil.nvl(request.getParameter("addr1")); // 주소
            String addr2 = CmmUtil.nvl(request.getParameter("addr2")); // 상세주소

            // 로그 출력
            log.info("user_id : " + user_id);
            log.info("user_name : " + user_nm);
            log.info("password : " + user_pw);
            log.info("email : " + email);
            log.info("addr1 : " + addr1);
            log.info("addr2 : " + addr2);

            //웹(회원정보 입력화면)에서 받는 정보를 저장할 변수를 메모리에 올리기
            pDTO = new UserInfoDTO();
            
            // 이 둘로 먼저 쿼리 계산함
            pDTO.setUser_id(user_id);

            //비밀번호는 절대로 복호화되지 않도록 해시 알고리즘으로 암호화함
            pDTO.setUser_pw(EncryptUtil.encHashSHA256(user_pw));

            // 비밀번호가 같다면, 하위 작업을 실행하기
            if (CmmUtil.nvl(userInfoService.getUserInfo(pDTO).getUser_pw()).equals(pDTO.getUser_pw())) {

                pDTO.setUser_nm(user_nm);

                //민감 정보인 이메일은 AES128-CBC로 암호화함
                pDTO.setEmail(EncryptUtil.encAES128CBC(email));
                pDTO.setAddr1(addr1);
                pDTO.setAddr2(addr2);

                //회원 정보 수정
                res = userInfoService.updateUserInfo(pDTO);

            } else { // 값이 없다면
                res = 2;
            }

            log.info("회원 수정 결과(res) : "+ res);

            if (res==1) {

                model.addAttribute("msg", "회원 수정되었습니다.");
                model.addAttribute("url", "/mypage");

            }else if (res==2){

                model.addAttribute("msg", "회원 정보가 올바르지 않습니다.");
                model.addAttribute("url", "/mypage");

            }else {

                model.addAttribute("msg",  "오류로 인해 회원수정이 실패하였습니다.");
                model.addAttribute("url", "/user/userAdjust");

            }

        }catch(Exception e){
            //저장이 실패되면 사용자에게 보여줄 메시지
            log.info(e.toString());
            e.printStackTrace();

        }finally{
            log.info(this.getClass().getName() + ".updateUserInfo end!");

            //변수 초기화(메모리 효율화 시키기 위해 사용함)
            pDTO = null;

        }

        return "/user/userInfoAdjustResult";
    }

    /**
     * 회원수정 로직 처리
     * */
    @GetMapping(value="user/userdelete")
    public String deleteUserInfo(HttpSession session, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {

        log.info(this.getClass().getName() + ".deleteUserInfo start!");

        // 웹에서 받는 정보를 String 변수로 저장
        String user_id = CmmUtil.nvl((String) session.getAttribute("SS_USER_ID")); //아이디

        UserInfoDTO pDTO = new UserInfoDTO();

        pDTO.setUser_id(user_id);

        pDTO.setSequence(userInfoService.getUserInfo(pDTO).getSequence());

        int res = userInfoService.deleteUserInfo(pDTO);
        
        // 메세지 전송하기
        if (res==1) {
            // 성공 로그 저장
            userAdminService.insertUserAdmin(pDTO, "delete", "user_deleted");

            model.addAttribute("msg", "회원이 삭제되었습니다.");
            model.addAttribute("url", "/");
            
            // 세션 초기화
            session.invalidate();

        }else if (res==2){

            model.addAttribute("msg", "회원 정보가 올바르지 않습니다.");
            model.addAttribute("url", "/mypage");

        }else {

            model.addAttribute("msg",  "오류로 인해 회원수정이 실패하였습니다.");
            model.addAttribute("url", "/mypage");

        }

        log.info(this.getClass().getName() + ".deleteUserInfo End!");

        return "/user/userInfoDeleteResult";
    }

}
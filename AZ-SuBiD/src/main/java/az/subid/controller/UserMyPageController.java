package az.subid.controller;

import az.subid.dto.UserInfoDTO;
import az.subid.service.IUserInfoService;
import az.subid.service.IUserMyPageService;
import az.subid.util.CmmUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * MyPage는 사용자가 자신의 정보를 볼 수 있는 페이지입니다.
 * User가 이 항목에 포함되어 있습니다.
 */
@Slf4j
@Controller
public class UserMyPageController {

    /**
     *
     * 비즈니스 로직(중요 로직을 수행하기 위해 사용되는 서비스를 메모리에 적재(싱글톤패턴 적용됨)
     */
    @Resource(name = "UserMyPageService")
    private IUserMyPageService userMyPageService;

    @Resource(name = "UserInfoService")
    private IUserInfoService userInfoService;

    /**
     * 메인 화면 보여주기
     *
     * GetMapping(value = "main") => GET방식을 통해 접속되는 URL이 main인 경우 아래 함수를 실행함
     */
    @GetMapping(value = {"mypage"})
    public String myPageMain(HttpSession session, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {

        log.info(this.getClass().getName() + ".myPageMain Start!");

        int res = 0;

        // DTO 값 초기화
        UserInfoDTO pDTO = new UserInfoDTO();

        try {

            // 세션값 아이디 받아오기
            String user_id = CmmUtil.nvl((String) session.getAttribute("SS_USER_ID"));

            // 로그 표시하기
            log.info("user_id : " + user_id);

            // DTO에 값 저장하기
            pDTO.setUser_id(user_id);

            UserInfoDTO rDTO = userInfoService.getUserInfo(pDTO);

            model.addAttribute("rDTO", rDTO);

        } catch (Exception e) {

            throw new RuntimeException(e);

        } finally {

            // 널 처리해주기
            pDTO = null;

            log.info(this.getClass().getName() + ".myPageMain End!");
        }
        
        return "/user/userMypageList";

    }

}

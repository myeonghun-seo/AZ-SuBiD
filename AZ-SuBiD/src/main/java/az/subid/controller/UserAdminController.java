package az.subid.controller;

import az.subid.dto.UserAdminDTO;
import az.subid.dto.UserInfoDTO;
import az.subid.service.IUserAdminService;
import az.subid.service.IUserInfoService;
import az.subid.util.CmmUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Admin은 관리자가 유저와 게시글을 관리하며, 통계를 보는 페이지입니다.
 */
@Slf4j
@Controller
public class UserAdminController {

    /**
     *
     * 비즈니스 로직(중요 로직을 수행하기 위해 사용되는 서비스를 메모리에 적재(싱글톤패턴 적용됨)
     */
    @Resource(name = "UserAdminService")
    private IUserAdminService userAdminService;

    @Resource(name = "UserInfoService")
    private IUserInfoService userInfoService;

    // MongoDB 컬렉션 이름
    private final String colNm = "UserAdminCollection";

    /**
     * 메인 화면 보여주기
     *
     * GetMapping(value = "main") => GET방식을 통해 접속되는 URL이 main인 경우 아래 함수를 실행함
     */
    @GetMapping(value = {"admin"})
    public String getListAdminUser(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {

        log.info(this.getClass().getName() + ".getListAdminUser Start!");

        List<UserInfoDTO> rList = userAdminService.getListAdminUser();

        if (rList==null){
            rList = new ArrayList<UserInfoDTO>();
        }

        //조회된 리스트 결과값 넣어주기
        model.addAttribute("rList", rList);

        //변수 초기화(메모리 효율화 시키기 위해 사용함)
        rList = null;

        log.info(this.getClass().getName() + ".getListAdminUser End!");

        return "/user/userAdmin";
    }

    /**
     * 상세 화면 보여주기
     *
     * GetMapping(value = "main") => GET방식을 통해 접속되는 URL이 main인 경우 아래 함수를 실행함
     */
    @GetMapping(value = {"admin/info"})
    public String getAdminUserinfo(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {

        log.info(this.getClass().getName() + ".getAdminUserinfo Start!");

        // 시퀸스 값 파라미터로 받아오기
        String nSeq = CmmUtil.nvl(request.getParameter("nSeq"));

        // 로그 찍기
        log.info("nSeq : "+ nSeq);

        // DTO 생성하기
        UserInfoDTO pDTO = new UserInfoDTO();
        UserAdminDTO aDTO = new UserAdminDTO();

        pDTO.setSequence(nSeq);
        aDTO.setUser_seq(nSeq);

        UserInfoDTO rDTO = userInfoService.getUserInfo(pDTO);
        List<UserAdminDTO> rList = userAdminService.getListAdminInfo(aDTO);

        if (rDTO == null){
            rDTO = new UserInfoDTO();
        }

        model.addAttribute("rDTO", rDTO);
        model.addAttribute("rList", rList);

        //변수 초기화(메모리 효율화 시키기 위해 사용함)
        rDTO = null;

        log.info(this.getClass().getName() + ".getAdminUserinfo End!");

        return "/user/userAdminInfo";
    }

    @GetMapping(value = {"admin/insert"})
    public String getAdminUserInsert(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {

        log.info(this.getClass().getName() + ".getAdminUserInsert Start!");

        // DTO 생성하기
        UserAdminDTO aDTO = new UserAdminDTO();

        aDTO.setLog_name("insert");

        List<UserAdminDTO> rList = userAdminService.getListAdminInfo(aDTO);

        if (rList == null){
            rList = new ArrayList<UserAdminDTO>();
        }

        model.addAttribute("rList", rList);

        //변수 초기화(메모리 효율화 시키기 위해 사용함)
        rList = null;

        log.info(this.getClass().getName() + ".getAdminUserInsert End!");

        return "/user/userAdminInsert";
    }

    @GetMapping(value = {"admin/delete"})
    public String getAdminUserDelete(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {

        log.info(this.getClass().getName() + ".getAdminUserDelete Start!");

        // DTO 생성하기
        UserAdminDTO aDTO = new UserAdminDTO();

        aDTO.setLog_name("delete");

        List<UserAdminDTO> rList = userAdminService.getListAdminInfo(aDTO);

        if (rList == null){
            rList = new ArrayList<UserAdminDTO>();
        }

        model.addAttribute("rList", rList);

        //변수 초기화(메모리 효율화 시키기 위해 사용함)
        rList = null;

        log.info(this.getClass().getName() + ".getAdminUserDelete End!");

        return "/user/userAdminDelete";
    }
}

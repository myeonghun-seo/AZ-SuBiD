package az.subid.controller;

import com.subid.market.service.IAdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;

/**
 * Admin은 관리자가 유저와 게시글을 관리하며, 통계를 보는 페이지입니다.
 */
@Slf4j
@Controller
public class AdminController {

    /**
     *
     * 비즈니스 로직(중요 로직을 수행하기 위해 사용되는 서비스를 메모리에 적재(싱글톤패턴 적용됨)
     */
    @Resource(name = "AdminService")
    private IAdminService AdminService;

    /**
     * 메인 화면 보여주기
     *
     * GetMapping(value = "main") => GET방식을 통해 접속되는 URL이 main인 경우 아래 함수를 실행함
     */
    @GetMapping(value = {"admin"})
    public String AdminMain() throws Exception {

        log.info(this.getClass().getName() + ".AdminMain Start!");

        log.info(this.getClass().getName() + ".AdminMain End!");

        return "/admin/mainadmin";

    }

}

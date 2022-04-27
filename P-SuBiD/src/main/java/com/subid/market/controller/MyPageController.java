package com.subid.market.controller;

import com.subid.market.service.IHomeMainService;
import com.subid.market.service.IMyPageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * MyPage는 사용자가 자신의 정보를 볼 수 있는 페이지입니다.
 */
@Slf4j
@Controller
public class MyPageController {

    /**
     *
     * 비즈니스 로직(중요 로직을 수행하기 위해 사용되는 서비스를 메모리에 적재(싱글톤패턴 적용됨)
     */
    @Resource(name = "MyPageService")
    private IMyPageService MyPageService;

    /**
     * 메인 화면 보여주기
     *
     * GetMapping(value = "main") => GET방식을 통해 접속되는 URL이 main인 경우 아래 함수를 실행함
     */
    @GetMapping(value = {"mypage"})
    public String MyPageMain() throws Exception {

        log.info(this.getClass().getName() + ".MyPageMain Start!");

        log.info(this.getClass().getName() + ".MyPageMain End!");

        return "/mypage/mainmypage";

    }

    /**
     * 회원가입 화면
     */
    @GetMapping(value = "register")
    public String UserRegister() throws Exception {

        log.info(this.getClass().getName() + ".UserRegister Start!");

        log.info(this.getClass().getName() + ".UserRegister End!");

        return "/mypage/userregister";
    }

}

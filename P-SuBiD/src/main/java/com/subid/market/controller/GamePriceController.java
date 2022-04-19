package com.subid.market.controller;

import com.subid.market.service.IGamePriceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * GamePrice는 다양한 ESD사이트의 상품을 모아서 가격을 비교하는 페이지입니다.
 */
@Slf4j
@Controller
public class GamePriceController {

    /**
     *
     * 비즈니스 로직(중요 로직을 수행하기 위해 사용되는 서비스를 메모리에 적재(싱글톤패턴 적용됨)
     */
    @Resource(name = "GamePriceService")
    private IGamePriceService GamePriceService;

    /**
     * 메인 화면 보여주기
     *
     * GetMapping(value = "main") => GET방식을 통해 접속되는 URL이 main인 경우 아래 함수를 실행함
     */
    @GetMapping(value = {"gameprice"})
    public String GamePriceMain() throws Exception {

        log.info(this.getClass().getName() + ".GamePriceMain Start!");

        log.info(this.getClass().getName() + ".GamePriceMain End!");

        return "/gameprice/maingameprice";

    }

}

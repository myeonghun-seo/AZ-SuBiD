package az.subid.controller;

import az.subid.service.ISubScriptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;

/**
 * SubScription은 게임 구독 서비스의 정보를 찾아 볼 수 있는 페이지입니다.
 */
@Slf4j
@Controller
public class SubScriptionController {

    /**
     *
     * 비즈니스 로직(중요 로직을 수행하기 위해 사용되는 서비스를 메모리에 적재(싱글톤패턴 적용됨)
     */
    @Resource(name = "SubScriptionService")
    private ISubScriptionService SubScriptionService;

    /**
     * 메인 화면 보여주기
     *
     * GetMapping(value = "main") => GET방식을 통해 접속되는 URL이 main인 경우 아래 함수를 실행함
     */
    @GetMapping(value = {"subscription"})
    public String SubScriptionMain() throws Exception {

        log.info(this.getClass().getName() + ".SubScriptionMain Start!");

        log.info(this.getClass().getName() + ".SubScriptionMain End!");

        return "/subscription/mainsubscription";

    }
}

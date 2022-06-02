package az.subid.controller;

import com.subid.market.service.IHomeMainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * HomeMain은 홈페이지 역할을 하며, 기본 서식을 담당하는 페이지입니다.
 */
@Slf4j
@Controller
public class HomeMainController {

    /**
     *
     * 비즈니스 로직(중요 로직을 수행하기 위해 사용되는 서비스를 메모리에 적재(싱글톤패턴 적용됨)
     */
    @Resource(name = "HomeMainService")
    private IHomeMainService HomeMainService;

    /**
     * GetMapping은 GET방식을 통해 접속되는 URL 호출에 대해 실행되는 함수로 설정함을 의미함
     * PostMapping은 POST방식을 통해 접속되는 URL 호출에 대해 실행되는 함수로 설정함을 의미함
     * GetMapping(value = "index") =>  GET방식을 통해 접속되는 URL이 index인 경우 아래 함수를 실행함
     */
    @GetMapping(value = "index")
    public String Index() {

        log.info(this.getClass().getName() + ".Index Start!");

        log.info(this.getClass().getName() + ".Index End!");

        return "/index";

    }

    /**
     * 메인 화면 보여주기
     *
     * GetMapping(value = "main") => GET방식을 통해 접속되는 URL이 main인 경우 아래 함수를 실행함
     */
    @RequestMapping(value = {"/", "/main"})
    public String HomeMain() throws Exception {

        log.info(this.getClass().getName() + ".HomeMain Start!");

        log.info(this.getClass().getName() + ".HomeMain End!");

        return "/homemain/mainhomepage";

    }

}

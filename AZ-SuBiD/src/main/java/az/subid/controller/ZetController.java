package az.subid.controller;

import az.subid.service.IZetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;

/**
 * Zet는 기타 실험적인 기능을 담고 있습니다.
 * 홈페이지와 연관이 없는 기능을 갖고 있을수도 있고 동떨어질 수도 있습니다.
 * 단순히 그러한 페이지입니다.
 * 신경쓰지 않아도 됩니다.
 */
@Slf4j
@Controller
public class ZetController {

    /**
     *
     * 비즈니스 로직(중요 로직을 수행하기 위해 사용되는 서비스를 메모리에 적재(싱글톤패턴 적용됨)
     */
    @Resource(name = "ZetService")
    private IZetService ZetService;

    /**
     * 메인 화면 보여주기
     *
     * GetMapping(value = "main") => GET방식을 통해 접속되는 URL이 main인 경우 아래 함수를 실행함
     */
    @GetMapping(value = {"zet"})
    public String ZetMain() throws Exception {

        log.info(this.getClass().getName() + ".ZetMain Start!");

        log.info(this.getClass().getName() + ".ZetMain End!");

        return "/zet/aaazet";

    }

}

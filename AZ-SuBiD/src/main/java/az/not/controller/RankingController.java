package az.subid.controller;

import az.subid.service.IRankingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;

/**
 * Ranking은 모든 플렛폼에서 사용자, 매출 등 다양한 기준으로 순위를 매긴 페이지입니다.
 */
@Slf4j
@Controller
public class RankingController {

    /**
     *
     * 비즈니스 로직(중요 로직을 수행하기 위해 사용되는 서비스를 메모리에 적재(싱글톤패턴 적용됨)
     */
    @Resource(name = "RankingService")
    private IRankingService RankingService;

    /**
     * 메인 화면 보여주기
     *
     * GetMapping(value = "main") => GET방식을 통해 접속되는 URL이 main인 경우 아래 함수를 실행함
     */
    @GetMapping(value = {"ranking"})
    public String RankingeMain() throws Exception {

        log.info(this.getClass().getName() + ".RankingeMain Start!");

        log.info(this.getClass().getName() + ".RankingeMain End!");

        return "/ranking/mainranking";

    }
}

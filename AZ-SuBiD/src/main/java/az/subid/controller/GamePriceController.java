package az.subid.controller;

import az.subid.dto.GameIgdbDTO;
import az.subid.dto.GameInfoDTO;
import az.subid.dto.GamePriceDTO;
import az.subid.service.IGamePriceService;
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
    private IGamePriceService gamePriceService;

    /**
     * 메인 화면 보여주기
     *
     * GetMapping(value = "main") => GET방식을 통해 접속되는 URL이 main인 경우 아래 함수를 실행함
     */
    @GetMapping(value = {"gameprice"})
    public String gamePriceMain(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {

        //로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".GamePriceMain Start!");

        // 게임 정보 리스트 가져오기
        List<GameInfoDTO> infoList = gamePriceService.getGameInfoList();
        List<GameIgdbDTO> igdbList = gamePriceService.getGameIgdbList();
        List<GamePriceDTO> priceList = gamePriceService.getGamePriceList();

        // 값이 없을 시 초기화
        if (infoList == null){
            infoList = new ArrayList<GameInfoDTO>();
        }
        if (igdbList == null){
            igdbList = new ArrayList<GameIgdbDTO>();
        }
        if (priceList == null){
            priceList = new ArrayList<GamePriceDTO>();
        }

        //조회된 리스트 결과값 넣어주기
        model.addAttribute("infoList", infoList);
        model.addAttribute("igdbList", igdbList);
        model.addAttribute("priceList", priceList);

        //변수 초기화(메모리 효율화 시키기 위해 사용함)
        infoList = null;
        igdbList = null;
        priceList = null;

        //로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".GamePriceMain End!");

        return "/gameprice/aaaGameprice";
    }

    @GetMapping(value = {"gameprice/list"})
    public String GamePriceList() throws Exception {

        //로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".GamePriceList Start!");

        //로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".GamePriceList End!");

        return "/gameprice/gamepriceList";
    }

    @GetMapping(value = {"gameprice/info"})
    public String GamePriceInfo(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {

        //로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".GamePriceInfo Start!");

        /*
         * 게시판 글 등록되기 위해 사용되는 form객체의 하위 input 객체 등을 받아오기 위해 사용함
         * */
        long id = Long.parseLong(request.getParameter("id")); //공지글번호(PK)

        /*
         * #######################################################
         * 	 반드시, 값을 받았으면, 꼭 로그를 찍어서 값이 제대로 들어오는지 파악해야함
         * 						반드시 작성할 것
         * #######################################################
         * */
        log.info("id : "+ id);

        /*
         * 값 전달은 반드시 DTO 객체를 이용해서 처리함
         * 전달 받은 값을 DTO 객체에 넣는다.
         * */
        GameInfoDTO pDTO = new GameInfoDTO();

        pDTO.setIgdb_id_(id);

        //공지사항 글 조회수 증가
        gamePriceService.updateGameInfoReadCnt(pDTO);

        log.info("read_cnt update success!!!");

        //공지사항 상세정보 가져오기

        GameInfoDTO infoDTO = gamePriceService.getGameInfo(pDTO);
        GameIgdbDTO igdbDTO = gamePriceService.getGameIgdb(pDTO);
        GamePriceDTO priceDTO = gamePriceService.getGamePrice(pDTO);

        if (infoDTO == null) infoDTO = new GameInfoDTO();
        if (igdbDTO == null) igdbDTO = new GameIgdbDTO();
        if (priceDTO == null) priceDTO = new GamePriceDTO();

        log.info("getNoticeInfo success!!!");

        //조회된 리스트 결과값 넣어주기
        model.addAttribute("infoDTO", infoDTO);
        model.addAttribute("igdbDTO", igdbDTO);
        model.addAttribute("priceDTO", priceDTO);

        //변수 초기화(메모리 효율화 시키기 위해 사용함)
        pDTO = null;
        infoDTO = null;
        igdbDTO = null;
        priceDTO = null;

        //로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".GamePriceInfo End!");

        return "gameprice/gamepriceInfo";
    }

}

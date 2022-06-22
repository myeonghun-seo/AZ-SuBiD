//package az.subid.service.impl;
//
//import az.subid.dto.GameInfoDTO;
//import az.subid.dto.GamePriceDTO;
//import az.subid.filter.EsdUrlFilter;
//import az.subid.filter.IgdbFilter;
//import az.subid.persistance.mongodb.IGameIgdbMapper;
//import az.subid.persistance.mongodb.IGameInfoMapper;
//import az.subid.persistance.mongodb.IGamePriceMapper;
//import az.subid.persistance.mongodb.ISequenceMapper;
//import az.subid.service.ISchedulerService;
//import az.subid.util.CmmUtil;
//import com.api.igdb.apicalypse.APICalypse;
//import com.api.igdb.apicalypse.Sort;
//import com.api.igdb.exceptions.RequestException;
//import com.api.igdb.request.IGDBWrapper;
//import com.api.igdb.request.ProtoRequestKt;
//import com.api.igdb.utils.TwitchToken;
//import lombok.extern.slf4j.Slf4j;
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.scheduling.annotation.EnableAsync;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
//import proto.Game;
//
//import javax.annotation.Resource;
//import java.text.SimpleDateFormat;
//import java.util.*;
//
///**
// * 스케줄러 서비스
// * 정해진 시간에 맞추어 동작하는 클래스입니다.
// *
// * @ Component 클래스에 추가
// * @ EnableAsync 클래스 병렬 허용
// * @ Scheduled 메소드에 추가
// * @ Async 메소드 병렬 허용
// */
//
//@Slf4j
//@EnableAsync
//@Component
//@Service("SchedulerService")
//public class SchedulerService implements ISchedulerService {
//
//    // MongoDB에 저장할 Mapper
//    @Resource(name = "GameIgdbMapper")
//    private IGameIgdbMapper gameIgdbMapper;
//
//    // MongoDB에 저장할 Mapper2
//    @Resource(name = "GameInfoMapper")
//    private IGameInfoMapper gameInfoMapper;
//
//    // MongoDB에 저장할 Mapper3
//    @Resource(name = "GamePriceMapper")
//    private IGamePriceMapper gamePriceMapper;
//
//    // MongoDB에 시퀸스 검색 Mapper
//    @Resource(name = "SequenceMapper")
//    private ISequenceMapper sequenceMapper;
//
//    // 트위치 클라이언트 id
//    @Value("${igdb.twitch.client_id}")
//    private String client_id;
//
//    // 트위치 클라이언트 secret
//    @Value("${igdb.twitch.client_secret}")
//    private String client_secret;
//
//    private static long skip = 0;
//    private final long adding = 1;
//    /**
//     * initalDelay : 초기화 시간
//     * fixedDelay : 끝나는 시간 기준
//     * fixedRate : 시작하는 시간 기준
//     * corn : 작업예약 (정해진 시간에 실행 )
//     */
////    @Scheduled(cron = "0 15 10 15 * ?", zone = "Europe/Paris") // 매월(*) 15일 오전 10시 15분에 실행, 유럽 시간
////    @Scheduled(initialDelay = 1000, fixedDelay = 1000, fixedRate = 1000)
//    @Override
//    public void testScheduler() throws InterruptedException {
//
//        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
//        log.info(this.getClass().getName() + ".testScheduler Start!");
//
////        // Java Example
////        APICalypse apicalypse = new APICalypse()
////                .fields("*")
////                .exclude("*")
////                .limit(10)
////                .offset(0)
////                .search("Halo")
////                .sort("release_dates.date", Sort.ASCENDING)
////                .where("platforms = 48");
//
////        // Java Example
////        byte[] bytes = wrapper.apiProtoRequest(endpoint: Endpoints.GAMES, apicalypseQuery: "fields *;");
////        List<Game> listOfGames = GameR
////
////        // Java Example
////        String json = wrapper.apiJsonRequest(endpoint: Endpoints.GAMES, "fields *;");
//
//        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
//        log.info(this.getClass().getName() + ".testScheduler end!");
//
//    }
//
//    @Override
//    @Scheduled(initialDelay = 1000, fixedDelay = 1000)
//    public void mainScheduler() throws InterruptedException {
//
//        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
//        log.info(this.getClass().getName() + ".mainScheduler Start!");
//
//        log.info(skip + " ~ " + (skip + adding));
//
//        // 엑서스 셋팅
//        TwitchToken token = new IgdbFilter().twichTokenAccess(client_id, client_secret);
//        IGDBWrapper wrapper = new IgdbFilter().igdbWrapperAccess(client_id, token);
//        // 요청 할 값 필터
//        APICalypse apicalypse = new APICalypse()
//                .fields("*")
//                .limit(500)
//                .sort("release.dates.date", Sort.DESCENDING)
//                .where("platforms = " + skip);
//
//        try{
//
//            // 게임 리스트 생성
//            List<Game> games = ProtoRequestKt.games(wrapper, apicalypse);
//
//            // 게임 리스트가 잘 들어갔는지 사이즈 로그
//            log.info("Total : " + games.size());
//
//            // 게임 리스트 업데이트
//            for(Game i : games) {
//
//                log.info(i.getName() + " [" + i.getPlatformsCount() + "]");
//
//
//                // 1. IGDB 에서 값을 받아온다.
//                igdbInitAndUpdate(i);
//
//                // 2. Info 에서 크롤링해서 값을 받아온다.
//                infoInitAndUpdate(i);
//                esdInitAndUpdate(i);
//
//                // 3. Price에서 크롤링해서 값을 받아온다.
//                priceInitAndUpdate(i);
//
//            }
//
//            // 대기 시간
//            Thread.sleep(5000);
//            skip += adding;
//
//        } catch (RequestException e) {
//
//            //조회가 실패되면 사용자에게 보여줄 메시지
//            log.info(e.toString());
//            skip += adding;
//            e.printStackTrace();
//
//        } catch (Exception e) {
//
//            throw new RuntimeException(e);
//
//        }
//
//        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
//        log.info(this.getClass().getName() + ".mainScheduler Start!");
//
//    }
//
//    @Override
//    // @Scheduled(cron = "0 00 00 * * ?", zone = "Europe/Paris") // 매월(*) 매일(*) 오전 01시 00분에 실행, 유럽 시간
//    public void igdbInitAndUpdate(Game game) throws Exception {
//
//        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
//        log.info(this.getClass().getName() + ".igdbInitAndUpdate Start!");
//
//        // Game IGDB
//        // 값이 겹치지 않는다면, insert
//        if (gameIgdbMapper.getGameIgdbCheck(game) == 1) {
//
//            gameIgdbMapper.insertGameIgdb(game);
//
//        } else { // 값이 이미 있다면, update
//
//            gameIgdbMapper.updateGameIgdb(game);
//
//        }
//
//        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
//        log.info(this.getClass().getName() + ".igdbInitAndUpdate End!");
//
//    }
//
//    @Override
//    // @Scheduled(cron = "0 00 00 * * ?", zone = "Europe/Paris") // 매월(*) 매일(*) 오전 01시 00분에 실행, 유럽 시간
//    public void infoInitAndUpdate(Game game) throws Exception {
//
//        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
//        log.info(this.getClass().getName() + ".esdInitAndupdate Start!");
//
//        // Game Information
//        // 값이 겹치지 않는다면, insert
//        if (gameInfoMapper.getGameInfoCheck(game) == 1) {
//
//            // GameInfo 값 넣기
//            GameInfoDTO pDTO = new GameInfoDTO();
//
//            pDTO.setSequence(sequenceMapper.getSequence(pDTO.colNm()).getCol_seq());
//            pDTO.setIgdb_id_(game.getId());
//
//            // 조회수 넣기
//            pDTO.setRead_cnt("0");
//
//            // 작성 정보 넣기
//            pDTO.setReg_id("AUTO");
//            pDTO.setReg_dt(SimpleDateFormat.getDateInstance().format(new Date()));
//
//            // 최근 정보 넣기
//            pDTO.setChg_id("AUTO");
//            pDTO.setChg_dt(SimpleDateFormat.getDateInstance().format(new Date()));
//
//            // 값 넣기
//            int success = gameInfoMapper.insertGameInfo(pDTO);
//
//            if (success > 0) {
//
//                // 시퀸스 값 증가
//                sequenceMapper.updateSequence(pDTO.colNm());
//
//            } else {
//
//                log.info("failed");
//
//            }
//
//        } else { // 값이 이미 있다면, update
//
//            // GameInfo 값 넣기
//            GameInfoDTO pDTO = new GameInfoDTO();
//
//            // 최근 정보 넣기
//            pDTO.setChg_id("AUTO");
//            pDTO.setChg_dt(SimpleDateFormat.getDateInstance().format(new Date()));
//
//            // 값 넣기
//            gameInfoMapper.updateGameInfo(pDTO);
//
//        }
//
//        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
//        log.info(this.getClass().getName() + ".esdInitAndupdate End!");
//
//    }
//
//    @Override
//    // @Scheduled(cron = "0 00 00 * * ?", zone = "Europe/Paris") // 매월(*) 매일(*) 오전 01시 00분에 실행, 유럽 시간
//    public void esdInitAndUpdate(Game game) throws Exception {
//
//        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
//        log.info(this.getClass().getName() + ".esdInitAndupdate Start!");
//
//        int res = 0;
//
//        /*
//        1. GameInfo의 모든 정보를 불러온다.
//        2. GameInfo.id_값을 참조해서 IGDB값을 불러온다.
//        3. GameInfo.id_값을 참조해서 GamePrice를 만든다.
//         */
//
//
//        // Game Information ESD
//        // 값이 없다면, update
//        if (gameInfoMapper.getGameInfoEsdCheck(game) == 1) {
//
//            // GameInfo 값 넣기
//            GameInfoDTO pDTO = new GameInfoDTO();
//
//            // IGDB 주소
//            String igdbName = game
//                    .getName()
//                    .toLowerCase()
//                    .replace(" ", "-")
//                    .replace("(", "-")
//                    .replace(")", "-")
//                    .replace("--", "-")
//                    .replace("/", "slash")
//                    .replace(":", "")
//                    .replace(".", "")
//                    .replace("\"", "")
//                    .replace("%", "")
//                    .replace("|", "");
//            String url = "https://www.igdb.com/games/" + igdbName;
//
//            // JSOUP 라이브러리를 통해 사이트 접속되면, 그 사이트의 전체 HTML소스 저장할 변수
//            Document doc = Jsoup.connect(url).get();
//
//            // Element로 저장
//            Element elementO = doc.select("div.content").first();
//
//            // URL을 찾기 위해서 &quot로 나눈다.
//            String outerHtml = CmmUtil.nvl(Objects.requireNonNull(elementO).outerHtml());
//            List<String> putsemi = new ArrayList<>(Arrays.asList(outerHtml.replace(";", "").split("&quot")));
//            String imageId = putsemi.get(putsemi.indexOf("cover") + 10);
//
//            // 등록된 플랫폼에 따라 URL의 아이디 값을 저장함.
//            for (String i : putsemi) {
//
//                if (i.contains("https:")
//                        & !i.contains("gamepage-header")
//                        & !i.contains("wizardry-the-return-of-werdna--the-fourth-scenario")
//                        & !i.contains("data-react-cache-id=\"GameEntries-0\"")) {
//
//                    log.info(igdbName + " : " + i);
//
//                    // PC
//                    if (new EsdUrlFilter().getUrlToEsd(i).contains("PC")) {
//
//                        String temp = i
//                                .replace(new EsdUrlFilter().getEsdToUrl("PC"), "");
//
//                        log.info("steam id : " + temp);
//
//                        pDTO.setSteam_id(temp);
//                    }
//
//                    // 방치
//                    // ND
//                    if (new EsdUrlFilter().getUrlToEsd(i).contains("ND_NEW")) {
//
//                        String temp = i
//                                .replace(new EsdUrlFilter().getEsdToUrl("ND"), "");
//
//                        log.info("nintendo id : " + temp);
//
//                        pDTO.setNintendo_id(temp);
//                    }
//
//                    // PS
//                    if (new EsdUrlFilter().getUrlToEsd(i).contains("PS")) {
//
//                        String temp = i
//                                .replace(new EsdUrlFilter().getEsdToUrl("PS"), "");
//
//                        log.info("playstation id : " + temp);
//
//                        pDTO.setPlaystation_id(temp);
//                    }
//
//                    // XBOX
//                    if (new EsdUrlFilter().getUrlToEsd(i).contains("XB")) {
//
//                        String temp = i
//                                .replace(new EsdUrlFilter().getEsdToUrl("XB"), "");
//
//                        log.info("xbox id : " + temp);
//
//                        pDTO.setXbox_id(temp);
//                    }
//
//                }
//
//            }
//
//            // IGDB 값으로 찾기위해 값 넣기
//            pDTO.setIgdb_id_(game.getId());
//
//            // 이미지 정보 넣기
//            pDTO.setIgdb_cover_id(imageId);
//
//            // 최근 정보 넣기
//            pDTO.setChg_id("AUTO");
//            pDTO.setChg_dt(SimpleDateFormat.getDateInstance().format(new Date()));
//
//            // 값 넣기
//            int success = gameInfoMapper.updateGameInfoEsd(pDTO);
//
//            if (success > 0) {
//
//                log.info("Success!");
//
//            } else {
//
//                log.info("failed");
//
//            }
//
//        } else { // 값이 이미 있다면, pass
//
//            log.info("pass");
//
//        }
//
//        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
//        log.info(this.getClass().getName() + ".esdInitAndupdate End!");
//
//    }
//
//    @Override
//    // @Scheduled(cron = "0 00 00 * * ?", zone = "Europe/Paris") // 매월(*) 매일(*) 오전 01시 00분에 실행, 유럽 시간
//    public void priceInitAndUpdate(Game game) throws Exception {
//
//        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
//        log.info(this.getClass().getName() + ".priceInitAndupdate Start!");
//
//        // Game Price
//        // 오늘 날짜가 없다면, insert
//        if (gamePriceMapper.getGamePriceCheck(game) == 1) {
//
//            // GameInfo 값 넣기
//            GameInfoDTO insertDTO = new GameInfoDTO();
//            insertDTO.setIgdb_id_(game.getId());
//            GameInfoDTO infoDTO = gameInfoMapper.getGameInfo(insertDTO);
//
//            // GamePrice 값 넣기
//            GamePriceDTO pDTO = new GamePriceDTO();
//
//            pDTO.setSequence(sequenceMapper.getSequence(pDTO.colNm()).getCol_seq());
//            pDTO.setIgdb_id_(game.getId());
//
//            // 크롤링 한 정보를 저장할 리스트
//            List<Integer> platforms = new LinkedList<>();
//            List<Double> prices = new LinkedList<>();
//            List<String> dates = new LinkedList<>();
//
//            // 플랫폼 분류
//            // PC
//            if (!infoDTO.getSteam_id().equals("")) {
//
//                // 스팀 주소
//                String url = new EsdUrlFilter().getEsdToUrl("PC") + new GameInfoDTO().getSteam_id();
//
//                // JSOUP 라이브러리를 통해 사이트 접속되면, 그 사이트의 전체 HTML소스 저장할 변수
//                Document doc = Jsoup.connect(url).get();
//
//                // Element로 저장
//                Element elementO = doc.select("div.content").first();
//
//                log.info("steam");
//            }
//            // Nintendo
//            if (!infoDTO.getNintendo_id().equals("")) {
//
//                // Nintendo 주소
//                String url = new EsdUrlFilter().getEsdToUrl("ND") + new GameInfoDTO().getNintendo_id();
//
//                // JSOUP 라이브러리를 통해 사이트 접속되면, 그 사이트의 전체 HTML소스 저장할 변수
//                Document doc = Jsoup.connect(url).get();
//
//                // Element로 저장
//                Element elementO = doc.select("div.content").first();
//
//                log.info("nds");
//            }
//            // Playstation
//            if (!infoDTO.getPlaystation_id().equals("")) {
//
//                // Playstation 주소
//                String url = new EsdUrlFilter().getEsdToUrl("PS") + new GameInfoDTO().getPlaystation_id();
//
//                // JSOUP 라이브러리를 통해 사이트 접속되면, 그 사이트의 전체 HTML소스 저장할 변수
//                Document doc = Jsoup.connect(url).get();
//
//                // Element로 저장
//                Elements elementO = doc.select("div.psw-c-bg-2");
//
//                log.info(url);
//                log.info(elementO.outerHtml());
////
////                double price = Double.parseDouble(Objects.requireNonNull(elementO).select("span.psw-t-title-m").text().replace("$", ""));
////
////                log.info(String.valueOf(price));
//
//                log.info("PS");
//            }
//            // XBOX
//            if (!infoDTO.getXbox_id().equals("")) {
//
//                // Xbox 주소
//                String url = new EsdUrlFilter().getEsdToUrl("XB") + new GameInfoDTO().getXbox_id();
//
//                // JSOUP 라이브러리를 통해 사이트 접속되면, 그 사이트의 전체 HTML소스 저장할 변수
//                Document doc = Jsoup.connect(url).get();
//
//                // Element로 저장
//                Element elementO = doc.select("div.content").first();
//
//                log.info("xbox");
//            }
//            // 여기 아래에 각 사이트에 대한 크롤링을 실행할 것
//
//
//
//            // 1. 어떤 플렛폼URL인지 분석하기
//            // 2. 플렛폼 URL에 따라 가격을 크롤링 하는 규칙을 생성
//            // 3. 규칙에 따라 크롤링 해서 값을 가져오기
//
//            // 리스트 값 넣기
//            pDTO.setPlatform(platforms);
//            pDTO.setPrice(prices);
//            pDTO.setDate(dates);
//
//            // 작성 정보 넣기
//            pDTO.setReg_id("AUTO");
//            pDTO.setReg_dt(SimpleDateFormat.getDateInstance().format(new Date()));
//
//            // 최근 정보 넣기
//            pDTO.setChg_id("AUTO");
//            pDTO.setChg_dt(SimpleDateFormat.getDateInstance().format(new Date()));
//
//            // 값 넣기
//            int success = gamePriceMapper.insertGamePrice(pDTO);
//
//            if (success > 0) {
//
//
//                // 시퀸스 값 증가
//                sequenceMapper.updateSequence(pDTO.colNm());
//
//            } else {
//
//                log.info("failed");
//
//            }
//
//        } else { // 값이 이미 있다면, update
//
//            // GameInfo 값 넣기
//            GamePriceDTO pDTO = new GamePriceDTO();
//
//            // 최근 정보 넣기
//            pDTO.setChg_id("AUTO");
//            pDTO.setChg_dt(SimpleDateFormat.getDateInstance().format(new Date()));
//
//            // 값 넣기
//            gamePriceMapper.updateGamePrice(pDTO);
//
//        }
//
//        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
//        log.info(this.getClass().getName() + ".priceInitAndupdate End!");
//
//    }
//}

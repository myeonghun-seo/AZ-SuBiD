package az.subid.service.impl;

import az.subid.dto.GameIgdbDTO;
import az.subid.dto.GameInfoDTO;
import az.subid.dto.GamePriceDTO;
import az.subid.persistance.mongodb.IGameIgdbMapper;
import az.subid.persistance.mongodb.IGameInfoMapper;
import az.subid.persistance.mongodb.IGamePriceMapper;
import az.subid.persistance.mongodb.ISequenceMapper;
import az.subid.service.IGamePriceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@Service("GamePriceService")
public class GamePriceService implements IGamePriceService {

    // MongoDB에 저장할 Mapper
    @Resource(name = "GameInfoMapper")
    private IGameInfoMapper gameInfoMapper;
    @Resource(name = "GameIgdbMapper")
    private IGameIgdbMapper gameIgdbMapper;
    @Resource(name = "GamePriceMapper")
    private IGamePriceMapper gamePriceMapper;

    // MongoDB에 시퀸스 검색 Mapper
    @Resource(name="SequenceMapper")
    private ISequenceMapper sequenceMapper;

    @Override
    public List<GameInfoDTO> getGameInfoList() throws Exception {

        //로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".GamePriceMain Start!");

        // 조회 결과를 전달하기 위한 객체 생성하기
        List<GameInfoDTO> rList = new LinkedList<>();

        // 조회 결과 담기
        rList = gameInfoMapper.getGameInfoList();

        //로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".GamePriceMain End!");

        return rList;
    }

    @Override
    public List<GameIgdbDTO> getGameIgdbList() throws Exception {

        //로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".GamePriceMain Start!");

        // 조회 결과를 전달하기 위한 객체 생성하기
        List<GameIgdbDTO> rList = new LinkedList<>();

        // 조회 결과 담기
        rList = gameIgdbMapper.getGameIgdbList();

        //로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".GamePriceMain End!");

        return rList;
    }

    @Override
    public List<GamePriceDTO> getGamePriceList() throws Exception {

        //로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".GamePriceMain Start!");

        // 조회 결과를 전달하기 위한 객체 생성하기
        List<GamePriceDTO> rList = new LinkedList<>();

        // 조회 결과 담기
        rList = gamePriceMapper.getGamePriceList();

        //로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".GamePriceMain End!");

        return rList;
    }

    @Override
    public int updateGameInfoReadCnt(GameInfoDTO pDTO) throws Exception {

        //로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".updateGameInfoReadCnt Start!");

        int res = 0;

        // MongoDB에 데이터 새로고침하기
        res = gameInfoMapper.updateGameInfoReadCnt(pDTO);

        //로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".updateGameInfoReadCnt End!");

        return res;
    }

    @Override
    public GameInfoDTO getGameInfo(GameInfoDTO pDTO) throws Exception {

        //로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".getGameInfo Start!");

        // 조회 결과를 전달하기 위한 객체 생성하기
        GameInfoDTO rDTO = new GameInfoDTO();

        // 조회 결과 담기
        rDTO = gameInfoMapper.getGameInfo(pDTO);

        //로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".getGameInfo End!");

        return rDTO;
    }

    @Override
    public GameIgdbDTO getGameIgdb(GameInfoDTO pDTO) throws Exception {

        //로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".getGameIgdb Start!");

        // 조회 결과를 전달하기 위한 객체 생성하기
        GameIgdbDTO rDTO = new GameIgdbDTO();

        // 조회 결과 담기
        rDTO = gameIgdbMapper.getGameIgdb(pDTO);

        //로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".getGameIgdb End!");

        return rDTO;
    }

    @Override
    public GamePriceDTO getGamePrice(GameInfoDTO pDTO) throws Exception {

        //로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".getNoticeInfo Start!");

        // 조회 결과를 전달하기 위한 객체 생성하기
        GamePriceDTO rDTO = new GamePriceDTO();

        // 조회 결과 담기
        rDTO = gamePriceMapper.getGamePrice(pDTO);

        //로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".getNoticeInfo End!");

        return rDTO;
    }

}

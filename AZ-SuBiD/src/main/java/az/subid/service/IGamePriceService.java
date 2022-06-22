package az.subid.service;

import az.subid.dto.GameIgdbDTO;
import az.subid.dto.GameInfoDTO;
import az.subid.dto.GamePriceDTO;

import java.util.List;

public interface IGamePriceService {

    /**
     * 게임 리스트 가져오기
     *
     * @return 게임 결과 전달
     * @throws Exception 예외 설정
     */
    List<GameInfoDTO> getGameInfoList() throws Exception;
    List<GameIgdbDTO> getGameIgdbList() throws Exception;
    List<GamePriceDTO> getGamePriceList() throws Exception;

    /**
     * 게임 상세보기 가져오기
     *
     * @param pDTO 조회수 고칠 DTO
     * @return 조회수 업 결과 전달
     * @throws Exception 예외 설정
     */
    int updateGameInfoReadCnt(GameInfoDTO pDTO) throws Exception;

    /**
     * 게임 정보 조회하기
     * 
     * @param pDTO 조회할 정보
     * @return 게임 결과 전달
     * @throws Exception 예외 설정
     */
    GameInfoDTO getGameInfo(GameInfoDTO pDTO) throws Exception;
    GameIgdbDTO getGameIgdb(GameInfoDTO pDTO) throws Exception;
    GamePriceDTO getGamePrice(GameInfoDTO pDTO) throws Exception;
}

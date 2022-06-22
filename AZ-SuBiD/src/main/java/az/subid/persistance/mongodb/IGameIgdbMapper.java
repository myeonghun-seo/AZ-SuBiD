package az.subid.persistance.mongodb;

import az.subid.dto.GameIgdbDTO;
import az.subid.dto.GameInfoDTO;
import proto.Game;

import java.util.List;

public interface IGameIgdbMapper {

    /**
     * IGDB 목록 겹치는지 체크
     * 
     * @param game 체크할 게임
     * @return 체크 결과
     * @throws Exception 예외 체크
     */
    int getGameIgdbCheck(Game game) throws Exception;

    /**
     * IGDB 목록 입력
     *
     * @param game 입력할 게임
     * @return 입력 결과
     * @throws Exception 예외 체크
     */
    int insertGameIgdb(Game game) throws Exception;

    /**
     * IGDB 목록 수정
     *
     * @param game 수정할 게임
     * @return 수정 결과
     * @throws Exception 예외 체크
     */
    int updateGameIgdb(Game game) throws Exception;

    /**
     * GameIgdb List 조회
     * 스케줄이 아니기 때문에 컬렉션 이름을 사용한다.
     *
     * @return 조회 결과
     * @throws Exception 예외 체크
     */
    List<GameIgdbDTO> getGameIgdbList() throws Exception;

    /**
     * GameInfo 조회하기
     * 스케줄이 아니기 때문에 컬렉션 이름을 사용한다.
     *
     * @param pDTO 조회할 DTO
     * @return 조회 결과 DTO
     * @throws Exception 예외 체크
     */
    GameIgdbDTO getGameIgdb(GameInfoDTO pDTO) throws Exception;
}

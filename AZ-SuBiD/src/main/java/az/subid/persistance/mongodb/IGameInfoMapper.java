package az.subid.persistance.mongodb;

import az.subid.dto.GameInfoDTO;
import proto.Game;

import java.util.List;

public interface IGameInfoMapper {

    /**
     * GameInfo 목록 겹치는지 체크
     *
     * @param game 체크할 게임
     * @return 체크 결과
     * @throws Exception 예외 체크
     */
    int getGameInfoCheck(Game game) throws Exception;

    /**
     * GameInfo 입력
     *
     * @param pDTO 입력할 DTO값
     * @return 입력 결과
     * @throws Exception 예외 체크
     */
    int insertGameInfo(GameInfoDTO pDTO) throws Exception;

    /**
     * GameInfo 수정
     *
     * @param pDTO 수정할 DTO값
     * @return 수정 결과
     * @throws Exception 예외 체크
     */
    int updateGameInfo(GameInfoDTO pDTO) throws Exception;

    /**
     * GameInfo Esd 목록 겹치는지 체크
     *
     * @param game 체크할 게임
     * @return 체크 결과
     * @throws Exception 예외 체크
     */
    int getGameInfoEsdCheck(Game game) throws Exception;

    /**
     * GameInfo Esd수정
     *
     * @param pDTO 수정할 DTO값
     * @return 수정 결과
     * @throws Exception 예외 체크
     */
    int updateGameInfoEsd(GameInfoDTO pDTO) throws Exception;

    /**
     * GameInfo List 조회
     * 스케줄이 아니기 때문에 컬렉션 이름을 사용한다.
     *
     * @return 조회 결과
     * @throws Exception 예외 체크
     */
    List<GameInfoDTO> getGameInfoList() throws Exception;

    /**
     * GameInfo readcnt 올리기
     * 스케줄이 아니기 때문에 컬렉션 이름을 사용한다.
     *
     * @param pDTO 바꿀 DTO
     * @return 카운트 결과
     * @throws Exception 예외 체크
     */
    int updateGameInfoReadCnt(GameInfoDTO pDTO) throws Exception;

    /**
     * GameInfo 조회하기
     * 스케줄이 아니기 때문에 컬렉션 이름을 사용한다.
     * 
     * @param pDTO 조회할 DTO
     * @return 조회 결과 DTO
     * @throws Exception 예외 체크
     */
    GameInfoDTO getGameInfo(GameInfoDTO pDTO) throws Exception;
}

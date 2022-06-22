package az.subid.persistance.mongodb;

import az.subid.dto.GameInfoDTO;
import az.subid.dto.GamePriceDTO;
import proto.Game;

import java.util.List;

public interface IGamePriceMapper {

    /**
     * GameInfo 목록 겹치는지 체크
     *
     * @param game 체크할 게임
     * @return 체크 결과
     * @throws Exception 예외 체크
     */
    int getGamePriceCheck(Game game) throws Exception;

    /**
     * GameInfo 입력
     *
     * @param pDTO 입력할 DTO값
     * @return 입력 결과
     * @throws Exception 예외 체크
     */
    int insertGamePrice(GamePriceDTO pDTO) throws Exception;

    /**
     * GameInfo 수정
     *
     * @param pDTO 수정할 DTO값
     * @return 수정 결과
     * @throws Exception 예외 체크
     */
    int updateGamePrice(GamePriceDTO pDTO) throws Exception;

    /**
     * GamePrice List 조회
     * 스케줄이 아니기 때문에 컬렉션 이름을 사용한다.
     *
     * @return 조회 결과
     * @throws Exception 예외 체크
     */
    List<GamePriceDTO> getGamePriceList() throws Exception;

    /**
     * GameInfo 조회하기
     * 스케줄이 아니기 때문에 컬렉션 이름을 사용한다.
     *
     * @param pDTO 조회할 DTO
     * @return 조회 결과 DTO
     * @throws Exception 예외 체크
     */
    GamePriceDTO getGamePrice(GameInfoDTO pDTO) throws Exception;
}

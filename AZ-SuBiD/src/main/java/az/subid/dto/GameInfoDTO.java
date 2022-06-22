package az.subid.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

/*
 * 게임정보 DTO
 * 게임정보의 생성, 열람, 수정, 삭제를 관여합니다.
 */

/**
 * lombok은 코딩을 줄이기 위해 @어노테이션을 통한 자동 코드 완성기능임
 * 값이 0인 것을 방지함.
 * @ Data => getter, setter 함수를 작성하지 않았지만, 자동 생성
 */
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Data
@EqualsAndHashCode(callSuper=false)
public class GameInfoDTO extends AAAdefaultDTO {

    /*
     * 상속한 기본 값
     *
     * 시퀸스 번호 sequence
     * 수정자 reg_id
     * 수정일 reg_dt
     * 최근 수정자 chg_id
     * 최근 수정일 chg_dt
     */

    private Long igdb_id_; // IGDB 아이디 번호
    private String read_cnt; // 조회수
    
    // 크롤링 할 주소 저장

    private String igdb_cover_id; // 커버 아이디

    private String steam_id; //Steam 데이터
    private String nintendo_id; // Nintendo 데이터
    private String playstation_id; // Playstation 데이터
    private String xbox_id; // XBOX 데이터

}

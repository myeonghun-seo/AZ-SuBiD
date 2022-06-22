package az.subid.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/*
 * 게임 가격 DTO
 * 게임 가격의 정보를 저장, 조회, 수정, 삭제를 관여합니다.
 */

/**
 * lombok은 코딩을 줄이기 위해 @어노테이션을 통한 자동 코드 완성기능임
 * 값이 0인 것을 방지함.
 * @ Data => getter, setter 함수를 작성하지 않았지만, 자동 생성
 */
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Data
@EqualsAndHashCode(callSuper=false)
public class GamePriceDTO extends AAAdefaultDTO{

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
    private List<Integer> platform; // 플렛폼 번호
    private List<Double> price; // 게임 가격 // 해외에서는 소수점 단위로 해놓았기 때문에 조심 또 조심해서 적어야 할 것!
    private List<String> sale_yn; // 세일 여부 // 세일 여부가 따로 있는 이유는
    private List<String> date; // 등록 날짜

}

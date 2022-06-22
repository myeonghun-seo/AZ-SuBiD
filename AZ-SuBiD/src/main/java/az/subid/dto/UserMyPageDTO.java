package az.subid.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

/*
 * 사용자 이용 DTO
 * 사용자가 좋아요 표시를 한 정보를 저장, 조회, 수정, 삭제를 관여합니다.
 */

/**
 * lombok은 코딩을 줄이기 위해 @어노테이션을 통한 자동 코드 완성기능임
 * 값이 0인 것을 방지함.
 * @ Data => getter, setter 함수를 작성하지 않았지만, 자동 생성
 */
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Data
@EqualsAndHashCode(callSuper=false)
public class UserMyPageDTO extends AAAdefaultDTO {

    /*
     * 상속한 기본 값
     *
     * 시퀸스 번호 sequence
     * 수정자 reg_id
     * 수정일 reg_dt
     * 최근 수정자 chg_id
     * 최근 수정일 chg_dt
     */

    private String user_seq; // 사용자의 시퀸스
    private String col_nm; // 컬렉션 이름
    private String mp_like; // 내 좋아요 번호

}

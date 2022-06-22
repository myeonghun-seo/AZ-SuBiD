package az.subid.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

/*
 * 사용자 정보 DTO
 * 사용자의 정보를 저장, 조회, 수정, 삭제를 관여합니다.
 */

/**
 * lombok은 코딩을 줄이기 위해 @어노테이션을 통한 자동 코드 완성기능임
 * 값이 0인 것을 방지함.
 * @ Data => getter, setter 함수를 작성하지 않았지만, 자동 생성
 */
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Data
@EqualsAndHashCode(callSuper=false)
public class UserInfoDTO extends AAAdefaultDTO {

    /*
     * 상속한 기본 값
     *
     * 시퀸스 번호 sequence
     * 수정자 reg_id
     * 수정일 reg_dt
     * 최근 수정자 chg_id
     * 최근 수정일 chg_dt
     */

    private String user_id; // 아이디
    private String user_nm; // 사용자명
    private String user_pw; // 비밀번호
    private String user_auth; // 권한
    private String email; // 이메일
    private String addr1; // 주소
    private String addr2; // 상세 주소

    // 회원 가입 시, 중복가입을 방지를 위해 사용할 변수
    // DB를 조회해서 회원이 존재하면 Y값을 반환함.
    // DB테이블에 존재하지 않는 가상의 컬럼(ALIAS)
    private String exists_yn;

}

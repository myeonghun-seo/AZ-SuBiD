package az.subid.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * 사용자 정보 DTO
 * 사용자의 정보를 저장, 조회, 수정, 삭제를 관여합니다.
 */

/**
 * lombok은 코딩을 줄이기 위해 @어노테이션을 통한 자동 코드 완성기능임
 * 값이 0인 것을 방지함.
 * @Getter => getter 함수를 작성하지 않았지만, 자동 생성
 * @Setter => setter 함수를 작성하지 않았지만, 자동 생성
 */
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Data
@Getter
@Setter
public class UserInfoDTO {

    String user_seq; // 사용자 번호
    String user_id; // 아이디
    String user_nm; // 사용자명
    String user_pw; // 비밀번호
    String user_auth; // 권한 .
    String email; // 이메일
    String addr1; // 주소
    String addr2; // 상세 주소
    String reg_id; // 수정자
    String reg_dt; // 수정일
    String chg_id; // 최근 수정자
    String chg_dt; // 최근 수정일

    // 회원 가입 시, 중복가입을 방지를 위해 사용할 변수
    // DB를 조회해서 회원이 존재하면 Y값을 반환함.
    // DB테이블에 존재하지 않는 가상의 컬럼(ALIAS)
    String exists_yn;

}

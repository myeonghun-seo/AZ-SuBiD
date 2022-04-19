package com.subid.market.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * lombok은 코딩을 줄이기 위해 @어노테이션을 통한 자동 코드 완성기능임
 * @Getter => getter 함수를 작성하지 않았지만, 자동 생성
 * @Setter => setter 함수를 작성하지 않았지만, 자동 생성
 */
@Getter
@Setter
public class HomeMainDTO {

    int user_no; // 사용자 번호
    String user_id; // 아이디
    String user_pw; // 비밀번호
    String user_nm; // 사용자명
    String user_auth; // 권한
    String email; // 이메일
    String addr1; // 주소
    String addr2; // 상세 주소
    String reg_id; // 수정자
    String reg_dt; // 수정일
    String chg_id; // 최근 수정자
    String chg_dt; // 최근 수정일

}

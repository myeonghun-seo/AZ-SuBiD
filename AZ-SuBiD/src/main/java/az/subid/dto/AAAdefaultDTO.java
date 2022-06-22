package az.subid.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/*
 * 기본 정보 DTO
 * DTO의 기본 정보를 상속하기 위해서 적어놓습니다.
 */

/**
 * lombok은 코딩을 줄이기 위해 @어노테이션을 통한 자동 코드 완성기능임
 * 값이 0인 것을 방지함.
 * @ Data => getter, setter 함수를 작성하지 않았지만, 자동 생성
 */
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Data
public class AAAdefaultDTO {

    private String sequence; // 시퀸스 번호
    private String reg_id; // 수정자
    private String reg_dt; // 수정일
    private String chg_id; // 최근 수정자
    private String chg_dt; // 최근 수정일

    // 컬랙션 이름 생성기
    public String colNm() { return this.getClass().getSimpleName().replace("DTO", "Collection"); }

}

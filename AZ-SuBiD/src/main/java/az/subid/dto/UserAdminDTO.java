package az.subid.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

/*
 * 관리자 정보 DTO
 * 사용자의 정보를 관리하며, 통계를 관여합니다.
 */

/**
 * lombok은 코딩을 줄이기 위해 @어노테이션을 통한 자동 코드 완성기능임
 * 값이 0인 것을 방지함.
 * @ Data => getter, setter 함수를 작성하지 않았지만, 자동 생성
 */
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Data
@EqualsAndHashCode(callSuper=false)
public class UserAdminDTO extends AAAdefaultDTO {

    /*
     * 상속한 기본 값
     *
     * 시퀸스 번호 sequence
     * 수정자 reg_id
     * 수정일 reg_dt
     * 최근 수정자 chg_id
     * 최근 수정일 chg_dt
     */

    private String user_seq;
    private String user_id;
    private String log_name;
    private String log_info;

    // UserInfo -> UserAdmin
    public UserAdminDTO(UserInfoDTO pDTO, String log_name, String log_info) {

        user_seq = pDTO.getSequence();
        user_id = pDTO.getUser_id();
        this.log_name = log_name;
        this.log_info = log_info;

    }

    public UserAdminDTO() {

    }
}

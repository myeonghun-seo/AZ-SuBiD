package az.subid.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * 공지사항 DTO
 * 공지사항의 생성, 열람, 수정, 삭제를 관여합니다.
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
public class NoticeDTO {

	String notice_seq; // 기본키, 순번
	String title; // 제목
	String notice_yn; // 공지글 여부
	String contents; // 글 내용
	String user_id; // 작성자
	String read_cnt; // 조회수
	String reg_id; // 등록자 아이디
	String reg_dt; // 등록일
	String chg_id; // 수정자 아이디
	String chg_dt; // 수정일

}

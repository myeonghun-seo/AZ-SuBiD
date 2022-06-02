package az.subid.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * 메일 DTO
 * ?//공지사항의 생성, 열람, 수정, 삭제를 관여합니다.
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
public class MailDTO {
	
	String toMail;	// 받는 사람
	String title;	// 본는 메일 제목
	String contents;// 보내는 메일 제목

}

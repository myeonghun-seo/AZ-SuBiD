package az.subid.service;

import az.subid.dto.MailDTO;

public interface IMailService {

	/**
	 * 메일 발송하기
	 * 
	 * @param pDTO 발송할 대상 정보
	 * @return 발송 결과
	 */
	// 메일 발송
	int doSendMail(MailDTO pDTO);
	
}

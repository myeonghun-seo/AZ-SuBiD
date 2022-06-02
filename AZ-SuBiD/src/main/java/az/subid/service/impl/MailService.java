package az.subid.service.impl;

import java.util.Properties;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import az.subid.dto.MailDTO;
import az.subid.service.IMailService;
import az.subid.util.CmmUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("MailService")
public class MailService implements IMailService {

	// 로그 파일 생성 및 로그 출력을 위한 log4j 프레임워크의 자바 객체
	private Logger log = Logger.getLogger(String.valueOf(this.getClass()));

	// 네이버에서 제공하는 SMTP서버
	@Value("${spring.mail.host}")
	private String host;

//	// 네이버에서 제공하는 포트
//	@Value("${spring.mail.port}")
//	private String port;

	// 본인 네이버 아이디
	@Value("${spring.mail.username}")
	private String user;

	// 본인 네이버 아이디
	@Value("${spring.mail.password}")
	private String password;

	@Override
	public int doSendMail(MailDTO pDTO) {

		// 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
		log.info(this.getClass().getName() + ".doSendMail start!");

		// 메일 발송 성공여부(발송성공 : 1 / 발송실패 : 0)
		int res = 1;

		//전달 받은 DTO로부터 데이터 가져오기(DTO객체가 메모리에 올라가지 않아 Null이 발생할 수 있기 때문에 에러방지차원으로 if문 사용함
		if (pDTO == null) {
			pDTO = new MailDTO();
		}

		String toMail = CmmUtil.nvl(pDTO.getToMail()); // 받는사람

		Properties props = new Properties();
		props.put("mail.smtp.host", host); // javax 외부 라이브러리에 메일 보내는 사람의 정보 설정
//		// 포트를 적을 시 방화벽에 포트를 뚫어야함. 사용하지 않음.
//		props.put("mail.smtp.port", port); // javax 외부 라이브러리에 메일 포트 정보
		props.put("mail.smtp.auth", "true"); // javax 외부 라이브러리에 메일 보내는 사람 인증 여부 설정

		// 네이버 SMTP서버 인증 처리 로직
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});

		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(toMail));

			// 메일 제목
			message.setSubject(CmmUtil.nvl(pDTO.getTitle()));

			// 메일 내용
			message.setText(CmmUtil.nvl(pDTO.getContents()));

			// 메일발송
			Transport.send(message);

		} catch (MessagingException e) { //메일 전송 관련 에러 다 잡기
			res = 0; // 메일 발송이 실패해기 때문에 0으로 변경
			log.info("[ERROR] " + this.getClass().getName() + ".doSendMail : " + e);

		} catch (Exception e) {//모든 에러 다 잡기
			res = 0; // 메일 발송이 실패해기 때문에 0으로 변경
			log.info("[ERROR] " + this.getClass().getName() + ".doSendMail : " + e);
		}

		// 로그 찍기(추후 찍은 로그를 통해 이 함수 호출이 끝났는지 파악하기 용이하다.)
		log.info(this.getClass().getName() + ".doSendMail end!");

		return res;
	}

}
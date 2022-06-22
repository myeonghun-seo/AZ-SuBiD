package az.subid.util;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EncryptTest {

	public static void main(String[] args) throws Exception{
		
		System.out.println("----------------------------------");
		System.out.println("해시 암호화 알고리즘");
		//암호화 문자열
		String str = "암호화할 문자열";
		
		//복호화가 불가능한 해시암호화 알고리즘 실행
		String hashEnc = EncryptUtil.encHashSHA256(str);
		
		//해시 암호화 알고리즘 결과 출력
		System.out.println("hashEnc : " + hashEnc);
		
		System.out.println("----------------------------------");
		System.out.println("AES128-CRC 암, 복호화 알고리즘");
		
		//AES128-CRC 암호화 알고리즘 실행
		String enc = EncryptUtil.encAES128CBC(str);
		
		//AES128-CRC 암호화 알고리즘 결과 출력
		System.out.println("enc : " + enc);
		
		//AES128-CRC 복호화 알고리즘 실행
		String dec = EncryptUtil.decAES128CBC(str);		
		
		//AES128-CRC 복호화 알고리즘 결과 출력
		System.out.println("dec : " + dec);

		System.out.println("----------------------------------");
	}
}

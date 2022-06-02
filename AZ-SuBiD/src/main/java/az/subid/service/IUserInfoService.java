package az.subid.service;

import az.subid.dto.UserInfoDTO;

public interface IUserInfoService {

    /**
     * 회원 가입하기 (회원정보 등록하기)
     *
     * @param pDTO 저장할 정보
     * @param colNm 참조할 컬렉션 이름
     * @return 저장 성공 여부
     * @throws Exception
     */
    int insertUserInfo(UserInfoDTO pDTO, String colNm) throws Exception;

    /**
     *  로그인 체크하기
     * 
     * @param pDTO 로그인 할 정보
     * @param colNm 참조할 컬렉션 이름
     * @return 로그인 성공 여부
     * @throws Exception
     */
    int getUserLoginCheck(UserInfoDTO pDTO, String colNm) throws Exception;

}

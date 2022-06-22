package az.subid.service;

import az.subid.dto.UserInfoDTO;

public interface IUserInfoService {

    /**
     * 회원 가입하기 (회원정보 등록하기)
     *
     * @param pDTO 저장할 정보
     * @return 저장 성공 여부
     * @throws Exception 예외 체크
     */
    int insertUserInfo(UserInfoDTO pDTO) throws Exception;

    /**
     *  로그인 체크하기
     * 
     * @param pDTO 로그인 할 정보
     * @return 로그인 성공 여부
     * @throws Exception 예외 체크
     */
    int getUserLoginCheck(UserInfoDTO pDTO) throws Exception;

    /**
     *  비밀번호 초기화하기
     *
     * @param pDTO 이메일 정보
     * @return 초기화 성공 여부
     * @throws Exception 예외 체크
     */
    String initUserInfoPasswd(UserInfoDTO pDTO) throws Exception;
    
    /**
     * 로그인 한 모든 정보를 가져오기
     *
     * @param pDTO 로그인 할 정보
     * @return 로그인 한 정보값 가져오기
     * @throws Exception 예외 체크
     */
    UserInfoDTO getUserInfo(UserInfoDTO pDTO) throws Exception;

    /**
     * 비밀번호 변경하기
     *
     * @param pDTO 바꿀 정보
     * @return 성공 여부
     * @throws Exception 예외 체크
     */
    int getUpdatePasswd(UserInfoDTO pDTO) throws Exception;

    /**
     * 회원 수정하기 (회원정보 등록하기)
     *
     * @param pDTO 저장할 정보
     * @return 저장 성공 여부
     * @throws Exception 예외 체크
     */
    int updateUserInfo(UserInfoDTO pDTO) throws Exception;

    /**
     * 회원 탈퇴하기 (회원정보 삭제하기)
     *
     * @param pDTO 삭제할 정보
     * @return 삭제 성공 여부
     * @throws Exception 예외 체크
     */
    int deleteUserInfo(UserInfoDTO pDTO) throws Exception;

}

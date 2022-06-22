package az.subid.persistance.mongodb;

import az.subid.dto.UserInfoDTO;

public interface IUserInfoMapper {

    /**
     * 회원 가입하기 (회원정보 등록하기)
     *
     * @param pDTO 저장할 정보
     * @return 저장 성공 여부
     * @throws Exception 예외 체크
     */
    int insertUserInfo(UserInfoDTO pDTO) throws Exception;

    /**
     * 회원 가입 전 중복체크하기 (DB 조회하기)
     *
     * @param pDTO 저장된 정보
     * @return 조회 성공 여부
     * @throws Exception 예외 체크
     */
    UserInfoDTO getUserExists(UserInfoDTO pDTO) throws Exception;

    /**
     * 로그인을 위해 아이디와 비밀번호가 일치하는지 확인하기
     *
     * @param pDTO 입력한 정보
     * @return 조회한 값 반환
     * @throws Exception 예외 체크
     */
    UserInfoDTO getUserLoginCheck(UserInfoDTO pDTO) throws Exception;

    /**
     *  비밀번호 초기화하기
     *
     * @param pDTO 이메일 정보
     * @return 초기화 성공 여부
     * @throws Exception 예외 체크
     */
    int initUserInfoPasswd(UserInfoDTO pDTO) throws Exception;

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
     * 회원 수정하기 (회원정보 갱신하기)
     *
     * @param pDTO 수정할 정보
     * @return 수정 성공 여부
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

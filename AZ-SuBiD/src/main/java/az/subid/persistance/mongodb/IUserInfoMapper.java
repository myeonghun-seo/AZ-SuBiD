package az.subid.persistance.mongodb;

import az.subid.dto.UserInfoDTO;

public interface IUserInfoMapper {

    /**
     * 회원 가입하기 (회원정보 등록하기)
     *
     * @param pDTO 저장할 정보
     * @param colNm 저장할 컬랙션 이름
     * @return 저장 성공 여부
     * @throws Exception
     */
    int insertUserInfo(UserInfoDTO pDTO, String colNm) throws Exception;

    /**
     * 회원 가입 전 중복체크하기 (DB 조회하기)
     *
     * @param pDTO 저장된 정보
     * @param colNm 조회할 컬렉션 이름
     * @return 조회 성공 여부
     * @throws Exception
     */
    UserInfoDTO getUserExists(UserInfoDTO pDTO, String colNm) throws Exception;

    /**
     * 로그인을 위해 아이디와 비밀번호가 일치하는지 확인하기
     *
     * @param pDTO 입력한 정보
     * @param colNm 조회할 컬렉션 이름
     * @return 조회한 값 반환
     * @throws Exception
     */
    UserInfoDTO getUserLoginCheck(UserInfoDTO pDTO, String colNm) throws Exception;

}

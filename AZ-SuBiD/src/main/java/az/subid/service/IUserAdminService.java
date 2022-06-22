package az.subid.service;

import az.subid.dto.UserAdminDTO;
import az.subid.dto.UserInfoDTO;

import java.util.List;

public interface IUserAdminService {

    /**
     * Admin 페이지에서 모든 User의 정보를 가져오기
     *
     * @return 조회한 리스트
     * @throws Exception 예외처리
     */
    List<UserInfoDTO> getListAdminUser() throws Exception;

    /**
     * Admin 페이지에서 해당 로그의 정보를 가져오기
     *
     * @param pDTO 조회할 아이디 값
     * @return 조회한 리스트
     * @throws Exception 예외처리
     */
    public List<UserAdminDTO> getListAdminInfo(UserAdminDTO pDTO) throws Exception;
    /**
     * Admin에 어떠한 상황에서든지 로그를 심어줌.
     *
     * @param pDTO 넣어줄 로그 정보
     * @return 입력 결과
     * @throws Exception 예외처리
     */
    int insertUserAdmin(UserAdminDTO pDTO) throws Exception;
    int insertUserAdmin(UserInfoDTO pDTO, String log_name, String log_info) throws Exception;
}

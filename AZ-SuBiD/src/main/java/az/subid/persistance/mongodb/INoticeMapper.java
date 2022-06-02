package az.subid.persistance.mongodb;

import az.subid.dto.NoticeDTO;

import java.util.List;

public interface INoticeMapper {

    /**
     *
     * @param colNm 조회할 컬랙션 이름
     * @return 공지사항 리스트
     * @throws Exception
     */
    List<NoticeDTO> getNoticeList(String colNm) throws Exception;

    /**
     *
     * @param pDTO 저장될 정보
     * @param colNm 저장할 컬렉션 이름
     * @return 저장 결과
     * @throws Exception
     */
    int insertNoticeInfo(NoticeDTO pDTO, String colNm) throws Exception;

    /**
     *
     * @param pDTO 조회할 정보
     * @param colNm 보회할 컬렉션 이름
     * @return 조회 결과
     * @throws Exception
     */
    NoticeDTO getNoticeInfo(NoticeDTO pDTO, String colNm) throws Exception;

    /**
     *
     * @param pDTO 업데이트할 정보
     * @param colNm 업데이트할 컬렉션 이름
     * @return 업데이트 결과
     * @throws Exception
     */
    int updateNoticeReadCnt(NoticeDTO pDTO, String colNm) throws Exception;

    /**
     *
     * @param pDTO 수정할 정보
     * @param colNm 수정할 컬렉션 이름
     * @return 수정 결과
     * @throws Exception
     */
    int updateNoticeInfo(NoticeDTO pDTO, String colNm) throws Exception;

    /**
     *
     * @param pDTO 삭제할 정보
     * @param colNm 삭제할 컬렉션 이름
     * @return 삭제 결과
     * @throws Exception
     */
    int deleteNoticeInfo(NoticeDTO pDTO, String colNm) throws Exception;

}

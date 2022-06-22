package az.subid.persistance.mongodb;

import az.subid.dto.SequenceDTO;

public interface ISequenceMapper {

    /**
     * 원하는 컬랙션의 시퀸스 값 출력
     * 
     * @param colNm 컬렉션 이름
     * @return 시퀸스 값
     * @throws Exception
     */
    SequenceDTO getSequence(String colNm) throws Exception;

    /**
     * 생성이 완료되었다면, 시퀸스 증가시키기
     *
     * @param colNm 컬렉션 이름
     * @return 시퀸스 증감성공 여부
     * @throws Exception
     */
    int updateSequence(String colNm) throws Exception;
}

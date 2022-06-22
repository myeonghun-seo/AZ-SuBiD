package az.subid.service;

import proto.Game;

public interface ISchedulerService {

    /**
     * 스케줄링 테스트. 각종 테스트를 담당.
     *
     * @throws InterruptedException 인터럽트 예외체크
     */
    void testScheduler() throws InterruptedException;

    /**
     * Main 스케줄.
     * 하위 메소드를 포함한다.
     *
     * @throws InterruptedException 인터럽트 예외체크
     */
    void mainScheduler() throws InterruptedException;
    
    /**
     * IGDB API에서 정보 받아오기
     * 하루에 한번씩 가져오기
     *
     * @throws InterruptedException 인터럽트 예외체크
     */
    void igdbInitAndUpdate(Game game) throws Exception;

    /**
     * info 에 정보 기입하기
     * 하루에 한번씩 가져오기
     *
     * @throws InterruptedException 인터럽트 예외체크
     */
    void infoInitAndUpdate(Game game) throws Exception;

    /**
     * esd 에서 정보 받아오기
     * 하루에 한번씩 가져오기
     *
     * @throws InterruptedException 인터럽트 예외체크
     */
    void esdInitAndUpdate(Game game) throws Exception;
    /**
     * price 에 정보 기입하기
     * 하루에 한번씩 가져오기
     *
     * @throws InterruptedException 인터럽트 예외체크
     */
    void priceInitAndUpdate(Game game) throws Exception;

}

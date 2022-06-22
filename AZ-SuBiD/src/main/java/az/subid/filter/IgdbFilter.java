package az.subid.filter;

import com.api.igdb.request.IGDBWrapper;
import com.api.igdb.request.TwitchAuthenticator;
import com.api.igdb.utils.TwitchToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * IGDB에 정보를 가져오기위해 하는 선입행동과 동작들을 담고 있음
 */
@Slf4j
@Component
public class IgdbFilter {

    /**
     * 트위치 토큰 (Overloading)
     * 따로 넣을 값이 있으면 넣고 실행
     * 
     * @param client_id 트위치 클라이언트 아이디
     * @param client_secret 트위치 클라이언트 시크릿
     * @return 엑서스 값
     */
    public TwitchToken twichTokenAccess(String client_id, String client_secret) {

        // 트위치 토큰 엑서스
        TwitchAuthenticator tAuth = TwitchAuthenticator.INSTANCE;
        TwitchToken token = tAuth.requestTwitchToken(client_id, client_secret);

//        // IGDB 토큰 테스트
//        log.info("IGDB : " + token.getAccess_token());

        return token;
    }

    /**
     * IGDB Wrapper 엑서스 (Overloading)
     * 따로 넣을 값이 있으면 넣고 실행
     *
     * @param client_id 트위치 클라이언트 아이디
     * @param token 트위치 클라이언트 토큰
     * @return 엑서스 값
     */
    public IGDBWrapper igdbWrapperAccess(String client_id, TwitchToken token) {

        // IGDB Wrapper 엑서스
        IGDBWrapper wrapper = IGDBWrapper.INSTANCE;
        wrapper.setCredentials(client_id, token.getAccess_token());

//        // IGDB Wrapper 테스트
//        log.info("Wrapper : " + wrapper.toString().length());

        return wrapper;
    }

}

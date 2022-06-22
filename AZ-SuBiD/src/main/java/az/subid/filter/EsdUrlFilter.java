package az.subid.filter;

import org.bson.Document;

public class EsdUrlFilter {

    public Document esdUrl () {

        Document url = new Document();

        // URL
        url.append("URL_PC", "https://www.store.steampowered.com/app/");
        url.append("URL_ND_OLD", "https://www.nintendo.com/games/detail/");
        url.append("URL_ND_NEW", "https://www.nintendo.com/store/products/");
        url.append("URL_ND_KR", "https://www.nintendo.com/games/detail/");
        url.append("URL_PS", "https://store.playstation.com/en-us/concept/");
        url.append("URL_PS_KR", "https://store.playstation.com/ko-kr/concept/");
        url.append("URL_XB", "https://marketplace.xbox.com//en-US/Product/");
        url.append("URL_XB_KR", "https://marketplace.xbox.com//ko-kr/Product/");

        return url;
    }

    // 플랫폼 URL 출력하기
    public String getEsdToUrl (String platform) {

        String url = "";

        switch (platform) {

            // PC
            // Steam 값 넣기
            // https://store.steampowered.com/app/id/name
            case "PC" :
            case "URL_PC" :
                url = esdUrl().getString("URL_PC");
                break;

            // Nintendo
            // Nintendo 값 넣기
            // https://www.nintendo.com/store/products/name-platform/
            case "ND" :
            case "URL_ND" :
                url = esdUrl().getString("URL_ND");
                break;

            // PlayStation
            // PlayStation 값 넣기
            // https://store.playstation.com/en-us/concept/id
            case "PS" :
            case "URL_PS" :
                url = esdUrl().getString("URL_PS");
                break;

            // Xbox
            // Xbox 값 넣기
            // https://marketplace.xbox.com//en-US/Product/name/id
            case "XB" :
            case "URL_XB" :
                url = esdUrl().getString("URL_XB");
                break;

        }

        return url;
    }

    public String getUrlToEsd (String url) {

        for (String str : esdUrl().keySet() ) {
            if (url.contains(esdUrl().getString(str)))
                return str;
        }

        return "";
    }

}

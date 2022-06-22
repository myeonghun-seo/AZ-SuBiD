package az.subid.filter;

import az.subid.dto.GameIgdbDTO;
import com.google.common.base.Functions;
import com.google.common.collect.Lists;
import org.bson.Document;
import proto.Game;

import java.util.LinkedList;
import java.util.List;

public class GamePlatformFilter {

    // 플랫폼
    public Document platforms() {
        
        Document pf = new Document();

        // PC
        pf.append("Linux", 3);
        pf.append("Windows", 6);
        pf.append("Mac", 14);

        // Nintendo
        pf.append("Nintendo Switch", 130);

        // PlayStation
        pf.append("PlayStation 2", 8);
        pf.append("PlayStation 4", 48);
        pf.append("PlayStation 5", 167);
        pf.append("PlayStation Portable", 38);

        // Xbox
        pf.append("Xbox", 11);
        pf.append("Xbox 360", 12);
        pf.append("Xbox One", 49);
        pf.append("Xbox Series X|S", 169);

        // ETC
        pf.append("Arcade", 52);

        return pf;
    }
    
    // 플랫폼을 입력하면 코드 번호를 출력
    public int getIdInt(String pf) {
        
        return platforms().getInteger(pf);

    }

    // 플랫폼을 입력하면 코드 번호 문장으로 출력
    public String getIdString(String pf) {

        return "id: " + getIdInt(pf) + "\n";

    }

    // 번호를 입력 하면 플렛폼을 출력
    public String getPlatform(int value) {

        for (String str : platforms().keySet() ) {
            if (value == platforms().getInteger(str))
                return str;
        }

        return "";
    }

    // 문장을 입력 하면 플렛폼을 출력
    public String getPlatform(String value) {

        for (String str : platforms().keySet() ) {
            if (value.contains(String.valueOf(platforms().getInteger(str))))
                return str;
        }

        return "";
    }

    // 플랫폼 선택
    public List<String> selectPlatforms(GameIgdbDTO pDTO) {

        List<String> rList = new LinkedList<>();

        // PC 플렛폼 : 1
        if(pDTO.getPlatforms_().contains(getIdString("Linux"))
          |pDTO.getPlatforms_().contains(getIdString("Windows"))
          |pDTO.getPlatforms_().contains(getIdString("Mac"))
        ) {
            rList.add("PC");
        }
        // Nintendo : 2
        if(pDTO.getPlatforms_().contains(getIdString("Nintendo Switch"))
        ) {
            rList.add("ND");
        }
        // PlayStation : 4
        if(pDTO.getPlatforms_().contains(getIdString("PlayStation 2"))
          |pDTO.getPlatforms_().contains(getIdString("PlayStation 4"))
          |pDTO.getPlatforms_().contains(getIdString("PlayStation 5"))
          |pDTO.getPlatforms_().contains(getIdString("PlayStation Portable"))
        ) {
            rList.add("PS");
        }
        // Xbox : 8
        if(pDTO.getPlatforms_().contains(getIdString("Xbox"))
          |pDTO.getPlatforms_().contains(getIdString("Xbox 360"))
          |pDTO.getPlatforms_().contains(getIdString("Xbox One"))
          |pDTO.getPlatforms_().contains(getIdString("Xbox Series X|S"))
        ) {
            rList.add("XB");
        }

        return rList;
    }

    public List<String> selectPlatforms(Game game) {

        GameIgdbDTO rDTO = new GameIgdbDTO();

        rDTO.setPlatforms_(Lists.transform(game.getPlatformsList(), Functions.toStringFunction()));

        return selectPlatforms(rDTO);
    }
    
}

package az.subid.persistance.mongodb.impl;

import az.subid.dto.GameInfoDTO;
import az.subid.filter.GamePlatformFilter;
import az.subid.persistance.mongodb.AbstractMongoDBComon;
import az.subid.persistance.mongodb.IGameInfoMapper;
import az.subid.util.CmmUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Functions;
import com.google.common.collect.Lists;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.stereotype.Component;
import proto.Game;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static com.mongodb.client.model.Updates.set;

@Slf4j
@Component("GameInfoMapper")
public class GameInfoMapper extends AbstractMongoDBComon implements IGameInfoMapper {

    // MongoDB 컬렉션 이름
    private final String colNm = new GameInfoDTO().colNm();

    @Override
    public int getGameInfoCheck(Game game) throws Exception {

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".getGameInfoCheck Start!");

        // 컬렉션이 없다면 생성하기
        if(!mongodb.collectionExists(colNm)) {

            // 컬렉션 생성
            super.createCollection(colNm);
            // 인덱스 생성
//            mongodb.getCollection(colNm).createIndex(Indexes.ascending("sequence"));

        }

        int res = 0;

        // MongoDB 컬렉션 지정하기
        MongoCollection<Document> col = mongodb.getCollection(colNm);

        // 조회 결과 중 출력할 컬럼들(SQL의 SELECT절과 FROM절 가운데 컬럼들과 유사함)
        Document projection = new Document();

        projection.append("igdb_id_", "$igdb_id_");

        // MongoDB의 find 명령어를 통해 조회할 경우 사용함
        // 조회하는 데이터의 양이 적은 경우, find를 사용하고, 데이터양이 많은 경우 무조건 Aggregate 사용한다.
        FindIterable<Document> rs = col.find(new Document("igdb_id_", game.getId())).projection(projection);

        Document doc = rs.first();

        if (doc == null) {

            doc = new Document();

        }

        // 조회 테스트
        String igdb_id_ = CmmUtil.nvl(String.valueOf(doc.getLong("igdb_id_")));

        log.info("Myinfo DB id : " + igdb_id_);
//        log.info("IGDB id : " + game.getId());

        // 같은 값이 없다면...
        if (!igdb_id_.equals(Long.toString(game.getId()))) {
            res = 1;
        }

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".getGameInfoCheck End!");

        return res;
    }

    @Override
    public int insertGameInfo(GameInfoDTO pDTO) throws Exception {

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".insertGameInfo End!");

        int res = 0;

        // MongoDB 컬렉션 지정하기
        MongoCollection<Document> col = mongodb.getCollection(colNm);

        // DTO를 Map 데이터타입으로 변경한 뒤, 변경된 Map 데이터타입을 Document로 변경하기
        col.insertOne(new Document(new ObjectMapper().convertValue(pDTO, Map.class)));

        res = 1;

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".insertGameInfo End!");

        return res;
    }

    @Override
    public int updateGameInfo(GameInfoDTO pDTO) throws Exception {

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".updateGameInfo Start!");

        int res = 0;

        // MongoDB 컬렉션 지정하기
        MongoCollection<Document> col = mongodb.getCollection(colNm);

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".updateGameInfo End!");

        return res;
    }

    @Override
    public int getGameInfoEsdCheck(Game game) throws Exception {

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".getGameInfoEsdCheck Start!");

        int res = 0;

        // MongoDB 컬렉션 지정하기
        MongoCollection<Document> col = mongodb.getCollection(colNm);

        // 플랫폼 리스트 변환하기기
        List<String> pfList = new LinkedList<>();

        for (String pf : Lists.transform(game.getPlatformsList(), Functions.toStringFunction())) {

            pfList.add(new GamePlatformFilter().getPlatform(pf));

        }

        // 만약 해당되는 플랫폼을 가지고 있지 않다면, 나가기
        if (pfList.size() == 0) {
            log.info("No Has PlatForms");
        } else {
            res = 1;
        }

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".getGameInfoEsdCheck End!");

        return res;
    }

    @Override
    public int updateGameInfoEsd(GameInfoDTO pDTO) throws Exception {

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".updateGameInfoEsd Start!");

        int res = 0;

        // MongoDB 컬렉션 지정하기
        MongoCollection<Document> col = mongodb.getCollection(colNm);

        // 조회 결과 중 출력할 컬럼들(SQL의 SELECT절과 FROM절 가운데 컬럼들과 유사함)
        Document projection = new Document();
        projection.append("igdb_id_", "$igdb_id_");

        // MongoDB의 find 명령어를 통해 조회할 경우 사용함
        // 조회하는 데이터의 양이 적은 경우, find를 사용하고, 데이터양이 많은 경우 무조건 Aggregate 사용한다.
        FindIterable<Document> rs = col.find(new Document("igdb_id_", pDTO.getIgdb_id_())).projection(projection);

        // 한줄로 append해서 수정할 필드 추가해도 되지만, 가독성이 떨어져 줄마다 append 함
        Document updateDoc = new Document();
        updateDoc.append("igdb_cover_id", CmmUtil.nvl(pDTO.getIgdb_cover_id())); // 기존 필드 수정
        updateDoc.append("steam_id", CmmUtil.nvl(pDTO.getSteam_id())); // 기존 필드 수정
        updateDoc.append("nintendo_id", CmmUtil.nvl(pDTO.getNintendo_id())); // 기존 필드 수정
        updateDoc.append("playstation_id", CmmUtil.nvl(pDTO.getPlaystation_id())); // 기존 필드 수정
        updateDoc.append("xbox_id", CmmUtil.nvl(pDTO.getXbox_id())); // 기존 필드 수정

        rs.forEach(doc -> col.updateOne(doc, new Document("$set", updateDoc)));

        res = 1;

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".updateGameInfoEsd End!");

        return res;
    }

    @Override
    public List<GameInfoDTO> getGameInfoList() throws Exception {

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".getGameInfoList Start!");

        // 조회 결과를 전달하기 위한 객체 생성하기
        List<GameInfoDTO> rList = new LinkedList<>();

        // 컬렉션이 없다면 생성하기
        if(!mongodb.collectionExists(colNm)) {

            // 컬렉션 생성
            super.createCollection(colNm);
            // 인덱스 생성
//            mongodb.getCollection(colNm).createIndex(Indexes.ascending("sequence"));

        }

        // MongoDB 컬렉션 지정하기
        MongoCollection<Document> col = mongodb.getCollection(colNm);

        // 조회 결과 중 출력할 컬럼들(SQL의 SELECT절과 FROM절 가운데 컬럼들과 유사함)
        Document projection = new Document();
        projection.append("sequence", "$sequence");
        projection.append("reg_id", "$reg_id");
        projection.append("reg_dt", "$reg_dt");
        projection.append("chg_id", "$chg_id");
        projection.append("chg_dt", "$chg_dt");
        projection.append("igdb_id_", "$igdb_id_");
        projection.append("read_cnt", "$read_cnt");
        projection.append("igdb_cover_id", "$igdb_cover_id");

        // MongoDB는 무조건 ObjectID가 자동생성되며, ObjectID는 사용하지 않을 때, 조회할 필요가 없음.
        projection.append("_id", 0);

        // MongoDB의 find 명령어를 통해 조회할 경우 사용함
        // 조회하는 데이터의 양이 적은 경우, find를 사용하고, 데이터양이 많은 경우 무조건 Aggregate 사용한다.
        FindIterable<Document> rs = col.find(new Document()).projection(projection);

        for (Document doc : rs) {

            if (doc == null) {

                doc = new Document();

            }

            // 조회 테스트
            String sequence = CmmUtil.nvl(doc.getString("sequence"));
            String reg_id = CmmUtil.nvl(doc.getString("reg_id"));
            String reg_dt = CmmUtil.nvl(doc.getString("reg_dt"));
            String chg_id = CmmUtil.nvl(doc.getString("chg_id"));
            String chg_dt = CmmUtil.nvl(doc.getString("chg_dt"));
            long igdb_id_ = doc.getLong("igdb_id_");
            String read_cnt = CmmUtil.nvl(doc.getString("read_cnt"));
            String igdb_cover_id = CmmUtil.nvl(doc.getString("igdb_cover_id"));

            log.info("sequence : " + sequence);
            log.info("reg_id : " + reg_id);
            log.info("reg_dt : " + reg_dt);
            log.info("chg_id : " + chg_id);
            log.info("chg_dt : " + chg_dt);
            log.info("igdb_id_ : " + igdb_id_);
            log.info("read_cnt : " + read_cnt);
            log.info("igdb_cover_id : " + igdb_cover_id);

            GameInfoDTO rDTO = new GameInfoDTO();

            rDTO.setSequence(sequence);
            rDTO.setReg_id(reg_id);
            rDTO.setReg_dt(reg_dt);
            rDTO.setChg_id(chg_id);
            rDTO.setChg_dt(chg_dt);
            rDTO.setIgdb_id_(igdb_id_);
            rDTO.setRead_cnt(read_cnt);
            rDTO.setIgdb_cover_id(igdb_cover_id);

            // 레코드 결과를 List에 저장하기
            rList.add(rDTO);

        }

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".getGameInfoList End!");

        return rList;
    }

    @Override
    public int updateGameInfoReadCnt(GameInfoDTO pDTO) throws Exception {

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".updateGameInfoReadCnt Start!");

        int res = 0;

        // MongoDB 컬렉션 지정하기
        MongoCollection<Document> col = mongodb.getCollection(colNm);

        // 조회 결과 중 출력할 컬럼들(SQL의 SELECT절과 FROM절 가운데 컬럼들과 유사함)
        Document projection = new Document();

        projection.append("read_cnt", "$read_cnt");

        // MongoDB의 find 명령어를 통해 조회할 경우 사용함
        // 조회하는 데이터의 양이 적은 경우, find를 사용하고, 데이터양이 많은 경우 무조건 Aggregate 사용한다.
        FindIterable<Document> rs = col.find(new Document("igdb_id_", pDTO.getIgdb_id_())).projection(projection);

        Document doc = rs.first();

        if (doc == null) {

            doc = new Document();

        }


        // 조회 테스트
        String read_cnt = CmmUtil.nvl(doc.getString("read_cnt"));

        log.info("read_cnt : " + read_cnt);

        // rDTO에 값 집어넣기
        pDTO.setRead_cnt(Integer.toString(Integer.parseInt(read_cnt) + 1));

        log.info(pDTO.getRead_cnt());

        // 람다식 활용하여 nickname 필드 추가하기
        // 전체 컬랙션에 있는 데이터를 삭제하기
        rs.forEach(ddoc -> col.updateOne(ddoc, set("read_cnt", pDTO.getRead_cnt())));

        res = 1;

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".updateGameInfoReadCnt End!");

        return res;
    }

    @Override
    public GameInfoDTO getGameInfo(GameInfoDTO pDTO) {

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".getGameInfo Start!");


        // 조회 결과를 전달하기 위한 객체 생성하기
        GameInfoDTO rDTO = new GameInfoDTO();

        // MongoDB 컬렉션 지정하기
        MongoCollection<Document> col = mongodb.getCollection(colNm);

        // 조회 결과 중 출력할 컬럼들(SQL의 SELECT절과 FROM절 가운데 컬럼들과 유사함)
        Document projection = new Document();
        projection.append("sequence", "$sequence");
        projection.append("reg_id", "$reg_id");
        projection.append("reg_dt", "$reg_dt");
        projection.append("chg_id", "$chg_id");
        projection.append("chg_dt", "$chg_dt");
        projection.append("igdb_id_", "$igdb_id_");
        projection.append("read_cnt", "$read_cnt");
        projection.append("igdb_cover_id", "$igdb_cover_id");
        projection.append("steam_id", "$steam_id");
        projection.append("nintendo_id", "$nintendo_id");
        projection.append("playstation_id", "$playstation_id");
        projection.append("xbox_id", "$xbox_id");

        // MongoDB는 무조건 ObjectID가 자동생성되며, ObjectID는 사용하지 않을 때, 조회할 필요가 없음.
        projection.append("_id", 0);

        // MongoDB의 find 명령어를 통해 조회할 경우 사용함
        // 조회하는 데이터의 양이 적은 경우, find를 사용하고, 데이터양이 많은 경우 무조건 Aggregate 사용한다.
        FindIterable<Document> rs = col.find(new Document("igdb_id_", pDTO.getIgdb_id_())).projection(projection);

        Document doc = rs.first();

        if (doc == null) {

            doc = new Document();

        }

        // 조회 테스트
        String sequence = CmmUtil.nvl(doc.getString("sequence"));
        String reg_id = CmmUtil.nvl(doc.getString("reg_id"));
        String reg_dt = CmmUtil.nvl(doc.getString("reg_dt"));
        String chg_id = CmmUtil.nvl(doc.getString("chg_id"));
        String chg_dt = CmmUtil.nvl(doc.getString("chg_dt"));
        long igdb_id_ = doc.getLong("igdb_id_");
        String read_cnt = CmmUtil.nvl(doc.getString("read_cnt"));
        String igdb_cover_id = CmmUtil.nvl(doc.getString("igdb_cover_id"));
        String steam_id = CmmUtil.nvl(doc.getString("steam_id"));
        String nintendo_id = CmmUtil.nvl(doc.getString("nintendo_id"));
        String playstation_id = CmmUtil.nvl(doc.getString("playstation_id"));
        String xbox_id = CmmUtil.nvl(doc.getString("xbox_id"));

        log.info("sequence : " + sequence);
        log.info("reg_id : " + reg_id);
        log.info("reg_dt : " + reg_dt);
        log.info("chg_id : " + chg_id);
        log.info("chg_dt : " + chg_dt);
        log.info("igdb_id_ : " + igdb_id_);
        log.info("read_cnt : " + read_cnt);
        log.info("igdb_cover_id : " + igdb_cover_id);
        log.info("steam_id : " + steam_id);
        log.info("nintendo_id : " + nintendo_id);
        log.info("playstation_id : " + playstation_id);
        log.info("xbox_id : " + xbox_id);

        rDTO.setSequence(sequence);
        rDTO.setReg_id(reg_id);
        rDTO.setReg_dt(reg_dt);
        rDTO.setChg_id(chg_id);
        rDTO.setChg_dt(chg_dt);
        rDTO.setIgdb_id_(igdb_id_);
        rDTO.setRead_cnt(read_cnt);
        rDTO.setIgdb_cover_id(igdb_cover_id);
        rDTO.setSteam_id(steam_id);
        rDTO.setNintendo_id(nintendo_id);
        rDTO.setPlaystation_id(playstation_id);
        rDTO.setXbox_id(xbox_id);

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".getGameInfo End!");

        return rDTO;
    }

}

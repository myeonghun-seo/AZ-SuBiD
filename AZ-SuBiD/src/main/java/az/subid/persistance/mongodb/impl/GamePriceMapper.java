package az.subid.persistance.mongodb.impl;

import az.subid.dto.GameInfoDTO;
import az.subid.dto.GamePriceDTO;
import az.subid.persistance.mongodb.AbstractMongoDBComon;
import az.subid.persistance.mongodb.IGamePriceMapper;
import az.subid.util.CmmUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.stereotype.Component;
import proto.Game;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Slf4j
@Component("GamePriceMapper")
public class GamePriceMapper extends AbstractMongoDBComon implements IGamePriceMapper {

    // MongoDB 컬렉션 이름
    private final String colNm = new GamePriceDTO().colNm();

    @Override
    public int getGamePriceCheck(Game game) throws Exception {

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".getGamePriceCheck Start!");

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
        projection.append("platform", "$platform");
        projection.append("price", "$price");
        projection.append("sale_yn", "$sale_yn");
        projection.append("date", "$date");

        // MongoDB의 find 명령어를 통해 조회할 경우 사용함
        // 조회하는 데이터의 양이 적은 경우, find를 사용하고, 데이터양이 많은 경우 무조건 Aggregate 사용한다.
        FindIterable<Document> rs = col.find(new Document("igdb_id_", game.getId())).projection(projection);

        Document doc = rs.first();

        if (doc == null) {

            doc = new Document();

        }

        // 조회 테스트
        String igdb_id_ = CmmUtil.nvl(String.valueOf(doc.getLong("igdb_id_")));
        List<Integer> platform = doc.getList("platform", Integer.class);
        List<Double> price = doc.getList("price", Double.class);
        List<String> sale_yn = doc.getList("sale_yn", String.class);
        List<String> date = CmmUtil.nvl(doc.getList("date", String.class));

        log.info("Myprice DB id : " + igdb_id_);
//        log.info("IGDB id : " + game.getId());

        // 오늘 날짜 기록이 없다면...
        if (!date.contains(SimpleDateFormat.getDateInstance().format(new Date()))) {

            res = 1;

        }

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".getGamePriceCheck End!");

        return res;
    }

    @Override
    public int insertGamePrice(GamePriceDTO pDTO) throws Exception {

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".insertGamePrice End!");

        int res = 0;

        // MongoDB 컬렉션 지정하기
        MongoCollection<Document> col = mongodb.getCollection(colNm);

        // DTO를 Map 데이터타입으로 변경한 뒤, 변경된 Map 데이터타입을 Document로 변경하기
        col.insertOne(new Document(new ObjectMapper().convertValue(pDTO, Map.class)));

        res = 1;

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".insertGamePrice End!");

        return res;
    }

    @Override
    public int updateGamePrice(GamePriceDTO pDTO) throws Exception {

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".updateGameInfo End!");

        int res = 0;

        // MongoDB 컬렉션 지정하기
        MongoCollection<Document> col = mongodb.getCollection(colNm);

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".updateGameInfo End!");

        return res;
    }

    @Override
    public List<GamePriceDTO> getGamePriceList() throws Exception {

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".getGamePriceList Start!");

        // 조회 결과를 전달하기 위한 객체 생성하기
        List<GamePriceDTO> rList = new LinkedList<>();

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
        projection.append("platform", "$platform");
        projection.append("price", "$price");
        projection.append("sale_yn", "$sale_yn");
        projection.append("date", "$date");

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
            long igdb_id_ = (doc.getLong("igdb_id_"));
            List<Integer> platform = doc.getList("platform", Integer.class);
            List<Double> price = doc.getList("price", Double.class);
            List<String> sale_yn = doc.getList("sale_yn", String.class);
            List<String> date = doc.getList("date", String.class);

            log.info("sequence : " + sequence);
            log.info("reg_id : " + reg_id);
            log.info("reg_dt : " + reg_dt);
            log.info("chg_id : " + chg_id);
            log.info("chg_dt : " + chg_dt);
            log.info("igdb_id_ : " + igdb_id_);
            log.info("platform : " + platform);
            log.info("price : " + price);
            log.info("sale_yn : " + sale_yn);
            log.info("date : " + date);

            GamePriceDTO rDTO = new GamePriceDTO();

            rDTO.setSequence(sequence);
            rDTO.setReg_id(reg_id);
            rDTO.setReg_dt(reg_dt);
            rDTO.setChg_id(chg_id);
            rDTO.setChg_dt(chg_dt);
            rDTO.setIgdb_id_(igdb_id_);
            rDTO.setPlatform(platform);
            rDTO.setPrice(price);
            rDTO.setSale_yn(sale_yn);
            rDTO.setDate(date);

            // 레코드 결과를 List에 저장하기
            rList.add(rDTO);

        }

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".getGamePriceList End!");

        return rList;
    }

    @Override
    public GamePriceDTO getGamePrice(GameInfoDTO pDTO) {

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".getGamePrice Start!");


        // 조회 결과를 전달하기 위한 객체 생성하기
        GamePriceDTO rDTO = new GamePriceDTO();

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
        projection.append("platform", "$platform");
        projection.append("price", "$price");
        projection.append("sale_yn", "$sale_yn");
        projection.append("date", "$date");

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
        long igdb_id_ = (doc.getLong("igdb_id_"));
        List<Integer> platform = doc.getList("platform", Integer.class);
        List<Double> price = doc.getList("price", Double.class);
        List<String> sale_yn = doc.getList("sale_yn", String.class);
        List<String> date = doc.getList("date", String.class);

        log.info("sequence : " + sequence);
        log.info("reg_id : " + reg_id);
        log.info("reg_dt : " + reg_dt);
        log.info("chg_id : " + chg_id);
        log.info("chg_dt : " + chg_dt);
        log.info("igdb_id_ : " + igdb_id_);
        log.info("platform : " + platform);
        log.info("price : " + price);
        log.info("sale_yn : " + sale_yn);
        log.info("date : " + date);

        rDTO.setSequence(sequence);
        rDTO.setReg_id(reg_id);
        rDTO.setReg_dt(reg_dt);
        rDTO.setChg_id(chg_id);
        rDTO.setChg_dt(chg_dt);
        rDTO.setIgdb_id_(igdb_id_);
        rDTO.setPlatform(platform);
        rDTO.setPrice(price);
        rDTO.setSale_yn(sale_yn);
        rDTO.setDate(date);

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".getGamePrice End!");

        return rDTO;
    }

}

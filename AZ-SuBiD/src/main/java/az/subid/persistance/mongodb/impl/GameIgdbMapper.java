package az.subid.persistance.mongodb.impl;

import az.subid.dto.GameIgdbDTO;
import az.subid.dto.GameInfoDTO;
import az.subid.persistance.mongodb.AbstractMongoDBComon;
import az.subid.persistance.mongodb.IGameIgdbMapper;
import az.subid.util.CmmUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.stereotype.Component;
import proto.Game;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Slf4j
@Component("GameIgdbMapper")
public class GameIgdbMapper extends AbstractMongoDBComon implements IGameIgdbMapper {

    // MongoDB 컬렉션 이름
    private final String colNm = new GameIgdbDTO().colNm();

    @Override
    public int getGameIgdbCheck(Game game) throws Exception {

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".getGameIgdbCheck Start!");

        // 컬렉션이 없다면 생성하기
        if(!mongodb.collectionExists(colNm)) {

            // 컬렉션 생성
            super.createCollection(colNm);
            // 인덱스 생성
//            mongodb.getCollection(colNm).createIndex(Indexes.ascending("id_"));

        }

        int res = 0;

        // MongoDB 컬렉션 지정하기
        MongoCollection<Document> col = mongodb.getCollection(colNm);

        // 조회 결과 중 출력할 컬럼들(SQL의 SELECT절과 FROM절 가운데 컬럼들과 유사함)
        Document projection = new Document();

        projection.append("id_", "$id_");

        // MongoDB의 find 명령어를 통해 조회할 경우 사용함
        // 조회하는 데이터의 양이 적은 경우, find를 사용하고, 데이터양이 많은 경우 무조건 Aggregate 사용한다.
        FindIterable<Document> rs = col.find(new Document("id_", game.getId())).projection(projection);

        Document doc = rs.first();

        if (doc == null) {

            doc = new Document();

        }

        // 조회 테스트
        String id_ = CmmUtil.nvl(String.valueOf(doc.getLong("id_")));

        log.info("Mygame DB id : " + id_);
//        log.info("IGDB id : " + game.getId());

        // 같은 값이 없다면...
        if (!id_.equals(Long.toString(game.getId()))) {
            res = 1;
        }

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".getGameIgdbCheck End!");

        return res;
    }

    @Override
    public int insertGameIgdb(Game game) throws Exception{

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".insertGameIgdb End!");

        int res = 0;

        GameIgdbDTO rDTO = new GameIgdbDTO();
        rDTO.setGameIGDB(game);

        // MongoDB 컬렉션 지정하기
        MongoCollection<Document> col = mongodb.getCollection(colNm);

        // DTO를 Map 데이터타입으로 변경한 뒤, 변경된 Map 데이터타입을 Document로 변경하기
        col.insertOne(new Document(new ObjectMapper().convertValue(rDTO, Map.class)));

        res = 1;

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".insertGameIgdb End!");

        return res;
    }

    @Override
    public int updateGameIgdb(Game game) throws Exception{

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".updateGameIgdb End!");

        int res = 0;

        // MongoDB 컬렉션 지정하기
        MongoCollection<Document> col = mongodb.getCollection(colNm);

        GameIgdbDTO rDTO = new GameIgdbDTO();
        rDTO.setGameIGDB(game);
        Document test = new Document(new ObjectMapper().convertValue(rDTO, Map.class));

        // 조회 테스트
//        log.info(String.valueOf(test.get("totalRating_")));
//        log.info(String.valueOf(game.getAggregatedRating() == rDTO.getAggregatedRating_()));

        res = 1;

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".updateGameIgdb End!");

        return res;
    }

    @Override
    public List<GameIgdbDTO> getGameIgdbList() throws Exception {

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".getGameIgdbList Start!");

        // 조회 결과를 전달하기 위한 객체 생성하기
        List<GameIgdbDTO> rList = new LinkedList<>();

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

        // 1 ~ 5
        projection.append("id_", "$id_");
        projection.append("ageRatings_", "$ageRatings_");
        projection.append("aggregatedRating_", "$aggregatedRating_");
        projection.append("aggregatedRatingCount_", "$aggregatedRatingCount_");
        projection.append("alternativeNames_", "$alternativeNames_");

        // 6 ~ 10
        projection.append("artworks_", "$artworks_");
        projection.append("bundles_", "$bundles_");
        projection.append("category_", "$category_");
        projection.append("collection_", "$collection_");
        projection.append("cover_", "$cover_");

        // 11 ~ 15
        projection.append("createdAt_", "$createdAt_");
        projection.append("dlcs_", "$dlcs_");
        projection.append("expansions_", "$expansions_");
        projection.append("externalGames_", "$externalGames_");
        projection.append("firstReleaseDate_", "$firstReleaseDate_");

        // 16 ~ 20
        projection.append("follows_", "$follows_");
        projection.append("franchise_", "$franchise_");
        projection.append("franchises_", "$franchises_");
        projection.append("gameEngines_", "$gameEngines_");
        projection.append("gameModes_", "$gameModes_");

        // 21 ~ 25
        projection.append("genres_", "$genres_");
        projection.append("hypes_", "$hypes_");
        projection.append("involvedCompanies_", "$involvedCompanies_");
        projection.append("keywords_", "$keywords_");
        projection.append("multiplayerModes_", "$multiplayerModes_");

        // 26 ~ 30
        projection.append("name_", "$name_");
        projection.append("parentGame_", "$parentGame_");
        projection.append("platforms_", "$platforms_");
        projection.append("playerPerspectives_", "$playerPerspectives_");
        projection.append("rating_", "$rating_");

        // 31 ~ 35
        projection.append("ratingCount_", "$ratingCount_");
        projection.append("releaseDates_", "$releaseDates_");
        projection.append("screenshots_", "$screenshots_");
        projection.append("similarGames_", "$similarGames_");
        projection.append("slug_", "$slug_");

        // 36 ~ 40
        projection.append("standaloneExpansions_", "$standaloneExpansions_");
        projection.append("status_", "$status_");
        projection.append("storyline_", "$storyline_");
        projection.append("summary_", "$summary_");
        projection.append("tags_", "$tags_"); // 40 - 1
        projection.append("tagsMemoizedSerializedSize", "$tagsMemoizedSerializedSize"); // 40 -2

        // 41 ~ 45
        projection.append("themes_", "$themes_");
        projection.append("totalRating_", "$totalRating_");
        projection.append("totalRatingCount_", "$totalRatingCount_");
        projection.append("updatedAt_", "$updatedAt_");
        projection.append("url_", "$url_");

        // 46 ~ 50
        projection.append("versionParent_", "$versionParent_");
        projection.append("versionTitle_", "$versionTitle_");
        projection.append("videos_", "$videos_");
        projection.append("websites_", "$websites_");
        projection.append("checksum_", "$checksum_");

        // 51 ~ 55
        projection.append("remakes_", "$remakes_");
        projection.append("remasters_", "$remasters_");
        projection.append("expandedGames_", "$expandedGames_");
        projection.append("ports_", "$ports_");
        projection.append("forks_", "$forks_");

        // MongoDB는 무조건 ObjectID가 자동생성되며, ObjectID는 사용하지 않을 때, 조회할 필요가 없음.
        projection.append("_id", 0);

        // MongoDB의 find 명령어를 통해 조회할 경우 사용함
        // 조회하는 데이터의 양이 적은 경우, find를 사용하고, 데이터양이 많은 경우 무조건 Aggregate 사용한다.
        FindIterable<Document> rs = col.find(new Document()).projection(projection);

        for (Document doc : rs) {

            if (doc == null) {

                doc = new Document();

            }

            log.info(doc.getString("name_"));

            // 조회 테스트
            // 1 ~ 5
            long id_ = doc.getLong("id_");
            List<String> AgeRatings_ = doc.getList("AgeRatings_", String.class);
            double aggregatedRating_ = doc.getDouble("aggregatedRating_");
            int aggregatedRatingCount_ = doc.getInteger("aggregatedRatingCount_");
            List<String> alternativeNames_ = doc.getList("alternativeNames_", String.class);


            // 6 ~ 10
            List<String> artworks_ = doc.getList("artworks_", String.class);
            List<String> bundles_ = doc.getList("bundles_", String.class);
            int category_ = doc.getInteger("category_");
            String collection_ = CmmUtil.nvl(doc.getString("collection_"));
            String cover_ = CmmUtil.nvl(doc.getString("cover_"));

            // 11 ~ 15
            String createdAt_ = CmmUtil.nvl(doc.getString("createdAt_"));
            List<String> dlcs_ = doc.getList("dlcs_", String.class);
            List<String> expansions_ = doc.getList("expansions_", String.class);
            List<String> externalGames_ = doc.getList("externalGames_", String.class);
            String firstReleaseDate_ = CmmUtil.nvl(doc.getString("firstReleaseDate_"));

            // 16 ~ 20
            int follows_ = doc.getInteger("follows_");
            String franchise_ = CmmUtil.nvl(doc.getString("franchise_"));
            List<String> franchises_ = doc.getList("franchises_", String.class);
            List<String> gameEngines_ = doc.getList("gameEngines_", String.class);
            List<String> gameModes_ = doc.getList("gameModes_", String.class);

            // 21 ~ 25
            List<String> genres_ = doc.getList("genres_", String.class);
            int hypes_ = doc.getInteger("hypes_");
            List<String> involvedCompanies_ = doc.getList("involvedCompanies_", String.class);
            List<String> keywords_ = doc.getList("keywords_", String.class);
            List<String> multiplayerModes_ = doc.getList("multiplayerModes_", String.class);

            // 26 ~ 30
            String name_ = CmmUtil.nvl(doc.getString("name_"));
            String parentGame_ = CmmUtil.nvl(doc.getString("parentGame_"));
            List<String> platforms_ = doc.getList("platforms_", String.class);
            List<String> playerPerspectives_ = doc.getList("playerPerspectives_", String.class);
            double rating_ = doc.getDouble("rating_");

            // 31 ~ 35
            int ratingCount_ = doc.getInteger("ratingCount_");
            List<String> releaseDates_ = doc.getList("releaseDates_", String.class);
            List<String> screenshots_ = doc.getList("screenshots_", String.class);
            List<String> similarGames_ = doc.getList("similarGames_", String.class);
            String slug_ = CmmUtil.nvl(doc.getString("slug_"));

            // 36 ~ 40
            List<String> standaloneExpansions_ = doc.getList("standaloneExpansions_", String.class);
            int status_ = doc.getInteger("status_");
            String storyline_ = CmmUtil.nvl(doc.getString("storyline_"));
            String summary_ = CmmUtil.nvl(doc.getString("summary_"));
            List<Integer> tags_ = doc.getList("tags_", Integer.class);
            int tagsMemoizedSerializedSize = doc.getInteger("tagsMemoizedSerializedSize");

            // 41 ~ 45
            List<String> themes_ = doc.getList("themes_", String.class);
            double totalRating_ = doc.getDouble("totalRating_");
            int totalRatingCount_ = doc.getInteger("totalRatingCount_");
            String updatedAt_ = CmmUtil.nvl(doc.getString("updatedAt_"));
            String url_ = CmmUtil.nvl(doc.getString("url_"));

            // 46 ~ 50
            String versionParent_ = CmmUtil.nvl(doc.getString("versionParent_"));
            String versionTitle_ = CmmUtil.nvl(doc.getString("versionTitle_"));
            List<String> videos_ = doc.getList("videos_", String.class);
            List<String> websites_ = doc.getList("websites_", String.class);
            String checksum_ = CmmUtil.nvl(doc.getString("checksum_"));
            
            // 51 ~ 55
            List<String> remakes_ = doc.getList("remakes_", String.class);
            List<String> remasters_ = doc.getList("remasters_", String.class);
            List<String> expandedGames_ = doc.getList("expandedGames_", String.class);
            List<String> ports_ = doc.getList("ports_", String.class);
            List<String> forks_ = doc.getList("forks_", String.class);

            // 너무 많아서 로그는 중요한 것만 출력
            log.info("id_ : " + id_);
            log.info("name_ : " + name_);
            log.info("platforms_ : " + platforms_);

            GameIgdbDTO rDTO = new GameIgdbDTO();

            // 1 ~ 5
            rDTO.setId_(id_);
            rDTO.setAgeRatings_(AgeRatings_);
            rDTO.setAggregatedRating_(aggregatedRating_);
            rDTO.setAggregatedRatingCount_(aggregatedRatingCount_);
            rDTO.setAlternativeNames_(alternativeNames_);

            // 6 ~ 10
            rDTO.setArtworks_(artworks_);
            rDTO.setBundles_(bundles_);
            rDTO.setCategory_(category_);
            rDTO.setCollection_(collection_);
            rDTO.setCover_(cover_);

            // 11 ~ 15
            rDTO.setCreatedAt_(createdAt_);
            rDTO.setDlcs_(dlcs_);
            rDTO.setExpandedGames_(expansions_);
            rDTO.setExternalGames_(externalGames_);
            rDTO.setFirstReleaseDate_(firstReleaseDate_);

            // 16 ~ 20
            rDTO.setFollows_(follows_);
            rDTO.setFranchise_(franchise_);
            rDTO.setFranchises_(franchises_);
            rDTO.setGameEngines_(gameEngines_);
            rDTO.setGameModes_(gameModes_);

            // 21 ~ 25
            rDTO.setGenres_(genres_);
            rDTO.setHypes_(hypes_);
            rDTO.setInvolvedCompanies_(involvedCompanies_);
            rDTO.setKeywords_(keywords_);
            rDTO.setMultiplayerModes_(multiplayerModes_);

            // 26 ~ 30
            rDTO.setName_(name_);
            rDTO.setParentGame_(parentGame_);
            rDTO.setPlatforms_(platforms_);
            rDTO.setPlayerPerspectives_(playerPerspectives_);
            rDTO.setRating_(rating_);

            // 31 ~ 35
            rDTO.setRatingCount_(ratingCount_);
            rDTO.setReleaseDates_(releaseDates_);
            rDTO.setScreenshots_(screenshots_);
            rDTO.setScreenshots_(similarGames_);
            rDTO.setSlug_(slug_);

            // 36 ~ 40
            rDTO.setStandaloneExpansions_(standaloneExpansions_);
            rDTO.setStatus_(status_);
            rDTO.setStoryline_(storyline_);
            rDTO.setSummary_(summary_);
            rDTO.setTags_(tags_); // 40 - 1
            rDTO.setTagsMemoizedSerializedSize(tagsMemoizedSerializedSize); // 40 - 2

            // 41 ~ 45
            rDTO.setThemes_(themes_);
            rDTO.setTotalRating_(totalRating_);
            rDTO.setTotalRatingCount_(totalRatingCount_);
            rDTO.setUpdatedAt_(updatedAt_);
            rDTO.setUrl_(url_);

            // 46 ~ 50
            rDTO.setVersionParent_(versionParent_);
            rDTO.setVersionTitle_(versionTitle_);
            rDTO.setVideos_(videos_);
            rDTO.setWebsites_(websites_);
            rDTO.setChecksum_(checksum_);

            // 51 ~ 55
            rDTO.setRemakes_(remakes_);
            rDTO.setRemasters_(remasters_);
            rDTO.setExpandedGames_(expandedGames_);
            rDTO.setPorts_(ports_);
            rDTO.setForks_(forks_);

            // 레코드 결과를 List에 저장하기
            rList.add(rDTO);

        }

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".getGameIgdbList End!");

        return rList;
    }

    @Override
    public GameIgdbDTO getGameIgdb(GameInfoDTO pDTO) {

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".getGameInfo Start!");


        // 조회 결과를 전달하기 위한 객체 생성하기
        GameIgdbDTO rDTO = new GameIgdbDTO();

        // MongoDB 컬렉션 지정하기
        MongoCollection<Document> col = mongodb.getCollection(colNm);

        // 조회 결과 중 출력할 컬럼들(SQL의 SELECT절과 FROM절 가운데 컬럼들과 유사함)
        Document projection = new Document();

        // 1 ~ 5
        projection.append("id_", "$id_");
        projection.append("ageRatings_", "$ageRatings_");
        projection.append("aggregatedRating_", "$aggregatedRating_");
        projection.append("aggregatedRatingCount_", "$aggregatedRatingCount_");
        projection.append("alternativeNames_", "$alternativeNames_");

        // 6 ~ 10
        projection.append("artworks_", "$artworks_");
        projection.append("bundles_", "$bundles_");
        projection.append("category_", "$category_");
        projection.append("collection_", "$collection_");
        projection.append("cover_", "$cover_");

        // 11 ~ 15
        projection.append("createdAt_", "$createdAt_");
        projection.append("dlcs_", "$dlcs_");
        projection.append("expansions_", "$expansions_");
        projection.append("externalGames_", "$externalGames_");
        projection.append("firstReleaseDate_", "$firstReleaseDate_");

        // 16 ~ 20
        projection.append("follows_", "$follows_");
        projection.append("franchise_", "$franchise_");
        projection.append("franchises_", "$franchises_");
        projection.append("gameEngines_", "$gameEngines_");
        projection.append("gameModes_", "$gameModes_");

        // 21 ~ 25
        projection.append("genres_", "$genres_");
        projection.append("hypes_", "$hypes_");
        projection.append("involvedCompanies_", "$involvedCompanies_");
        projection.append("keywords_", "$keywords_");
        projection.append("multiplayerModes_", "$multiplayerModes_");

        // 26 ~ 30
        projection.append("name_", "$name_");
        projection.append("parentGame_", "$parentGame_");
        projection.append("platforms_", "$platforms_");
        projection.append("playerPerspectives_", "$playerPerspectives_");
        projection.append("rating_", "$rating_");

        // 31 ~ 35
        projection.append("ratingCount_", "$ratingCount_");
        projection.append("releaseDates_", "$releaseDates_");
        projection.append("screenshots_", "$screenshots_");
        projection.append("similarGames_", "$similarGames_");
        projection.append("slug_", "$slug_");

        // 36 ~ 40
        projection.append("standaloneExpansions_", "$standaloneExpansions_");
        projection.append("status_", "$status_");
        projection.append("storyline_", "$storyline_");
        projection.append("summary_", "$summary_");
        projection.append("tags_", "$tags_"); // 40 - 1
        projection.append("tagsMemoizedSerializedSize", "$tagsMemoizedSerializedSize"); // 40 -2

        // 41 ~ 45
        projection.append("themes_", "$themes_");
        projection.append("totalRating_", "$totalRating_");
        projection.append("totalRatingCount_", "$totalRatingCount_");
        projection.append("updatedAt_", "$updatedAt_");
        projection.append("url_", "$url_");

        // 46 ~ 50
        projection.append("versionParent_", "$versionParent_");
        projection.append("versionTitle_", "$versionTitle_");
        projection.append("videos_", "$videos_");
        projection.append("websites_", "$websites_");
        projection.append("checksum_", "$checksum_");

        // 51 ~ 55
        projection.append("remakes_", "$remakes_");
        projection.append("remasters_", "$remasters_");
        projection.append("expandedGames_", "$expandedGames_");
        projection.append("ports_", "$ports_");
        projection.append("forks_", "$forks_");

        // MongoDB는 무조건 ObjectID가 자동생성되며, ObjectID는 사용하지 않을 때, 조회할 필요가 없음.
        projection.append("_id", 0);

        // MongoDB의 find 명령어를 통해 조회할 경우 사용함
        // 조회하는 데이터의 양이 적은 경우, find를 사용하고, 데이터양이 많은 경우 무조건 Aggregate 사용한다.
        FindIterable<Document> rs = col.find(new Document("id_", pDTO.getIgdb_id_())).projection(projection);

        Document doc = rs.first();

        if (doc == null) {

            doc = new Document();

        }

        // 조회 테스트
        // 1 ~ 5
        long id_ = doc.getLong("id_");
        List<String> AgeRatings_ = doc.getList("AgeRatings_", String.class);
        double aggregatedRating_ = doc.getDouble("aggregatedRating_");
        int aggregatedRatingCount_ = doc.getInteger("aggregatedRatingCount_");
        List<String> alternativeNames_ = doc.getList("alternativeNames_", String.class);


        // 6 ~ 10
        List<String> artworks_ = doc.getList("artworks_", String.class);
        List<String> bundles_ = doc.getList("bundles_", String.class);
        int category_ = doc.getInteger("category_");
        String collection_ = CmmUtil.nvl(doc.getString("collection_"));
        String cover_ = CmmUtil.nvl(doc.getString("cover_"));

        // 11 ~ 15
        String createdAt_ = CmmUtil.nvl(doc.getString("createdAt_"));
        List<String> dlcs_ = doc.getList("dlcs_", String.class);
        List<String> expansions_ = doc.getList("expansions_", String.class);
        List<String> externalGames_ = doc.getList("externalGames_", String.class);
        String firstReleaseDate_ = CmmUtil.nvl(doc.getString("firstReleaseDate_"));

        // 16 ~ 20
        int follows_ = doc.getInteger("follows_");
        String franchise_ = CmmUtil.nvl(doc.getString("franchise_"));
        List<String> franchises_ = doc.getList("franchises_", String.class);
        List<String> gameEngines_ = doc.getList("gameEngines_", String.class);
        List<String> gameModes_ = doc.getList("gameModes_", String.class);

        // 21 ~ 25
        List<String> genres_ = doc.getList("genres_", String.class);
        int hypes_ = doc.getInteger("hypes_");
        List<String> involvedCompanies_ = doc.getList("involvedCompanies_", String.class);
        List<String> keywords_ = doc.getList("keywords_", String.class);
        List<String> multiplayerModes_ = doc.getList("multiplayerModes_", String.class);

        // 26 ~ 30
        String name_ = CmmUtil.nvl(doc.getString("name_"));
        String parentGame_ = CmmUtil.nvl(doc.getString("parentGame_"));
        List<String> platforms_ = doc.getList("platforms_", String.class);
        List<String> playerPerspectives_ = doc.getList("playerPerspectives_", String.class);
        double rating_ = doc.getDouble("rating_");

        // 31 ~ 35
        int ratingCount_ = doc.getInteger("ratingCount_");
        List<String> releaseDates_ = doc.getList("releaseDates_", String.class);
        List<String> screenshots_ = doc.getList("screenshots_", String.class);
        List<String> similarGames_ = doc.getList("similarGames_", String.class);
        String slug_ = CmmUtil.nvl(doc.getString("slug_"));

        // 36 ~ 40
        List<String> standaloneExpansions_ = doc.getList("standaloneExpansions_", String.class);
        int status_ = doc.getInteger("status_");
        String storyline_ = CmmUtil.nvl(doc.getString("storyline_"));
        String summary_ = CmmUtil.nvl(doc.getString("summary_"));
        List<Integer> tags_ = doc.getList("tags_", Integer.class);
        int tagsMemoizedSerializedSize = doc.getInteger("tagsMemoizedSerializedSize");

        // 41 ~ 45
        List<String> themes_ = doc.getList("themes_", String.class);
        double totalRating_ = doc.getDouble("totalRating_");
        int totalRatingCount_ = doc.getInteger("totalRatingCount_");
        String updatedAt_ = CmmUtil.nvl(doc.getString("updatedAt_"));
        String url_ = CmmUtil.nvl(doc.getString("url_"));

        // 46 ~ 50
        String versionParent_ = CmmUtil.nvl(doc.getString("versionParent_"));
        String versionTitle_ = CmmUtil.nvl(doc.getString("versionTitle_"));
        List<String> videos_ = doc.getList("videos_", String.class);
        List<String> websites_ = doc.getList("websites_", String.class);
        String checksum_ = CmmUtil.nvl(doc.getString("checksum_"));

        // 51 ~ 55
        List<String> remakes_ = doc.getList("remakes_", String.class);
        List<String> remasters_ = doc.getList("remasters_", String.class);
        List<String> expandedGames_ = doc.getList("expandedGames_", String.class);
        List<String> ports_ = doc.getList("ports_", String.class);
        List<String> forks_ = doc.getList("forks_", String.class);

        // 너무 많아서 로그는 중요한 것만 출력
        log.info("id_ : " + id_);
        log.info("name_ : " + name_);
        log.info("platforms_ : " + platforms_);

        // 1 ~ 5
        rDTO.setId_(id_);
        rDTO.setAgeRatings_(AgeRatings_);
        rDTO.setAggregatedRating_(aggregatedRating_);
        rDTO.setAggregatedRatingCount_(aggregatedRatingCount_);
        rDTO.setAlternativeNames_(alternativeNames_);

        // 6 ~ 10
        rDTO.setArtworks_(artworks_);
        rDTO.setBundles_(bundles_);
        rDTO.setCategory_(category_);
        rDTO.setCollection_(collection_);
        rDTO.setCover_(cover_);

        // 11 ~ 15
        rDTO.setCreatedAt_(createdAt_);
        rDTO.setDlcs_(dlcs_);
        rDTO.setExpandedGames_(expansions_);
        rDTO.setExternalGames_(externalGames_);
        rDTO.setFirstReleaseDate_(firstReleaseDate_);

        // 16 ~ 20
        rDTO.setFollows_(follows_);
        rDTO.setFranchise_(franchise_);
        rDTO.setFranchises_(franchises_);
        rDTO.setGameEngines_(gameEngines_);
        rDTO.setGameModes_(gameModes_);

        // 21 ~ 25
        rDTO.setGenres_(genres_);
        rDTO.setHypes_(hypes_);
        rDTO.setInvolvedCompanies_(involvedCompanies_);
        rDTO.setKeywords_(keywords_);
        rDTO.setMultiplayerModes_(multiplayerModes_);

        // 26 ~ 30
        rDTO.setName_(name_);
        rDTO.setParentGame_(parentGame_);
        rDTO.setPlatforms_(platforms_);
        rDTO.setPlayerPerspectives_(playerPerspectives_);
        rDTO.setRating_(rating_);

        // 31 ~ 35
        rDTO.setRatingCount_(ratingCount_);
        rDTO.setReleaseDates_(releaseDates_);
        rDTO.setScreenshots_(screenshots_);
        rDTO.setScreenshots_(similarGames_);
        rDTO.setSlug_(slug_);

        // 36 ~ 40
        rDTO.setStandaloneExpansions_(standaloneExpansions_);
        rDTO.setStatus_(status_);
        rDTO.setStoryline_(storyline_);
        rDTO.setSummary_(summary_);
        rDTO.setTags_(tags_); // 40 - 1
        rDTO.setTagsMemoizedSerializedSize(tagsMemoizedSerializedSize); // 40 - 2

        // 41 ~ 45
        rDTO.setThemes_(themes_);
        rDTO.setTotalRating_(totalRating_);
        rDTO.setTotalRatingCount_(totalRatingCount_);
        rDTO.setUpdatedAt_(updatedAt_);
        rDTO.setUrl_(url_);

        // 46 ~ 50
        rDTO.setVersionParent_(versionParent_);
        rDTO.setVersionTitle_(versionTitle_);
        rDTO.setVideos_(videos_);
        rDTO.setWebsites_(websites_);
        rDTO.setChecksum_(checksum_);

        // 51 ~ 55
        rDTO.setRemakes_(remakes_);
        rDTO.setRemasters_(remasters_);
        rDTO.setExpandedGames_(expandedGames_);
        rDTO.setPorts_(ports_);
        rDTO.setForks_(forks_);

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".getGameInfo End!");

        return rDTO;
    }

}

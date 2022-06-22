package az.subid.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.base.Functions;
import com.google.common.collect.Lists;
import lombok.Data;
import lombok.EqualsAndHashCode;
import proto.Game;

import java.util.List;

/*
 * 게임 IGDB 정보 DTO
 * 게임 IGDB의 정보를 API를 통해 입력, DTO를 통해 열람이 가능합니다.
 */

/**
 * lombok은 코딩을 줄이기 위해 @어노테이션을 통한 자동 코드 완성기능임
 * 값이 0인 것을 방지함.
 * @ Data => getter, setter 함수를 작성하지 않았지만, 자동 생성
 */
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Data
@EqualsAndHashCode(callSuper=false)
public class GameIgdbDTO extends AAAdefaultDTO{ // 기본 DTO 상속 안함

//    private static final long serialVersionUID = 0L;
//    public static final int ID_FIELD_NUMBER = 1;
    private Long id_;
//    public static final int AGE_RATINGS_FIELD_NUMBER = 2;
    private List<String> ageRatings_;
//    public static final int AGGREGATED_RATING_FIELD_NUMBER = 3;
    private Double aggregatedRating_;
//    public static final int AGGREGATED_RATING_COUNT_FIELD_NUMBER = 4;
    private Integer aggregatedRatingCount_;
//    public static final int ALTERNATIVE_NAMES_FIELD_NUMBER = 5;
    private List<String> alternativeNames_;
//    public static final int ARTWORKS_FIELD_NUMBER = 6;
    private List<String> artworks_;
//    public static final int BUNDLES_FIELD_NUMBER = 7;
    private List<String> bundles_;
//    public static final int CATEGORY_FIELD_NUMBER = 8;
    private Integer category_;
//    public static final int COLLECTION_FIELD_NUMBER = 9;
    private String collection_;
//    public static final int COVER_FIELD_NUMBER = 10;
    private String cover_;
//    public static final int CREATED_AT_FIELD_NUMBER = 11;
    private String createdAt_;
//    public static final int DLCS_FIELD_NUMBER = 12;
    private List<String> dlcs_;
//    public static final int EXPANSIONS_FIELD_NUMBER = 13;
    private List<String> expansions_;
//    public static final int EXTERNAL_GAMES_FIELD_NUMBER = 14;
    private List<String> externalGames_;
//    public static final int FIRST_RELEASE_DATE_FIELD_NUMBER = 15;
    private String firstReleaseDate_;
//    public static final int FOLLOWS_FIELD_NUMBER = 16;
    private Integer follows_;
//    public static final int FRANCHISE_FIELD_NUMBER = 17;
    private String franchise_;
//    public static final int FRANCHISES_FIELD_NUMBER = 18;
    private List<String> franchises_;
//    public static final int GAME_ENGINES_FIELD_NUMBER = 19;
    private List<String> gameEngines_;
//    public static final int GAME_MODES_FIELD_NUMBER = 20;
    private List<String> gameModes_;
//    public static final int GENRES_FIELD_NUMBER = 21;
    private List<String> genres_;
//    public static final int HYPES_FIELD_NUMBER = 22;
    private Integer hypes_;
//    public static final int INVOLVED_COMPANIES_FIELD_NUMBER = 23;
    private List<String> involvedCompanies_;
//    public static final int KEYWORDS_FIELD_NUMBER = 24;
    private List<String> keywords_;
//    public static final int MULTIPLAYER_MODES_FIELD_NUMBER = 25;
    private List<String> multiplayerModes_;
//    public static final int NAME_FIELD_NUMBER = 26;
    private volatile String name_;
//    public static final int PARENT_GAME_FIELD_NUMBER = 27;
    private String parentGame_;
//    public static final int PLATFORMS_FIELD_NUMBER = 28;
    private List<String> platforms_;
//    public static final int PLAYER_PERSPECTIVES_FIELD_NUMBER = 29;
    private List<String> playerPerspectives_;
//    public static final int RATING_FIELD_NUMBER = 30;
    private Double rating_;
//    public static final int RATING_COUNT_FIELD_NUMBER = 31;
    private Integer ratingCount_;
//    public static final int RELEASE_DATES_FIELD_NUMBER = 32;
    private List<String> releaseDates_;
//    public static final int SCREENSHOTS_FIELD_NUMBER = 33;
    private List<String> screenshots_;
//    public static final int SIMILAR_GAMES_FIELD_NUMBER = 34;
    private List<String> similarGames_;
//    public static final int SLUG_FIELD_NUMBER = 35;
    private volatile String slug_;
//    public static final int STANDALONE_EXPANSIONS_FIELD_NUMBER = 36;
    private List<String> standaloneExpansions_;
//    public static final int STATUS_FIELD_NUMBER = 37;
    private Integer status_;
//    public static final int STORYLINE_FIELD_NUMBER = 38;
    private volatile String storyline_;
//    public static final int SUMMARY_FIELD_NUMBER = 39;
    private volatile String summary_;
//    public static final int TAGS_FIELD_NUMBER = 40;
    private List<Integer> tags_;
    private Integer tagsMemoizedSerializedSize;
//    public static final int THEMES_FIELD_NUMBER = 41;
    private List<String> themes_;
//    public static final int TOTAL_RATING_FIELD_NUMBER = 42;
    private Double totalRating_;
//    public static final int TOTAL_RATING_COUNT_FIELD_NUMBER = 43;
    private Integer totalRatingCount_;
//    public static final int UPDATED_AT_FIELD_NUMBER = 44;
    private String updatedAt_;
//    public static final int URL_FIELD_NUMBER = 45;
    private volatile String url_;
//    public static final int VERSION_PARENT_FIELD_NUMBER = 46;
    private String versionParent_;
//    public static final int VERSION_TITLE_FIELD_NUMBER = 47;
    private volatile String versionTitle_;
//    public static final int VIDEOS_FIELD_NUMBER = 48;
    private List<String> videos_;
//    public static final int WEBSITES_FIELD_NUMBER = 49;
    private List<String> websites_;
//    public static final int CHECKSUM_FIELD_NUMBER = 50;
    private volatile String checksum_;
//    public static final int REMAKES_FIELD_NUMBER = 51;
    private List<String> remakes_;
//    public static final int REMASTERS_FIELD_NUMBER = 52;
    private List<String> remasters_;
//    public static final int EXPANDED_GAMES_FIELD_NUMBER = 53;
    private List<String> expandedGames_;
//    public static final int PORTS_FIELD_NUMBER = 54;
    private List<String> ports_;
//    public static final int FORKS_FIELD_NUMBER = 55;
    private List<String> forks_;

    // 한번에 옮기는 Game
    public void setGameIGDB(Game game) {

        // 1 ~ 5
        id_ = game.getId();
        ageRatings_ = Lists.transform(game.getAgeRatingsList(), Functions.toStringFunction());
        aggregatedRating_ = game.getAggregatedRating();
        aggregatedRatingCount_ = game.getAggregatedRatingCount();
        alternativeNames_ = Lists.transform(game.getAlternativeNamesList(), Functions.toStringFunction());

        // 6 ~ 10
        artworks_ = Lists.transform(game.getArtworksList(), Functions.toStringFunction());;
        bundles_ = Lists.transform(game.getBundlesList(), Functions.toStringFunction());
        category_ = game.getCategoryValue();
        collection_ = game.getCollection().toString();
        cover_ = game.getCover().toString();

        // 11 ~ 15
        createdAt_ = game.getCreatedAt().toString();
        dlcs_ = Lists.transform(game.getDlcsList(), Functions.toStringFunction());
        expansions_ = Lists.transform(game.getExpansionsList(), Functions.toStringFunction());
        externalGames_ = Lists.transform(game.getExternalGamesList(), Functions.toStringFunction());
        firstReleaseDate_ = game.getFirstReleaseDate().toString();

        // 16 ~ 20
        follows_ = game.getFollows();
        franchise_ = game.getFranchise().toString();
        franchises_ = Lists.transform(game.getFranchisesList(), Functions.toStringFunction());
        gameEngines_ = Lists.transform(game.getGameEnginesList(), Functions.toStringFunction());
        gameModes_ = Lists.transform(game.getGameModesList(), Functions.toStringFunction());

        // 21 ~ 25
        genres_ = Lists.transform(game.getGenresList(), Functions.toStringFunction());
        hypes_ = game.getHypes();
        involvedCompanies_ = Lists.transform(game.getInvolvedCompaniesList(), Functions.toStringFunction());
        keywords_ = Lists.transform(game.getKeywordsList(), Functions.toStringFunction());
        multiplayerModes_ = Lists.transform(game.getMultiplayerModesList(), Functions.toStringFunction());

        // 26 ~ 30
        name_ = game.getName();
        parentGame_ = game.getParentGame().toString();
        platforms_ = Lists.transform(game.getPlatformsList(), Functions.toStringFunction());
        playerPerspectives_ = Lists.transform(game.getPlayerPerspectivesList(), Functions.toStringFunction());
        rating_ = game.getRating();

        // 31 ~ 35
        ratingCount_ = game.getRatingCount();
        releaseDates_ = Lists.transform(game.getReleaseDatesList(), Functions.toStringFunction());
        screenshots_ = Lists.transform(game.getScreenshotsList(), Functions.toStringFunction());
        similarGames_ = Lists.transform(game.getSimilarGamesList(), Functions.toStringFunction());
        slug_ = game.getSlug();

        // 36 ~ 40
        standaloneExpansions_ = Lists.transform(game.getStandaloneExpansionsList(), Functions.toStringFunction());
        status_ = game.getStatusValue();
        storyline_ = game.getStoryline();
        summary_ = game.getSummary();
        tags_ = game.getTagsList(); // 40-1
        tagsMemoizedSerializedSize = game.getTagsCount(); // 40-2

        // 41 ~ 45
        themes_ = Lists.transform(game.getThemesList(), Functions.toStringFunction());
        totalRating_ = game.getTotalRating();
        totalRatingCount_ = game.getTotalRatingCount();
        updatedAt_ = game.getUpdatedAt().toString();
        url_ = game.getUrl();

        // 46 ~ 50
        versionParent_ = game.getVersionParent().toString();
        versionTitle_ = game.getVersionTitle();
        videos_ = Lists.transform(game.getVideosList(), Functions.toStringFunction());
        websites_ = Lists.transform(game.getWebsitesList(), Functions.toStringFunction());
        checksum_ = game.getChecksum();

        // 51 ~ 55
        remakes_ = Lists.transform(game.getRemakesList(), Functions.toStringFunction());
        remasters_ = Lists.transform(game.getRemastersList(), Functions.toStringFunction());
        expandedGames_ = Lists.transform(game.getExpandedGamesList(), Functions.toStringFunction());
        ports_ = Lists.transform(game.getPortsList(), Functions.toStringFunction());
        forks_ = Lists.transform(game.getForksList(), Functions.toStringFunction());

    }


//    //    private static final long serialVersionUID = 0L;
////    public static final int ID_FIELD_NUMBER = 1;
//    private long id_;
//    //    public static final int AGE_RATINGS_FIELD_NUMBER = 2;
//    private List<AgeRating> ageRatings_;
//    //    public static final int AGGREGATED_RATING_FIELD_NUMBER = 3;
//    private double aggregatedRating_;
//    //    public static final int AGGREGATED_RATING_COUNT_FIELD_NUMBER = 4;
//    private int aggregatedRatingCount_;
//    //    public static final int ALTERNATIVE_NAMES_FIELD_NUMBER = 5;
//    private List<AlternativeName> alternativeNames_;
//    //    public static final int ARTWORKS_FIELD_NUMBER = 6;
//    private List<Artwork> artworks_;
//    //    public static final int BUNDLES_FIELD_NUMBER = 7;
//    private List<Game> bundles_;
//    //    public static final int CATEGORY_FIELD_NUMBER = 8;
//    private int category_;
//    //    public static final int COLLECTION_FIELD_NUMBER = 9;
//    private Collection collection_;
//    //    public static final int COVER_FIELD_NUMBER = 10;
//    private Cover cover_;
//    //    public static final int CREATED_AT_FIELD_NUMBER = 11;
//    private Timestamp createdAt_;
//    //    public static final int DLCS_FIELD_NUMBER = 12;
//    private List<Game> dlcs_;
//    //    public static final int EXPANSIONS_FIELD_NUMBER = 13;
//    private List<Game> expansions_;
//    //    public static final int EXTERNAL_GAMES_FIELD_NUMBER = 14;
//    private List<ExternalGame> externalGames_;
//    //    public static final int FIRST_RELEASE_DATE_FIELD_NUMBER = 15;
//    private Timestamp firstReleaseDate_;
//    //    public static final int FOLLOWS_FIELD_NUMBER = 16;
//    private int follows_;
//    //    public static final int FRANCHISE_FIELD_NUMBER = 17;
//    private Franchise franchise_;
//    //    public static final int FRANCHISES_FIELD_NUMBER = 18;
//    private List<Franchise> franchises_;
//    //    public static final int GAME_ENGINES_FIELD_NUMBER = 19;
//    private List<GameEngine> gameEngines_;
//    //    public static final int GAME_MODES_FIELD_NUMBER = 20;
//    private List<GameMode> gameModes_;
//    //    public static final int GENRES_FIELD_NUMBER = 21;
//    private List<Genre> genres_;
//    //    public static final int HYPES_FIELD_NUMBER = 22;
//    private int hypes_;
//    //    public static final int INVOLVED_COMPANIES_FIELD_NUMBER = 23;
//    private List<InvolvedCompany> involvedCompanies_;
//    //    public static final int KEYWORDS_FIELD_NUMBER = 24;
//    private List<Keyword> keywords_;
//    //    public static final int MULTIPLAYER_MODES_FIELD_NUMBER = 25;
//    private List<MultiplayerMode> multiplayerModes_;
//    //    public static final int NAME_FIELD_NUMBER = 26;
//    private volatile Object name_;
//    //    public static final int PARENT_GAME_FIELD_NUMBER = 27;
//    private Game parentGame_;
//    //    public static final int PLATFORMS_FIELD_NUMBER = 28;
//    private List<Platform> platforms_;
//    //    public static final int PLAYER_PERSPECTIVES_FIELD_NUMBER = 29;
//    private List<PlayerPerspective> playerPerspectives_;
//    //    public static final int RATING_FIELD_NUMBER = 30;
//    private double rating_;
//    //    public static final int RATING_COUNT_FIELD_NUMBER = 31;
//    private int ratingCount_;
//    //    public static final int RELEASE_DATES_FIELD_NUMBER = 32;
//    private List<ReleaseDate> releaseDates_;
//    //    public static final int SCREENSHOTS_FIELD_NUMBER = 33;
//    private List<Screenshot> screenshots_;
//    //    public static final int SIMILAR_GAMES_FIELD_NUMBER = 34;
//    private List<Game> similarGames_;
//    //    public static final int SLUG_FIELD_NUMBER = 35;
//    private volatile Object slug_;
//    //    public static final int STANDALONE_EXPANSIONS_FIELD_NUMBER = 36;
//    private List<Game> standaloneExpansions_;
//    //    public static final int STATUS_FIELD_NUMBER = 37;
//    private int status_;
//    //    public static final int STORYLINE_FIELD_NUMBER = 38;
//    private volatile Object storyline_;
//    //    public static final int SUMMARY_FIELD_NUMBER = 39;
//    private volatile Object summary_;
//    //    public static final int TAGS_FIELD_NUMBER = 40;
//    private Internal.IntList tags_;
//    private int tagsMemoizedSerializedSize;
//    //    public static final int THEMES_FIELD_NUMBER = 41;
//    private List<Theme> themes_;
//    //    public static final int TOTAL_RATING_FIELD_NUMBER = 42;
//    private double totalRating_;
//    //    public static final int TOTAL_RATING_COUNT_FIELD_NUMBER = 43;
//    private int totalRatingCount_;
//    //    public static final int UPDATED_AT_FIELD_NUMBER = 44;
//    private Timestamp updatedAt_;
//    //    public static final int URL_FIELD_NUMBER = 45;
//    private volatile Object url_;
//    //    public static final int VERSION_PARENT_FIELD_NUMBER = 46;
//    private Game versionParent_;
//    //    public static final int VERSION_TITLE_FIELD_NUMBER = 47;
//    private volatile Object versionTitle_;
//    //    public static final int VIDEOS_FIELD_NUMBER = 48;
//    private List<GameVideo> videos_;
//    //    public static final int WEBSITES_FIELD_NUMBER = 49;
//    private List<Website> websites_;
//    //    public static final int CHECKSUM_FIELD_NUMBER = 50;
//    private volatile Object checksum_;
//    //    public static final int REMAKES_FIELD_NUMBER = 51;
//    private List<Game> remakes_;
//    //    public static final int REMASTERS_FIELD_NUMBER = 52;
//    private List<Game> remasters_;
//    //    public static final int EXPANDED_GAMES_FIELD_NUMBER = 53;
//    private List<Game> expandedGames_;
//    //    public static final int PORTS_FIELD_NUMBER = 54;
//    private List<Game> ports_;
//    //    public static final int FORKS_FIELD_NUMBER = 55;
//    private List<Game> forks_;
//
//    // 한번에 옮기는 Game
//    public void setGameIGDB(Game game) {
//
//        // 1 ~ 5
//        id_ = game.getId();
//        ageRatings_ = game.getAgeRatingsList();
//        aggregatedRating_ = game.getAggregatedRating();
//        aggregatedRatingCount_ = game.getAggregatedRatingCount();
//        alternativeNames_ = game.getAlternativeNamesList();
//
//        // 6 ~ 10
//        artworks_ = game.getArtworksList();
//        bundles_ = game.getBundlesList();
//        category_ = game.getCategoryValue();
//        collection_ = game.getCollection();
//        cover_ = game.getCover();
//
//        // 11 ~ 15
//        createdAt_ = game.getCreatedAt();
//        dlcs_ = game.getDlcsList();
//        expansions_ = game.getExpansionsList();
//        externalGames_ = game.getExternalGamesList();
//        firstReleaseDate_ = game.getFirstReleaseDate();
//
//        // 16 ~ 20
//        follows_ = game.getFollows();
//        franchise_ = game.getFranchise();
//        franchises_ = game.getFranchisesList();
//        gameEngines_ = game.getGameEnginesList();
//        gameModes_ = game.getGameModesList();
//
//        // 21 ~ 25
//        genres_ = game.getGenresList();
//        hypes_ = game.getHypes();
//        involvedCompanies_ = game.getInvolvedCompaniesList();
//        keywords_ = game.getKeywordsList();
//        multiplayerModes_ = game.getMultiplayerModesList();
//
//        // 26 ~ 30
//        name_ = game.getName();
//        parentGame_ = game.getParentGame();
//        platforms_ = game.getPlatformsList();
//        playerPerspectives_ = game.getPlayerPerspectivesList();
//        rating_ = game.getRating();
//
//        // 31 ~ 35
//        ratingCount_ = game.getRatingCount();
//        releaseDates_ = game.getReleaseDatesList();
//        screenshots_ = game.getScreenshotsList();
//        similarGames_ = game.getSimilarGamesList();
//        slug_ = game.getSlug();
//
//        // 36 ~ 40
//        standaloneExpansions_ = game.getStandaloneExpansionsList();
//        status_ = game.getStatusValue();
//        storyline_ = game.getStoryline();
//        summary_ = game.getSummary();
//        tags_ = (Internal.IntList) game.getTagsList(); // 40-1
//        tagsMemoizedSerializedSize = game.getTagsCount(); // 40-2
//
//        // 41 ~ 45
//        themes_ = game.getThemesList();
//        totalRating_ = game.getTotalRating();
//        totalRatingCount_ = game.getTotalRatingCount();
//        updatedAt_ = game.getUpdatedAt();
//        url_ = game.getUrl();
//
//        // 46 ~ 50
//        versionParent_ = game.getVersionParent();
//        versionTitle_ = game.getVersionTitle();
//        videos_ = game.getVideosList();
//        websites_ = game.getWebsitesList();
//        checksum_ = game.getChecksum();
//
//        // 51 ~ 55
//        remakes_ = game.getRemakesList();
//        remasters_ = game.getRemastersList();
//        expandedGames_ = game.getExpandedGamesList();
//        ports_ = game.getPortsList();
//        forks_ = game.getForksList();
//
//    }

}

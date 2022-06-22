<%@ page import="az.subid.dto.GameIgdbDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="az.subid.dto.GamePriceDTO" %>
<%@ page import="az.subid.dto.GameInfoDTO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="az.subid.util.CmmUtil" %>
<%@ page import="com.api.igdb.utils.ImageBuilderKt" %>
<%@ page import="com.api.igdb.utils.ImageSize" %>
<%@ page import="com.api.igdb.utils.ImageType" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.util.Locale" %>
<%@ page import="az.subid.filter.GamePlatformFilter" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!-- session Start -->
<%@include file="/WEB-INF/views/inc/session.jsp" %>
<!-- session end -->

<%
    // 게임 정보 리스트 넣기
    List<GameInfoDTO> infoList = (List<GameInfoDTO>) request.getAttribute("infoList");
    List<GameIgdbDTO> igdbList = (List<GameIgdbDTO>) request.getAttribute("igdbList");
//    List<GamePriceDTO> priceList = (List<GamePriceDTO>) request.getAttribute("priceList");

    // 게시판 조회하는데, 없으면 new 해주기
    if (infoList == null) {

        infoList = new ArrayList<GameInfoDTO>();

    }
    if (igdbList == null) {

        igdbList = new ArrayList<GameIgdbDTO>();

    }
//    if (priceList == null) {
//
//        priceList = new ArrayList<GamePriceDTO>();
//
//    }
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>

    <title>게임가격비교 홈 : SuBiD</title>

    <!-- head Start -->
    <%@include file="/WEB-INF/views/inc/head.jsp" %>
    <!-- head end -->

    <script type="text/javascript">

        //상세보기 이동
        function doDetail(id) {

            location.href = "/gameprice/info?id=" + id;

        }

    </script>

</head>
<body class="sb-nav-fixed">

<!-- head Start -->
<%@include file="/WEB-INF/views/inc/nav.jsp" %>
<!-- head end -->

<div id="layoutSidenav">

    <!-- sidenav Start -->
    <%@include file="/WEB-INF/views/inc/sidenav.jsp" %>
    <!-- sidenav end -->

    <div id="layoutSidenav_content">

        <main>

            <div class="container-fluid px-4">
                <h1 class="mt-4">Game Price</h1>
                <ol class="breadcrumb mb-4">
                    <li class="breadcrumb-item active">List</li>
                </ol>

                <div class="row">

                    <%
                        // 리스트 하나씩 출력
                        for (int i = 0; i < infoList.size(); i++) {

                            GameInfoDTO infoDTO = infoList.get(i);
                            GameIgdbDTO igdbDTO = igdbList.get(i);
//                            GamePriceDTO priceDTO = priceList.get(i);

                            if (infoDTO == null) {
                                infoDTO = new GameInfoDTO();
                            }
                            if (igdbDTO == null) {
                                igdbDTO = new GameIgdbDTO();
                            }
//                            if (priceDTO == null) {
//                                priceDTO = new GamePriceDTO();
//                            }
                    %>

                    <%
                        // 기본값, 노란색
                        String bg = new String();

                        // 단일 플랫폼이면,
                        if (new GamePlatformFilter().selectPlatforms(igdbDTO).size() == 1) {

                            switch (new GamePlatformFilter().selectPlatforms(igdbDTO).get(0)) {

                                case "PC" : bg = "bg-warning"; break;
                                case "ND" : bg = "bg-primary"; break;
                                case "PS" : bg = "bg-warning"; break;
                                case "XB" : bg = "bg-success"; break;

                            }

                        } else {
                            bg = "bg-black";
                        }

                        // 커버 변수
                        String cover_id = infoDTO.getIgdb_cover_id();
                        String coverURL = ImageBuilderKt.imageBuilder(cover_id, ImageSize.COVER_BIG, ImageType.PNG);
                    %>

                    <div class="col-xl-auto col-md-auto">
                        <div class="card bg <%=bg%> text-white mb-4">
                            <img src="<%=coverURL%>">
                            <div class="card-body">
                                <%=CmmUtil.nvl(igdbDTO.getName_()) %>
                            </div>
                            <div class="card-footer d-flex align-items-center justify-content-between">
                                <a class="small text-white stretched-link" href="javascript:doDetail('<%=igdbDTO.getId_()%>');">View Details</a>
                                <div class="small text-white">
                                    <svg class="svg-inline--fa fa-angle-right" aria-hidden="true" focusable="false"
                                         data-prefix="fas" data-icon="angle-right" role="img"
                                         xmlns="http://www.w3.org/2000/svg" viewBox="0 0 256 512" data-fa-i2svg="">
                                        <path fill="currentColor"
                                              d="M64 448c-8.188 0-16.38-3.125-22.62-9.375c-12.5-12.5-12.5-32.75 0-45.25L178.8 256L41.38 118.6c-12.5-12.5-12.5-32.75 0-45.25s32.75-12.5 45.25 0l160 160c12.5 12.5 12.5 32.75 0 45.25l-160 160C80.38 444.9 72.19 448 64 448z"></path>
                                    </svg><!-- <i class="fas fa-angle-right"></i> Font Awesome fontawesome.com --></div>
                            </div>
                        </div>
                    </div>

                    <%
                        }
                    %>

                </div>

            </div>

        </main>
        <!-- main end! -->

        <!-- footer&Scroll Up start -->
        <%@include file="/WEB-INF/views/inc/footer.jsp" %>
        <!-- footer&Scroll Up end -->

    </div>
</div>

</body>
</html>

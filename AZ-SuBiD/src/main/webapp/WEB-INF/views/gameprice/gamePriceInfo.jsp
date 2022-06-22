<%@ page import="az.subid.dto.GameIgdbDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="az.subid.dto.GamePriceDTO" %>
<%@ page import="az.subid.dto.GameInfoDTO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="az.subid.util.CmmUtil" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!-- session Start -->
<%@include file="/WEB-INF/views/inc/session.jsp" %>
<!-- session end -->

<%
    // 게임 정보 리스트 넣기
    GameInfoDTO infoDTO = (GameInfoDTO) request.getAttribute("infoDTO");
    GameIgdbDTO igdbDTO = (GameIgdbDTO) request.getAttribute("igdbDTO");
    GamePriceDTO priceDTO = (GamePriceDTO) request.getAttribute("priceDTO");

    // 게시판 조회하는데, 없으면 new 해주기
    if (infoDTO == null) {

        infoDTO = new GameInfoDTO();

    }
    if (igdbDTO == null) {

        igdbDTO = new GameIgdbDTO();

    }
//    if (priceDTO == null) {
//
//        priceDTO = new GamePriceDTO();
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

            location.href = "/gameprice/GameInfo?nSeq=" + id;

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

        <!-- divmain Start -->
        <%--        <%@include file="/WEB-INF/views/inc/divmain.jsp" %>--%>
        <!-- divmain end -->

        <!-- main Start! -->
        <main>

            <div class="container-fluid px-4">
                <h1 class="mt-4"><%=igdbDTO.getName_()%></h1>
                <ol class="breadcrumb mb-4">
                    <li class="breadcrumb-item active"><%=igdbDTO.getPlatforms_()%></li>
                </ol>

                <div class="row">

<%--                    제목, 그림, 가격, 링크, 그래프 --%>

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

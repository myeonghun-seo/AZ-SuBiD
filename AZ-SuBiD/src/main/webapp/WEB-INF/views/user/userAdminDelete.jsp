<%@ page import="az.subid.dto.NoticeDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="az.subid.dto.UserInfoDTO" %>
<%@ page import="org.apache.catalina.User" %>
<%@ page import="az.subid.util.CmmUtil" %>
<%@ page import="az.subid.dto.UserAdminDTO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!-- session Start -->
<%@include file="/WEB-INF/views/inc/session.jsp" %>
<!-- session end -->

<%

List<UserAdminDTO> rList = (List<UserAdminDTO>) request.getAttribute("rList");

//게시판 조회 결과 보여주기
if (rList == null) {
rList = new ArrayList<UserAdminDTO>();
}

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>

    <title>관리자 홈 : SuBiD</title>

    <!-- head Start -->
    <%@include file="/WEB-INF/views/inc/head.jsp" %>
    <!-- head end -->

    <script type="text/javascript">

        //상세보기 이동
        function doDetail(seq) {
            location.href = "/admin/info?nSeq=" + seq;
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

            <%-- 제목 --%>
            <div class="container-fluid px-4">
                <h1 class="mt-4">Delete</h1>
                <ol class="breadcrumb mb-4">
                    <li class="breadcrumb-item active">List</li>
                </ol>

                <%-- 테이블 --%>
                <div class="divTable minimalistBlack">
                    <div class="divTableHeading">
                        <div class="divTableRow">
                            <div class="divTableHead">Sequence</div>
                            <div class="divTableHead">id</div>
                            <div class="divTableHead">auth</div>
                            <div class="divTableHead">registered date</div>
                        </div>
                    </div>
                    <div class="divTableBody">
                        <%
                            for (UserAdminDTO iDTO : rList) {

                                if (iDTO == null) {
                                    iDTO = new UserAdminDTO();
                                }
                        %>
                        <div class="divTableRow">
                            <div class="divTableCell"><%=iDTO.getSequence()%></div>
                            <div class="divTableCell">
<%--                                <a href="javascript:doDetail('<%=CmmUtil.nvl(iDTO.getSequence())%>');">--%>
                                    <%=iDTO.getUser_id()%></a>
                            </div>
                            <div class="divTableCell"><%=iDTO.getLog_info()%></div>
                            <div class="divTableCell"><%=iDTO.getReg_dt()%></div>
                        </div>
                        <%
                            }
                        %>
                    </div>
<%--                    <div class="divTableFoot tableFootStyle">--%>
<%--                        <div class="divTableRow">--%>
<%--                            <div class="divTableCell">foot1</div>--%>
<%--                            <div class="divTableCell">foot2</div>--%>
<%--                            <div class="divTableCell">foot3</div>--%>
<%--                            <div class="divTableCell">foot4</div>--%>
<%--                        </div>--%>
<%--                    </div>--%>
                </div>
            </div>

        </main>

    <!-- footer&Scroll Up start -->
    <%@include file="/WEB-INF/views/inc/footer.jsp" %>
    <!-- footer&Scroll Up end -->

    </div>

</div>

</body>
</html>

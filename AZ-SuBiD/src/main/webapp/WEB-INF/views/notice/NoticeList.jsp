<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="az.subid.dto.NoticeDTO" %>
<%@ page import="az.subid.util.CmmUtil" %>

<!-- session Start -->
<%@include file="/WEB-INF/views/inc/session.jsp" %>
<!-- session end -->

<%
    List<NoticeDTO> rList = (List<NoticeDTO>) request.getAttribute("rList");

//게시판 조회 결과 보여주기
    if (rList == null) {
        rList = new ArrayList<NoticeDTO>();

    }

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
    <title>공지 리스트</title>

    <!-- head Start -->
    <%@include file="/WEB-INF/views/inc/head.jsp" %>
    <!-- head end -->

    <script type="text/javascript">

        //상세보기 이동
        function doDetail(seq) {
            location.href = "/notice/NoticeInfo?nSeq=" + seq;
        }

    </script>
</head>
<body>
<h2>공지사항</h2>
<hr/>
<br/>

<div class="table_style">
    <ul class="header">
        <li class="column1">순번</li>
        <li class="column1">제목</li>
        <li class="column1">조회수</li>
        <li class="column1">등록자</li>
        <li class="column1">등록일</li>
    </ul>
    <%
        for (int i = 0; i < rList.size(); i++) {
            NoticeDTO rDTO = rList.get(i);

            if (rDTO == null) {
                rDTO = new NoticeDTO();
            }

    %>
    <ul>
        <li class="column1">
            <%
                //공지글이라면, [공지]표시
                if (CmmUtil.nvl(rDTO.getNotice_yn()).equals("1")) {
                    out.print("<b>[공지]</b>");

                    //공지글이 아니라면, 글번호 보여주기
                } else {
                    out.print(CmmUtil.nvl(rDTO.getSequence()));

                }
            %></li>
        <li class="column1">
            <a href="javascript:doDetail('<%=CmmUtil.nvl(rDTO.getSequence())%>');">
                <%=CmmUtil.nvl(rDTO.getTitle()) %>
            </a>
        </li>
        <li class="column1"><%=CmmUtil.nvl(rDTO.getRead_cnt()) %>
        </li>
        <li class="column1"><%=CmmUtil.nvl(rDTO.getUser_id()) %>
        </li>
        <li class="column1"><%=CmmUtil.nvl(rDTO.getReg_dt()) %>
        </li>
    </ul>
    <%
        }
    %>
</div>
<a href="/notice/NoticeReg">[글쓰기]</a>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR" %>
<%@ page import="az.subid.util.CmmUtil" %>
<%
    String jspRes = CmmUtil.nvl((String) request.getAttribute("res"), "0");

    String toMail = CmmUtil.nvl(request.getParameter("toMail"));
    String title = CmmUtil.nvl(request.getParameter("title"));
    String contents = CmmUtil.nvl(request.getParameter("contents"));
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="EUC-KR">
    <title>Insert title here</title>
</head>
<body>
<%
    if (jspRes.equals("1")) {
        out.println(toMail + "로 메일 전송이 성공하였습니다.");
        out.println("메일 제목 : " + title);
        out.println("메일 내용 : " + contents);

    } else {
        out.println(toMail + "로 메일 전송이 실패하였습니다.");
    }
%>
<!-- 메일을 보낸 정보에 대한 성공 및 실패 메시지를 띄워준다. -->
</body>
</html>
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
        out.println(toMail + "�� ���� ������ �����Ͽ����ϴ�.");
        out.println("���� ���� : " + title);
        out.println("���� ���� : " + contents);

    } else {
        out.println(toMail + "�� ���� ������ �����Ͽ����ϴ�.");
    }
%>
<!-- ������ ���� ������ ���� ���� �� ���� �޽����� ����ش�. -->
</body>
</html>
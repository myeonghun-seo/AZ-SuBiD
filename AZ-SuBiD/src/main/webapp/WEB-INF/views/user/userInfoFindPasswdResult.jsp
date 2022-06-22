<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR" %>
<%@ page import="az.subid.util.CmmUtil" %>
<%
    //Controller로부터 전달받은 데이터
    String msg = CmmUtil.nvl((String) request.getAttribute("msg"));
    String url = CmmUtil.nvl((String) request.getAttribute("url"));
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
    <meta charset="EUC-KR" content="">
    <title>Find Passwd Result</title>
    <script type="text/javascript">
        alert("<%=msg%>");
        window.location = '<%=url%>';
    </script>
</head>
<body>
</body>
</html>
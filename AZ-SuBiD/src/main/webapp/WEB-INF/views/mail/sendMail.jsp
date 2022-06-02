<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>
<form action="mail/sendMail" method="post">
    <table border="1">
        <tr>
            <td>받는 사람</td>
            <td><input name="toMail" type="text"></td>
        </tr>
        <tr>
            <td>메일 제목</td>
            <td><input name="title" type="text"></td>
        </tr>
        <tr>
            <td>메일 내용</td>
            <td><input name="contents" type="text"></td>
        </tr>
    </table>
    <input type="submit" value="[메일 전송]">
    <input type="reset" value="[내용 초기화]">
</form>
</body>
</html>
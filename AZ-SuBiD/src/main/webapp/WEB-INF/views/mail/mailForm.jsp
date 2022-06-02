<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>메일 전송 폼</title>

</head>
<body>
<div style="margin:auto;text-align:center;">
    <form action="/mail/sendMail" method="post" style="display:inline-block;">
        <table border="1" width="auto" height="auto" style="text-alian:center;">
            <tr width="400px" height="50px">
                <td width="100x">받는사람</td>
                <td width="300px"><input type="text" name="toMail" style="width:300px; height:30px;"></td>
            </tr>
            <tr width="400px" height="50px">
                <td width="100x">메일제목</td>
                <td width="300px"><input type="text" name="title" style="width:300px; height:30px;"></td>
            </tr>
            <tr width="400px" height="200px">
                <td width="100x">메일내용</td>
                <div class='box1'>
                    <td width="300px"><textarea style="width:298px; height:168px;" name="contents"></textarea></td>
                </div>
            </tr>
        </table>
        <input type="submit" style="float:left" value="[매일전송]">
        <input type="reset" style="float:left" value="[내용초기화]">
    </form>

</div>
</body>
</html>
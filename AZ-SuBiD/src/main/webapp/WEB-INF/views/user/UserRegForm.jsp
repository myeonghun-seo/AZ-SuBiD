<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="EUC-KR" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="EUC-KR">
    <title>ȸ������ ȭ��</title>
    <script type="text/javascript">
        //ȸ������ ������ ��ȿ�� üũ�ϱ�
        function doRegUserCheck(f) {

            if (f.user_id.value == "") {
                alert("���̵� �Է��ϼ���.");
                f.user_id.focus();
                return false;
            }

            if (f.user_nm.value == "") {
                alert("�̸��� �Է��ϼ���.");
                f.user_nm.focus();
                return false;
            }

            if (f.user_pw.value == "") {
                alert("��й�ȣ�� �Է��ϼ���.");
                f.user_pw.focus();
                return false;
            }

            if (f.user_pw2.value == "") {
                alert("��й�ȣȮ���� �Է��ϼ���.");
                f.user_pw2.focus();
                return false;
            }

            if (f.email.value == "") {
                alert("�̸����� �Է��ϼ���.");
                f.email.focus();
                return false;
            }

            if (f.addr1.value == "") {
                alert("�ּҸ� �Է��ϼ���.");
                f.addr1.focus();
                return false;
            }

            if (f.addr2.value == "") {
                alert("���ּҸ� �Է��ϼ���.");
                f.addr2.focus();
                return false;
            }
        }
    </script>
</head>
<body>

<h1>ȸ������ ȭ��</h1>
<br/>
<br/>
<form name="f" method="post" action="/user/insertUserInfo" onsubmit="return doRegUserCheck(this);">
    <table border="1">
        <col width="150px">
        <col width="150px">
        <col width="150px">
        <col width="150px">
        <tr>
            <td>���̵�</td>
            <td><input type="text" name="user_id" style="width:150px"/></td>
            <td>�̸�</td>
            <td><input type="text" name="user_nm" style="width:150px"/></td>
        </tr>
        <tr>
            <td>��й�ȣ</td>
            <td><input type="password" name="user_pw" style="width:150px"/></td>
            <td>��й�ȣ Ȯ��</td>
            <td><input type="password" name="user_pw2" style="width:150px"/></td>
        </tr>
        <tr>
            <td>�̸���</td>
            <td colspan="3"><input type="text" name="email" style="width:450px"/></td>
        </tr>
        <tr>
            <td>�ּ�</td>
            <td colspan="3"><input type="text" name="addr1" style="width:450px"/></td>
        </tr>
        <tr>
            <td>��</td>
            <td colspan="3"><input type="text" name="addr2" style="width:450px"/></td>
        </tr>
    </table>
    <input type="submit" value="ȸ������"/>
</form>
</body>
</html>


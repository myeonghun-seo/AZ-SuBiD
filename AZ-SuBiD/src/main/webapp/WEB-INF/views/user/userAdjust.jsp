<%@ page import="az.subid.dto.UserInfoDTO" %>
<%@ page import="az.subid.util.EncryptUtil" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>

    <title>Adjust - SuBiD</title>

    <!-- head Start -->
    <%@include file="/WEB-INF/views/inc/head.jsp" %>
    <!-- head end -->

    <%
        UserInfoDTO rDTO = (UserInfoDTO) request.getAttribute("rDTO");
    %>

    <!-- login script Start -->
    <script type="text/javascript">

        //회원가입 정보의 유효성 체크하기
        function doRegUserCheck(f) {

            if (f.user_id.value == "") {
                alert("아이디를 입력하세요.");
                f.user_id.focus();
                return false;
            }

            // 아이디 유효성 검사
            // 4 ~ 12 글자 제한
            var ruser_id = /^[a-zA-z0-9]{4,12}$/;

            if (!ruser_id.test(f.user_id.value)) {
                alert("아이디는 4~12자리로 입력해야 합니다.")
                f.user_id.focus();
                return false;
            }

            if (f.user_nm.value == "") {
                alert("이름을 입력하세요.");
                f.user_nm.focus();
                return false;
            }

            if (f.user_pw.value == "") {
                alert("비밀번호를 입력하세요.");
                f.user_pw.focus();
                return false;
            }

            if (f.user_pw2.value == "") {
                alert("비밀번호확인을 입력하세요.");
                f.user_pw2.focus();
                return false;
            }

            if (f.email.value == "") {
                alert("이메일 주소를 입력하세요.");
                f.email.focus();
                return false;
            }

            // 이메일 유효성 검사
            var remail = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

            if (!remail.test(f.email.value)) {
                alert("올바른 이메일 주소를 입력하세요")
                f.email.focus();
                return false;
            }

            if (f.addr1.value == "") {
                alert("주소를 입력하세요.");
                f.addr1.focus();
                return false;
            }

            if (f.addr2.value == "") {
                alert("상세주소를 입력하세요.");
                f.addr2.focus();
                return false;
            }

            f.submit();
        }
    </script>
    <!-- login script End -->

</head>
<body class="bg-primary">
<div id="layoutAuthentication">
    <div id="layoutAuthentication_content">
        <main>
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-lg-7">
                        <div class="card shadow-lg border-0 rounded-lg mt-5">
                            <div class="card-header"><h3 class="text-center font-weight-light my-4">Update Account</h3>
                            </div>
                            <div class="card-body">
                                <form name="f" method="post" action="/user/updateUserInfo" onsubmit="return doRegUserCheck(this);">
                                    <div class="row mb-3">
                                        <div class="col-md-6">
                                            <div class="form-floating mb-3 mb-md-0">
                                                <input class="form-control" id="inputId" type="text"
                                                       name="user_id" placeholder="Enter your id" value="<%=rDTO.getUser_id()%>" readonly/>
                                                <label for="inputId">id</label>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-floating">
                                                <input class="form-control" id="inputName" type="text"
                                                       name="user_nm" placeholder="Enter your name" value="<%=rDTO.getUser_nm()%>"/>
                                                <label for="inputName">name</label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row mb-3">
                                        <div class="col-md-6">
                                            <div class="form-floating mb-3 mb-md-0">
                                                <input class="form-control" id="inputPassword" type="password"
                                                       name="user_pw" placeholder="Create a password"/>
                                                <label for="inputPassword">Password</label>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-floating mb-3 mb-md-0">
                                                <input class="form-control" id="inputPasswordConfirm" type="password"
                                                       name="user_pw2" placeholder="Confirm password"/>
                                                <label for="inputPasswordConfirm">Confirm Password</label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-floating mb-3">
                                        <input class="form-control" id="inputEmail" type="email"
                                               name="email" placeholder="name@example.com" value="<%=EncryptUtil.decAES128CBC(rDTO.getEmail())%>"/>
                                        <label for="inputEmail">Email address</label>
                                    </div>
                                    <div class="form-floating mb-3">
                                        <input class="form-control" id="inputAddr" type="text"
                                               name="addr1" placeholder="name@example.com" value="<%=rDTO.getAddr1()%>"/>
                                        <label for="inputAddr">Address</label>
                                    </div>
                                    <div class="form-floating mb-3">
                                        <input class="form-control" id="inputAddr2" type="text"
                                               name="addr2" placeholder="name@example.com" value="<%=rDTO.getAddr2()%>"/>
                                        <label for="inputAddr2">Address imfoamations</label>
                                    </div>
                                    <div class="mt-4 mb-0">
                                        <div class="d-grid"><a class="btn btn-primary btn-block" href="#" onclick="doRegUserCheck(f)">Update
                                            Account</a></div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </main>
    </div>
    <div id="layoutAuthentication_footer">

        <!-- footer&Scroll Up start -->
        <%@include file="/WEB-INF/views/inc/footer.jsp" %>
        <!-- footer&Scroll Up end -->

    </div>
</div>

</body>
</html>

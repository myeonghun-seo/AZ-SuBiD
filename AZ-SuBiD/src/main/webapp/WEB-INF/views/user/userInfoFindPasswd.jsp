<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="ko">
<head>

    <title>Password Reset - SuBiD</title>

    <!-- head Start -->
    <%@include file="/WEB-INF/views/inc/head.jsp" %>
    <!-- head end -->

    <!-- login script Start -->
    <script type="text/javascript">

        //회원가입 정보의 유효성 체크하기
        function doRegUserCheck(f) {

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
                    <div class="col-lg-5">
                        <div class="card shadow-lg border-0 rounded-lg mt-5">
                            <div class="card-header"><h3 class="text-center font-weight-light my-4">Password
                                Recovery</h3></div>
                            <div class="card-body">
                                <div class="small mb-3 text-muted">Enter your email address and we will send you a link
                                    to reset your password.
                                </div>
                                <form name="f" method="post" action="/user/getfindpasswd" onsubmit="return doRegUserCheck(this);">
                                    <div class="form-floating mb-3">
                                        <input class="form-control" id="inputEmail" type="email"
                                               name="email" placeholder="name@example.com"/>
                                        <label for="inputEmail">Email address</label>
                                    </div>
                                    <div class="d-flex align-items-center justify-content-between mt-4 mb-0">
                                        <a class="small" href="login">Return to login</a>
                                        <a class="btn btn-primary" href="#" onclick="doRegUserCheck(f)">Reset Password</a>
                                    </div>
                                </form>
                            </div>
                            <div class="card-footer text-center py-3">
                                <div class="small"><a href="register">Need an account? Sign up!</a></div>
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

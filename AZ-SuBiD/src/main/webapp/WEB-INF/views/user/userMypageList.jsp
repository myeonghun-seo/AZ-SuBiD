<%@ page import="az.subid.dto.UserInfoDTO" %>
<%@ page import="az.subid.util.EncryptUtil" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!-- session Start -->
<%@include file="/WEB-INF/views/inc/session.jsp" %>
<!-- session end -->

<%
    UserInfoDTO rDTO = (UserInfoDTO) request.getAttribute("rDTO");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>

    <title>Mypage : SuBiD</title>

    <!-- head Start -->
    <%@include file="/WEB-INF/views/inc/head.jsp" %>
    <!-- head end -->

    <!-- login script Start -->
    <script type="text/javascript">

        //회원가입 정보의 유효성 체크하기
        function doRegUserCheck(f) {
            f.submit();
        }
    </script>
    <!-- login script End -->

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

        <div id="layoutAuthentication_content">
            <main>
                <div class="container">
                    <div class="row justify-content-center">
                        <div class="col-lg-7">
                            <div class="card shadow-lg border-0 rounded-lg mt-5">
                                <div class="card-header"><h3 class="text-center font-weight-light my-4">MyPage</h3>
                                </div>
                                <div class="card-body">
                                    <form name="f" method="post" action="user/userAdjust"
                                          onsubmit="return doRegUserCheck(this);">
                                        <div class="row mb-3">
                                            <div class="col-md-6">
                                                <div class="form-floating mb-3 mb-md-0">
                                                    <input class="form-control" id="infoId" type="text"
                                                           name="user_id" value="<%=rDTO.getUser_id()%>" readonly/>
                                                    <label for="infoId">id</label>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-floating">
                                                    <input class="form-control" id="infoName" type="text"
                                                           name="user_nm" value="<%=rDTO.getUser_nm()%>" readonly/>
                                                    <label for="infoId">name</label>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-floating mb-3">
                                            <input class="form-control" id="infoEmail" type="email"
                                                   name="email" placeholder="name@example.com"
                                                   value="<%=EncryptUtil.decAES128CBC(rDTO.getEmail())%>" readonly/>
                                            <label for="infoEmail">Email address</label>
                                        </div>
                                        <div class="form-floating mb-3">
                                            <input class="form-control" id="inputAddr" type="text"
                                                   name="addr1" placeholder="name@example.com"
                                                   value="<%=rDTO.getAddr1()%>" readonly/>
                                            <label for="inputAddr">Address</label>
                                        </div>
                                        <div class="form-floating mb-3">
                                            <input class="form-control" id="inputAddr2" type="text"
                                                   name="addr2" placeholder="name@example.com"
                                                   value="<%=rDTO.getAddr2()%>" readonly/>
                                            <label for="inputAddr2">Address imfoamations</label>
                                        </div>
                                        <div class="mt-4 mb-0">
                                            <div class="d-grid"><a class="btn btn-primary btn-block" href="#"
                                                                   onclick="doRegUserCheck(f)">Edit Account</a></div>
                                        </div>
                                    </form>
                                </div>
                                <div class="card-footer text-center py-3">
                                    <div class="small">
                                        <a href="/updatepasswd">update passwd</a>
                                    </div>
                                </div>
                                <div class="card-footer text-center py-3">
                                    <div class="small">
                                        <a href="/user/userdelete">Did U Wanted Delete Account? Go to Delete</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </main>
        </div>

        <!-- footer&Scroll Up start -->
        <%@include file="/WEB-INF/views/inc/footer.jsp" %>
        <!-- footer&Scroll Up end -->

    </div>
</div>

</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>

    <title>401 Error : SuBiD</title>

    <!-- head Start -->
    <%@include file="/WEB-INF/views/inc/head.jsp" %>
    <!-- head end -->

</head>
<body>
<div id="layoutError">
    <div id="layoutError_content">
        <main>
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-lg-6">
                        <div class="text-center mt-4">
                            <h1 class="display-1">401</h1>
                            <p class="lead">Unauthorized</p>
                            <p>Access to this resource is denied.</p>
                            <a href="/">
                                <i class="fas fa-arrow-left me-1"></i>
                                Return to homepage
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </main>
    </div>
    <div id="layoutError_footer">

        <!-- footer&Scroll Up start -->
        <%@include file="/WEB-INF/views/inc/footer.jsp" %>
        <!-- footer&Scroll Up end -->

    </div>
</div>
</body>
</html>

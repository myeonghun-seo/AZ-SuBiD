<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div id="layoutSidenav_nav">
    <nav class="sb-sidenav accordion sb-sidenav-green" id="sidenavAccordion">
        <div class="sb-sidenav-menu">
            <div class="nav">

                <%--                <!-- 뉴스 -->--%>
                <%--                <div class="sb-sidenav-menu-heading">News</div>--%>

                <%--                <!-- 각종 뉴스 -->--%>
                <%--                <a class="nav-link" href="news">--%>
                <%--                    <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>--%>
                <%--                    news--%>
                <%--                </a>--%>

                <%--                <!-- 리뷰 뉴스 -->--%>
                <%--                <a class="nav-link" href="news">--%>
                <%--                    <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>--%>
                <%--                    reviews--%>
                <%--                </a>--%>


                <!-- 게임 정보 -->
                <div class="sb-sidenav-menu-heading">Game information</div>

                <!-- 가격 정보 -->
                <a class="nav-link" href="gameprice">
                    <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                    Game Price
                </a>


                <%--                <!-- 구독 정보 -->--%>
                <%--                <a class="nav-link collapsed" href="subScription" data-bs-toggle="collapse" data-bs-target="#collapsePages"--%>
                <%--                   aria-expanded="false" aria-controls="collapsePages">--%>
                <%--                    <div class="sb-nav-link-icon"><i class="fas fa-book-open"></i></div>--%>
                <%--                    SubScription Service--%>
                <%--                    <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>--%>
                <%--                </a>--%>

                <%--                <!-- 구독 방식 -->--%>
                <%--                <div class="collapse" id="collapsePages" aria-labelledby="headingTwo" data-bs-parent="#sidenavAccordion">--%>
                <%--                    <nav class="sb-sidenav-menu-nested nav accordion" id="sidenavAccordionPages">--%>

                <%--                        <!-- 플렛폼 선택 -->--%>
                <%--                        <a class="nav-link collapsed" href="#" data-bs-toggle="collapse"--%>
                <%--                           data-bs-target="#pagesCollapseAuth" aria-expanded="false" aria-controls="pagesCollapseAuth">--%>
                <%--                            Anywhere--%>
                <%--                            <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>--%>
                <%--                        </a>--%>
                <%--                        <div class="collapse" id="pagesCollapseAuth" aria-labelledby="headingOne"--%>
                <%--                             data-bs-parent="#sidenavAccordionPages">--%>
                <%--                            <nav class="sb-sidenav-menu-nested nav">--%>
                <%--                                <a class="nav-link" href="/">Console</a>--%>
                <%--                                <a class="nav-link" href="/">Mobile</a>--%>
                <%--                                <a class="nav-link" href="/">ETC</a>--%>
                <%--                            </nav>--%>
                <%--                        </div>--%>

                <%--                        <!-- 다른 곳에서 구독 -->--%>
                <%--                        <a class="nav-link collapsed" href="#" data-bs-toggle="collapse"--%>
                <%--                           data-bs-target="#pagesCollapseError" aria-expanded="false"--%>
                <%--                           aria-controls="pagesCollapseError">--%>
                <%--                            ETC--%>
                <%--                            <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>--%>
                <%--                        </a>--%>
                <%--                        <div class="collapse" id="pagesCollapseError" aria-labelledby="headingOne"--%>
                <%--                             data-bs-parent="#sidenavAccordionPages">--%>
                <%--                            <nav class="sb-sidenav-menu-nested nav">--%>
                <%--                                <a class="nav-link" href="errorpage/401">401</a>--%>
                <%--                                <a class="nav-link" href="errorpage/404">404</a>--%>
                <%--                                <a class="nav-link" href="errorpage/500">500</a>--%>
                <%--                            </nav>--%>
                <%--                        </div>--%>

                <%--                    </nav>--%>
                <%--                </div>--%>

                <!-- 장난질 -->
                <div class="sb-sidenav-menu-heading">PROJECT : ZET</div>
                <a class="nav-link" href="zet/mainzet">
                    <div class="sb-nav-link-icon"><i class="fas fa-chart-area"></i></div>
                    What is this?
                </a>

            </div>
        </div>
        <% if (!SS_USER_ID.equals("")) { %>
        <div class="sb-sidenav-footer">
            <div class="small">Logged in as:</div>
            <%=SS_USER_ID %>
        </div>
        <% } %>
    </nav>
</div>
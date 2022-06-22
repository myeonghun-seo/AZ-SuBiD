<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ page import="az.subid.util.CmmUtil" %>
<%
    //Controller 에 저장된 세션으로 로그인 할 때 생성됨
    String SS_USER_ID = CmmUtil.nvl((String) session.getAttribute("SS_USER_ID"));
    String SS_USER_AUTH = CmmUtil.nvl((String) session.getAttribute("SS_USER_AUTH"));
%>
<%@ page import="com.Pebble.it.model.MemberDTO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pebble.it Home</title>
</head>
<body>
    <%
        // 세션에서 로그인된 사용자 정보 가져오기
        MemberDTO loginUser = (MemberDTO) session.getAttribute("loginUser");

        if (loginUser == null) {
            // 로그인되지 않은 상태라면 index.jsp로 리다이렉트
            response.sendRedirect("index.jsp");
            return;
        }
    %>

    <!-- 로그인 성공 시 사용자 환영 메시지 -->
    <h1>환영합니다, <%= loginUser.getId() %>님!</h1>
</body>
</html>

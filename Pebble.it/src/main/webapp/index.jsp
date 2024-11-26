<%@ page import="com.Pebble.it.model.MemberDTO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인Pebblt.it</title>
</head>
<body>
	<!-- 메인, 로그인 기능 구현 -->
	 <h1>Pebble.it 로그인</h1>
    <form action="Login" method="post">
        <label for="id">아이디:</label>
        <input type="text" id="id" name="id" required>
        <br><br>
        <label for="pw">비밀번호:</label>
        <input type="password" id="pw" name="pw" required>
        <br><br>
        <button type="submit">로그인</button>
        <br><br>
    </form>

    <!-- 회원가입 버튼 -->
    <form action="join.jsp" method="get">
        <button type="submit">회원가입</button>
    </form>
</body>
</html>


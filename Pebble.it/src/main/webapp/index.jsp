<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.Pebble.it.model.MemberDTO"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pebble.it</title>
    <link rel="stylesheet" href="login.css">
</head>
<body>
    <!-- 왼쪽 섹션: 이미지 배경 -->
    <div class="left_section">
        <div class="left_text">Make it happen!</div> <!-- 동기 부여 텍스트 -->
    </div>
    
    <div class="container">
        <!-- 좌측 섹션 -->
        <div class="left-section">
            <div class="overlay"></div>
        </div>
        
        <!-- 우측 섹션 -->
        <div class="right-section">
            <h2><img src="code/img/menuimg09.png" alt="">나만의 운동 일정 관리 플랫폼</h2>
            <h1 class="logo">Pebble.it</h1>
            <form action="Login" method="post"> <!-- 로그인 요청 -->
                <input type="text" name="id" placeholder="아이디를 입력하세요" required>
                <input type="password" name="pw" placeholder="비밀번호를 입력하세요" required>
                <button type="submit">로그인</button>
                <p>계정이 없으신가요? <a href="join.jsp">회원가입</a></p> <!-- 회원가입 링크 -->
            </form>
        </div>
    </div>
</body>
</html>

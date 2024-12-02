<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.Pebble.it.model.MemberDTO"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Pebble.it</title>
<link rel="stylesheet" href="resources/css/login.css">
	<!-- 랜덤 배경 JS-->
	<script src="resources/js/rd_img.js"></script>
</head>
<body>
	<div class="container">
		<!-- 왼쪽 영역 : 이미지 -->
		<div class="left_section">
			<!-- 동기 부여 텍스트 -->
			<div class="overlay">
				<div class="left_text">Make it happen!</div>
			</div>
		</div>

		<!-- 우측 섹션 -->
		<div class="right-section">
				<span id="top_title"><img src="resources/img/pebble.png" alt="페블잇" id="pebble">나만의 운동 일정 관리 플랫폼</span>
				<img src="resources/img/pebble_it_big_logo.png" id="pebble_it_big_logo">
			<form action="Login" method="post">
				<!-- 로그인 요청 -->
				<input type="text" name="id" placeholder="아이디를 입력하세요" required>
				<input type="password" name="pw" placeholder="비밀번호를 입력하세요" required>
				<button type="submit">로그인</button>
				<p>
					<!-- 회원가입 링크 -->
					계정이 없으신가요? <a href="join.jsp">회원가입</a>
				</p>
			</form>
		</div>
	</div>
</body>
</html>

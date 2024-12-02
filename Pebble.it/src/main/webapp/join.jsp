<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.Pebble.it.model.MemberDTO"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Pebble.it</title>

<link rel="stylesheet" href="resources/css/join.css">
<!-- 랜덤 배경 JS-->
<script src="resources/js/rd_img.js"></script>
<!-- 유효성 검사 스크립트 -->
<script defer src="resources/js/formValidation.js"></script>
</head>
<body>
	<!-- 왼쪽 섹션: 이미지 배경 -->
	<div class="left_section">
		<div class="left_text">Make it happen!</div>
	</div>

	<!-- 오른쪽 섹션: 회원가입 폼 -->
	<div class="right_section">
		<span id="top_title"><img src="resources/img/pebble.png"
			alt="페블잇" id="pebble">나만의 운동 일정 관리 플랫폼</span> <img
			src="resources/img/pebble_it_big_logo.png" id="pebble_it_big_logo">

		<!-- 회원가입 폼 -->
		<form action="Join" method="post" enctype="multipart/form-data">
			<!-- 프로필 사진 업로드 -->
			<div class="profile_img">
				<label for="profile-upload" id="profile-label" style="cursor: pointer;">프로필 사진 선택</label>
  				<input id="profile-upload" name="profileImg" type="file" accept="image/*" style="display: none;" />
  				<img id="profile-preview" src="#" alt="미리보기" style="display:none; width: 100px; height: 100px;" />
				
				<!-- 프로필 사진이 올라가면 라벨 사라지는 스크립트 -->
				<script src="resources/js/join.js"></script>

			</div>

			<!-- 닉네임 입력 -->
			<div class="Join">
				<input type="text" name="name" id="nickname" maxlength="8"
					placeholder="닉네임을 입력하세요 (1~8자 영문, 한글, 숫자)" required>
				<button type="button" id="check-nick" class="check-btn">중복확인</button>
				<small id="nick-error" class="error-message"></small>
			</div>

			<!-- 아이디 입력 -->
			<div class="Join">
				<input type="text" name="id" id="userId" maxlength="10"
					placeholder="아이디를 입력하세요 (5~10자의 영문 소문자, 숫자)" required>
				<button type="button" id="check-id" class="check-btn">중복확인</button>
				<small id="id-error" class="error-message"></small>
			</div>

			<!-- 비밀번호 입력 -->
			<div class="Join">
				<input type="password" name="pw" id="password" maxlength="16"
					placeholder="비밀번호를 입력하세요 (8~16자의 영문 대/소문자, 숫자)" required
					pattern="^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,16}$"
					title="비밀번호는 8~16자 사이의 영문(대/소문자)과 숫자를 포함해야 합니다."> <small
					id="password-error" class="error-message"></small>
			</div>

			<!-- 관심 운동 선택 -->
			<div class="sport_check">
				<p>| 관심 운동 선택 (택1)</p>
				<label><input type="radio" name="favoriteSport" value="헬스"
					required> 헬스</label> <label><input type="radio"
					name="favoriteSport" value="수영" required> 수영</label> <label><input
					type="radio" name="favoriteSport" value="테니스" required> 테니스</label>
			</div>

			<!-- 운동 수준 선택 -->
			<div class="sport_check">
				<p>| 나의 운동 수준 (택1)</p>
				<label><input type="radio" name="sportLevel" value="lev1"
					required> 초급자</label> <label><input type="radio"
					name="sportLevel" value="lev2" required> 중급자</label> <label><input
					type="radio" name="sportLevel" value="lev3" required> 전문가</label>
			</div>

			<!-- 버튼 영역 -->
			<div class="buttons">
				<button type="button" class="cancel-btn" onclick="history.back()">취소</button>
				<button type="submit" class="join-btn">회원가입</button>
			</div>


			<!-- 메시지 영역 -->
			<div id="message" style="display: none;"></div>
		</form>
	</div>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Sidebar</title>
  <link rel="stylesheet" href="resources/css/Sidebar.css">
      	<!-- 모달 스타일 연결 -->
        <link rel="stylesheet" href="resources/css/modal.css">
</head>

<body>
  <!-- 사이드바 영역, 클래스로 수정 -->
  <div class="left-sidebar">
    <img src="resources/img/home_logo.png" alt="페블잇 로고" id="pebble_it_menu_logo">
    <div id="my_info">
      <table id="info_output">
        <tr>
          <td id="profile_td"><img src="resources/img/no_profile_img.png" alt="프로필 이미지" id="profile_img"></td>
          <td>닉네임님<br>오늘도 할 수 있어요!</td>
        </tr>
        <tr>
          <td colspan="2">관심운동: 수영<br>포인트: 999,999P</td>
        </tr>
        <tr>
          <td colspan="2">
            <a href="#"><div id="info_config" class="btn"><img src="resources/img/info_config_img.png">&nbsp;&nbsp;내 정보 변경</div></a>
          </td>
        </tr>
      </table>
    </div>
    <nav class="sidebar-menu">
      <a href="home_content.html"><div id="menu_name" class="menu01"><img src="resources/img/home_menu_img.png">&nbsp;&nbsp;홈</div></a>
      <a href="calender.html"><div id="menu_name"><img src="resources/img/calender_menu_img.png">&nbsp;&nbsp;일정</div></a>
      <a href="todo_list.html"><div id="menu_name"><img src="resources/img/todo_menu_img.png">&nbsp;&nbsp;할일</div></a>
      <a href="diary_list.html"><div id="menu_name"><img src="resources/img/diary_menu_img.png">&nbsp;&nbsp;기록</div></a>
      <a href="#"><div id="menu_name"><img src="resources/img/challenge_menu_img.png">&nbsp;&nbsp;챌린지</div></a>

      <a href="#"><div id="logout_menu"><img src="resources/img/logout_img.png">&nbsp;&nbsp;로그아웃</div></a>
    </nav>
  </div>

  <!-- 내 정보 변경 모달창-->
  <div id="modalContainer" class="modal">
    <!-- 내 정보 변경 모달 내용 -->
    <!-- 내 정보 변경 폼 -->
  <form action="/UpdateProfile" method="post" enctype="multipart/form-data">
    <div id="todo_post_modal" class="modal-content_config">
      <table class="modal_table">
        <tr class="tr_line">
          <td>내 정보 변경 <span class="resign">회원 탈퇴</span>
            <!-- 모달 내에서 취소 버튼이 1개밖에 동작하지 않아 일단 주석처리 -->
            <!-- <button id="modalCloseButton" class="close">X</button> -->
          </td>
        </tr>
        <tr>
          <td>
          <!-- 프로필 사진 업로드 -->
          <div class="profile_img">
              <label for="profile-upload">프로필 사진 선택</label>
              <input id="profile-upload" name="profileImg" type="file" accept="image/*" style="display: none;" />
              <img id="profile-preview" src="#" alt="미리보기" style="display:none; width: 150px; height: 150px; margin-top: 10px;" />
          </div>

          <div class="my_id">ID: smilecat</div>

          <!-- 닉네임 입력 -->
          <div class="Join">
              <input type="text" name="name" id="nickname" placeholder="닉네임 (1~8자 영문, 한글, 숫자)" required>
              <button type="button" id="check-nick" class="check-btn">중복확인</button>
              <small id="nick-error" class="error-message"></small>
          </div>

          <!-- 기존 비밀번호 입력 -->
          <div class="Join">
              <input type="password" name="pw" id="old_password" class="password"
              placeholder="기존 비밀번호 입력 (8~16자의 영문 대/소문자, 숫자)" required
              pattern="^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,16}$"
              title="비밀번호는 8~16자 사이의 영문(대/소문자)과 숫자를 포함해야 합니다." >
              <small id="password-error" class="error-message"></small>
          </div>

                    <!-- 새 비밀번호 입력 -->
                    <div class="Join">
                      <input type="password" name="pw" id="new_password" class="password"
                      placeholder="새 비밀번호 입력 (8~16자의 영문 대/소문자, 숫자)" required
                      pattern="^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,16}$"
                      title="비밀번호는 8~16자 사이의 영문(대/소문자)과 숫자를 포함해야 합니다." >
                      <small id="password-error" class="error-message"></small>
                  </div>

          <!-- 관심 운동 선택 -->
          <div class="sport_check">
              <p class="sport_text">| 관심 운동 선택 (택1)</p>
              <label><input type="radio" name="favoriteSport" value="헬스" required> 헬스</label>
              <label><input type="radio" name="favoriteSport" value="수영" required> 수영</label>
              <label><input type="radio" name="favoriteSport" value="테니스" required> 테니스</label>
          </div>

          <!-- 운동 수준 선택 -->
          <div class="sport_check">
            <p class="sport_text">| 나의 운동 수준 (택1)</p>
              <label><input type="radio" name="sportLevel" value="lev1" required> 초급자</label>
              <label><input type="radio" name="sportLevel" value="lev2" required> 중급자</label>
              <label><input type="radio" name="sportLevel" value="lev3" required> 전문가</label>
          </div>

          <!-- 메시지 영역 -->
          <div id="message">※ 운동, 수준 변경 시 새로운 운동정보, 추천할일은 다음날 반영됩니다.</div>

          <!-- 버튼 영역 -->
          <div class="button-container">
            <div class="buttens">
            <button type="button" id="cancel-button" class="close">취소</button>
            <button type="submit" id="submit-button" class="join-btn">등록</button>
          </div>
          </div>

          </td>
        </tr>
      </table>
    </form>
    </div>
    <!-- 내 정보 변경 모달 내용 끝 -->
  </div>
        <!-- 내 정보 변경 모달창 끝-->

        <!-- JavaScript 파일 연결 -->
	<script src="resources/js/modal.js"></script>
</body>
</html>

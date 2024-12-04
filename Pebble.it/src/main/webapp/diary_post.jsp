<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang = "ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Pebble.it</title>

<!-- Summernote CSS -->
<link rel="stylesheet" href="resources/summernote/summernote-lite.css">

<!-- Custom CSS -->
<link rel="stylesheet" href="resources/css/diary_edit.css">

<!-- jQuery (필수) -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<!-- Summernote JS -->
<script src="resources/summernote/summernote-lite.js"></script>

<!-- Summernote 한국어 언어 파일 -->
<script src="resources/summernote/summernote-ko-KR.js"></script>

<!-- Custom JS -->
<script src="resources/js/diary_edit.js"></script>

<!-- 사이드바 스타일 시트 -->
<link rel="stylesheet" href="resources/css/Sidebar.css" />

</head>

<body>

	<%
	String userID = null;
	if (session.getAttribute("userID") != null) {
		userID = (String) session.getAttribute("userID");
	}
	%>
	<!-- 사이드바 -->
	<div class="sidebar">
		<script>
			$(document).ready(
					function() {
						$(".sidebar").load(
								"Sidebar.jsp",
								function(response, status, xhr) {
									if (status == "error") {
										$(".sidebar").load(
												"/Pebble.it/Sidebar.jsp");
									}
								});
					});
		</script>
	</div>

	<!-- 메인 콘텐츠 -->
	<div class="main-content">

		<span class="post_title">나의 기록 등록</span>
			
			<!-- 제목 입력 -->
			<div class="title-input-container">
			
				<input type="text" id="editor-title" class="title-input"
					placeholder="제목을 입력하세요.">


		</div>

			<!-- Summernote 에디터 -->
			<div id="summernote"></div>
			<textarea name="diaryContent" id="diaryContent" hidden></textarea>

			<!-- 버튼 컨테이너 -->
			<div class="button-container">
				<button id="cancel-btn" class="action-btn cancel-btn">취소</button>
				<button id="submit-btn" class="action-btn submit-btn">등록</button>
			</div>
	</div>


</body>

</html>
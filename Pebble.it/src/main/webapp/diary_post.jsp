<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>diary_post</title>

    <!-- Summernote CSS -->
    <link rel="stylesheet" href="resources/summernote/summernote-lite.min.css">

    <!-- Custom CSS -->
    <link rel="stylesheet" href="resources/css/editor_euna.css">

    <!-- jQuery (필수) -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

    <!-- Summernote JS -->
    <script src="resources/summernote/summernote-lite.min.js"></script>

    <!-- Summernote 한국어 언어 파일 -->
    <script src="resources/summernote/summernote-ko-KR.min.js"></script>

    <!-- Custom JS -->
    <script src="resources/js/editor_euna.js" defer></script>
</head>
<body>
  <!-- 사이드바 -->
    <div class="sidebar">
        <script>
            $(document).ready(function () {
                // 사이드바 동적 로드
                $(".sidebar").load("Sidebar.html");
            });
        </script>
    </div>

    <!-- 메인 콘텐츠 -->
   
    <div class="main-content">
		<form action="diary" method="post">
        <!-- 제목 입력 -->
        <div class="title-input-container">
        
            <input type="text" id="editor-title" name="title" class="title-input" placeholder="제목을 입력하세요.">
        </div>

        <!-- Summernote 에디터 -->
        <div id="summernote" name="note"></div>

        <!-- 버튼 컨테이너 -->
        <div class="button-container">
            <button id="cancel-btn" class="action-btn cancel-btn" onclick="location.href='diary_list.jsp'; return false;">취소</button>
            <button id="submit-btn" class="action-btn submit-btn" button type="submit">등록</button>
        </div>
        </form>	
    </div>
</body>
</html>
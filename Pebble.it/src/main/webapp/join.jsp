<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pebblt.it</title>
</head>
<body>
	<!-- 회원가입 기능 구현 -->
	<h1>Pebble.it 회원가입</h1>
	<form action="JoinService" method="post">
		<label for="id">아이디:</label> <input type="text" id="id" name="id"
			placeholder="아이디를 입력하세요" required> <br>
		<br> <label for="pw">비밀번호:</label> <input type="password" id="pw"
			name="pw" placeholder="비밀번호를 입력하세요" required> <br>
		<br> <label for="name">이름:</label> <input type="text" id="name"
			name="name" placeholder="이름을 입력하세요" required> <br>
		<br> <label for="profileImg">프로필 이미지:</label> <input type="text"
			id="profileImg" name="profileImg" placeholder="이미지 URL을 입력하세요">
		<br>
		<br> <label for="favoriteSport">좋아하는 스포츠:</label> <input
			type="text" id="favoriteSport" name="favoriteSport"
			placeholder="좋아하는 스포츠를 입력하세요" required> <br>
		<br> <label for="sportLevel">스포츠 레벨:</label> <input type="text"
			id="sportLevel" name="sportLevel" placeholder="스포츠 레벨을 입력하세요"
			required> <br>
		<br>
		<button type="submit">회원가입</button>
	</form>

	<!-- 에러 메시지 출력 -->
	<%
	// 에러 파라미터 확인
	String error = request.getParameter("error");
	if ("1".equals(error)) {
	%>
	<p style="color: red;">회원가입에 실패했습니다. 다시 시도하세요.</p>
	<%
	}
	%>

</body>
</html>
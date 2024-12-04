<%@page import="com.Pebble.it.model.todoDAO"%>
<%@page import="org.apache.ibatis.reflection.SystemMetaObject"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.Pebble.it.model.todoDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Pebble.it</title>

	<!-- jQuery 추가 -->
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

	<!-- 사이드바 스타일 연결 -->
	<link rel="stylesheet" href="resources/css/Sidebar.css">
	<!-- 투두리스트 스타일 연결 -->
	<link rel="stylesheet" href="resources/css/todo_list.css">
	<!-- 모달 스타일 연결 -->
	<link rel="stylesheet" href="resources/css/modal.css">
	<!-- AI 스타일 연결 -->
	<link rel="stylesheet" href="resources/css/ai.css">
</head>
<body>
		<!-- 레이아웃 컨테이너 -->
	<!-- 사이드바 -->
	<div class="sidebar">
		<script>
			$(document).ready(
					function() {
						$(".sidebar").load(
								"Sidebar.jsp",
								function(response, status, xhr) {
									if (status == "error") {
										// 만약 Sidebar.html이 로드되지 않으면 Sidebar.html으로 대체 시도
										$(".sidebar").load(
												"/Pebble.it/Sidebar.jsp");
									}
								});
					});
					</script>
	</div>
	<%
	// todo_list.jsp 페이지가 실행되자 마자
	// todoDAO 클래스를 통하여 showTodo()호출!
	
	// 로그인한 사용자의 내용 띄우기
	// 0. session에서 로그인 사용자의 id 값 가져오기
	todoDTO result = (todoDTO) session.getAttribute("result");
	ArrayList<todoDTO> list = null;
	if(result != null){
		System.out.println("로그인한 사용자 정보:"+result.getUserId());
		// 1. todoDAO 객체 생성
		todoDAO dao = new todoDAO();
		list = dao.showTodo(result.getUserId());
	}
	%>
	<!-- 메인 콘텐츠 영역 -->
	<div class="main-content">
		<!-- 상단 툴바 -->
		<div class="calendar-toolbar">
			<!-- 할일 등록 버튼 -->
			<button id="registerTodoBtn" class="todo_btn">+ 할일 등록</button>
		</div>

				<div class="content-wrapper">
					<!-- 왼쪽 컨텐츠 -->
					<div class="left-content">
						<!-- 할일 목록-->
			<table width="100%" class="todo_content">
				<%
				if (result != null){
					for (int i =0; i< list.size();i++){
				
				%>
				<tr>
					<td class="tr_line"><label><input type="checkbox"><span><%= list.get(i).getTodoTitle() %></span></label></td>
					<td width="40px" class="tr_line">
                            <img src="resources/img/todo_edit_img.png" alt="수정" id="editBtn_<%= i %>" class="todo_btn">
                        </td>
                        <td width="40px" class="tr_line">
                            <img src="resources/img/todo_delete_img.png" alt="삭제" id="deleteBtn_<%= i %>" class="todo_btn">
                        </td>
                </tr>
                <%}
				}
				%>
			</table>
			<!-- 할일 목록 하단 버튼 영역-->
			<div class="pagination">
				<!-- 왼쪽 하단: 페이지 정보 -->
				<div class="page-info">
					<span id="page-info">1</span>/<span id="total-pages">20Page</span>
				</div>
				
				<!-- 오른쪽 하단: 이전 및 다음 버튼 -->
				<div class="page-buttons">
					<button id="prev-page" class="pagination-btn"><</button>
					<button id="next-page" class="pagination-btn">></button>
				</div>
			</div>
		</div>
		
        
					<!-- 오른쪽 컨텐츠 -->
					<div class="right-content">
						<!-- 오늘의 추천할일 출력 -->
						<div class="todo_recomment_title">
							오늘의 추천할일
						  </div>
						  <div id="AIoutput_todo" class="scroll-content_todo">
							<div class="todo_recomment_content">
							  <button id="generateBtn">추천할일 생성하기</button>
							</div>
						  </div>
						  <script type="module" src="resources/js/AI_recommend_todo.js"></script>
						</div>
						</div>
					</div>

    
     <!-- 할일 등록 모달창 (ID와 클래스 변경)-->
    <div id="todoRegisterModal" class="modal">
        <div class="modal-content">
            <table class="modal_table">
                <tr class="tr_line">
                    <td>할일 등록</td>
                </tr>
                <tr>
                    <td><input type="text" id="registerTodoContent" placeholder="할일 내용" name="todoTitle"></td>
                </tr>
                <tr>
                    <td>
                        <div class="button-container">
                            <button id="cancelRegisterTodo" class="todo_btn close">취소</button>
                            <button id="submitRegisterTodo" class="todo_btn submit-button" type="submit">등록</button>
                        </div>
                    </td>
                </tr>
            </table>
        </div>
    </div>

    <!-- 할일 수정 모달창 (ID와 클래스 변경)-->
    <div id="todoEditModal" class="modal">
        <div class="modal-content">
            <table class="modal_table">
                <tr class="tr_line">
                    <td>할일 수정</td>
                </tr>
                <tr>
                    <td><input type="text" id="editTodoContent" value="들어가 있는 할일 내용"></td>
                </tr>
                <tr>
                    <td>
                        <div class="button-container">
                            <button id="cancelEditTodo" class="todo_btn close">취소</button>
                            <button id="submitEditTodo" class="todo_btn submit-button">수정</button>
                        </div>
                    </td>
                </tr>
            </table>
        </div>
    </div>

    <!-- 할일 삭제 모달창 (ID와 클래스 변경)-->
    <div id="todoDeleteModal" class="modal">
        <div class="modal-content">
            <table class="modal_table">
                <tr class="tr_line">
                    <td>할일 삭제</td>
                </tr>
                <tr>
                    <td id="deleteMsg">할일을 삭제하시겠습니까?</td>
                </tr>
                <tr>
                    <td>
                        <div class="button-container">
                            <button id="cancelDeleteTodo" class="todo_btn close">취소</button>
                            <button id="submitDeleteTodo" class="todo_btn submit-button">삭제</button>
                        </div>
                    </td>
                </tr>
            </table>
        </div>
    </div>

    <!-- JavaScript 파일 연결 -->
    <script src="resources/js/to_do_list.js"></script>
    <script src="resources/js/todo_check.js"></script>

    <script>
        // 할일 등록 모달 열기
        $("#registerTodoBtn").click(function() {
            $("#todoRegisterModal").show();
        });

        // 할일 등록 모달 취소
        $("#cancelRegisterTodo").click(function() {
            $("#todoRegisterModal").hide();
        });

        // 할일 등록
        $("#submitRegisterTodo").click(function() {
            var todoContent = $("#registerTodoContent").val();
            // 서버로 데이터 전송
            // 등록 후 모달 닫기
            $("#todoRegisterModal").hide();
        });

        // 할일 수정 모달 열기
        $("img[id^='editBtn_']").click(function() {
            var index = $(this).attr("id").split("_")[1];  // index 추출
            var todoContent = $("tr").eq(index).find("span").text(); // 해당 내용 가져오기
            $("#editTodoContent").val(todoContent);
            $("#todoEditModal").show();
        });

        // 할일 수정 취소
        $("#cancelEditTodo").click(function() {
            $("#todoEditModal").hide();
        });

        // 할일 수정
        $("#submitEditTodo").click(function() {
            var editedContent = $("#editTodoContent").val();
            // 수정된 내용 서버로 전송
            $("#todoEditModal").hide();
        });

        // 할일 삭제 모달 열기
        $("img[id^='deleteBtn_']").click(function() {
            var index = $(this).attr("id").split("_")[1]; // index 추출
            $("#todoDeleteModal").show();
        });

        // 할일 삭제 취소
        $("#cancelDeleteTodo").click(function() {
            $("#todoDeleteModal").hide();
        });

        // 할일 삭제
        $("#submitDeleteTodo").click(function() {
            // 해당 할일 삭제 처리
            $("#todoDeleteModal").hide();
        });
    </script>
</body>
</html>
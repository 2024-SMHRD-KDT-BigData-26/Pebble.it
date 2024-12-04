package com.Pebble.it.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Pebble.it.model.DiaryDAO;
import com.Pebble.it.model.DiaryDTO;

@WebServlet("/diary")
public class diaryService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 인코딩
		request.setCharacterEncoding("UTF-8");

		// 파라미터 값 가져오기
		String diaryTitle = request.getParameter("diaryTitle"); // 입력된 제목
		String diaryContent = request.getParameter("diaryContent"); // 입력된 내용

		// 세션에서 사용자 ID 가져오기
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");

		// 객체 생성 데이터 세팅
		DiaryDTO dto = new DiaryDTO();
		dto.setUser_id(userId);
		dto.setDiary_title(diaryTitle);
		dto.setDiary_content(diaryContent);

		// DAO 호출
		DiaryDAO dao = new DiaryDAO();
		int result = dao.writeDiary(dto);

		// 결과에 따른 처리
		if (result > 0) {
			// AJAX 요청일 경우 성공 응답 전송
			if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
				response.setStatus(HttpServletResponse.SC_OK);
				response.getWriter().write("success");
			} else {
				// 일반 요청일 경우 다이어리 목록 페이지로 이동
				response.sendRedirect("diary_list.jsp");
			}
		} else {
			// 실패 시
			if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.getWriter().write("failure");
			} else {
				response.getWriter().write("<script>alert('등록 실패'); history.back();</script>");
			}
		}
	}
}

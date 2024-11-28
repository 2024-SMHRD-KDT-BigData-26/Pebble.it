package com.Pebble.it.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Pebble.it.model.MemberDAO;
import com.Pebble.it.model.MemberDTO;

@WebServlet("/Login")
public class LoginService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 한글 인코딩 설정
		request.setCharacterEncoding("UTF-8");
		
		// 요청에서 ID와 비밀번호를 가져오기
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		// pw는 출력하지 않음
		System.out.println("로그인 시도 - ID: " + id + ", PW: " + pw); // 입력값 로그 출력
		
		// DAO를 통해 사용자 정보 확인
		MemberDAO dao = new MemberDAO();
		MemberDTO user = dao.login(id, pw);
	
		if (user != null) {
			System.out.println("로그인 성공: " + user.getId());
			// 로그인 성공시 세션 생성 및 홈페이지로 리다이렉트
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", user);
			session.setMaxInactiveInterval(60*60); // 세션 유지 시간 : 1시간
			
			// home.jsp로 리다이렉트
			response.sendRedirect("home.jsp");
		} else {
		    // 로그인 실패: index.jsp로 이동하며 오류 메시지 전달
		    System.out.println("로그인 실패: 아이디 또는 비밀번호 불일치");
		    request.setAttribute("errorMessage", "아이디 또는 비밀번호가 잘못되었습니다.");
		    request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}
}



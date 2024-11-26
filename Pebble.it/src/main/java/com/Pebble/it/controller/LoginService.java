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
		// 요청에서 ID와 비밀번호를 가져오기
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		// DAO를 통해 사용자 정보 확인
		MemberDAO dao = new MemberDAO();
		MemberDTO user = dao.login(id,pw);
	
		if (user != null) {
			// 로그인 성공시 세션 생성 및 홈페이지로 리다이렉트
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", user);
			
			// home.jsp로 리다이렉트
			response.sendRedirect("home.jsp");
		} else { 
			// 로그인 실패: index.jsp로 단순 리다이렉트
			response.sendRedirect("index.jsp");
		}
		
	}
		
		
}



package com.Pebble.it.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.Pebble.it.model.MemberDAO;
import com.Pebble.it.model.MemberDTO;

@WebServlet("/Join")
public class JoinService extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	// 한글 인코딩
    	request.setCharacterEncoding("UTF-8");
    	
    	// 요청 파라미터에서 데이터 가져오기
        String id = request.getParameter("id");
        String pw = request.getParameter("pw");
        String name = request.getParameter("name");
        String profileImg = request.getParameter("profileImg");
        String favoriteSport = request.getParameter("favoriteSport");
        String sportLevel = request.getParameter("sportLevel");
        
        // DTO 생성
        MemberDTO dto = new MemberDTO();
		dto.setId(id);
		dto.setPw(pw);
		dto.setName(name);
		dto.setProfileImg(profileImg);
		dto.setFavoriteSport(favoriteSport);
		dto.setSportLevel(sportLevel);
		
        // DAO를 통해 회원가입 처리
        MemberDAO dao = new MemberDAO();
        int result = dao.join(dto);

        // 결과에 따른 페이지 이동
        if (result > 0) {
            // 회원가입 성공: JavaScript를 통해 메시지 띄운 후 로그인 페이지로 이동
			// HttpSession session = request.getSession();
			// session.setAttribute("email", email);
			
			RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
			rd.forward(request, response);
			
        } else {
            // 회원가입 실패: 회원가입 페이지로 리다이렉트
            response.sendRedirect("join.jsp?error=1");
        }
    }
}


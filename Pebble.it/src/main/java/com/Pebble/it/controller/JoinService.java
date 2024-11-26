package com.Pebble.it.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.Pebble.it.model.MemberDAO;
import com.Pebble.it.model.MemberDTO;

@WebServlet("/JoinService")
public class JoinService extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 요청 파라미터에서 데이터 가져오기
        String id = request.getParameter("id");
        String pw = request.getParameter("pw");
        String name = request.getParameter("name");
        String profileImg = request.getParameter("profileImg");
        String favoriteSport = request.getParameter("favoriteSport");
        String sportLevel = request.getParameter("sportLevel");
        
        // DTO 생성
        MemberDTO member = new MemberDTO(id, pw, name, profileImg, favoriteSport, sportLevel);

        // DAO를 통해 회원가입 처리
        MemberDAO dao = new MemberDAO();
        boolean result = dao.register(member);

        // 결과에 따른 페이지 이동
        if (result) {
            // 회원가입 성공: JavaScript를 통해 메시지 띄운 후 로그인 페이지로 이동
            response.setContentType("text/html; charset=UTF-8");
            response.getWriter().println(
                "<script>" +
                    "alert('회원가입이 성공적으로 완료되었습니다. 로그인 페이지로 이동합니다.');" +
                    "location.href='index.jsp';" +
                "</script>"
            );
        } else {
            // 회원가입 실패: 회원가입 페이지로 리다이렉트
            response.sendRedirect("join.jsp?error=1");
        }
    }
}


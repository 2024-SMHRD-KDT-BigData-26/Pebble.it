package com.Pebble.it.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Logout")
public class LogoutService extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // POST 요청 처리
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 세션 꺼내오기
        HttpSession session = request.getSession();

        // 2. 세션 무효화 시켜주기(모든 데이터 지우기)
        session.invalidate();

        // 3. index.jsp로 redirect 방식으로 이동
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    // GET 요청 처리 (추가)
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // POST 요청과 동일하게 처리
        doPost(request, response);
    }
}

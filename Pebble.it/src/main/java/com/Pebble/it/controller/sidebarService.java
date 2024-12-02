package com.Pebble.it.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Pebble.it.model.MemberDTO;
import com.Pebble.it.model.MemberDAO;

@WebServlet("/sidebar")
public class sidebarService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    request.setCharacterEncoding("UTF-8");

	    // 세션 검사: 세션이 없거나 userId가 없는 경우 로그인 페이지로 리다이렉트
	    HttpSession session = request.getSession(false);

	    if (session == null || session.getAttribute("loginUser_ID") == null) {
	        response.sendRedirect("index.jsp");
	        return;
	    }

	    // 세션에서 로그인한 사용자 정보 가져오기
	    String id = (String)session.getAttribute("loginUser.getUSER_ID()");
	    
		PrintWriter out = response.getWriter();
		
		out.print(id);
	    

	}
	
}


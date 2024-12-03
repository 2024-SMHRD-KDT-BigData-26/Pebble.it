package com.Pebble.it.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Pebble.it.model.todoDAO;
import com.Pebble.it.model.todoDTO;

@WebServlet("/todo")
public class todoService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		// 1. 한글 인코딩
		request.setCharacterEncoding("UTF-8");
		
		// 2. 요청으로부터 데이터 꺼내오기
		String todoTitle = request.getParameter("todoTitle");
		
		// 3. 데이터 처리하기 ->  DB전달
		todoDTO dto = new todoDTO();
		dto.setTodoTitle(todoTitle);
		
		todoDAO dao = new todoDAO();
		int result = dao.insertTodo(dto);
		
		// 결과에 따른 화면 출력 작업
		RequestDispatcher rd = request.getRequestDispatcher("todo_list.jsp");
		rd.forward(request, response);
	}
}
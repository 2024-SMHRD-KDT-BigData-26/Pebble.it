package com.Pebble.it.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Pebble.it.model.todoDAO;
import com.Pebble.it.model.todoDTO;

@WebServlet("/todo")
public class todoService extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 한글 인코딩 처리
        request.setCharacterEncoding("UTF-8");

        // 폼 데이터 수집
      
        String todoTitle = request.getParameter("todoTitle");

        // 입력값 검증
        if (todoTitle == null || todoTitle.trim().isEmpty()) {
            response.sendRedirect("todo_list.jsp?error=empty_title");
            return; 
        }

        // TodoDTO 객체 생성
        todoDTO dto = new todoDTO();
       
        dto.setTodoTitle(todoTitle);
       

        // DAO 호출
        todoDAO dao = new todoDAO();
        int result = dao.insertTodo(dto);

        // 결과 처리
        if (result > 0) {
            response.sendRedirect("todo_list.jsp"); // 성공 시 목록 페이지로 이동
        } else {
            response.sendRedirect("error.jsp"); // 실패 시 에러 페이지로 이동
        }
    }
}
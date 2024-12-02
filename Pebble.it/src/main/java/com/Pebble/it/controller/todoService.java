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
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");

        if (userId == null) {
            response.sendRedirect("index.jsp"); // 세션에 userId가 없으면 로그인 페이지로 이동
            return;
        }

        // 한글 인코딩 처리
        request.setCharacterEncoding("UTF-8");

        // 폼 데이터 수집
        String todoTitle = request.getParameter("todoTitle");

        // TodoDTO 생성
        todoDTO dto = new todoDTO();
        dto.setUserId(userId); // userId 설정
        dto.setTodoTitle(todoTitle);
        dto.setTodoStatus("PENDING");

        // DAO 호출
        todoDAO dao = new todoDAO();
        int result = dao.insertTodo(dto);

        // 결과 처리
        if (result > 0) {
            response.sendRedirect("/todo"); // 할일 목록으로 이동
        } else {
            request.setAttribute("error", "할일 등록 실패");
            RequestDispatcher rd = request.getRequestDispatcher("todo_list.jsp");
            rd.forward(request, response);
        }
    }
    }

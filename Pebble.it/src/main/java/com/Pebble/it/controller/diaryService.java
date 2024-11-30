package com.Pebble.it.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
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

		request.setCharacterEncoding("UTF-8");

		String title = request.getParameter("title");
		String note = request.getParameter("note");
		
		DiaryDTO dto = new DiaryDTO();
		dto.setTitle(title);
		dto.setNote(note);

		DiaryDAO dao = new DiaryDAO();
		int result = dao.writeNote(dto);
		
		if(result >0) {
			HttpSession session = request.getSession();
			session.setAttribute("title", title);
			
			RequestDispatcher rd = request.getRequestDispatcher("diary_list.jsp");
			rd.forward(request, response);
		}
	}

}

package com.Pebble.it.controller;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Pebble.it.model.CalendarDAO;
import com.Pebble.it.model.CalendarDTO;

@WebServlet("/Calendar")
public class CalendarService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 한글 인코딩 처리
		request.setCharacterEncoding("UTF-8");

        // 클라이언트에서 받은 파라미터 읽기
        String title = request.getParameter("title");
        String date = request.getParameter("datetime");
        String memo = request.getParameter("memo");

        // 입력값 검증 (필요한 경우)
        if (title == null || title.isEmpty() || date == null || date.isEmpty()) {
            response.sendRedirect("error.jsp");
            return;
        }

        // 날짜 형식 변환
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate parsedDate = LocalDate.parse(date, formatter);

        // DTO 객체 생성 및 데이터 설정
        CalendarDTO dto = new CalendarDTO();
        dto.setTitle(title);
        dto.setDatetime(parsedDate);
        dto.setMemo(memo);

        // DAO 호출 및 결과 처리
        CalendarDAO dao = new CalendarDAO();
        int result = dao.insertCalendar(dto);

        if (result > 0) {
            response.sendRedirect("calendar.jsp");
        } else {
            response.sendRedirect("error.jsp");
        }
    }
}
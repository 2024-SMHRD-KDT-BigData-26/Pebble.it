package com.Pebble.it.controller;

import java.io.IOException;
import java.sql.Date;
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
		// Form 데이터 받아오기
		String title = request.getParameter("title");
		String datetime = request.getParameter("datetime");
		String allDay = request.getParameter("allDay");
		String memo = request.getParameter("memo");

		// 종일 체크 여부 처리
		boolean isAllDay = "on".equals(allDay);

		 // 날짜와 시간 형식 변환
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime parsedDateTime = LocalDateTime.parse(datetime, formatter);

        // DTO 객체 생성
        CalendarDTO dto = new CalendarDTO();
        dto.setTitle(title);
        dto.setDatetime(parsedDateTime);
        dto.setAllDay(isAllDay);
        dto.setDatetime(parsedDateTime);
        
        // DAO 호출
        CalendarDAO dao = new CalendarDAO();
        int result = dao.insertCalendar(dto);

        // 결과에 따라 페이지 이동
        if (result > 0) {
            // 성공 시
            response.sendRedirect("calendar.jsp");
        } else {
            // 실패 시
            response.sendRedirect("error.jsp");
        }
    }
}

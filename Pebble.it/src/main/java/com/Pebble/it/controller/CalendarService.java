package com.Pebble.it.controller;

import java.io.IOException;
import java.util.List;
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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        // 사용자 입력 데이터 받아오기
        String userId = request.getParameter("USER_ID");
        String title = request.getParameter("CAL_TITLE");
        String content = request.getParameter("CAL_CONTENT");
        String startDate = request.getParameter("CAL_ST_DT");
        String endDate = request.getParameter("CAL_ED_DT");
        String color = request.getParameter("CAL_COLOR");

        CalendarDTO calendar = new CalendarDTO(0, userId, title, content, startDate, endDate, color, "ACTIVE");
        CalendarDAO dao = new CalendarDAO();

        int result = dao.insertCalendar(calendar);

        if (result > 0) {
            response.sendRedirect("calendar.jsp"); // 성공 시 캘린더 페이지로 리다이렉트
        } else {
            response.getWriter().write("<script>alert('일정 등록 실패!'); history.back();</script>");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String userId = request.getParameter("USER_ID");
        CalendarDAO dao = new CalendarDAO();

        List<CalendarDTO> calendarList = dao.getCalendars(userId);

        request.setAttribute("calendarList", calendarList);
        request.getRequestDispatcher("calendar.jsp").forward(request, response);
    }
}

package com.Pebble.it.controller;

import com.Pebble.it.model.CalendarDAO;
import com.Pebble.it.model.CalendarDTO;
import com.Pebble.it.util.MyBatisUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/events")
public class CalendarService extends HttpServlet {
    private SqlSessionFactory sqlSessionFactory;

    @Override
    public void init() throws ServletException {
        sqlSessionFactory = MyBatisUtil.getSqlSessionFactory();
        System.out.println("[DEBUG] CalendarService initialized.");
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String calIdxParam = request.getParameter("cal_idx"); // 클라이언트에서 보낸 cal_idx
        String userId = request.getParameter("user_id"); // 특정 사용자 ID로 일정 필터링 (필요시)
        System.out.println("[DEBUG] Received cal_idx: " + calIdxParam);
        System.out.println("[DEBUG] Received user_id: " + userId);

        try (SqlSession session = sqlSessionFactory.openSession()) {
            CalendarDAO dao = new CalendarDAO(session);

            // 특정 일정의 상세 정보 요청 (cal_idx가 있을 때)
            if (calIdxParam != null && !calIdxParam.isEmpty()) {
                try {
                    int cal_idx = Integer.parseInt(calIdxParam); // cal_idx를 Integer로 변환
                    CalendarDTO event = dao.getEventById(cal_idx); // 특정 일정 가져오기

                    if (event != null) {
                        System.out.println("[DEBUG] Event found: " + event);
                        ObjectMapper mapper = new ObjectMapper();
                        response.setContentType("application/json");
                        response.getWriter().write(mapper.writeValueAsString(event));
                    } else {
                        System.out.println("[DEBUG] Event not found for cal_idx: " + cal_idx);
                        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                        response.getWriter().write("{\"error\":\"Event not found\"}");
                    }
                } catch (NumberFormatException e) {
                    System.err.println("[ERROR] Invalid cal_idx format: " + calIdxParam);
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    response.getWriter().write("{\"error\":\"Invalid cal_idx format\"}");
                }
                return; // 특정 일정 처리 후 메서드 종료
            }

            // 일정 목록 요청 (cal_idx가 없을 때)
            List<CalendarDTO> events;
            if (userId != null && !userId.isEmpty()) {
                System.out.println("[DEBUG] Fetching events for user_id: " + userId);
                events = dao.getEventsByUserId(userId); // 특정 사용자 ID로 필터링
            } else {
                System.out.println("[DEBUG] Fetching all events");
                events = dao.getAllEvents(); // 모든 일정 가져오기
            }

            List<Map<String, Object>> eventList = new ArrayList<>();
            for (CalendarDTO event : events) {
                Map<String, Object> eventMap = new HashMap<>();
                eventMap.put("id", event.getCal_idx());
                eventMap.put("title", event.getCal_title());
                eventMap.put("start", event.getCal_st_dt());
                eventMap.put("end", event.getCal_ed_dt());
                eventMap.put("color", event.getCal_color());
                eventList.add(eventMap);
            }

            ObjectMapper mapper = new ObjectMapper();
            response.setContentType("application/json");
            response.getWriter().write(mapper.writeValueAsString(eventList));

        } catch (Exception e) {
            System.err.println("[ERROR] Error while processing request: " + e.getMessage());
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\":\"An unexpected error occurred\"}");
        }
    }




    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("[DEBUG] POST request received.");
        try {
            // 요청 데이터를 출력
            BufferedReader reader = request.getReader();
            StringBuilder requestData = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                requestData.append(line);
            }
            System.out.println("[DEBUG] Received data: " + requestData);

            ObjectMapper mapper = new ObjectMapper();
            CalendarDTO event = mapper.readValue(requestData.toString(), CalendarDTO.class);

            try (SqlSession session = sqlSessionFactory.openSession()) {
                CalendarDAO dao = new CalendarDAO(session);
                if (event.getCal_idx() == 0) {
                    System.out.println("[DEBUG] Inserting new event: " + event);
                    dao.insertEvent(event);
                } else {
                    System.out.println("[DEBUG] Updating existing event: " + event);
                    dao.updateEvent(event);
                }
                session.commit();
            }

            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("{\"message\":\"Success\"}");
        } catch (Exception e) {
            System.err.println("[ERROR] Exception in POST request: " + e.getMessage());
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\":\"Invalid request data\"}");
        }
    }


    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("[DEBUG] DELETE request received.");
        try (SqlSession session = sqlSessionFactory.openSession()) {
            CalendarDAO dao = new CalendarDAO(session);
            String cal_idx = request.getParameter("cal_idx");

            if (cal_idx != null) {
                System.out.println("[DEBUG] Deleting event with cal_idx: " + cal_idx);
                dao.deleteEvent(Integer.parseInt(cal_idx));
                session.commit();
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().write("{\"message\":\"Success\"}");
            } else {
                System.out.println("[ERROR] cal_idx is missing in DELETE request.");
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("{\"error\":\"Missing cal_idx parameter\"}");
            }
        } catch (Exception e) {
            System.err.println("[ERROR] Exception in DELETE request: " + e.getMessage());
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\":\"Server error\"}");
        }
    }
    
    // 업데이트
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try (BufferedReader reader = request.getReader()) {
            ObjectMapper mapper = new ObjectMapper();
            CalendarDTO event = mapper.readValue(reader, CalendarDTO.class);

            try (SqlSession session = sqlSessionFactory.openSession()) {
                CalendarDAO dao = new CalendarDAO(session);
                dao.updateEvent(event); // 데이터베이스 업데이트

                session.commit(); // 변경사항 커밋
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().write("{\"message\":\"Event updated successfully\"}");
            } catch (Exception e) {
                e.printStackTrace();
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write("{\"error\":\"Failed to update event\"}");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\":\"Invalid request format\"}");
        }
    }

}

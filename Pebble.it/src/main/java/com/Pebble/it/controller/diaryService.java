package com.Pebble.it.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Pebble.it.model.DiaryDAO;
import com.Pebble.it.model.DiaryDTO;

@WebServlet("/diary")
public class diaryService extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 한글 인코딩 처리
        request.setCharacterEncoding("UTF-8");

        // 파라미터 수집
        String userId = "defaultUser"; // 로그인 없이 작성 가능하도록 기본값 설정
        String title = request.getParameter("title");
        String content = request.getParameter("note");

        // 입력값 검증
        if (title == null || title.isEmpty() || content == null || content.isEmpty()) {
            response.sendRedirect("diary_list.jsp?error=empty_fields");
            return;
        }

        // DTO 생성 및 데이터 설정
        DiaryDTO dto = new DiaryDTO();
        dto.setUserId(userId);
        dto.setDiaryCategory("Default"); // 카테고리는 기본값으로 설정
        dto.setDiaryTitle(title);
        dto.setDiaryContent(content);
        dto.setDiaryFile(""); // 파일 첨부 기능이 없으므로 기본값 설정

        // DAO 호출
        DiaryDAO dao = new DiaryDAO();
        int result = dao.insertDiary(dto);

        // 결과 처리
        if (result > 0) {
            response.sendRedirect("diary_list.jsp");
        } else {
            response.sendRedirect("error.jsp");
        }
    }
}
package com.Pebble.it.controller;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.Pebble.it.model.MemberDAO;
import com.Pebble.it.model.MemberDTO;

@WebServlet("/Join")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50 // 50MB
)
public class JoinService extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 한글 인코딩
        request.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");
        MemberDAO dao = new MemberDAO();

        try {
            // 닉네임 중복 확인 로직
            if ("checkNickname".equals(action)) {
                String nickname = request.getParameter("nickname");
                handleNicknameCheck(nickname, dao, response);
                return;
            }

            // 아이디 중복 확인 로직
            if ("checkId".equals(action)) {
                String id = request.getParameter("id");
                handleIdCheck(id, dao, response);
                return;
            }

            // 회원가입 로직
            processJoin(request, response, dao);

        } catch (Exception e) {
            // 예외 처리
            e.printStackTrace();
            sendErrorMessage(response, "서버 오류가 발생했습니다. 다시 시도해주세요.");
        }
    }

    // 닉네임 중복 확인 처리
    private void handleNicknameCheck(String nickname, MemberDAO dao, HttpServletResponse response) throws IOException {
        try {
            boolean isAvailable = dao.checkDuplicateNickname(nickname);
            response.setContentType("application/json; charset=UTF-8");
            if (isAvailable) {
                response.getWriter().write("{\"available\": false, \"message\": \"닉네임이 중복되었습니다. 다른 닉네임을 입력해주세요.\"}");
            } else {
                response.getWriter().write("{\"available\": true, \"message\": \"사용 가능한 닉네임입니다.\"}");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("{\"available\": false, \"message\": \"오류가 발생했습니다. 다시 시도해주세요.\"}");
        }
    }

    // 아이디 중복 확인 처리
    private void handleIdCheck(String id, MemberDAO dao, HttpServletResponse response) throws IOException {
        try {
            boolean isAvailable = dao.checkDuplicateId(id);
            response.setContentType("application/json; charset=UTF-8");
            if (isAvailable) {
                response.getWriter().write("{\"available\": false, \"message\": \"아이디가 중복되었습니다. 다른 아이디를 입력해주세요.\"}");
            } else {
                response.getWriter().write("{\"available\": true, \"message\": \"사용 가능한 아이디입니다.\"}");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("{\"available\": false, \"message\": \"오류가 발생했습니다. 다시 시도해주세요.\"}");
        }
    }

    // 회원가입 처리
    private void processJoin(HttpServletRequest request, HttpServletResponse response, MemberDAO dao)
            throws IOException, ServletException {
        try {
            // 요청 파라미터 가져오기
            String id = request.getParameter("id");
            String pw = request.getParameter("pw");
            String name = request.getParameter("name");
            String favoriteSport = request.getParameter("favoriteSport");
            String sportLevel = request.getParameter("sportLevel");

            // 프로필 이미지 처리
            Part filePart = request.getPart("profileImg");
            String fileName = null;
            if (filePart != null && filePart.getSize() > 0) {
                fileName = filePart.getSubmittedFileName();
                String uploadPath = getServletContext().getRealPath("/uploads");
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }
                filePart.write(uploadPath + File.separator + fileName);
            } else {
                fileName = "default.png";
            }

            // DTO 생성
            MemberDTO dto = new MemberDTO();
            dto.setId(id);
            dto.setPw(pw);
            dto.setName(name);
            dto.setProfileImg(fileName);
            dto.setFavoriteSport(favoriteSport);
            dto.setSportLevel(sportLevel);
            dto.setUidLevel("USER");

            // DAO를 통해 회원가입 처리
            int result = dao.join(dto);

            if (result > 0) {
                // 성공 시
                sendSuccessMessage(response, "회원가입이 성공적으로 완료되었습니다!", "index.jsp");
            } else {
                // 실패 시
                sendConfirmationMessage(response, "회원가입에 실패했습니다. 다시 회원가입하시겠습니까?", "join.jsp", "index.jsp");
            }

        } catch (Exception e) {
            e.printStackTrace();
            sendConfirmationMessage(response, "서버 오류가 발생했습니다. 다시 회원가입하시겠습니까?", "join.jsp", "index.jsp");
        }
    }

    // 성공 메시지 전송
    private void sendSuccessMessage(HttpServletResponse response, String message, String redirectUrl) throws IOException {
        response.setContentType("text/html; charset=UTF-8");
        response.getWriter().println("<script>");
        response.getWriter().println("alert('" + message + "');");
        response.getWriter().println("location.href='" + redirectUrl + "';");
        response.getWriter().println("</script>");
    }

    // 확인 메시지 전송
    private void sendConfirmationMessage(HttpServletResponse response, String message, String yesUrl, String noUrl)
            throws IOException {
        response.setContentType("text/html; charset=UTF-8");
        response.getWriter().println("<script>");
        response.getWriter().println("if (confirm('" + message + "')) {");
        response.getWriter().println("    location.href = '" + yesUrl + "';");
        response.getWriter().println("} else {");
        response.getWriter().println("    location.href = '" + noUrl + "';");
        response.getWriter().println("}");
        response.getWriter().println("</script>");
    }

    // 에러 메시지 전송
    private void sendErrorMessage(HttpServletResponse response, String message) throws IOException {
        response.setContentType("text/html; charset=UTF-8");
        response.getWriter().println("<script>");
        response.getWriter().println("alert('" + message + "');");
        response.getWriter().println("</script>");
    }
}

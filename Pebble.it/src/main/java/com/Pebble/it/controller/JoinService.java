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

    // POST 요청 처리
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        String nickname = request.getParameter("nickname");
        String id = request.getParameter("id");
        MemberDAO dao = new MemberDAO();

        try {
            if ("checkNickname".equals(action)) {
                handleNicknameCheck(nickname, dao, response);
                return;
            }

            if ("checkId".equals(action)) {
                handleIdCheck(id, dao, response);
                return;
            }

            processJoin(request, response, dao);
        } catch (Exception e) {
            e.printStackTrace();
            sendErrorMessage(response, "서버 오류가 발생했습니다. 다시 시도해주세요.");
        }
    }

    // GET 요청 처리
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String nickname = request.getParameter("nickname");
        String id = request.getParameter("id");
        MemberDAO dao = new MemberDAO();

        try {
            if ("checkNickname".equals(action)) {
                handleNicknameCheck(nickname, dao, response);
                return;
            }

            if ("checkId".equals(action)) {
                handleIdCheck(id, dao, response);
                return;
            }

            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"available\": false, \"message\": \"잘못된 요청입니다.\"}");
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"available\": false, \"message\": \"서버 오류가 발생했습니다.\"}");
        }
    }

    private void handleNicknameCheck(String nickname, MemberDAO dao, HttpServletResponse response) throws IOException {
        response.setContentType("application/json; charset=UTF-8");
        try {
            boolean isAvailable = dao.checkDuplicateNickname(nickname);  // DAO 호출
            System.out.println("닉네임 중복 여부 반환값: " + isAvailable); // 디버깅용 로그
            
            if (isAvailable) {  // 중복됨
                response.getWriter().write("{\"available\": false, \"message\": \"닉네임이 중복되었습니다.\"}");
            } else { // 중복되지 않음
                response.getWriter().write("{\"available\": true, \"message\": \"사용 가능한 닉네임입니다.\"}");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("{\"available\": false, \"message\": \"오류가 발생했습니다.\"}");
        }
    }

    private void handleIdCheck(String id, MemberDAO dao, HttpServletResponse response) throws IOException {
        response.setContentType("application/json; charset=UTF-8");
        try {
            boolean isAvailable = dao.checkDuplicateId(id);
            System.out.println("아이디 중복 여부 반환값: " + isAvailable); // 디버깅용 로그
            if (isAvailable) {
                response.getWriter().write("{\"available\": false, \"message\": \"아이디가 중복되었습니다.\"}");
            } else {
                response.getWriter().write("{\"available\": true, \"message\": \"사용 가능한 아이디입니다.\"}");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("{\"available\": false, \"message\": \"오류가 발생했습니다.\"}");
        }
    }

    private void processJoin(HttpServletRequest request, HttpServletResponse response, MemberDAO dao)
            throws IOException, ServletException {
        try {
            String id = request.getParameter("id");
            String pw = request.getParameter("pw");
            String name = request.getParameter("name");
            String favoriteSport = request.getParameter("favoriteSport");
            String sportLevel = request.getParameter("sportLevel");

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

            MemberDTO dto = new MemberDTO();
            dto.setId(id);
            dto.setPw(pw);
            dto.setName(name);
            dto.setProfileImg(fileName);
            dto.setFavoriteSport(favoriteSport);
            dto.setSportLevel(sportLevel);
            dto.setUidLevel("USER");

            int result = dao.join(dto);

            if (result > 0) {
                sendSuccessMessage(response, "회원가입이 완료되었습니다!", "index.jsp");
            } else {
                sendConfirmationMessage(response, "회원가입에 실패했습니다. 다시 시도하시겠습니까?", "join.jsp", "index.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
            sendConfirmationMessage(response, "서버 오류가 발생했습니다.", "join.jsp", "index.jsp");
        }
    }

    private void sendSuccessMessage(HttpServletResponse response, String message, String redirectUrl) throws IOException {
        response.setContentType("text/html; charset=UTF-8");
        response.getWriter().println("<script>");
        response.getWriter().println("alert('" + message + "');");
        response.getWriter().println("location.href='" + redirectUrl + "';");
        response.getWriter().println("</script>");
    }

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

    private void sendErrorMessage(HttpServletResponse response, String message) throws IOException {
        response.setContentType("text/html; charset=UTF-8");
        response.getWriter().println("<script>");
        response.getWriter().println("alert('" + message + "');");
        response.getWriter().println("</script>");
    }
    
    public boolean validatePassword(String password) {
        String regex = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,16}$";
        return password != null && password.matches(regex);
    }

}

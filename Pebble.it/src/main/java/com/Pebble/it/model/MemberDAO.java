package com.Pebble.it.model;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import com.Pebble.it.db.SqlSessionManager;

public class MemberDAO {

	// DB 연결
	private SqlSessionFactory sqlSessionFactory = SqlSessionManager.getSqlSession();
	private SqlSession sqlSession;

	// 회원가입 메서드
	public int join(MemberDTO dto) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) { // 자동 커밋
			int cnt = sqlSession.insert("com.Pebble.it.db.MemberMapper.join", dto); // 조인 쿼리 실행

			if (cnt > 0) {
				System.out.println("회원가입 성공: " + dto.getId());
			} else {
				System.out.println("회원가입 실패");
			}
			return cnt;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("회원가입 중 오류 발생: " + e.getMessage());
			return 0;
		}
	}

	// 로그인 메서드
	public MemberDTO login(String id, String pw) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
			// 로그인 쿼리 실행
			MemberDTO user = sqlSession.selectOne("com.Pebble.it.db.MemberMapper.login", new MemberDTO(id, pw));

			if (user != null) {
				System.out.println("로그인 성공: " + user.getName());
			} else {
				System.out.println("로그인 실패: 아이디 또는 비밀번호 불일치");
			}
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("로그인 중 오류 발생: " + e.getMessage());
			return null;
		}
	}

	public boolean checkDuplicateId(String id) {
		try {
			String result = sqlSession.selectOne("MemberMapper.checkDuplicateId", id);
			return "Y".equals(result); // "Y"와 비교
		} catch (Exception e) {
			e.printStackTrace();
			return false; // 예외 발생 시 중복된 것으로 간주
		}
	}

	public boolean checkDuplicateNickname(String nickname) {
		try {
			String result = sqlSession.selectOne("MemberMapper.checkDuplicateNickname", nickname);
			return "Y".equals(result); // "Y"와 비교
		} catch (Exception e) {
			e.printStackTrace();
			return false; // 예외 발생 시 중복된 것으로 간주
		}
	}

}

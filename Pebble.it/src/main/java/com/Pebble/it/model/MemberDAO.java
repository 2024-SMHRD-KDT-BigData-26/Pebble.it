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
		try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
			String result = sqlSession.selectOne("com.Pebble.it.db.MemberMapper.checkDuplicateId", id);
			System.out.println("쿼리 실행 결과 (아이디): " +result); // 디버깅용 로그
			return "Y".equals(result);  // "Y"는 중복, 그렇지 않으면 중복 아님
		} catch (Exception e) {
			e.printStackTrace();
			return false; // 예외 발생 시 중복된 것으로 간주
		}
	}

	public boolean checkDuplicateNickname(String nickname) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
			String result = sqlSession.selectOne("com.Pebble.it.db.MemberMapper.checkDuplicateNickname", nickname);
			System.out.println("쿼리 실행 결과 (닉네임): " +result); // 디버깅용 로그
			return "Y".equals(result); // "Y"는 중복, 그렇지 않으면 중복 아님
		} catch (Exception e) {
			e.printStackTrace();
			return false; // 예외 발생 시 중복된 것으로 간주
		}
	}

	public SqlSession getSqlSession() {
		return sqlSession;
	}

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

}

package com.Pebble.it.model;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.Pebble.it.db.SqlSessionManager;

public class MemberDAO {

	// DB 연결
	private SqlSessionFactory sqlSessionFactory = SqlSessionManager.getSqlSession();

	// 회원 가입 메서드
	public int join(MemberDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession(true);

		int cnt = sqlSession.insert("join", dto);

		if (cnt > 0) {
			System.out.println("회원가입 성공");
		} else {
			System.out.println("회원가입 실패");
		}

		sqlSession.close();

		return cnt;

	}

	// 로그인 메서드
	public MemberDTO login(String id, String pw) {
		SqlSession session = null;
		MemberDTO member = null;

		try {
			session = sqlSessionFactory.openSession();
			// MemberMapper.xml의 login 쿼리를 호출
			member = session.selectOne("MemberMapper.login", new MemberDTO(id, pw, null, null, null, null));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		return member;
	}
}

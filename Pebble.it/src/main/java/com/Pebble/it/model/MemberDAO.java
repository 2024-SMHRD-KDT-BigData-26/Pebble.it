package com.Pebble.it.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.Pebble.it.db.SqlSessionManager;

public class MemberDAO {
	
	private SqlSessionFactory sqlSessionFactory = SqlSessionManager.getSqlSession();

	// 회원 가입 메서드
	public boolean register(MemberDTO member) {
		SqlSession session = null;
		
		try { 
			session = sqlSessionFactory.openSession();
			// MemberMapper.xml의 register 쿼리를 호출
			int result = session.insert("MemberMapper.register", member);
			session.commit(); // 트랜잭션 커밋
			return result > 0; // 삽입 성공 여부 반환
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session !=null) session.close();
		}
		return false;
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
            if (session != null) session.close();
        }
        return member;
    }
}

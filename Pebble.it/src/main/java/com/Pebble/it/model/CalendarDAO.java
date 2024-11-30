package com.Pebble.it.model;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.Pebble.it.db.SqlSessionManager;

public class CalendarDAO {

	// DB연결
	SqlSessionFactory sqlSessionFactory = SqlSessionManager.getSqlSession();

	public int insertCalendar(CalendarDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession(true);

		int cnt = sqlSession.insert("insert", dto);

		if (cnt > 0) {
			System.out.println("정보 입력 성공");
		} else {
			System.out.println("정보 입력 실패");
		}

		sqlSession.close();

		return cnt;
	}
}
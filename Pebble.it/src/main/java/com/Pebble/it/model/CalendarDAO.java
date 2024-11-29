package com.Pebble.it.model;

import com.Pebble.it.model.CalendarDTO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionManager;

public class CalendarDAO {

	// SqlSessionFactory 객체 생성 (DB 연결)
	SqlSessionFactory sqlSessionFactory = SqlSessionManager.getSqlSession();
	// 일정 등록 메서드
	public int insertCalendar(CalendarDTO dto) {
		// MyBatis의 SqlSession 대여 (true: 자동 commit)
		SqlSession sqlSession = sqlSessionFactory.openSession(true);

		// SQL 실행 및 결과값 받기
		int cnt = sqlSession.insert("insertCalendar", dto);

		// 결과 출력
		if (cnt > 0) {
			System.out.println("일정 등록 성공");
		} else {
			System.out.println("일정 등록 실패");
		}

		// SqlSession 반납
		sqlSession.close();

		// 결과 반환
		return cnt;
	}

}

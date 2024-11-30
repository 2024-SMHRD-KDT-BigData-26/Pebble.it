package com.Pebble.it.model;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionManager;

public class DiaryDAO {
	// 기록 목록
	SqlSessionFactory sqlSessionFactory = SqlSessionFactory.getsqlSession();
	
	public int writeNote(DiaryDTO dto) {

		SqlSession sqlSession = sqlSessionFactory.openSession();

		int cnt = sqlSession.insert("writeNote", dto);

		sqlSession.close();

		return cnt;
	}

}

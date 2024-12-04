package com.Pebble.it.model;

import java.sql.PreparedStatement;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import com.Pebble.it.db.SqlSessionManager;


public class DiaryDAO {

	// DB 연결
	private SqlSessionFactory sqlSessionFactory = SqlSessionManager.getSqlSession();

	// 다이어리 목록 가져오기
	public List<DiaryDTO> getList() {
		SqlSession sqlSession = sqlSessionFactory.openSession(true);
		List<DiaryDTO> list = sqlSession.selectList("DiaryMapper.getDiaryList");
		sqlSession.close();
		return list;
	}
	
	// 다이어리 가져오기
	public DiaryDTO getDiary(int diaryIdx) {
		SqlSession sqlSession = sqlSessionFactory.openSession(true);
		DiaryDTO res = sqlSession.selectOne("DiaryMapper.getDiary", diaryIdx);
		sqlSession.close();
		return res;
	}
	
	// 다이어리 작성
	public int writeDiary(DiaryDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession(true);
		int res = sqlSession.insert("DiaryMapper.WriteDiary", dto);
		sqlSession.close();
		return res;
	}
}

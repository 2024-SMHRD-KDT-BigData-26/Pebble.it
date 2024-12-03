package com.Pebble.it.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import com.Pebble.it.db.SqlSessionManager;

public class todoDAO {
	 SqlSessionFactory sqlSessionFactory = SqlSessionManager.getSqlSession();

	// 할일 추가
	public int insertTodo(todoDTO dto) {
	  
		SqlSession sqlSession = sqlSessionFactory.openSession(true);
		
		int cnt = sqlSession.insert("insertTodo", dto);
		
		sqlSession.close();
		
		return cnt;
	}

	// 모든 할일 조회
	ArrayList<todoDTO> list = new ArrayList<>();

	public ArrayList<todoDTO> showTodo(String id) {
		
	SqlSession sqlSession = sqlSessionFactory.openSession(true);
	
	list = (ArrayList)sqlSession.selectList("showTodo", id);
	
	sqlSession.close();
	
	return list;
		
	}
}

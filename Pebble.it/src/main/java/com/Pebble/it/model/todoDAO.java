package com.Pebble.it.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import com.Pebble.it.db.SqlSessionManager;

public class todoDAO {
	private SqlSessionFactory sqlSessionFactory = SqlSessionManager.getSqlSession();

	// 할일 추가
	public int insertTodo(todoDTO todo) {
	    if (todo.getUserId() == null || todo.getTodoTitle() == null || todo.getTodoStatus() == null) {
	        throw new IllegalArgumentException("필수 값이 누락되었습니다.");
	    }

	    SqlSession sqlSession = null;
	    try {
	        sqlSession = sqlSessionFactory.openSession(true); // 자동 커밋
	        return sqlSession.insert("com.Pebble.it.mapper.todoMapper.insertTodo", todo);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return 0;
	    } finally {
	        if (sqlSession != null) {
	            sqlSession.close(); // SqlSession 반환
	        }
	    }
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

package com.Pebble.it.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.Pebble.it.db.SqlSessionManager;


public class todoDAO {
    private SqlSessionFactory sqlSessionFactory = SqlSessionManager.getSqlSession();

    // 할일 등록
    public int insertTodo(todoDTO todo) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            return sqlSession.insert("com.Pebble.it.mapper.todoMapper.insertTodo", todo);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    // 모든 할일 조회
    public List<todoDTO> selectAllTodo() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return sqlSession.selectList("com.Pebble.it.mapper.todoMapper.selectAllTodo");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }	
    }

    // 할일 상태 업데이트`
    public int updateTodoStatus(int todoIdx, String todoStatus) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            todoDTO dto = new todoDTO();
            dto.setTodoIdx(todoIdx);
            dto.setTodoStatus(todoStatus);
            return sqlSession.update("com.Pebble.it.mapper.todoMapper.updateTodoStatus", dto);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    // 할일 삭제
    public int deleteTodo(int todoIdx) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            return sqlSession.delete("com.Pebble.it.mapper.todoMapper.deleteTodo", todoIdx);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}

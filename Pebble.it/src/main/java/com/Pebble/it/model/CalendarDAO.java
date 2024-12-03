package com.Pebble.it.model;

import java.util.List;
import javax.servlet.annotation.WebServlet;
import org.apache.ibatis.session.SqlSession;
import com.Pebble.it.db.SqlSessionManager;
import org.apache.ibatis.session.SqlSessionFactory;

import com.Pebble.it.model.CalendarDAO;

public class CalendarDAO {
	
	// DB 연결
	private SqlSessionFactory sqlSessionFactory = SqlSessionManager.getSqlSession();
	private SqlSession sqlSession;

    // 일정 등록
    public int insertCalendar(CalendarDTO calendar) {
        int result = 0;
        try {
            result = sqlSession.insert("com.Pebble.it.mapper.CalendarMapper.insertCalendar", calendar);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
        return result;
    }

    // 일정 조회
    public List<CalendarDTO> getCalendars(String userId) {
        List<CalendarDTO> list = null;
        try {
            list = sqlSession.selectList("com.Pebble.it.mapper.CalendarMapper.getCalendars", userId);
        } finally {
            sqlSession.close();
        }
        return list;
    }
}
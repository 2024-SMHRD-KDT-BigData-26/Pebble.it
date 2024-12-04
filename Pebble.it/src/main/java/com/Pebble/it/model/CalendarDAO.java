package com.Pebble.it.model;

import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class CalendarDAO {
    private SqlSession sqlSession;

    public CalendarDAO(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public List<CalendarDTO> getAllEvents() {
        return sqlSession.selectList("calendarMapper.getAllEvents");
    }

    public CalendarDTO getEventById(int cal_idx) {
        return sqlSession.selectOne("calendarMapper.getEventById", cal_idx);
    }

    public void insertEvent(CalendarDTO event) {
        sqlSession.insert("calendarMapper.insertEvent", event);
    }

    public void updateEvent(CalendarDTO event) {
        sqlSession.update("calendarMapper.updateEvent", event);
    }

    public void deleteEvent(int cal_idx) {
        sqlSession.delete("calendarMapper.deleteEvent", cal_idx);
    }
    
    // 특정 회원 일정 가져오기
    public List<CalendarDTO> getEventsByUserId(String userId) {
        return sqlSession.selectList("calendarMapper.getEventsByUserId", userId);
    }
    
    
}

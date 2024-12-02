package com.Pebble.it.model;

import java.sql.Date;

public class todoDTO {
    private int todoIdx;        // 고유 번호 (Primary Key)
    private String userId;      // 사용자 ID
    private String todoTitle;   // 할일 제목
    private String todoStatus;  // 상태 (예: 'Pending', 'Completed')
    private Date todoDt;        // 등록 날짜

    // Getters and Setters
    public int getTodoIdx() {
        return todoIdx;
    }

    public void setTodoIdx(int todoIdx) {
        this.todoIdx = todoIdx;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTodoTitle() {
        return todoTitle;
    }

    public void setTodoTitle(String todoTitle) {
        this.todoTitle = todoTitle;
    }

    public String getTodoStatus() {
        return todoStatus;
    }

    public void setTodoStatus(String todoStatus) {
        this.todoStatus = todoStatus;
    }

    public Date getTodoDt() {
        return todoDt;
    }

    public void setTodoDt(Date todoDt) {
        this.todoDt = todoDt;
    }
}
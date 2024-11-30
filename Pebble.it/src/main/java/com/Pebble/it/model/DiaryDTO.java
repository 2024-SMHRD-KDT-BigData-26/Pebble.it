package com.Pebble.it.model;

import java.sql.Date;

public class DiaryDTO {
    private int diaryIdx;
    private String userId;
    private String diaryCategory;
    private String diaryTitle;
    private String diaryContent;
    private Date diaryDt;
    private String diaryFile;

    // Getters and Setters
    public int getDiaryIdx() {
        return diaryIdx;
    }

    public void setDiaryIdx(int diaryIdx) {
        this.diaryIdx = diaryIdx;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDiaryCategory() {
        return diaryCategory;
    }

    public void setDiaryCategory(String diaryCategory) {
        this.diaryCategory = diaryCategory;
    }

    public String getDiaryTitle() {
        return diaryTitle;
    }

    public void setDiaryTitle(String diaryTitle) {
        this.diaryTitle = diaryTitle;
    }

    public String getDiaryContent() {
        return diaryContent;
    }

    public void setDiaryContent(String diaryContent) {
        this.diaryContent = diaryContent;
    }

    public Date getDiaryDt() {
        return diaryDt;
    }

    public void setDiaryDt(Date diaryDt) {
        this.diaryDt = diaryDt;
    }

    public String getDiaryFile() {
        return diaryFile;
    }

    public void setDiaryFile(String diaryFile) {
        this.diaryFile = diaryFile;
    }
}

package com.Pebble.it.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalendarDTO {
    private int CAL_IDX; // 일정 번호
    private String USER_ID; // 회원 아이디
    private String CAL_TITLE; // 일정 제목
    private String CAL_CONTENT; // 일정 내용
    private String CAL_ST_DT; // 일정 시작 시간
    private String CAL_ED_DT; // 일정 종료 시간
    private String CAL_COLOR; // 일정 컬러
    private String CAL_STATUS; // 일정 상태
}
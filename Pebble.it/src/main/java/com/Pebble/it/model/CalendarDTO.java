package com.Pebble.it.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalendarDTO {
    private String title;               // 일정 제목
    private LocalDateTime datetime;     // 일정 시작 날짜 및 시간
    private boolean isAllDay;           // 종일 여부
    private String memo;                // 메모

}

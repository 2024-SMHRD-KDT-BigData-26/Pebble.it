package com.Pebble.it.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalendarDTO {
    private String title;       // 일정 제목
    private LocalDate datetime; // 일정 날짜
    private String memo;        // 메모
}
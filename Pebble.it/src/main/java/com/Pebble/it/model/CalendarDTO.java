package com.Pebble.it.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalendarDTO {
    private int cal_idx;
    private String user_id;
    private String cal_title;
    private String cal_content;
    private String cal_st_dt;
    private String cal_ed_dt;
    private String cal_color;
    private String cal_status;
}
package com.Pebble.it.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class todoDTO {
	
    private int todoIdx;
    private String userId;
    private String todoTitle;
    private String todoStatus;
    private String todoDt;  
}

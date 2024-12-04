package com.Pebble.it.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// lombok 연결
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiaryDTO {
	
	private int idx;      	 // 기록 인덱스
	private String user_id;         // 회원 아이디
	private String diary_category;	// 기록 카테고리
	private String diary_title;		// 기록 제목
	private String diary_content;	// 기록 내용
	private String diary_dt;		// 기록 날짜
	private String diary_file;		// 기록 첨부파일
	
}

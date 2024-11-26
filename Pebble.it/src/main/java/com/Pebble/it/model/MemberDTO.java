package com.Pebble.it.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


//lombok 연결하기
@Data
@AllArgsConstructor // 전체 필드를 가지고 생성자 메소드를 생성!
@NoArgsConstructor // 기본의 생성자 메소드를 생성!

public class MemberDTO {
	private String id;   // 아이디
	private String pw;   // 비밀번호 
	private String name; // 이름
	private String profileImg; // PROFILE_IMG
    private String favoriteSport; // FAVORITE_SPORT
    private String sportLevel;    // SPORT_LEVEL
}

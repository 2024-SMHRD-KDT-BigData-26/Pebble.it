package com.Pebble.it.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Lombok 연결하기
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {
    private String USER_ID;                // USER_ID와 매핑
    private String PW;                // PASSWORD와 매핑
    private String NICK;              // NICK (닉네임)
    private String PROFILE_IMG;        // 프로필 이미지경로
    private String FAVORITE_SPORT;     // 관심 운동 
    private String SPORT_LEVEL;        // 운동 수준
    private String UID_LEVEL = "USER"; // UID_LEVEL 추가 (기본값 설정)
    private String JOIN_DT;          // 가입 날짜 
    
 // id와 pw만 초기화하는 생성자 (로그인에 사용)
    public MemberDTO(String id, String pw) {
        this.USER_ID = id;
        this.PW = pw;
    }
    
}



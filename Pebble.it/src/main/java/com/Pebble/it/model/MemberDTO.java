package com.Pebble.it.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Lombok 연결하기
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {
    private String id;                // USER_ID와 매핑
    private String pw;                // PASSWORD와 매핑
    private String name;              // NICK (닉네임)
    private String profileImg;        // 프로필 이지미경로
    private String favoriteSport;     // 관심 운동 
    private String sportLevel;        // 운동 수준
    private String joinDate;          // 가입 날짜 
    private String uidLevel = "USER"; // UID_LEVEL 추가 (기본값 설정)
    
 // id와 pw만 초기화하는 생성자 (로그인에 사용)
    public MemberDTO(String id, String pw) {
        this.id = id;
        this.pw = pw;
    }
    
}



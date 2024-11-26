package com.Pebble.it.model;

public class MemberDTO {
	private String id;   // 아이디
	private String pw;   // 비밀번호 
	private String name; // 이름
	private String profileImg; // PROFILE_IMG
    private String favoriteSport; // FAVORITE_SPORT
    private String sportLevel;    // SPORT_LEVEL
	
    // 기본 생성자 
    public MemberDTO() {}  
    
    // 모든 필드를 포함한 생성자
    public MemberDTO(String id, String pw, String name, String profileImg, String favoriteSport, String sportLevel) {
    	this.id = id;
    	this.pw = pw;
    	this.name = name;
    	this.profileImg = profileImg;
    	this.favoriteSport = favoriteSport;
    	this.sportLevel = sportLevel;
    }

    // Getter & Setter
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProfileImg() {
		return profileImg;
	}

	public void setProfileImg(String profileImg) {
		this.profileImg = profileImg;
	}

	public String getFavoriteSport() {
		return favoriteSport;
	}

	public void setFavoriteSport(String favoriteSport) {
		this.favoriteSport = favoriteSport;
	}

	public String getSportLevel() {
		return sportLevel;
	}

	public void setSportLevel(String sportLevel) {
		this.sportLevel = sportLevel;
	}
	
    

}

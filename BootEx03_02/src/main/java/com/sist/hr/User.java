package com.sist.hr;

/**
 * IP:211.238.142.102
 * post:1521
 * SID:orcl
 * sist/1224
 * 
 * users
 * u_id	varchar2(10)
   name	varchar2(10)
   password	varchar2(20)

 * @author sist1
 *
 */
public class User {

	private String u_id;
	private String name;
	private String password;
	
	/**등급*/
	private Level hLevel  ;
	/**로그인 횟수*/
	private int login     ;
	/**추천 횟수*/
	private int recommend ;
	/**Email*/
	private String email  ;
	/**등록일*/
	private String regDt  ;	
	
	public User() {}

	public User(String u_id, String name, String password, Level hLevel, int login, int recommend, String email,
			String regDt) {
		super();
		this.u_id = u_id;
		this.name = name;
		this.password = password;
		this.hLevel = hLevel;
		this.login = login;
		this.recommend = recommend;
		this.email = email;
		this.regDt = regDt;
	}

	@Override
	public String toString() {
		return "User [u_id=" + u_id + ", name=" + name + ", password=" + password + ", hLevel=" + hLevel + ", login="
				+ login + ", recommend=" + recommend + ", email=" + email + ", regDt=" + regDt + "]";
	}

	public String getU_id() {
		return u_id;
	}

	public void setU_id(String u_id) {
		this.u_id = u_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Level gethLevel() {
		return hLevel;
	}

	public void sethLevel(Level hLevel) {
		this.hLevel = hLevel;
	}

	public int getLogin() {
		return login;
	}

	public void setLogin(int login) {
		this.login = login;
	}

	public int getRecommend() {
		return recommend;
	}

	public void setRecommend(int recommend) {
		this.recommend = recommend;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRegDt() {
		return regDt;
	}

	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}
	
	/**
	 * Next Level 값 Set
	 */
	public void upgradeLevel() {
		Level nextLevel = this.hLevel.getNextLevel();
		if(null == nextLevel) {
			throw new IllegalStateException(this.hLevel+"은 업그레이드가 불가능 합니다.");
		}
		
		this.hLevel = nextLevel;
	}
	
}








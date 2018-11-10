package com.sist.hr;

/**
 * 
 * 오라클 연결정보
 * ip 	: 211.238.142.102
 * port	: 1521
 * sid 	: orcl
 * sist/1224
 * 
 * users
 * u_id		varvhar2(10)
 * name		varvhar2(10)
 * password	varvhar2(20)
 * 
 * @author user
 */
public class User {
	private String u_id;
	private String name;
	private String password;
	
	public User() {}
	
	public User(String u_id, String name, String password) {
		super();
		this.u_id = u_id;
		this.name = name;
		this.password = password;
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

	@Override
	public String toString() {
		return "User [u_id=" + u_id + ", name=" + name + ", password=" + password + "]";
	}
	
}

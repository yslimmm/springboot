package com.sist.hr.domain;

public class MemberVO {
	
	private String name ;
	private String sex  ;
	private String tel  ;
	private String age  ;
	
	
	
	
	@Override
	public String toString() {
		return "MemberVO [name=" + name + ", sex=" + sex + ", tel=" + tel + ", age=" + age + "]";
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	
	
	
}

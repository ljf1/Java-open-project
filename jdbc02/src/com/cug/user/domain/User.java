package com.cug.user.domain;

public class User {
	private String username;
	private String password;
	private int age;
	private String gender;
	
	private String repassword;//确认密码
	private String verifyCode;//验证码
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getRepassword() {
		return repassword;
	}
	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}
	public String getVerifyCode() {
		return verifyCode;
	}
	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password
				+ ", age=" + age + ", gender=" + gender + ", repassword="
				+ repassword + ", verifyCode=" + verifyCode + "]";
	}	
}

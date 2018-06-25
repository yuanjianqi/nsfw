package com.yuan.iliya.guoshui.nsfw.user.entity;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "t_user")
public class User {
	private Integer id;
	private String department;
	private String username;
	private String account;
	private String password;
	
	private String headImage;
	private boolean gender;
	private String state;
	private String mobileNumber;
	private String email;
	private Date birthday;
	private String remark;
	
	public static String USER_VALID = "1";
	public static String USER_INVALID = "0";
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(length = 3, nullable = false)
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	
	@Column(name = "name", length = 20, nullable = false)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Column(length = 16, nullable = false)
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	@Column(length = 16, nullable = false)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Basic
	public String getHeadImage() {
		return headImage;
	}
	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}
	
	@Column(length = 1)
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Column(length=11)
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	@Basic
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public User() {
		super();
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", department=" + department + ", username=" + username + ", account=" + account
				+ ", password=" + password + ", headImage=" + headImage + ", gender=" + gender + ", state=" + state
				+ ", mobileNumber=" + mobileNumber + ", email=" + email + ", birthday=" + birthday + ", remark="
				+ remark + "]";
	}
	@Column(length=10)
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	@Column(length = 1, nullable = false)
	public boolean isGender() {
		return gender;
	}
	public void setGender(boolean gender) {
		this.gender = gender;
	}
	
	
	
	
	
	

}

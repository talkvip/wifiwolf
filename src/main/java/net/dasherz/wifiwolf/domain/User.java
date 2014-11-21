package net.dasherz.wifiwolf.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import net.dasherz.wifiwolf.common.persistence.IdLong;

@Entity
@Table(name = "t_user")
public class User extends IdLong {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4164149619614793584L;
	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "sex")
	private int sex;

	@Column(name = "age")
	private int age;

	@Transient
	private String plainPassword;

	@Column(name = "phone")
	private String phone;

	@Column(name = "email")
	private String email;

	@Column(name = "wifi_status")
	private int wifiStatus;

	@Column(name = "account_status")
	private int accountStatus;

	@Column(name = "user_type")
	private int userType;

	@Column(name = "create_time")
	private Date createTime;

	@Column(name = "is_phone_verified")
	private int isPhoneVerified;

	@Column(name = "is_email_verified")
	private int isEmailVerified;

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

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPlainPassword() {
		return plainPassword;
	}

	public void setPlainPassword(String plainPassword) {
		this.plainPassword = plainPassword;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getWifiStatus() {
		return wifiStatus;
	}

	public void setWifiStatus(int wifiStatus) {
		this.wifiStatus = wifiStatus;
	}

	public int getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(int accountStatus) {
		this.accountStatus = accountStatus;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getIsPhoneVerified() {
		return isPhoneVerified;
	}

	public void setIsPhoneVerified(int isPhoneVerified) {
		this.isPhoneVerified = isPhoneVerified;
	}

	public int getIsEmailVerified() {
		return isEmailVerified;
	}

	public void setIsEmailVerified(int isEmailVerified) {
		this.isEmailVerified = isEmailVerified;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}

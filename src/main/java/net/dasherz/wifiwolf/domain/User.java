package net.dasherz.wifiwolf.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import net.dasherz.wifiwolf.common.persistence.IdLong;

@Entity
public class User extends IdLong {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4164149619614793584L;

	@Size(max = 20)
	private String username;

	@Size(max = 100)
	private String password;

	private Integer sex;

	private Integer age;

	@Transient
	private String plainPassword;

	@Size(max = 20)
	private String phone;

	@Size(max = 100)
	private String email;

	private Integer wifiStatus;

	private Integer accountStatus;

	private Integer userType;

	private Date createTime;

	private Integer isPhoneVerified;

	private Integer isEmailVerified;

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

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
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

	public Integer getWifiStatus() {
		return wifiStatus;
	}

	public void setWifiStatus(Integer wifiStatus) {
		this.wifiStatus = wifiStatus;
	}

	public Integer getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(Integer accountStatus) {
		this.accountStatus = accountStatus;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getIsPhoneVerified() {
		return isPhoneVerified;
	}

	public void setIsPhoneVerified(Integer isPhoneVerified) {
		this.isPhoneVerified = isPhoneVerified;
	}

	public Integer getIsEmailVerified() {
		return isEmailVerified;
	}

	public void setIsEmailVerified(Integer isEmailVerified) {
		this.isEmailVerified = isEmailVerified;
	}

}

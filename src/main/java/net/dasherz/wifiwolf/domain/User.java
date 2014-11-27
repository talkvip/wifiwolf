package net.dasherz.wifiwolf.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import net.dasherz.wifiwolf.common.persistence.IdLong;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class User extends IdLong {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4164149619614793584L;

	@Size(max = 20)
	@NotBlank
	private String username;

	@Size(max = 100)
	private String password;

	private Integer sex;

	@Max(150)
	@Min(1)
	private Integer age;

	@Transient
	private String plainPassword;

	@Size(max = 20)
	private String phone;

	@Size(max = 100)
	@Email
	private String email;

	private Integer wifiStatus;

	private Integer accountStatus;

	private Integer userType;

	private Date createTime;

	private Integer isPhoneVerified;

	private Integer isEmailVerified;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	@OrderBy("id DESC")
	@NotFound(action = NotFoundAction.IGNORE)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private List<Node> nodes;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "registeredUser")
	@OrderBy("id DESC")
	@NotFound(action = NotFoundAction.IGNORE)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private List<Token> tokens;

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

	public List<Node> getNodes() {
		return nodes;
	}

	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}

	public List<Token> getTokens() {
		return tokens;
	}

	public void setTokens(List<Token> tokens) {
		this.tokens = tokens;
	}

}

package net.dasherz.wifiwolf.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import net.dasherz.wifiwolf.common.persistence.IdLong;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
public class Token extends IdLong {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5031216348093957234L;

	private String token;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "auth_type_id")
	@NotFound(action = NotFoundAction.IGNORE)
	@NotNull
	private AuthType authType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "registered_user_id")
	@NotFound(action = NotFoundAction.IGNORE)
	private User registeredUser;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "phone_user_id")
	@NotFound(action = NotFoundAction.IGNORE)
	private PhoneUser phoneUser;

	private Integer status;

	private Date createTime;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public AuthType getAuthType() {
		return authType;
	}

	public void setAuthType(AuthType authType) {
		this.authType = authType;
	}

	public User getRegisteredUser() {
		return registeredUser;
	}

	public void setRegisteredUser(User registeredUser) {
		this.registeredUser = registeredUser;
	}

	public PhoneUser getPhoneUser() {
		return phoneUser;
	}

	public void setPhoneUser(PhoneUser phoneUser) {
		this.phoneUser = phoneUser;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}

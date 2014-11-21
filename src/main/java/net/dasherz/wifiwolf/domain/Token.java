package net.dasherz.wifiwolf.domain;

import java.util.Date;

import javax.persistence.Entity;

import net.dasherz.wifiwolf.common.persistence.IdLong;

@Entity
public class Token extends IdLong {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5031216348093957234L;

	private String token;

	private Long authTypeId;

	private Long registeredUserId;

	private Long phoneUserId;

	private Integer status;

	private Date createTime;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Long getAuthTypeId() {
		return authTypeId;
	}

	public void setAuthTypeId(Long authTypeId) {
		this.authTypeId = authTypeId;
	}

	public Long getRegisteredUserId() {
		return registeredUserId;
	}

	public void setRegisteredUserId(Long registeredUserId) {
		this.registeredUserId = registeredUserId;
	}

	public Long getPhoneUserId() {
		return phoneUserId;
	}

	public void setPhoneUserId(Long phoneUserId) {
		this.phoneUserId = phoneUserId;
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

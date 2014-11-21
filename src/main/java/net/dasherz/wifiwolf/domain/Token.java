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

	private int authTypeId;

	private int registeredUserId;

	private int phoneUserId;
	
	private int status;
	
	private Date createTime;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getAuthTypeId() {
		return authTypeId;
	}

	public void setAuthTypeId(int authTypeId) {
		this.authTypeId = authTypeId;
	}

	public int getRegisteredUserId() {
		return registeredUserId;
	}

	public void setRegisteredUserId(int registeredUserId) {
		this.registeredUserId = registeredUserId;
	}

	public int getPhoneUserId() {
		return phoneUserId;
	}

	public void setPhoneUserId(int phoneUserId) {
		this.phoneUserId = phoneUserId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
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

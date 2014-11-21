package net.dasherz.wifiwolf.domain;

import javax.persistence.Entity;
import javax.validation.constraints.Size;

import net.dasherz.wifiwolf.common.persistence.IdLong;

@Entity
public class AuthType extends IdLong {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6123652221057645237L;

	@Size(max = 45)
	private String authType;

	private Integer status;

	public String getAuthType() {
		return authType;
	}

	public void setAuthType(String authType) {
		this.authType = authType;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}

package net.dasherz.wifiwolf.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.validation.constraints.Size;

import net.dasherz.wifiwolf.common.persistence.IdLong;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
public class AuthType extends IdLong {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6123652221057645237L;

	@Size(max = 45)
	private String authType;

	private Integer status;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "authType")
	@OrderBy("id DESC")
	@NotFound(action = NotFoundAction.IGNORE)
	private List<Connection> connections;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "authType")
	@OrderBy("id DESC")
	@NotFound(action = NotFoundAction.IGNORE)
	private List<Token> tokens;

	public String getAuthType() {
		return authType;
	}

	public void setAuthType(String authType) {
		this.authType = authType;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<Connection> getConnections() {
		return connections;
	}

	public void setConnections(List<Connection> connections) {
		this.connections = connections;
	}

	public List<Token> getTokens() {
		return tokens;
	}

	public void setTokens(List<Token> tokens) {
		this.tokens = tokens;
	}

}

package net.dasherz.wifiwolf.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import net.dasherz.wifiwolf.common.persistence.IdLong;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
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
	private List<Token> tokens;

	@Transient
	private String description;

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

	public List<Token> getTokens() {
		return tokens;
	}

	public void setTokens(List<Token> tokens) {
		this.tokens = tokens;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}

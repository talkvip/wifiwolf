package net.dasherz.wifiwolf.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import net.dasherz.wifiwolf.common.persistence.IdLong;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Token extends IdLong {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5031216348093957234L;

	@Size(max = 40)
	private String token;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "auth_type_id")
	@NotNull
	private AuthType authType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "registered_user_id")
	@NotFound(action = NotFoundAction.IGNORE)
	private User registeredUser;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "node_id")
	@NotNull
	private Node node;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "token")
	@NotFound(action = NotFoundAction.IGNORE)
	private Connection connection;

	private String originUrl;

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	private Integer status;

	private Date createTime;

	public Node getNode() {
		return node;
	}

	public void setNode(Node node) {
		this.node = node;
	}

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

	public String getOriginUrl() {
		return originUrl;
	}

	public void setOriginUrl(String originUrl) {
		this.originUrl = originUrl;
	}
}

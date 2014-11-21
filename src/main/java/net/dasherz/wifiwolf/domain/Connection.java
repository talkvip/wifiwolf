package net.dasherz.wifiwolf.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import net.dasherz.wifiwolf.common.persistence.IdLong;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
public class Connection extends IdLong {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2934172236674120862L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "node_id")
	@NotFound(action = NotFoundAction.IGNORE)
	@NotNull
	private Node node;

	private String ip;

	private String mac;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "token_id")
	@NotFound(action = NotFoundAction.IGNORE)
	@NotNull
	private Token token;

	private Long outgoing;

	private Long incoming;

	private Integer status;

	private Date updateTime;

	private Date createTime;

	private String originUrl;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "auth_type_id")
	@NotFound(action = NotFoundAction.IGNORE)
	@NotNull
	private AuthType authType;

	public Node getNode() {
		return node;
	}

	public void setNode(Node node) {
		this.node = node;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public Token getToken() {
		return token;
	}

	public void setToken(Token token) {
		this.token = token;
	}

	public long getOutgoing() {
		return outgoing;
	}

	public void setOutgoing(long outgoing) {
		this.outgoing = outgoing;
	}

	public long getIncoming() {
		return incoming;
	}

	public void setIncoming(long incoming) {
		this.incoming = incoming;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
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

	public AuthType getAuthType() {
		return authType;
	}

	public void setAuthType(AuthType authType) {
		this.authType = authType;
	}
}

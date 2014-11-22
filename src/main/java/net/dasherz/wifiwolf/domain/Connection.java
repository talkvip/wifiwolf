package net.dasherz.wifiwolf.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import net.dasherz.wifiwolf.common.persistence.IdLong;

@Entity
public class Connection extends IdLong {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2934172236674120862L;

	private String ip;

	private String mac;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "token_id", unique = true)
	@NotNull
	private Token token;

	private Long outgoing;

	private Long incoming;

	private Integer status;

	private Date updateTime;

	private Date createTime;

	private String originUrl;

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

	public Long getOutgoing() {
		return outgoing;
	}

	public void setOutgoing(Long outgoing) {
		this.outgoing = outgoing;
	}

	public Long getIncoming() {
		return incoming;
	}

	public void setIncoming(Long incoming) {
		this.incoming = incoming;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
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

}

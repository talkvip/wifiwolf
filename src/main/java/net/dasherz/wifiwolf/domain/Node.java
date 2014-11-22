package net.dasherz.wifiwolf.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import net.dasherz.wifiwolf.common.persistence.IdLong;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
public class Node extends IdLong {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1600308966679704193L;

	@Size(max = 45)
	private String gatewayId;

	@Size(max = 255)
	private String nodeName;

	private Integer lastHeartbeatSysUptime;

	private Integer lastHeartbeatWifidogUptime;

	private Integer lastHeartbeatSysMemfree;

	private Float lastHeartbeatSysLoad;

	@Size(max = 16)
	private String lastHeartbeatIp;

	private Date lastHeartbeatTimestamp;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "owner_id")
	@NotFound(action = NotFoundAction.IGNORE)
	@NotNull
	private User user;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "node_id")
	@NotFound(action = NotFoundAction.IGNORE)
	@NotNull
	private List<AuthPage> authPages;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "node_id")
	@NotFound(action = NotFoundAction.IGNORE)
	@NotNull
	private List<PortalPage> portalPages;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "node_id")
	@NotFound(action = NotFoundAction.IGNORE)
	private List<Token> tokens;

	public List<Token> getTokens() {
		return tokens;
	}

	public void setTokens(List<Token> tokens) {
		this.tokens = tokens;
	}

	public List<PortalPage> getPortalPages() {
		return portalPages;
	}

	public void setPortalPages(List<PortalPage> portalPages) {
		this.portalPages = portalPages;
	}

	public List<AuthPage> getAuthPages() {
		return authPages;
	}

	public void setAuthPages(List<AuthPage> authPages) {
		this.authPages = authPages;
	}

	public String getGatewayId() {
		return gatewayId;
	}

	public void setGatewayId(String gatewayId) {
		this.gatewayId = gatewayId;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public User getOwner() {
		return user;
	}

	public void setOwner(User user) {
		this.user = user;
	}

	public Integer getLastHeartbeatSysUptime() {
		return lastHeartbeatSysUptime;
	}

	public void setLastHeartbeatSysUptime(Integer lastHeartbeatSysUptime) {
		this.lastHeartbeatSysUptime = lastHeartbeatSysUptime;
	}

	public Integer getLastHeartbeatWifidogUptime() {
		return lastHeartbeatWifidogUptime;
	}

	public void setLastHeartbeatWifidogUptime(Integer lastHeartbeatWifidogUptime) {
		this.lastHeartbeatWifidogUptime = lastHeartbeatWifidogUptime;
	}

	public Integer getLastHeartbeatSysMemfree() {
		return lastHeartbeatSysMemfree;
	}

	public void setLastHeartbeatSysMemfree(Integer lastHeartbeatSysMemfree) {
		this.lastHeartbeatSysMemfree = lastHeartbeatSysMemfree;
	}

	public Float getLasHeartbeatSysLoad() {
		return lastHeartbeatSysLoad;
	}

	public void setLasHeartbeatSysLoad(Float lastHeartbeatSysLoad) {
		this.lastHeartbeatSysLoad = lastHeartbeatSysLoad;
	}

	public String getLastHeartbeatIp() {
		return lastHeartbeatIp;
	}

	public void setLastHeartbeatIp(String lastHeartbeatIp) {
		this.lastHeartbeatIp = lastHeartbeatIp;
	}

	public Date getLastHeartbeatTimestamp() {
		return lastHeartbeatTimestamp;
	}

	public void setLastHeartbeatTimestamp(Date lastHeartbeatTimestamp) {
		this.lastHeartbeatTimestamp = lastHeartbeatTimestamp;
	}

}

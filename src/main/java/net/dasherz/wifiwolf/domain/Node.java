package net.dasherz.wifiwolf.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import net.dasherz.wifiwolf.common.persistence.IdLong;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.google.common.collect.Lists;

@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Node extends IdLong {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1600308966679704193L;

	@Size(max = 45)
	private String gatewayId;

	@Size(max = 255)
	private String nodeDescription;

	private Integer lastHeartbeatSysUptime;

	private Integer lastHeartbeatWifidogUptime;

	private Integer lastHeartbeatSysMemfree;

	private Float lastHeartbeatSysLoad;

	@Size(max = 16)
	private String lastHeartbeatIp;

	private Date lastHeartbeatTimestamp;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "owner_id")
	@NotNull
	private User user;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "node_id")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private List<AuthPage> authPages;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "node_id")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private List<PortalPage> portalPages;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "node_id")
	@NotFound(action = NotFoundAction.IGNORE)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private List<Token> tokens;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "t_node_auth_type", joinColumns = { @JoinColumn(name = "node_id") }, inverseJoinColumns = { @JoinColumn(name = "auth_type_id") })
	@OrderBy("id")
	@NotFound(action = NotFoundAction.IGNORE)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private List<AuthType> authTypes;

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

	public String getNodeDescription() {
		return nodeDescription;
	}

	public void setNodeDescription(String nodeDescription) {
		this.nodeDescription = nodeDescription;
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

	public Float getLastHeartbeatSysLoad() {
		return lastHeartbeatSysLoad;
	}

	public void setLastHeartbeatSysLoad(Float lastHeartbeatSysLoad) {
		this.lastHeartbeatSysLoad = lastHeartbeatSysLoad;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<AuthType> getAuthTypes() {
		return authTypes;
	}

	public void setAuthTypes(List<AuthType> authTypes) {
		this.authTypes = authTypes;
	}

	@Transient
	public List<String> getAuthTypeIdList() {
		List<String> authTypeIdList = Lists.newArrayList();
		if (authTypes == null) {
			return authTypeIdList;
		}
		for (AuthType authType : authTypes) {
			authTypeIdList.add(authType.getId().toString());
		}
		return authTypeIdList;
	}

	@Transient
	public void setAuthTypeIdList(List<String> ids) {
		authTypes = Lists.newArrayList();
		for (String id : ids) {
			AuthType authType = new AuthType();
			authType.setId(Long.parseLong(id));
			authTypes.add(authType);
		}
	}

}

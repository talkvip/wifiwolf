package net.dasherz.wifiwolf.domain;

import java.util.Date;

import javax.persistence.Entity;

import net.dasherz.wifiwolf.common.persistence.IdLong;

@Entity
public class Node extends IdLong {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1600308966679704193L;

	private String gatewayId;

	private String nodeName;

	private Long ownerId;

	private Integer lastHeartbeatSysUptime;

	private Integer lastHeartbeatWifidogUptime;

	private Integer lastHeartbeatSysMemfree;

	private Float lastHeartbeatSysLoad;

	private String lastHeartbeatIp;

	private Date lastHeartbeatTimestamp;

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

	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}

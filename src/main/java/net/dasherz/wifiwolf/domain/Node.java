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

	private int ownerId;

	private int lastHeartbeatSysUptime;
	
	private int lastHeartbeatWifidogUptime;
	
	private int lastHeartbeatSysMemfree;
	
	private float lasHeartbeatSysLoad;
	
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

	public int getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}

	public int getLastHeartbeatSysUptime() {
		return lastHeartbeatSysUptime;
	}

	public void setLastHeartbeatSysUptime(int lastHeartbeatSysUptime) {
		this.lastHeartbeatSysUptime = lastHeartbeatSysUptime;
	}

	public int getLastHeartbeatWifidogUptime() {
		return lastHeartbeatWifidogUptime;
	}

	public void setLastHeartbeatWifidogUptime(int lastHeartbeatWifidogUptime) {
		this.lastHeartbeatWifidogUptime = lastHeartbeatWifidogUptime;
	}

	public int getLastHeartbeatSysMemfree() {
		return lastHeartbeatSysMemfree;
	}

	public void setLastHeartbeatSysMemfree(int lastHeartbeatSysMemfree) {
		this.lastHeartbeatSysMemfree = lastHeartbeatSysMemfree;
	}

	public float getLasHeartbeatSysLoad() {
		return lasHeartbeatSysLoad;
	}

	public void setLasHeartbeatSysLoad(float lasHeartbeatSysLoad) {
		this.lasHeartbeatSysLoad = lasHeartbeatSysLoad;
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

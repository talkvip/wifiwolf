package net.dasherz.wifiwolf.domain;

import java.util.Date;

import javax.persistence.Entity;

import net.dasherz.wifiwolf.common.persistence.IdLong;


@Entity
public class Log extends IdLong {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1600308966679704193L;

	private int logType;

	private String nodeName;
	
	private Date createTime;
	
	private String exception;

	public int getLogType() {
		return logType;
	}

	public void setLogType(int logType) {
		this.logType = logType;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}

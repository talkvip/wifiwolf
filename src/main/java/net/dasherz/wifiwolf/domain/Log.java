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

	private Integer logType;

	private Date createTime;

	private String exception;

	public Integer getLogType() {
		return logType;
	}

	public void setLogType(Integer logType) {
		this.logType = logType;
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

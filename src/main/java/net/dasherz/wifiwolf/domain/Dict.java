package net.dasherz.wifiwolf.domain;

import javax.persistence.Entity;
import javax.validation.constraints.Size;

import net.dasherz.wifiwolf.common.persistence.IdLong;

@Entity
public class Dict extends IdLong {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1588854955559771695L;

	@Size(max = 60)
	private String groupName;

	@Size(max = 50)
	private String code;

	@Size(max = 50)
	private String name;

	private Integer orderNum;

	private Integer status;

	@Size(max = 255)
	private String description;

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}

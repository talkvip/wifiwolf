package net.dasherz.wifiwolf.domain;

import javax.persistence.Entity;
import javax.validation.constraints.Size;

import net.dasherz.wifiwolf.common.persistence.IdLong;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PageTemplate extends IdLong {

	private static final long serialVersionUID = -3592722931131646425L;

	@Size(max = 255)
	private String templatePath;

	@Size(max = 45)
	private String templateName;

	private Integer status;

	private Integer templateType;

	public String getTemplatePath() {
		return templatePath;
	}

	public void setTemplatePath(String templatePath) {
		this.templatePath = templatePath;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getTemplateType() {
		return templateType;
	}

	public void setTemplateType(Integer templateType) {
		this.templateType = templateType;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

}

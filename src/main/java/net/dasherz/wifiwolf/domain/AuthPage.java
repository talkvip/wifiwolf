package net.dasherz.wifiwolf.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import net.dasherz.wifiwolf.common.persistence.IdLong;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AuthPage extends IdLong {

	private static final long serialVersionUID = 8521355894491138530L;

	private String customizeHtml;

	private String customizeUrl;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "template_id")
	private PageTemplate pageTemplate;

	public String getCustomizeHtml() {
		return customizeHtml;
	}

	public void setCustomizeHtml(String customizeHtml) {
		this.customizeHtml = customizeHtml;
	}

	public String getCustomizeUrl() {
		return customizeUrl;
	}

	public void setCustomizeUrl(String customizeUrl) {
		this.customizeUrl = customizeUrl;
	}

	public PageTemplate getPageTemplate() {
		return pageTemplate;
	}

	public void setPageTemplate(PageTemplate pageTemplate) {
		this.pageTemplate = pageTemplate;
	}

}

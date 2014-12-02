package net.dasherz.wifiwolf.domain;

import javax.persistence.Entity;
import javax.validation.constraints.Size;

import net.dasherz.wifiwolf.common.persistence.IdLong;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PortalPage extends IdLong {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1600308966679704193L;

	@Size(max = 45)
	private String templatePage;

	private String customizeCss;

	private String customizeHtml;

	private Integer useOriginUrl;

	private String customizeUrl;

	public String getTemplatePage() {
		return templatePage;
	}

	public void setTemplatePage(String templatePage) {
		this.templatePage = templatePage;
	}

	public String getCustomizeCss() {
		return customizeCss;
	}

	public void setCustomizeCss(String customizeCss) {
		this.customizeCss = customizeCss;
	}

	public String getCustomizeHtml() {
		return customizeHtml;
	}

	public void setCustomizeHtml(String customizeHtml) {
		this.customizeHtml = customizeHtml;
	}

	public Integer getUseOriginUrl() {
		return useOriginUrl;
	}

	public void setUseOriginUrl(Integer useOriginUrl) {
		this.useOriginUrl = useOriginUrl;
	}

	public String getCustomizeUrl() {
		return customizeUrl;
	}

	public void setCustomizeUrl(String customizeUrl) {
		this.customizeUrl = customizeUrl;
	}

}

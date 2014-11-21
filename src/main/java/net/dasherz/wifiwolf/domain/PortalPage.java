package net.dasherz.wifiwolf.domain;

import javax.persistence.Entity;

import net.dasherz.wifiwolf.common.persistence.IdLong;


@Entity
public class PortalPage extends IdLong {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1600308966679704193L;

	private Long nodeId;

	private String templatePage;
	
	private String customizeCss;
	
	private String customizeHtml;
	
	private int useOriginUrl;
	
	private String customizeUrl;

	public Long getNodeId() {
		return nodeId;
	}

	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}

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

	public int getUseOriginUrl() {
		return useOriginUrl;
	}

	public void setUseOriginUrl(int useOriginUrl) {
		this.useOriginUrl = useOriginUrl;
	}

	public String getCustomizeUrl() {
		return customizeUrl;
	}

	public void setCustomizeUrl(String customizeUrl) {
		this.customizeUrl = customizeUrl;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}

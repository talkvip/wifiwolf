package net.dasherz.wifiwolf.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import net.dasherz.wifiwolf.common.persistence.IdLong;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
public class PortalPage extends IdLong {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1600308966679704193L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "node_id")
	@NotFound(action = NotFoundAction.IGNORE)
	@NotNull
	private Node node;

	@Size(max = 45)
	private String templatePage;

	private String customizeCss;

	private String customizeHtml;

	private Integer useOriginUrl;

	private String customizeUrl;

	public Node getNode() {
		return node;
	}

	public void setNode(Node node) {
		this.node = node;
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

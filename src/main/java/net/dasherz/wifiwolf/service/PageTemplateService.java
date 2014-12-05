package net.dasherz.wifiwolf.service;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import net.dasherz.wifiwolf.domain.PageTemplate;
import net.dasherz.wifiwolf.repository.PageTemplateRepository;

import org.springframework.stereotype.Service;

@Service
@Transactional
public class PageTemplateService {
	public static final Integer TEMPLATE_TYPE_AUTH_PAGE = 1;
	public static final Integer TEMPLATE_TYPE_PORTAL_PAGE = 2;

	@Inject
	private PageTemplateRepository pageTemplateRepository;

	public PageTemplate getPageTemplate(Long id) {
		return pageTemplateRepository.findOne(id);
	}

	public List<PageTemplate> findAuthPageTemplate() {
		return pageTemplateRepository
				.findByTemplateType(TEMPLATE_TYPE_AUTH_PAGE);
	}

	public List<PageTemplate> findPortalPageTemplate() {
		return pageTemplateRepository
				.findByTemplateType(TEMPLATE_TYPE_PORTAL_PAGE);
	}
}

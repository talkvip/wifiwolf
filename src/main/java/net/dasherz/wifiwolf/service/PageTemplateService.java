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

	@Inject
	private PageTemplateRepository pageTemplateRepository;

	public List<PageTemplate> findAll() {
		return pageTemplateRepository.findAll();
	}
}

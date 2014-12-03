package net.dasherz.wifiwolf.repository;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.inject.Inject;

import net.dasherz.wifiwolf.domain.PageTemplate;

import org.junit.Before;
import org.junit.Test;

public class PageTemplateRepositoryTest extends BaseRepositoryTest {

	@Inject
	private PageTemplateRepository pageTemplateRepository;

	PageTemplate pageTemplate;

	@Before
	public void init() {
		pageTemplate = new PageTemplate();
		pageTemplate.setStatus(1);
		pageTemplate.setTemplateName("template new");
		pageTemplate.setTemplatePath("filepath/path");
	}

	@Test
	public void find() {
		PageTemplate template = pageTemplateRepository.findOne(1L);
		assertEquals("template/template1.jsp", template.getTemplatePath());
	}

	@Test
	public void findAll() {
		List<PageTemplate> result = pageTemplateRepository.findAll();
		assertEquals(3, result.size());
	}

	@Test
	public void save() {
		pageTemplateRepository.save(pageTemplate);
	}
}

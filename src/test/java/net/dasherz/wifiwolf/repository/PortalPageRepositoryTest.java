package net.dasherz.wifiwolf.repository;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.validation.Validator;

import net.dasherz.wifiwolf.domain.PortalPage;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class PortalPageRepositoryTest extends BaseRepositoryTest {

	@Autowired
	private PortalPageRepository PortalPageRepository;
	@Autowired
	private Validator validator;
	PortalPage portalPage;

	@Before
	public void init() {
		portalPage = new PortalPage();
		portalPage.setNodeId(1L);
		portalPage.setUseOriginUrl(1);
		PortalPageRepository.save(portalPage);
	}

	@Test
	public void find() {
		PortalPage page = PortalPageRepository.findOne(portalPage.getId());
		assertEquals(1, page.getUseOriginUrl().intValue());
	}

	@Test
	public void findAll() {
		List<PortalPage> result = PortalPageRepository.findAll();
		assertEquals(2, result.size());
	}

	@Test
	public void update() {
		// Given
		portalPage.setUseOriginUrl(0);

		// When
		PortalPage result = PortalPageRepository.save(portalPage);

		// Then
		assertEquals(0, result.getUseOriginUrl().intValue());
		assertEquals(portalPage.getId(), result.getId());
	}

	@Test
	public void delete() {
		PortalPageRepository.delete(portalPage);
		assertEquals(1, PortalPageRepository.count());
	}
}

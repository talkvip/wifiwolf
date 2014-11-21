package net.dasherz.wifiwolf.repository;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.inject.Inject;

import net.dasherz.wifiwolf.domain.PortalPage;

import org.junit.Before;
import org.junit.Test;

public class PortalPageRepositoryTest extends BaseRepositoryTest {

	@Inject
	private PortalPageRepository PortalPageRepository;
	@Inject
	private NodeRepository nodeRepository;

	PortalPage portalPage;

	@Before
	public void init() {
		portalPage = new PortalPage();
		portalPage.setNode(nodeRepository.findOne(1L));
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

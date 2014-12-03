package net.dasherz.wifiwolf.repository;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.inject.Inject;

import net.dasherz.wifiwolf.domain.PortalPage;

import org.junit.Before;
import org.junit.Test;

public class PortalPageRepositoryTest extends BaseRepositoryTest {

	@Inject
	private PortalPageRepository portalPageRepository;

	PortalPage portalPage;

	@Before
	public void init() {
		portalPage = new PortalPage();
		portalPage.setUseOriginUrl(1);
		portalPage.setCustomizeHtml("html");
		portalPage.setCustomizeUrl("url");
		portalPageRepository.save(portalPage);
	}

	@Test
	public void find() {
		PortalPage page = portalPageRepository.findOne(portalPage.getId());
		assertEquals(1, page.getUseOriginUrl().intValue());
	}

	@Test
	public void findAll() {
		List<PortalPage> result = portalPageRepository.findAll();
		assertEquals(2, result.size());
	}

	@Test
	public void update() {
		// Given
		portalPage.setUseOriginUrl(0);

		// When
		PortalPage result = portalPageRepository.save(portalPage);

		// Then
		assertEquals(0, result.getUseOriginUrl().intValue());
		assertEquals(portalPage.getId(), result.getId());
	}

	@Test
	public void delete() {
		portalPageRepository.delete(portalPage);
		assertEquals(1, portalPageRepository.count());
	}
}

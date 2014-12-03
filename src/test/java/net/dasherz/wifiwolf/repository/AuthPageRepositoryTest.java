package net.dasherz.wifiwolf.repository;

import static org.junit.Assert.assertEquals;

import javax.inject.Inject;

import net.dasherz.wifiwolf.domain.AuthPage;

import org.junit.Before;
import org.junit.Test;

public class AuthPageRepositoryTest extends BaseRepositoryTest {

	@Inject
	AuthPageRepository authPageRepository;

	AuthPage authPage;

	@Before
	public void init() {
		authPage = new AuthPage();
		authPage.setCustomizeHtml("html");
	}

	@Test
	public void findOne() {
		AuthPage page = authPageRepository.findOne(Long.valueOf(1));
		assertEquals("html", page.getCustomizeHtml());
		// assertEquals("wifiwolf", page.getNode().getGatewayId());
	}

	@Test
	public void save() {
		authPageRepository.save(authPage);
	}
}

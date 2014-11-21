package net.dasherz.wifiwolf.repository;

import static org.junit.Assert.assertEquals;

import javax.inject.Inject;

import net.dasherz.wifiwolf.domain.AuthPage;
import net.dasherz.wifiwolf.domain.Node;

import org.junit.Before;
import org.junit.Test;

public class AuthPageRepositoryTest extends BaseRepositoryTest {

	@Inject
	AuthPageRepository authPageRepository;

	AuthPage authPage;

	@Before
	public void init() {
		authPage = new AuthPage();
		authPage.setCustomizeCss("css");
		authPage.setCustomizeHtml("html");
		authPage.setNode(new Node());
		authPage.setTemplatePage("template");
	}

	@Test
	public void findOne() {
		AuthPage page = authPageRepository.findOne(Long.valueOf(1));
		assertEquals("css", page.getCustomizeCss());
		// assertEquals("wifiwolf", page.getNode().getGatewayId());
	}
}

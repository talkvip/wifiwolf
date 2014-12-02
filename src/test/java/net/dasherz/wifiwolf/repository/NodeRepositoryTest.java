package net.dasherz.wifiwolf.repository;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import net.dasherz.wifiwolf.domain.AuthPage;
import net.dasherz.wifiwolf.domain.Node;
import net.dasherz.wifiwolf.domain.PortalPage;

import org.junit.Before;
import org.junit.Test;

public class NodeRepositoryTest extends BaseRepositoryTest {

	@Inject
	private NodeRepository nodeRepository;
	@Inject
	private UserRepository userRepository;

	@Inject
	private AuthTypeRepository authTypeRepository;

	Node node;

	@Before
	public void init() {
		node = new Node();
		AuthPage authPage = new AuthPage();
		PortalPage portalPage = new PortalPage();
		List<AuthPage> authPages = new ArrayList<AuthPage>();
		List<PortalPage> portalPages = new ArrayList<PortalPage>();
		authPages.add(authPage);
		portalPages.add(portalPage);
		authPage.setNode(node);
		portalPage.setNode(node);
		node.setGatewayId("gw");
		node.setNodeDescription("asus");
		node.setOwner(userRepository.findOne(1L));
		node.setAuthPages(authPages);
		node.setPortalPages(portalPages);
		nodeRepository.save(node);
	}

	@Test
	public void find() {
		Node result = nodeRepository.findOne(1L);
		assertEquals("wifiwolf", result.getGatewayId());
	}

	@Test
	public void findAll() {
		List<Node> result = nodeRepository.findAll();
		assertEquals(2, result.size());
	}

	@Test
	public void update() {
		// Given
		node.setNodeDescription("test");

		// When
		Node result = nodeRepository.save(node);

		// Then
		assertEquals("test", result.getNodeDescription());
		assertEquals(node.getId(), result.getId());
	}

	@Test
	public void delete() {
		nodeRepository.delete(node);
		assertEquals(1, nodeRepository.count());
	}
}

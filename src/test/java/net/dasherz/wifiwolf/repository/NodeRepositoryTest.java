package net.dasherz.wifiwolf.repository;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.inject.Inject;

import net.dasherz.wifiwolf.domain.Node;

import org.junit.Before;
import org.junit.Test;

public class NodeRepositoryTest extends BaseRepositoryTest {

	@Inject
	private NodeRepository nodeRepository;
	@Inject
	private UserRepository userRepository;
	Node node;

	@Before
	public void init() {
		node = new Node();
		node.setGatewayId("gw");
		node.setNodeName("asus");
		node.setOwner(userRepository.findOne(1L));
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
		node.setNodeName("test");

		// When
		Node result = nodeRepository.save(node);

		// Then
		assertEquals("test", result.getNodeName());
		assertEquals(node.getId(), result.getId());
	}

	@Test
	public void delete() {
		nodeRepository.delete(node);
		assertEquals(1, nodeRepository.count());
	}
}

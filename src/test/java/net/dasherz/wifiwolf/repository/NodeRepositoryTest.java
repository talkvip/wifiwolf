package net.dasherz.wifiwolf.repository;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.validation.Validator;

import net.dasherz.wifiwolf.domain.Node;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class NodeRepositoryTest extends BaseRepositoryTest {

	@Autowired
	private NodeRepository nodeRepository;
	@Autowired
	private Validator validator;
	Node node;

	@Before
	public void init() {
		node = new Node();
		node.setGatewayId("gw");
		node.setNodeName("asus");
		node.setOwnerId(1L);
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

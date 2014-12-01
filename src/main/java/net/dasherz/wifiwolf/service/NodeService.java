package net.dasherz.wifiwolf.service;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import net.dasherz.wifiwolf.domain.Node;
import net.dasherz.wifiwolf.repository.NodeRepository;

import org.springframework.stereotype.Service;

@Service
@Transactional
public class NodeService {

	@Inject
	private NodeRepository nodeRepository;

	public List<Node> findAll() {
		return nodeRepository.findAll();
	}

	public void save(Node node) {
		nodeRepository.save(node);
	}

	public Node findByGatewayId(String gatewayId) {
		return nodeRepository.findByGatewayId(gatewayId);
	}

	public Node getNode(Long id) {

		return nodeRepository.findOne(id);
	}

}

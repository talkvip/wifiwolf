package net.dasherz.wifiwolf.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import net.dasherz.wifiwolf.domain.Node;
import net.dasherz.wifiwolf.domain.User;
import net.dasherz.wifiwolf.repository.NodeRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class NodeService {

	@Inject
	private NodeRepository nodeRepository;

	public List<Node> findAll() {
		return nodeRepository.findAll();
	}

	public List<String> findAllNodeName() {
		return nodeRepository.findAllNodeName();
	}

	public void save(Node node) {
		nodeRepository.save(node);
	}

	public Node findByGatewayId(String gatewayId) {
		return nodeRepository.findByGatewayId(gatewayId);
	}

	public Node findByNodeName(String nodeName) {
		return nodeRepository.findByNodeDescription(nodeName);
	}

	public Node getNode(Long id) {
		return nodeRepository.findOne(id);
	}

	public void remove(Long id) {
		nodeRepository.delete(id);
	}

	public Page<Node> searchNodes(int pageNum, int pageSize, Node node) {
		Specification<Node> spec = buildSpecification(node);
		if (spec == null) {
			return nodeRepository
					.findAll(new PageRequest(pageNum - 1, pageSize));
		} else {
			return nodeRepository.findAll(spec, new PageRequest(pageNum - 1,
					pageSize));
		}
	}

	public int getRegisterUserCount(Node node) {
		node = this.getNode(node.getId());
		List<User> users = node.getRegisteredUsers();
		return users.size();
	}

	private Specification<Node> buildSpecification(final Node node) {
		if (node != null) {
			return new Specification<Node>() {

				public Predicate toPredicate(Root<Node> root,
						CriteriaQuery<?> query, CriteriaBuilder cb) {
					/**
					 * 连接查询条件, 不定参数，可以连接0..N个查询条件
					 */
					List<Predicate> list = new ArrayList<Predicate>();
					if (node.getNodeDescription() != null
							&& !node.getNodeDescription().isEmpty()) {
						list.add(cb.equal(root.get("nodeDescription"),
								node.getNodeDescription()));
					}
					if (node.getGatewayId() != null
							&& !node.getGatewayId().isEmpty()) {
						list.add(cb.equal(root.get("gatewayId"),
								node.getGatewayId()));
					}

					if (list.isEmpty()) {
						return null;
					}

					Predicate[] p = new Predicate[list.size()];
					return cb.and(list.toArray(p));
				}
			};
		} else {
			return null;
		}
	}
}

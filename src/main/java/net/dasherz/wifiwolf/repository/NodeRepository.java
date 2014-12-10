package net.dasherz.wifiwolf.repository;

import java.util.List;

import net.dasherz.wifiwolf.common.repository.BaseRepository;
import net.dasherz.wifiwolf.domain.Node;

import org.springframework.data.jpa.repository.Query;

public interface NodeRepository extends BaseRepository<Node, Long> {

	public Node findByGatewayId(String gatewayId);

	@Query("select node.nodeDescription from Node node")
	public List<String> findAllNodeName();
}

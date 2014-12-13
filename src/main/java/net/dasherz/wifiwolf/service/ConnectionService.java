package net.dasherz.wifiwolf.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import net.dasherz.wifiwolf.common.util.Constants;
import net.dasherz.wifiwolf.domain.Connection;
import net.dasherz.wifiwolf.domain.Node;
import net.dasherz.wifiwolf.domain.Token;
import net.dasherz.wifiwolf.domain.User;
import net.dasherz.wifiwolf.repository.ConnectionRepository;
import net.dasherz.wifiwolf.wifidog.constant.WifidogConstants;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ConnectionService {

	@Inject
	private ConnectionRepository connectionRepository;

	public List<Connection> findAll() {
		return connectionRepository.findAll();
	}

	// 通过页码查找token
	public Page<Connection> getPageAuthTypes(int pageNum, int pageSize) {
		return connectionRepository.findAll(new PageRequest(pageNum - 1,
				pageSize));
	}

	public Connection getConnection(Long id) {
		return connectionRepository.findOne(id);
	}

	public Connection save(Connection connection) {
		return connectionRepository.save(connection);
	}

	public void createConnection(String ip, String mac, Token token,
			long incoming, long outgoing) {
		Connection connection = new Connection();
		connection.setIp(ip);
		connection.setMac(mac);
		connection.setToken(token);
		connection.setIncoming(incoming);
		connection.setOutgoing(outgoing);
		connection.setCreateTime(new Date());
		connection.setUpdateTime(new Date());
		connection.setStatus(Constants.STATUS_CONNECTION_NORMAL);
		save(connection);
	}

	public int getLiveConnectionCount(Node node) {

		return this.getLiveConnection(node).size();
	}

	public List<Connection> getLiveConnection(Node node) {
		List<Connection> connections = connectionRepository
				.findByTokenNodeIdAndStatus(node.getId(),
						Constants.STATUS_CONNECTION_NORMAL);
		Date lastCheckInteval = new Date(System.currentTimeMillis()
				- WifidogConstants.CONNECTION_TIMEOUT_MINUTE * 60 * 1000);
		List<Connection> result = new ArrayList<Connection>();
		for (Connection connection : connections) {
			if (connection.getUpdateTime().after(lastCheckInteval)) {
				result.add(connection);
			}
		}
		return result;
	}

	public Connection getLiveConnectionByUser(User user) {
		Connection connection = connectionRepository
				.findByTokenRegisteredUserIdAndStatus(user.getId(),
						Constants.STATUS_CONNECTION_NORMAL);
		if (connection != null) {
			Date lastCheckInteval = new Date(System.currentTimeMillis()
					- WifidogConstants.CONNECTION_TIMEOUT_MINUTE * 60 * 1000);
			if (connection.getUpdateTime().after(lastCheckInteval)) {
				return connection;
			}
		}
		return null;
	}

	public void setUserToOffline(User user) {
		Connection connection = getLiveConnectionByUser(user);
		if (connection != null) {
			connection.setStatus(Constants.STATUS_CONNECTION_CLOSED);
			save(connection);
		}
	}
}

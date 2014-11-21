package net.dasherz.wifiwolf.repository;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import javax.inject.Inject;

import net.dasherz.wifiwolf.domain.Connection;
import net.dasherz.wifiwolf.domain.Token;

import org.junit.Before;
import org.junit.Test;

public class ConnectionRepositoryTest extends BaseRepositoryTest {

	@Inject
	ConnectionRepository connectionRepository;

	@Inject
	AuthTypeRepository authTypeRepository;

	@Inject
	NodeRepository nodeRepository;

	@Inject
	TokenRepository tokenRepository;

	Connection connection;
	Token token;

	@Before
	public void init() {
		token = new Token();
		tokenRepository.save(token);
		connection = new Connection();
		connection.setIp("10.167.12.1");
		connection.setMac("11:11:11:22:33:22");
		connection.setOutgoing(454545454L);
		connection.setIncoming(232313213213213L);
		connection.setStatus(1);
		connection.setUpdateTime(new Date());
		connection.setCreateTime(new Date());
		connection.setOriginUrl("http://www.qq.com/");
		connection.setAuthType(authTypeRepository.findOne(1L));
		connection.setNode(nodeRepository.findOne(1L));
		connection.setToken(token);
		connectionRepository.save(connection);

	}

	@Test
	public void findOne() {
		Connection one = connectionRepository.findOne(connection.getId());
		assertEquals(connection.getId(), one.getId());
	}

	@Test
	public void update() {
		connection.setOutgoing(23456L);
		Connection newone = connectionRepository.save(connection);
		assertEquals(Long.valueOf(23456), newone.getOutgoing());
	}

	@Test
	public void delete() {
		connectionRepository.delete(connection);
		assertEquals(0, connectionRepository.count());
	}
}

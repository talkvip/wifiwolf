package net.dasherz.wifiwolf.repository;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.inject.Inject;

import net.dasherz.wifiwolf.domain.AuthType;
import net.dasherz.wifiwolf.domain.Connection;
import net.dasherz.wifiwolf.domain.Token;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AuthTypeRepositoryTest extends BaseRepositoryTest {
	@Inject
	AuthTypeRepository authTypeRepository;

	@Inject
	ConnectionRepository connectionRepository;

	@Inject
	TokenRepository tokenRepository;

	@Inject
	NodeRepository nodeRepository;

	@Inject
	UserRepository userRepository;

	AuthType authType;
	Connection connection;
	Token token;

	@Before
	public void init() {
		authType = new AuthType();
		authType.setAuthType("test");
		authType.setStatus(0);

		token = new Token();
		token.setAuthType(authTypeRepository.findOne(1L));
		token.setRegisteredUser(userRepository.findOne(1L));
		tokenRepository.save(token);

		connection = new Connection();
		// connection.setAuthType(authTypeRepository.findOne(1L));
		// connection.setNode(nodeRepository.findOne(1L));
		connection.setToken(token);
		connectionRepository.save(connection);

	}

	@Test
	public void findAll() {
		List<AuthType> authTypes = authTypeRepository.findAll();
		assertEquals(4, authTypes.size());
	}

	@Test
	public void findOne() {
		AuthType authType = authTypeRepository.findOne(Long.valueOf(1));
		assertEquals(Integer.valueOf(1), authType.getStatus());
		assertEquals("USER_UNAUTHENTICATION", authType.getAuthType());
	}

	@Test
	public void save() {
		AuthType type = authTypeRepository.save(authType);
		assertEquals(Integer.valueOf(0), type.getStatus());
		Assert.assertNotNull(type.getId());
	}

	@Test
	public void update() {
		AuthType authType = authTypeRepository.findOne(Long.valueOf(1));
		authType.setAuthType("test");
		authType = authTypeRepository.save(authType);
		assertEquals("test", authType.getAuthType());
	}

	@Test
	public void delete() {
		authTypeRepository.delete(Long.valueOf(1));
		assertEquals(3, authTypeRepository.count());
	}

	@Test
	public void readTokens() {
		AuthType type = authTypeRepository.findOne(1L);
		Assert.assertNotNull(type.getTokens());
		assertEquals(1, type.getTokens().size());
	}

}

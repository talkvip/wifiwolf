package net.dasherz.wifiwolf.repository;

import static org.junit.Assert.assertEquals;

import javax.inject.Inject;

import net.dasherz.wifiwolf.domain.PhoneUser;
import net.dasherz.wifiwolf.domain.Token;

import org.junit.Before;
import org.junit.Test;

public class TokenRepositoryTest extends BaseRepositoryTest {

	@Inject
	private TokenRepository tokenRepository;
	@Inject
	private UserRepository userRepository;
	@Inject
	private PhoneUserRepository phoneUserRepository;
	@Inject
	private AuthTypeRepository authTypeRepository;
	@Inject
	private NodeRepository nodeRepository;

	Token token;

	@Before
	public void init() {
		token = new Token();
		token.setPhoneUser(phoneUserRepository.save(new PhoneUser()));
		token.setRegisteredUser(userRepository.findOne(1L));
		token.setNode(nodeRepository.findOne(1L));
		token.setStatus(1);
		token.setAuthType(authTypeRepository.findOne(1L));
		token.setCreateTime(null);
		tokenRepository.save(token);
	}

	@Test
	public void find() {
		Token result = tokenRepository.findOne(token.getId());
		assertEquals(1L, result.getAuthType().getId().longValue());
	}

	@Test
	public void update() {
		// Given
		token.setStatus(0);

		// When
		Token result = tokenRepository.save(token);

		// Then
		assertEquals(0, result.getStatus().intValue());
		assertEquals(token.getId(), result.getId());
	}

	@Test
	public void delete() {
		tokenRepository.delete(token);
		assertEquals(0, tokenRepository.count());
	}
}

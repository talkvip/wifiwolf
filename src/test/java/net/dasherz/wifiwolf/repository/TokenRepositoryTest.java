package net.dasherz.wifiwolf.repository;

import static org.junit.Assert.assertEquals;

import javax.validation.Validator;

import net.dasherz.wifiwolf.domain.Token;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TokenRepositoryTest extends BaseRepositoryTest {

	@Autowired
	private TokenRepository tokenRepository;
	@Autowired
	private Validator validator;
	Token token;

	@Before
	public void init() {
		token = new Token();
		token.setPhoneUserId(1L);
		token.setRegisteredUserId(1L);
		token.setStatus(1);
		token.setAuthTypeId(1L);
		token.setCreateTime(null);
		tokenRepository.save(token);
	}

	@Test
	public void find() {
		Token result = tokenRepository.findOne(token.getId());
		assertEquals(1, result.getAuthTypeId().intValue());
	}

	@Test
	public void update() {
		// Given
		token.setAuthTypeId(2L);

		// When
		Token result = tokenRepository.save(token);

		// Then
		assertEquals(2, result.getAuthTypeId().intValue());
		assertEquals(token.getId(), result.getId());
	}

	@Test
	public void delete() {
		tokenRepository.delete(token);
		assertEquals(0, tokenRepository.count());
	}
}

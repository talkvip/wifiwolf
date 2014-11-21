package net.dasherz.wifiwolf.repository;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.inject.Inject;

import net.dasherz.wifiwolf.domain.AuthType;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AuthTypeRepositoryTest extends BaseRepositoryTest {
	@Inject
	AuthTypeRepository authTypeRepository;

	AuthType authType;

	@Before
	public void init() {
		authType = new AuthType();
		authType.setAuthType("test");
		authType.setStatus(0);
	}

	@Test
	public void findAll() {
		List<AuthType> authTypes = authTypeRepository.findAll();
		assertEquals(4, authTypes.size());
	}

	@Test
	public void findOne() {
		AuthType authType = authTypeRepository.findOne(Long.valueOf(1));
		assertEquals(1, authType.getStatus());
		assertEquals("USER_UNAUTHENTICATION", authType.getAuthType());
	}

	@Test
	public void save() {
		AuthType type = authTypeRepository.save(authType);
		assertEquals(0, authType.getStatus());
		Assert.assertNotNull(authType.getId());
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
}

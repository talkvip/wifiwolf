package net.dasherz.wifiwolf.repository;

import static org.junit.Assert.assertEquals;

import javax.inject.Inject;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import net.dasherz.wifiwolf.common.beanvalidator.BeanValidators;
import net.dasherz.wifiwolf.domain.Token;
import net.dasherz.wifiwolf.domain.User;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserRepositoryTest extends BaseRepositoryTest {

	@Inject
	private UserRepository userRepository;

	@Inject
	TokenRepository tokenRepository;

	@Inject
	NodeRepository nodeRepository;

	@Inject
	AuthTypeRepository authTypeRepository;
	@Inject
	private Validator validator;
	User user;
	Token token;

	@Before
	public void init() {
		user = new User();
		user.setUsername("test");
		user.setAge(100);
		user.setPhone("124342");
		user.setEmail("lh@lh.com");
		user.setSex(1);
		user.setWifiStatus(0);

		token = new Token();
		token.setRegisteredUser(userRepository.findOne(1L));
		token.setAuthType(authTypeRepository.findOne(1L));
		token.setNode(nodeRepository.findOne(1L));
		tokenRepository.save(token);
	}

	@Test
	public void find() {
		User user = userRepository.findOne(Long.valueOf(1));
		assertEquals(Integer.valueOf(25), user.getAge());
	}

	@Test
	public void update() {
		User user = userRepository.findOne(Long.valueOf(1));
		user.setAge(18);
		user = userRepository.save(user);
		assertEquals(Integer.valueOf(18), user.getAge());
	}

	@Test
	public void delete() {
		User user = userRepository.findOne(Long.valueOf(1));
		userRepository.delete(user);
		assertEquals(1, userRepository.count());
	}

	@Test
	// @Rollback(false)
	public void save() {
		BeanValidators.validateWithException(validator, user);
		userRepository.save(user);
	}

	@Test(expected = ConstraintViolationException.class)
	public void saveWithException() {
		user.setUsername("qwertyuiopasdfghjklzxcvb");
		BeanValidators.validateWithException(validator, user);
		userRepository.save(user);
	}

	@Test
	public void readNodes() {
		User admin = userRepository.findOne(1L);
		Assert.assertNotNull(admin.getNodes());
		assertEquals(1, admin.getNodes().size());
		assertEquals("wifiwolf", admin.getNodes().get(0).getGatewayId());
	}

	@Test
	public void readTokens() {
		User admin = userRepository.findOne(1L);
		Assert.assertNotNull(admin.getTokens());
		assertEquals(1, admin.getTokens().size());
	}

	@Test
	public void findByUsername() {
		User user = userRepository.findByUsername("admin");
		assertEquals(25, user.getAge().intValue());
	}

	@Test
	public void findByPhone() {
		User user = userRepository.findByPhone("13134524356");
		assertEquals(25, user.getAge().intValue());
	}

}

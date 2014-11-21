package net.dasherz.wifiwolf.repository;

import static org.junit.Assert.assertEquals;

import javax.inject.Inject;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import net.dasherz.wifiwolf.common.beanvalidator.BeanValidators;
import net.dasherz.wifiwolf.domain.User;

import org.junit.Before;
import org.junit.Test;

public class UserRepositoryTest extends BaseRepositoryTest {

	@Inject
	private UserRepository userRepository;
	@Inject
	private Validator validator;
	User user;

	@Before
	public void init() {
		user = new User();
		user.setUsername("test");
		user.setAge(100);
		user.setPhone("124342");
		user.setEmail("lh@lh.com");
		user.setSex(1);
		user.setWifiStatus(0);
	}

	@Test
	public void find() {
		User user = userRepository.findOne(Long.valueOf(1));
		assertEquals(25, user.getAge());
	}

	@Test
	public void update() {
		User user = userRepository.findOne(Long.valueOf(1));
		user.setAge(18);
		user = userRepository.save(user);
		assertEquals(18, user.getAge());
	}

	@Test
	public void delete() {
		User user = userRepository.findOne(Long.valueOf(1));
		userRepository.delete(user);
		assertEquals(0, userRepository.count());
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
}

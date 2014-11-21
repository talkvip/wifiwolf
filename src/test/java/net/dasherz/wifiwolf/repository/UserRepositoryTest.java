package net.dasherz.wifiwolf.repository;

import static org.junit.Assert.assertEquals;
import net.dasherz.wifiwolf.domain.User;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRepositoryTest extends BaseRepositoryTest {

	@Autowired
	private UserRepository userRepository;

	@Test
	public void find() {
		User user = userRepository.findOne(Long.valueOf(1));
		assertEquals(25, user.getAge());
	}

}

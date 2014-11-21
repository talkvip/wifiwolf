package net.dasherz.wifiwolf.repository;

import static org.junit.Assert.assertEquals;

import javax.validation.Validator;

import net.dasherz.wifiwolf.domain.PhoneUser;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class PhoneUserRepositoryTest extends BaseRepositoryTest {

	@Autowired
	private PhoneUserRepository phoneUserRepository;
	@Autowired
	private Validator validator;
	PhoneUser phoneUser;

	@Before
	public void init() {
		phoneUser = new PhoneUser();
		phoneUser.setPhoneNum("12345678");
		phoneUser.setStatus(1);
		phoneUser.setVerifyCode("test");
		phoneUserRepository.save(phoneUser);
	}

	@Test
	public void find() {
		PhoneUser result = phoneUserRepository.findOne(phoneUser.getId());
		assertEquals("test", result.getVerifyCode());
	}

	@Test
	public void update() {
		// Given
		phoneUser.setVerifyCode("sh");

		// When
		PhoneUser result = phoneUserRepository.save(phoneUser);

		// Then
		assertEquals("sh", result.getVerifyCode());
		assertEquals(phoneUser.getId(), result.getId());
	}

	@Test
	public void delete() {
		phoneUserRepository.delete(phoneUser);
		assertEquals(0, phoneUserRepository.count());
	}
}

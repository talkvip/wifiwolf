package net.dasherz.wifiwolf.repository;

import static org.junit.Assert.assertEquals;

import javax.validation.Validator;

import net.dasherz.wifiwolf.domain.Log;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class LogRepositoryTest extends BaseRepositoryTest {

	@Autowired
	private LogRepository logRepository;
	@Autowired
	private Validator validator;
	Log log;

	@Before
	public void init() {
		log = new Log();
		log.setLogType(0);
		log.setException("exception");
		log.setCreateTime(null);
		logRepository.save(log);
	}

	@Test
	public void find() {
		Log logger = logRepository.findOne(log.getId());
		assertEquals("exception", logger.getException());
	}

	@Test
	public void update() {
		// Given
		log.setException("test");

		// When
		Log result = logRepository.save(log);

		// Then
		assertEquals("test", result.getException());
		assertEquals(log.getId(), result.getId());
	}

	@Test
	public void delete() {
		logRepository.delete(log);
		assertEquals(0, logRepository.count());
	}
}

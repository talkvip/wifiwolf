package net.dasherz.wifiwolf.repository;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.inject.Inject;

import net.dasherz.wifiwolf.domain.Dict;

import org.junit.Before;
import org.junit.Test;

public class DictRepositoryTest extends BaseRepositoryTest {

	@Inject
	DictRepository dictRepository;

	Dict dict;

	@Before
	public void init() {
		dict = new Dict();
		dict.setGroupName("sex");
		dict.setCode("male");
		dict.setName("男");
		dict.setDescription("male desc");
		dict.setOrderNum(1);
	}

	@Test
	public void findAll() {
		List<Dict> dicts = dictRepository.findAll();
		assertEquals(20, dicts.size());
	}

	@Test
	public void save() {
		Dict saved = dictRepository.save(dict);
		assertEquals("sex", saved.getGroupName());

	}
}

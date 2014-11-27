package net.dasherz.wifiwolf.util;

import static org.junit.Assert.assertEquals;
import net.dasherz.wifiwolf.base.SpringTransactionalContextTests;
import net.dasherz.wifiwolf.common.util.DictUtils;

import org.junit.Test;

public class DictUtilsTest extends SpringTransactionalContextTests {

	@Test
	public void readGender() {
		String name = DictUtils.getName("sex", "1", "");
		assertEquals("男", name);
	}
}

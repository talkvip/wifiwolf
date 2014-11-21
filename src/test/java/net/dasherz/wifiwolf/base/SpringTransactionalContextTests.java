package net.dasherz.wifiwolf.base;

import java.util.Iterator;

import javax.sql.DataSource;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Spring 单元测试基类
 * 
 * @author lihe
 */
@ContextConfiguration(locations = { "/spring-context.xml", "/spring-mvc.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringTransactionalContextTests extends
		AbstractTransactionalJUnit4SpringContextTests {

	protected DataSource dataSource;

	@Override
	@Autowired
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
		this.dataSource = dataSource;
	}

	// @BeforeClass
	// public static void prepareMockShiroUser() {
	// User user = new User();
	// user.setId(1L);
	// user.setLoginUid("admin");
	// user.setDisplayName("admin");
	// ShiroTestUtils.mockAuthenticatedSubject(new Principal(user));
	//
	// }

	protected int getSizeForIterator(Iterator<?> iterator) {
		int length = 0;
		while (iterator.hasNext()) {
			length++;
			iterator.next();
		}
		return length;
	}

}
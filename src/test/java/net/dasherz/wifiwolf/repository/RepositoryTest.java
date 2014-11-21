package net.dasherz.wifiwolf.repository;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AuthPageRepositoryTest.class, AuthTypeRepositoryTest.class,
		DictRepositoryTest.class, UserRepositoryTest.class,
		LogRepositoryTest.class, NodeRepositoryTest.class,
		PhoneUserRepositoryTest.class, PortalPageRepositoryTest.class,
		TokenRepositoryTest.class, ConnectionRepositoryTest.class })
public class RepositoryTest {

}

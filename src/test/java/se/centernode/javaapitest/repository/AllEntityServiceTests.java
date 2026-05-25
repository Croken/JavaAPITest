package se.centernode.javaapitest.repository;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({ 
	StatementLinkServiceTest.class,
	StatementServiceTest.class,
	StatementTest.class,
	UserServiceTest.class })
public class AllEntityServiceTests {

}

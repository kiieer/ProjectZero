package com.revature.test.pctc.daotests;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectClasses({com.revature.test.pctc.daotests.ClientDAOTest.class,com.revature.test.pctc.daotests.AccountDAOTest.class})
public class TestSuite {
}

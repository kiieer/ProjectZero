package com.revature.test.pctc.utilities;

import java.sql.Connection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.revature.project.pctc.utilities.ConnUtil;

public class ConnUtilTest {
	@Test
	void connTest() {
		Connection conn = ConnUtil.createConnection();
		Assertions.assertNotNull(conn);
	}

}

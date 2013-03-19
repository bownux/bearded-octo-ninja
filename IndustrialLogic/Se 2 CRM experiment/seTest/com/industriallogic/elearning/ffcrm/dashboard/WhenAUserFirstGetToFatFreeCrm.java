package com.industriallogic.elearning.ffcrm.dashboard;

import org.junit.Test;

import com.industriallogic.elearning.BaseFFCRMTestCase;

public class WhenAUserFirstGetToFatFreeCrm extends BaseFFCRMTestCase {
	
	@Test
	public void theyCanLogin() {
		login(USERNAME, PASSWORD);
		assertWeAreOnTab("Dashboard");
	}

}

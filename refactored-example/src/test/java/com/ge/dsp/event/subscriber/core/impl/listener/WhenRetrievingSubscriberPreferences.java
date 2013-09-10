/*
 * Copyright (c) 2013 General Electric Company. All rights reserved.
 *
 * The copyright to the computer software herein is the property of
 * General Electric Company. The software may be used and/or copied only
 * with the written permission of General Electric Company or in accordance
 * with the terms and conditions stipulated in the agreement/contract
 * under which the software has been supplied.
 */

package com.ge.dsp.event.subscriber.core.impl.listener;

import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertFalse;

import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.reflect.Whitebox;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.ge.dsp.dsi.dups.api.IDups;
import com.ge.dsp.event.subscriber.core.entities.UserPreferenceEntity;
import com.ge.dsp.event.subscriber.core.fakes.DupsUserNotFoundException;
import com.ge.dsp.event.subscriber.core.fakes.FakeDupsService;
import com.ge.dsp.event.subscriber.core.fakes.IPreference;
import com.ge.dsp.event.subscriber.core.fakes.SpyLogger;
import com.ge.dsp.event.subscriber.core.impl.SubscriberHelper;
import com.ge.dsp.event.subscriber.core.impl.kernel.InternalConfiguration;

@PrepareForTest({InternalConfiguration.class, SubscriberHelper.class})
@SuppressWarnings({ "javadoc", "nls" })
public class WhenRetrievingSubscriberPreferences extends SubscriberListenerTestBase  {
	private IDups dupServiceFake;

	@BeforeTest
	public void setUp() {
		dupServiceFake = new FakeDupsService();
		internalConfig = InternalConfiguration.getInstance();
		internalConfig.setDupsService(dupServiceFake);
		fakeMessageEvent = createMessageEvent();
        spyLogger = new SpyLogger();
        
        Whitebox.setInternalState(SubscriberHelper.class, spyLogger);
	}
	

	@Test
	public void nullUserPreferenceList_ReturnsNullPreferenceEntitiesList() throws DupsUserNotFoundException {
		dupServiceFake.setPreferenceList(null);
		
		assertNull(returnedPreferenceEntities());
		assertEquals("listPreference was null.", spyLogger.toString());
	}    

	@Test
	public void jsonWithoutSubscriptionInformation_Returns_EmptyPreferenceEntitiesList() throws DupsUserNotFoundException {
		dupServiceFake.setPreferenceList(preferenceListWith(createPreferenceWith(JSON_WITH_NO_SUBSCRIPTION_SPECIFIED)));
		
		assertTrue(returnedPreferenceEntities().isEmpty());
		assertMessageContains("Undefined subscription preference: com.ge.dsp.event.subscriber.core.impl.listener.SubscriberListenerTestBase", spyLogger.toString());
	}
	
	@Test
	public void preferenceListWithNullJasonFlag_returnsEmptyUserPreferenceEntitiesList() throws DupsUserNotFoundException {
		dupServiceFake.setPreferenceList(preferenceListWith(createPreferenceWith(NULL_JSON)));
		
		assertTrue(returnedPreferenceEntities().isEmpty()); 
		assertEquals("listPreference was null.", spyLogger.toString());
	}
	
	@Test
	public void correctPreferenceList_WithContext_returnsPreferenceEntity_WithContext() throws DupsUserNotFoundException {
		dupServiceFake.setPreferenceList(preferenceListWith(createPreferenceWith(CORRECT_JSON)));
		
		assertFalse(returnedPreferenceEntities().isEmpty()); 
		assertPreferenceContextsMatch(preference, returnedPreferenceEntities().get(0));
	}

	
	protected void assertPreferenceContextsMatch(IPreference expectedPreference, UserPreferenceEntity actualPreferenceEntity) {
		if (! expectedPreference.getDupsContext().equals(actualPreferenceEntity.getDupsContext())) 
			fail("Expected preference contexts to match but actual preference entity context was " + actualPreferenceEntity.getDupsContext());
	}

	protected void assertMessageContains(String expectedString, String actualMessage) {
		if (!actualMessage.contains(expectedString)) fail("Expected message to contain string " + expectedString + ", but instead got: " + actualMessage);
		
	}
}


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


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.ge.dsp.event.subscriber.core.entities.UserPreferenceEntity;
import com.ge.dsp.event.subscriber.core.fakes.DupsUserNotFoundException;
import com.ge.dsp.event.subscriber.core.fakes.FakeDupsService;
import com.ge.dsp.event.subscriber.core.fakes.IPreference;
import com.ge.dsp.event.subscriber.core.fakes.SpyLogger;
import com.ge.dsp.event.subscriber.core.impl.SubscriberHelper;
import com.ge.dsp.event.subscriber.core.impl.kernel.InternalConfiguration;

@SuppressWarnings({ "javadoc", "nls" })
public class WhenRetrievingSubscriberPreferences extends SubscriberListenerTestBase  {



	@Before
	public void setUp() {
		dupsServiceFake = new FakeDupsService();
		internalConfig = InternalConfiguration.getInstance();
		internalConfig.setDupsService(dupsServiceFake);
		fakeMessageEvent = createGenericMessageEvent();
        subscriberHelper = new SubscriberHelper();
        spyLogger = new SpyLogger();		
        subscriberHelper.setLogger(spyLogger);

	}

	@Test
	public void nullUserPreferenceList_ReturnsNullPreferenceEntitiesList() throws DupsUserNotFoundException {
		dupsServiceFake.setPreferenceList(null);
		
		assertNull(returnedPreferenceEntities());
		assertEquals("listPreference was null.", spyLogger.toString());
	}    

	@Test
	public void jsonWithoutSubscriptionInformation_Returns_EmptyPreferenceEntitiesList() throws DupsUserNotFoundException {
		dupsServiceFake.setPreferenceList(preferenceListWith(createPreferenceWith(JSON_WITH_NO_SUBSCRIPTION_SPECIFIED)));
		
		assertTrue(returnedPreferenceEntities().isEmpty());
		assertMessageContains("Undefined subscription preference: com.ge.dsp.event.subscriber.core.impl.listener.SubscriberListenerTestBase", spyLogger.toString());
	}
	
	@Test
	public void preferenceListWithNullJasonFlag_returnsEmptyUserPreferenceEntitiesList() throws DupsUserNotFoundException {
		subscriberHelper.setDupsService(dupsServiceFake);
		
		dupsServiceFake.setPreferenceList(preferenceListWith(createPreferenceWith(NULL_JSON)));
		assertTrue(returnedPreferenceEntities().isEmpty()); 
	}
	
	@Test
	public void correctPreferenceList_WithContext_returnsPreferenceEntity_WithContext() throws DupsUserNotFoundException {
		dupsServiceFake.setPreferenceList(preferenceListWith(createPreferenceWith(CORRECT_JSON)));
		
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
	
	protected List<UserPreferenceEntity> returnedPreferenceEntities() {
		return subscriberHelper.getUsersSubscriptionPreferences(
				fakeMessageEvent.getEventName(),
				fakeMessageEvent.getEventType(),
				fakeMessageEvent.getContext());
	}
}


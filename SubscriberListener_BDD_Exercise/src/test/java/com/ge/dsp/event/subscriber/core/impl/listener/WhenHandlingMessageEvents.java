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
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.ge.dsp.event.subscriber.beans.EventDestination.NotificationType;
import com.ge.dsp.event.subscriber.core.fakes.FakeDupsService;
import com.ge.dsp.event.subscriber.core.fakes.SpyLogger;
import com.ge.dsp.event.subscriber.core.impl.SubscriberHelper;
import com.ge.dsp.event.subscriber.core.impl.SubscriberListener;
import com.ge.dsp.event.subscriber.core.impl.kernel.InternalConfiguration;
import com.ge.dsp.notification.beans.EmailNotification;
import com.ge.dsp.notification.beans.HTTPNotification;

@SuppressWarnings({ "javadoc", "nls" })
public class WhenHandlingMessageEvents extends SubscriberListenerTestBase  {
	@Before
	public void setUp() {
		dupsServiceFake = new FakeDupsService();
		internalConfig = InternalConfiguration.getInstance();
		internalConfig.setDupsService(dupsServiceFake);
		notification = setNotificationService();
		
		fakeMessageEvent = createGenericMessageEvent();
        subscriberHelper = new SubscriberHelper();
		
		fakeEventCallBack = new FakeEventCallback();
        subscriberHelper = new SubscriberHelper();
        
		createNewListenerWith(fakeEventCallBack);
		spyLogger = new SpyLogger();
        listener.setLogger(spyLogger);
	}

	@Test
	public void emailNotification_WithNullEventCallBack_isCreatedProperly() {
		returnedPreferenceEntities = aUserPreferenceEntityListWith(NotificationType.EMAIL);
		markEventAsRequiringEmail();
		listener.setEventCallback(null);
		listener.handleMessage(fakeMessageEvent);

		assertTrue(((FakeNotification) notification).wasCalled);
		assertTrue(getActualBean() instanceof EmailNotification);
	}

	private void markEventAsRequiringEmail() {
		fakeMessageEvent.setMessageRecipient("kaparaboyna@ge.com");
	}

	@Test
	public void httpNotification_WithNullEventCallBack_isCreatedProperly() {
		returnedPreferenceEntities = aUserPreferenceEntityListWith(NotificationType.HTTP);
		listener.setEventCallback(null);
		
		dupsServiceFake.setPreferenceList(preferenceListWith(createPreferenceWith(CORRECT_JSON)));
		subscriberHelper.setDupsService(dupsServiceFake);
		
		listener.handleMessage(fakeMessageEvent);
		
		assertTrue(((FakeNotification) notification).wasCalled);
		assertTrue(getActualBean() instanceof HTTPNotification);
	}

	@Test
	public void canHandle_NullMessageEvent() {
		listener.handleMessage(null);
		assertEquals("Error process Message Event", spyLogger.toString());
	}

	@Test
	public void canHandle_NonNull_EventCallback() {
		listener = new SubscriberListener(fakeEventCallBack);
		listener.handleMessage(fakeMessageEvent);

		assertTrue(fakeEventCallBack.processEventWasCalled);
	}
}

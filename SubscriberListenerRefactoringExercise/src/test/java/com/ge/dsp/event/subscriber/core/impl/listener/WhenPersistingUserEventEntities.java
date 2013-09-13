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

import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

import java.util.Calendar;

import org.powermock.core.classloader.annotations.PrepareForTest;
import org.springframework.beans.BeanUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.ge.dsp.event.subscriber.core.fakes.DBUtil;
import com.ge.dsp.event.subscriber.core.fakes.EntityManager;
import com.ge.dsp.event.subscriber.core.fakes.EntityManagerFactory;
import com.ge.dsp.event.subscriber.core.fakes.EntityTransaction;
import com.ge.dsp.event.subscriber.core.fakes.MessageEventEntity;
import com.ge.dsp.event.subscriber.core.fakes.UserEventEntity;
import com.ge.dsp.event.subscriber.core.impl.SubscriberHelper;
import com.ge.dsp.event.subscriber.core.util.SubConstants;

@PrepareForTest({DBUtil.class, SubscriberHelper.class})
@SuppressWarnings({ "javadoc", "nls" })
public class WhenPersistingUserEventEntities extends SubscriberListenerTestBase  {

	private MessageEventEntity messageEventEntity;

	@BeforeTest
	public void setUp() {
		createFakeCollaborators();
		fakeMessageEvent = createMessageEvent();
		messageEventEntity = createMessageEventEntity();
	}


	@Test 
	public void noExceptionIsThrown() throws Exception {
		SubscriberHelper.internalPersistence(createUserEventEntity(createPreferenceIndex()), SubConstants.EVENT_PERSISTENCE_UNIT);
	}
	
	
	
	
	private void createFakeCollaborators() {
		EntityManagerFactory emf = mock(EntityManagerFactory.class);
		EntityManager em = mock(EntityManager.class);
		EntityTransaction tx = mock(EntityTransaction.class);
		when(em.getTransaction()).thenReturn(tx);
		when(emf.createEntityManager()).thenReturn(em);
		
		mockStatic(DBUtil.class);
		when(DBUtil.getJpaEntityManagerFactory(UserEventEntity.class, SubConstants.EVENT_PERSISTENCE_UNIT)).thenReturn(emf);
	}
	
	private MessageEventEntity createMessageEventEntity() {
		messageEventEntity = new MessageEventEntity();
		messageEventEntity.setTimestamp(Calendar.getInstance().getTime());
		BeanUtils.copyProperties(fakeMessageEvent, messageEventEntity);
		return messageEventEntity;
	}
	
	private UserEventEntity createUserEventEntity(String preferenceIndex) {
		UserEventEntity userEventEntity = new UserEventEntity(
				"http://User.A@test.dsp.ge.com",
				TEST_CONTEXT_FOR_FILTERING, preferenceIndex);
		
		userEventEntity.setEvent(messageEventEntity);
		userEventEntity.setTimestamp(Calendar.getInstance().getTime());
		
		return userEventEntity;
	}
}


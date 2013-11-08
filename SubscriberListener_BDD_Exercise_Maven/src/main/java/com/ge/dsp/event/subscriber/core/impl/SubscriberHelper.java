/*
 * Copyright (c) 2012 General Electric Company. All rights reserved.
 *
 * The copyright to the computer software herein is the property of
 * General Electric Company. The software may be used and/or copied only
 * with the written permission of General Electric Company or in accordance
 * with the terms and conditions stipulated in the agreement/contract
 * under which the software has been supplied.
 */

package com.ge.dsp.event.subscriber.core.impl;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ge.dsp.commons.rest.MarshallUtils;
import com.ge.dsp.dsi.dups.api.IDups;
import com.ge.dsp.event.subscriber.beans.DigestPreference;
import com.ge.dsp.event.subscriber.beans.EventFilter;
import com.ge.dsp.event.subscriber.beans.SubscriptionPreference;
import com.ge.dsp.event.subscriber.core.entities.UserPreferenceEntity;
import com.ge.dsp.event.subscriber.core.fakes.DBConfigurationException;
import com.ge.dsp.event.subscriber.core.fakes.DBUtil;
import com.ge.dsp.event.subscriber.core.fakes.DupsUserNotFoundException;
import com.ge.dsp.event.subscriber.core.fakes.EntityManager;
import com.ge.dsp.event.subscriber.core.fakes.EntityManagerFactory;
import com.ge.dsp.event.subscriber.core.fakes.EntityTransaction;
import com.ge.dsp.event.subscriber.core.fakes.IPreference;
import com.ge.dsp.event.subscriber.core.impl.kernel.InternalConfiguration;
import com.ge.dsp.event.subscriber.core.util.SubscriberResources;

public class SubscriberHelper {
	private Logger _logger;
	private static SubscriberResources _resource = SubscriberResources
			.getInstance();
	private IDups dups;

	public SubscriberHelper() {
		_logger = LoggerFactory.getLogger(SubscriberHelper.class);
		dups = InternalConfiguration.getInstance().getDupsService();
	}

	public void setLogger(Logger injectedLogger) {
		_logger = injectedLogger;
	}

	public void setDupsService(IDups injectedDupsService) {
		dups = injectedDupsService;
	}

	public List<UserPreferenceEntity> getUsersSubscriptionPreferences(String eventName, String evenType, String context) {
		List<UserPreferenceEntity> users = new ArrayList<UserPreferenceEntity>();
		SubscriptionPreference subscriptionPreference;

		try {
			// retrieve list of users' preferences from dups
			List<IPreference> listPreference = dups
					.getUserPreferenceAll(context);

			if (listPreference == null) {
				_logger.error(_resource.getString(
						"null.parameter", "listPreference")); //$NON-NLS-1$ //$NON-NLS-2$
				return null;
			}

			// iterate through list of users preferences
			for (Object preference : listPreference) {
				String preferenceJson = ((IPreference) preference).getValue();

				// Check if the preference json string is the new form otherwise
				// skip it
				// why we doing this? Because this is tide to Dups!!!
				if (preferenceJson == null
						|| !preferenceJson.contains("eventFilter")) //$NON-NLS-1$
				{
					continue;
				}

				subscriptionPreference = (SubscriptionPreference) MarshallUtils
						.fromJson(new TypeReference<SubscriptionPreference>() {
							/** nothing **/
						}, preferenceJson);

				if (noSubscriptionServiceSpecified(subscriptionPreference)) {
					_logger.warn(_resource
							.getString("Undefined subscription preference: ") + preference); //$NON-NLS-1$                   
					continue;
				}

				if (filterMatches(eventName, evenType,context, subscriptionPreference)) {
					UserPreferenceEntity entity = mapPreferencesToEntity(subscriptionPreference, preference);
					setDigestPreferenceOnEntity(subscriptionPreference, entity);
					
					users.add(entity);
				}
			}
		} catch (DupsUserNotFoundException e) {
			_logger.error(" ", e); //$NON-NLS-1$
		}

		return users;

	}

	private void setDigestPreferenceOnEntity(
			SubscriptionPreference subscriptionPreference,
			UserPreferenceEntity entity) {
		DigestPreference digestPreference = subscriptionPreference.getDigestPreference();

		if (digestPreference != null) {
			entity.setDigestEnabled(digestPreference
					.isDigestEnable());
			entity.setDigestHour(digestPreference.getDigestTime());
			if (digestPreference.getDigestFrequency() != null) {
				entity.setFrequence(digestPreference
						.getDigestFrequency().toString());
			}
			entity.setMonthday(digestPreference.getDayOfMonth());
			if (digestPreference.getDayOfWeek() != null) {
				entity.setWeekdate(digestPreference.getDayOfWeek()
						.getValue());
			}
		}
	}

	private UserPreferenceEntity mapPreferencesToEntity(
			SubscriptionPreference subscriptionPreference, Object preference) {
		UserPreferenceEntity entity;
		
		entity = new UserPreferenceEntity();
		entity.setUuid(subscriptionPreference.getUserIdentityID());
		entity.setDupsContext(subscriptionPreference.getEventFilter().getContext());
		entity.setPreferenceIndex(((IPreference) preference).getKey());
		entity.setNotificationEnabled(subscriptionPreference.isNotificationEnabled());
		entity.setDestination(subscriptionPreference.getNotificationDestination());
		return entity;
	}

	private boolean filterMatches(String eventName, String evenType,
			String context, SubscriptionPreference subscriptionPreference) {
		boolean filterMatches = matchFilter(
				subscriptionPreference.getEventFilter(), eventName,
				evenType, context);
		return filterMatches;
	}

	public List<UserPreferenceEntity> getUsersSubscriptionPreferences(String dupsContext, String preferenceIndex) {
		IDups dupsService = InternalConfiguration.getInstance().getDupsService();
		List<UserPreferenceEntity> users = new ArrayList<UserPreferenceEntity>();

		try {
			List<IPreference> listPreference = dupsService.getUserPreferenceAll(dupsContext);

			if (listPreference == null) {
				_logger.error(_resource.getString(
						"null.parameter", "listPreference")); //$NON-NLS-1$ //$NON-NLS-2$
				return null;
			}

			setUserPreferencesOnEntities(preferenceIndex, users, listPreference);
		} catch (DupsUserNotFoundException e) {
			_logger.error(" ", e); //$NON-NLS-1$
		}

		return users;

	}

	private void setUserPreferencesOnEntities(String preferenceIndex,List<UserPreferenceEntity> users, List<IPreference> listPreference) {
		SubscriptionPreference subscriptionPreference;
		for (Object preference : listPreference) {
			subscriptionPreference = marshalPreferenceFromJson(preference);

			if (noSubscriptionServiceSpecified(subscriptionPreference)) {
				_logger.warn(_resource.getString("Undefined subscription preference: ") + preference); //$NON-NLS-1$                   
				continue;
			}
			String userPreferenceIndex = null;

			userPreferenceIndex = subscriptionPreference.getEventFilter().generateRoutingKey();

			if (userPreferenceIndex.equals(preferenceIndex)) {
				UserPreferenceEntity entity = new UserPreferenceEntity();

				entity.setUuid(subscriptionPreference.getUserIdentityID());
				entity.setDupsContext(subscriptionPreference
						.getEventFilter().getContext());

				entity.setPreferenceIndex(userPreferenceIndex);
				entity.setNotificationEnabled(subscriptionPreference
						.isNotificationEnabled());
				entity.setDestination(subscriptionPreference
						.getNotificationDestination());
				setDigestPreferenceOnEntity(subscriptionPreference, entity);
				users.add(entity);
			}
		}
	}

	private SubscriptionPreference marshalPreferenceFromJson(Object preference) {
		SubscriptionPreference subscriptionPreference;
		subscriptionPreference = (SubscriptionPreference) MarshallUtils
				.fromJson(new TypeReference<SubscriptionPreference>() {
					/** nothing **/
				}, ((IPreference) preference).getValue());
		return subscriptionPreference;
	}

	private boolean noSubscriptionServiceSpecified(
			SubscriptionPreference subscriptionPreference) {
		return subscriptionPreference == null
				|| subscriptionPreference.getEventFilter() == null;
	}

	private boolean matchFilter(EventFilter userFilter, String eventName,
			String eventType, String context) {

		boolean contextmatch = false;
		boolean namematch = false;
		boolean typematch = false;

		String userContext = userFilter.getContext();
		if (userContext.equals(context)) {
			contextmatch = true;
		}

		String userEventName = userFilter.getEventName();
		if (userEventName == null
				|| userEventName.equals("null") || userEventName.isEmpty()) //$NON-NLS-1$
		{
			namematch = true;
		} else if (userEventName.equals(eventName)) {
			namematch = true;

		}

		String userEventType = userFilter.getEventType();
		if (userEventType == null
				|| userEventType.equals("null") || userEventType.isEmpty()) //$NON-NLS-1$
		{
			typematch = true;
		} else if (userEventType.equals(eventType)) {
			typematch = true;
		}

		return (contextmatch && namematch && typematch);

	}

	public void internalPersistence(Object entity, String puName) {

		EntityManagerFactory emf;
		EntityManager em;

		try {

			emf = DBUtil.getJpaEntityManagerFactory(entity.getClass(), puName);
			em = emf.createEntityManager();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.persist(entity);
			em.flush();
			tx.commit();
			em.close();

		} catch (DBConfigurationException e) {
			_logger.error(_resource.getString("dbutil_error") + e); //$NON-NLS-1$
		}

	}

}

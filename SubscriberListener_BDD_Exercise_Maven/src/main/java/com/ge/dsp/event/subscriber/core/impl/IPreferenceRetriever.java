package com.ge.dsp.event.subscriber.core.impl;

import java.util.List;

import org.slf4j.Logger;

import com.ge.dsp.event.subscriber.core.entities.UserPreferenceEntity;

public interface IPreferenceRetriever {
	
	
	public List<UserPreferenceEntity> getUsersSubscriptionPreferences(String eventName, String evenType, String context);

	public void setLogger(Logger spyLogger);

}

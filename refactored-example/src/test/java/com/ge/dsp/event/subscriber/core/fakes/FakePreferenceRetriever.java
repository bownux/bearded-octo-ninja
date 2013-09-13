package com.ge.dsp.event.subscriber.core.fakes;

import java.util.List;

import org.slf4j.Logger;

import com.ge.dsp.event.subscriber.core.entities.UserPreferenceEntity;
import com.ge.dsp.event.subscriber.core.impl.IPreferenceRetriever;

public class FakePreferenceRetriever implements IPreferenceRetriever {
	
	private  Logger              _logger;

	public List<UserPreferenceEntity> getUsersSubscriptionPreferences(
			String eventName, String evenType, String context) {

		
		return null;
	}

	@Override
	public void setLogger(Logger spyLogger) {
		this._logger = spyLogger;
		
	}

}

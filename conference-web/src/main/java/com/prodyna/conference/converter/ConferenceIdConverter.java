package com.prodyna.conference.converter;

import com.prodyna.conference.model.Conference;
import com.prodyna.conference.service.ConferenceService;

public class ConferenceIdConverter extends AbstractIdConverter<Conference> {

	public ConferenceIdConverter(ConferenceService service) {
		setCrudService(service);
	}
}

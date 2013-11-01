package com.prodyna.conference.service;

import java.util.Date;
import java.util.List;

import com.prodyna.conference.model.Conference;
import com.prodyna.conference.model.Talk;

public interface ConferenceService extends GenericCrudService<Conference> {
	public List<Conference> listConferences();

	public List<Talk> listTalks(Conference conf);

	public List<Date> listDates(Conference conf);

	public List<Talk> listTalksForDate(Conference conf, Date date);
}

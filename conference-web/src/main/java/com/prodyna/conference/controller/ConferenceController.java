package com.prodyna.conference.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import com.prodyna.conference.model.Conference;
import com.prodyna.conference.model.Talk;
import com.prodyna.conference.service.ConferenceService;

@Model
public class ConferenceController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	FacesContext context;

	@Inject
	private ConferenceService conferenceService;

	public List<Conference> getConferences() {
		return conferenceService.listConferences();
	}

	public List<Talk> getTalks(Conference conf) {
		return conferenceService.listTalks(conf);
	}

	public List<Date> getConferenceDates(Conference conf) {
		return conferenceService.listDates(conf);
	}

	public List<Talk> getTalksForDate(Conference conf, Date date) {
		return conferenceService.listTalksForDate(conf, date);
	}

	public void doDeleteConference(Conference conference) {
		conferenceService.delete(conference);
	}

}

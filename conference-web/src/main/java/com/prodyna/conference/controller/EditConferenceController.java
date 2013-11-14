package com.prodyna.conference.controller;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import com.prodyna.conference.converter.GenericIdConverter;
import com.prodyna.conference.model.Conference;
import com.prodyna.conference.model.Talk;
import com.prodyna.conference.service.ConferenceService;
import com.prodyna.conference.util.ViewScope;

@Named
@ManagedBean
@RequestScoped
public class EditConferenceController extends BasicController implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private EditConferenceViewController editConferenceViewController;

	@Inject
	ConferenceService conferenceService;

	private TalkListHelper talkListHelper;

	public void doSave() {
		try {
			setConference(conferenceService.update(getConference()));
		} catch (Exception e) {
			addErrorMessage(e);
		}
	}

	public void doDelete() {
		try {
			conferenceService.delete(getConference());
		} catch (Exception e) {
			addErrorMessage(e);
		}
	}

	public String doEdit(Conference conf) {
		if (conf.getId() != null) {
			conf = conferenceService.read(conf.getId());
		}
		setConference(conf);
		return "editConference";
	}

	public String doNew() {
		Conference conference = getConference();
		conference.setAnfangsDatum(new Date());
		conference.setEndDatum(new Date());
		conference.setName("Put name here");
		conference.setKurzBeschreibung("Put Description here");
		return doEdit(conference);
	}

	public List<Talk> getTalks() {
		List<Talk> listTalks;
		Long confId = getConference().getId();
		if (confId != null) {
			boolean set = false;
			if (talkListHelper==null) {
				set = true;
			} else if (!confId.equals(talkListHelper.getId())) {
				set = true;
			}
			if (set)  {
				talkListHelper = new TalkListHelper(conferenceService.listTalks(getConference()), getConference().getId());
			}
			listTalks = talkListHelper.getTalks();
		} else {
			listTalks = Collections.emptyList();
		}
		return listTalks;
	}

	public List<Date> getDates() {
		getTalks();
		List<Date> listDates;
		if (talkListHelper!=null) {
			listDates = talkListHelper.getDates();
		} else {
			listDates = Collections.emptyList();
		}
		return listDates;
	}

	public List<Talk> talksForDate(Date date) {
		return talkListHelper.getTalksForDate(date);
	}

	public Conference getConference() {
		return getConferenceViewController().getConference();
	}

	private EditConferenceViewController getConferenceViewController() {
		if (editConferenceViewController == null) {
			editConferenceViewController = new ViewScope()
					.get(EditConferenceViewController.class);
		}
		return editConferenceViewController;
	}

	public void setConference(Conference conference) {
		getConferenceViewController().setConference(conference);
	}

	public boolean isConferenceExists() {
		Long id = getConference().getId();
		boolean b = id != null && id != 0;
		return b;
	}

	public Converter getIdConverter() {
		return new GenericIdConverter<Conference>(conferenceService);
	}

}

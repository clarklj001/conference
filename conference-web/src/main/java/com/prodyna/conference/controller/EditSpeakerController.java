package com.prodyna.conference.controller;

import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.convert.Converter;
import javax.inject.Inject;

import com.prodyna.conference.converter.GenericIdConverter;
import com.prodyna.conference.model.Speaker;
import com.prodyna.conference.model.Talk;
import com.prodyna.conference.service.SpeakerService;

@Model
public class EditSpeakerController extends BasicController {
	Speaker speaker;

	@Inject
	SpeakerService speakerService;

	private TalkListHelper talkListHelper;

	@PostConstruct
	void postConstruct() {
		speaker = new Speaker();
	}

	public Long getId() {
		return speaker.getId();
	}

	public void setId(Long id) {
		speaker.setId(id);
	}

	public String getName() {
		return speaker.getName();
	}

	public void setName(String name) {
		speaker.setName(name);
	}

	public String getKurzBeschreibung() {
		return speaker.getKurzBeschreibung();
	}

	public void setKurzBeschreibung(String kurzBeschreibung) {
		speaker.setKurzBeschreibung(kurzBeschreibung);
	}

	public String doNewSpeaker() {
		setName("Max Mustermann");
		setKurzBeschreibung("Kurze Beschreibung");
		return doEdit(speaker);
	}

	public String doEdit(Speaker speaker) {
		this.speaker = speaker;
		return "editSpeaker";
	}

	public void doSave() {
		try {
			speaker = speakerService.update(speaker);
		} catch (Exception e) {
			addErrorMessage(e);
		}
	}

	public String doDelete() {
		try {
			speakerService.delete(speaker);
		} catch (Exception e) {
			addErrorMessage(e);
		}
		return "speakers?faces-redirect=true";
	}
	
	public List<Talk> getTalks() {
		List<Talk> listTalks;
		Long speakerId = getSpeaker().getId();
		if (speakerId != null) {
			boolean set = false;
			if (talkListHelper==null) {
				set = true;
			} else if (!speakerId.equals(talkListHelper.getId())) {
				set = true;
			}
			if (set)  {
				talkListHelper = new TalkListHelper(speakerService.listTalks(getSpeaker()), getSpeaker().getId());
			}
			listTalks = talkListHelper.getTalks();
		} else {
			listTalks = Collections.emptyList();
		}
		return listTalks;
	}

	public Converter getIdConverter() {
		return new GenericIdConverter<Speaker>(speakerService);
	}

	public Speaker getSpeaker() {
		return speaker;
	}

	public void setSpeaker(Speaker speaker) {
		this.speaker = speaker;
	}

}

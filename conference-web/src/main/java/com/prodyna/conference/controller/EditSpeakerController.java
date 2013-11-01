package com.prodyna.conference.controller;

import java.util.Collections;
import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import com.prodyna.conference.model.Speaker;
import com.prodyna.conference.model.Talk;
import com.prodyna.conference.service.SpeakerService;

@Model
public class EditSpeakerController {
	Speaker speaker = new Speaker();

	@Inject
	SpeakerService speakerService;

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
		speaker = speakerService.update(speaker);
	}

	public List<Talk> getTalks() {
		List<Talk> talks;
		Long id = getId();
		if (id != null && id > 0) {
			speakerService.read(id);
			talks = speakerService.listTalks(speaker);
		} else {
			talks = Collections.emptyList();
		}
		return talks;
	}
}
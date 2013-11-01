package com.prodyna.conference.controller;

import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import com.prodyna.conference.model.Speaker;
import com.prodyna.conference.model.Talk;
import com.prodyna.conference.service.SpeakerService;

@Model
public class SpeakerController {
	@Inject
	private SpeakerService speakerService;

	public void doDelete(Speaker speaker) {
		speakerService.delete(speaker);
	}

	public List<Speaker> getSpeakers() {
		return speakerService.listSpeakers();
	}

	public List<Talk> getSpeakerTalks(Speaker speaker) {
		return speakerService.listTalks(speaker);
	}
}

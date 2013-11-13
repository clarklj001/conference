package com.prodyna.conference.rest;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import com.prodyna.conference.model.Speaker;
import com.prodyna.conference.service.SpeakerService;

@Path("speaker")
@RequestScoped
public class SpeakerRESTService {
	@Inject
	private SpeakerService speakerService;

	@GET
	@Path("speakers")
	public List<Speaker> getSpeakers() {
		return speakerService.listSpeakers();
	}

	// TODO develop other methods
}

package com.prodyna.conference.rest;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.prodyna.conference.model.Conference;
import com.prodyna.conference.model.Talk;
import com.prodyna.conference.service.ConferenceService;

@Path("conference")
@RequestScoped
public class ConferenceRESTService {
	@Inject
	private ConferenceService conferenceService;

	@GET
	@Path("conferences")
	public List<Conference> getConferences() {
		return conferenceService.listConferences();
	}

	@GET
	@Path("{id}/talks")
	public List<Talk> getTalks(@PathParam("id") Long id) {
		Conference conference = conferenceService.read(id);
		return conferenceService.listTalks(conference);
	}

	// TODO develop other methods
}

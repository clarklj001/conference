package com.prodyna.conference.rest;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import com.prodyna.conference.model.Conference;
import com.prodyna.conference.service.ConferenceService;

@Path("conference")
@RequestScoped
public class ConferenceRESTService {
	@Inject
	private ConferenceService conferenceService;

	@GET
	@Path("conferences")
	public List<Conference> helloWorld() {
		return conferenceService.listConferences();
	}
}

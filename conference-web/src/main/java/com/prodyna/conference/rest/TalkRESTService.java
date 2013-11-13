package com.prodyna.conference.rest;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Path;

import com.prodyna.conference.service.TalkService;

@Path("talk")
@RequestScoped
public class TalkRESTService {
	@Inject
	private TalkService talkService;

	// TODO develop other methods
}

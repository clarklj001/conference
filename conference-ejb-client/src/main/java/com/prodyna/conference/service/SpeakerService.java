package com.prodyna.conference.service;

import java.util.List;

import com.prodyna.conference.model.Speaker;
import com.prodyna.conference.model.Talk;

public interface SpeakerService extends GenericCrudService<Speaker> {

	public List<Talk> listTalks(Speaker speaker);

	public List<Speaker> listSpeakers();

}
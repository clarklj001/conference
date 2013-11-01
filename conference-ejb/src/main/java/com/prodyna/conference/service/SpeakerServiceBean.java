package com.prodyna.conference.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import com.prodyna.conference.model.Speaker;
import com.prodyna.conference.model.Talk;

@Stateless
public class SpeakerServiceBean extends GenericCrudServiceBean<Speaker>
		implements SpeakerService {
	public SpeakerServiceBean() {
		super(Speaker.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.prodyna.conference.service.SpeakerService#listTalks(com.prodyna.
	 * conference.model.Speaker)
	 */
	@Override
	public List<Talk> listTalks(Speaker speaker) {
		TypedQuery<Talk> namedQuery = entityManager.createNamedQuery(
				Speaker.READ_ALL_TALKS, Talk.class);
		namedQuery.setParameter("speaker", speaker);
		return namedQuery.getResultList();
	}

	@Override
	public List<Speaker> listSpeakers() {
		TypedQuery<Speaker> namedQuery = entityManager.createNamedQuery(
				Speaker.READ_ALL_SPEAKERS, Speaker.class);
		return namedQuery.getResultList();
	}
}
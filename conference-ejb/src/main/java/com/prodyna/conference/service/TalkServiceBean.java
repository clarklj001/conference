package com.prodyna.conference.service;

import javax.ejb.Stateless;

import com.prodyna.conference.model.Talk;

@Stateless
public class TalkServiceBean extends GenericCrudServiceBean<Talk> implements
		TalkService {
	public TalkServiceBean() {
		super(Talk.class);
	}

	@Override
	protected boolean isValid(Talk t) {
		// TODO check if each speaker has only this talk at that time.
		// TODO check if room is not occupied at the time of the talk.
		// TODO check if time of talk is within bounds of conference.
		return super.isValid(t);
	}
}

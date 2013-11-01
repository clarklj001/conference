package com.prodyna.conference.converter;

import com.prodyna.conference.model.Talk;
import com.prodyna.conference.service.TalkService;

public class TalkIdConverter extends AbstractIdConverter<Talk> {

	public TalkIdConverter(TalkService ts) {
		setCrudService(ts);
	}
}

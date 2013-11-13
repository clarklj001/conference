package com.prodyna.conference.service;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

import com.prodyna.conference.common.exception.ValidationException;
import com.prodyna.conference.common.monitoring.Monitored;
import com.prodyna.conference.common.monitoring.MonitoringInterceptor;
import com.prodyna.conference.model.Conference;
import com.prodyna.conference.model.Talk;

@Stateless
@Monitored
@Interceptors({ MonitoringInterceptor.class })
public class TalkServiceBean extends GenericCrudServiceBean<Talk> implements
		TalkService {
	@Inject
	ConferenceService conferenceService;

	@Inject
	TalkJms talkJms;

	public TalkServiceBean() {
		super(Talk.class);
	}

	@Override
	protected boolean isValid(Talk t) {
		// TODO check if each speaker has only this talk at that time.
		// TODO check if room is not occupied at the time of the talk.

		Conference conference = conferenceService.read(t.getConference()
				.getId());

		// check if time of talk is within bounds of conference.
		Date confAnfangsDatum = conference.getAnfangsDatum();
		Date confEndDatum = conference.getEndDatum();
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(confEndDatum);
		gc.add(Calendar.DATE, 1);
		confEndDatum = gc.getTime();
		Date talkStartDate = t.getStartDate();
		if (confAnfangsDatum.after(talkStartDate)
				|| confEndDatum.before(talkStartDate)) {
			// not valid
			throw new ValidationException(talkStartDate
					+ " not within conference bounds: [" + confAnfangsDatum
					+ ", " + confEndDatum + "]");
		}
		return super.isValid(t);
	}

	@Override
	public Talk update(Talk t) {
		Talk update = super.update(t);

		talkJms.sendMessage("Talk with id " + t.getId() + " updated.");

		return update;
	}
}

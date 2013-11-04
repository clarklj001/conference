package com.prodyna.conference.service;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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

		// check if time of talk is within bounds of conference.
		Date confAnfangsDatum = t.getConference().getAnfangsDatum();
		Date confEndDatum = t.getConference().getEndDatum();
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(confEndDatum);
		gc.add(Calendar.DATE, 1);
		confEndDatum = gc.getTime();
		Date talkStartDate = t.getStartDate();
		if (confAnfangsDatum.after(talkStartDate)
				|| confEndDatum.before(talkStartDate)) {
			// not valid
			throw new IllegalArgumentException(talkStartDate
					+ " not within conference bounds: [" + confAnfangsDatum
					+ ", " + confEndDatum + "]");
		}
		return super.isValid(t);
	}
}

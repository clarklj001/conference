package com.prodyna.conference.service;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.TypedQuery;

import com.prodyna.conference.common.monitoring.Monitored;
import com.prodyna.conference.common.monitoring.MonitoringInterceptor;
import com.prodyna.conference.model.Conference;
import com.prodyna.conference.model.Talk;

@Monitored
@Stateless
@Interceptors({ MonitoringInterceptor.class })
public class ConferenceServiceBean extends GenericCrudServiceBean<Conference>
		implements ConferenceService {
	public ConferenceServiceBean() {
		super(Conference.class);
	}

	public List<Conference> listConferences() {
		TypedQuery<Conference> query = entityManager.createNamedQuery(
				Conference.READ_ALL_CONFERENCES, Conference.class);

		return query.getResultList();
	}

	public List<Talk> listTalks(Conference conf) {
		TypedQuery<Talk> query = entityManager.createNamedQuery(
				Conference.READ_TALKS_FOR_CONFERENCE, Talk.class);
		query.setParameter("conferenceId", conf.getId());
		return query.getResultList();
	}

	public List<Date> listDates(Conference conf) {
		TypedQuery<Date> query = entityManager.createNamedQuery(
				Conference.READ_DATES_FOR_CONFERENCE, Date.class);
		query.setParameter("conferenceId", conf.getId());
		return query.getResultList();

	}

	public List<Talk> listTalksForDate(Conference conf, Date date) {
		TypedQuery<Talk> query = entityManager.createNamedQuery(
				Conference.READ_TALKS_FOR_CONFERENCE_AT_DATE, Talk.class);
		query.setParameter("conferenceId", conf.getId());
		query.setParameter("date", date);
		return query.getResultList();

	}

	@Override
	protected boolean isValid(Conference t) {
		if (t.getAnfangsDatum().equals(t.getEndDatum())) {
			// ok
		} else if (t.getEndDatum().before(t.getAnfangsDatum())) {
			throw new IllegalArgumentException("Conference Dates invalid: ["
					+ t.getAnfangsDatum() + ", " + t.getEndDatum() + "]");
		}
		return super.isValid(t);
	}
}

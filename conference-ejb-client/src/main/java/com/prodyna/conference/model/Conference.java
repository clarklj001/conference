package com.prodyna.conference.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@Table(name = "Conference")
@NamedQueries({
		@NamedQuery(name = Conference.READ_ALL_CONFERENCES, query = "select c from Conference c"),
		@NamedQuery(name = Conference.READ_TALKS_FOR_CONFERENCE, query = "select t from Talk t where t.conference.id=:conferenceId order by t.startDate"),
		@NamedQuery(name = Conference.READ_DATES_FOR_CONFERENCE, query = "select distinct t.startDate from Talk t where t.conference.id=:conferenceId order by t.startDate"),
		@NamedQuery(name = Conference.READ_TALKS_FOR_CONFERENCE_AT_DATE, query = "select t from Talk t where t.conference.id=:conferenceId and t.startDate=:date order by t.startDate"), })
public class Conference extends AbstractIdHolder implements NameHolder,
		BeschreibungHolder, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String READ_ALL_CONFERENCES = "read_all_conferences";

	public static final String READ_DATES_FOR_CONFERENCE = "read_dates_for_conference";

	public static final String READ_TALKS_FOR_CONFERENCE = "read_talks_for_conference";

	public static final String READ_TALKS_FOR_CONFERENCE_AT_DATE = "read_talks_for_conference_at_date";

	@Id
	@GeneratedValue
	Long id;

	String name;
	String kurzBeschreibung;

	Date anfangsDatum;
	Date endDatum;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKurzBeschreibung() {
		return kurzBeschreibung;
	}

	public void setKurzBeschreibung(String kurzBeschreibung) {
		this.kurzBeschreibung = kurzBeschreibung;
	}

	public Date getAnfangsDatum() {
		return anfangsDatum;
	}

	public void setAnfangsDatum(Date anfangsDatum) {
		this.anfangsDatum = anfangsDatum;
	}

	public Date getEndDatum() {
		return endDatum;
	}

	public void setEndDatum(Date endDatum) {
		this.endDatum = endDatum;
	}
}

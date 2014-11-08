package com.prodyna.conference.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@Table(name = "Speaker")
@NamedQueries({
		@NamedQuery(name = Speaker.READ_ALL_SPEAKERS, query = "select s from Speaker s"),
		@NamedQuery(name = Speaker.READ_ALL_TALKS, query = "select t from Talk t where  :speaker member of t.speakers") })
public class Speaker extends AbstractIdHolder implements NameHolder,
		BeschreibungHolder {
	public static final String READ_ALL_SPEAKERS = "read_all_speakers";

	public static final String READ_ALL_TALKS = "read_all_talks";

	@Id
	@GeneratedValue
	Long id;

	String name;
	String kurzBeschreibung;

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
}

package com.prodyna.conference.controller;

import java.io.Serializable;

import com.prodyna.conference.model.Conference;

public class EditConferenceViewController implements Serializable {
	private Conference conference = new Conference();

	public Conference getConference() {
		return conference;
	}

	public void setConference(Conference conference) {
		this.conference = conference;
	}
}

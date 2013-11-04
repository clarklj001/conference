package com.prodyna.conference.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;

import com.prodyna.conference.converter.GenericIdConverter;
import com.prodyna.conference.model.Conference;
import com.prodyna.conference.model.Room;
import com.prodyna.conference.model.Speaker;
import com.prodyna.conference.model.Talk;
import com.prodyna.conference.service.ConferenceService;
import com.prodyna.conference.service.RoomService;
import com.prodyna.conference.service.SpeakerService;
import com.prodyna.conference.service.TalkService;

@Model
public class EditTalkController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Talk talk;

	String editId;

	@Inject
	TalkService talkService;

	@Inject
	SpeakerService speakerService;

	@Inject
	RoomService roomService;

	@Inject
	ConferenceService conferenceService;

	@Inject
	FacesContext facesContext;

	@PostConstruct
	void postConstruct() {
		talk = new Talk();
		talk.setConference(new Conference());
		talk.setSpeakers(new ArrayList<Speaker>());
	}

	public Talk getTalk() {
		return talk;
	}

	public void setTalk(Talk talk) {
		if (talk != null) {
			this.talk = talk;
		}
	}

	public List<String> getSpeakerIds() {
		List<String> values = new LinkedList<String>();
		for (Speaker s : talk.getSpeakers()) {
			values.add("" + s.getId());
		}
		return values;
	}

	public void setSpeakerIds(List<String> speakers) {
		List<Speaker> sObjects = new LinkedList<Speaker>();
		for (String l : speakers) {
			sObjects.add(speakerService.read(Long.valueOf(l)));
		}
		talk.setSpeakers(sObjects);
	}

	public void setRoomId(String id) {
		Room room;
		if (id != null && !"".equals(id)) {
			room = roomService.read(Long.valueOf(id));
		} else {
			room = null;
		}
		talk.setRoom(room);
	}

	public String getRoomId() {
		Room room = talk.getRoom();
		String result = null;
		if (room != null) {
			result = "" + room.getId();
		}
		return result;
	}

	public void doSave() {
		try {
			talk = talkService.update(talk);
		} catch (Exception e) {
			addErrorMessage(e);
		}
	}

	private void addErrorMessage(Exception e) {
		FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,
				"Error " + e.getLocalizedMessage(), e.getLocalizedMessage());
		facesContext.addMessage("Error", fm);
	}

	public void doDelete() {
		try {
			talkService.delete(talk);
		} catch (Exception e) {
			addErrorMessage(e);
		}
	}

	public String doDelete(String id) {
		try {
			talkService.delete(Long.valueOf(id));
		} catch (Exception e) {
			addErrorMessage(e);
		}
		return "index?faces-redirect=true";
	}

	public String doNew(Conference conf) {
		conf = conferenceService.read(conf.getId());
		talk.setConference(conf);
		talk.setDauer(90);
		talk.setStartDate(conf.getAnfangsDatum());
		talk.setKurzBeschreibung("Kurzbeschreibung");
		talk.setName("Talkname");
		return doEdit((String) null);
	}

	public String doEdit() {
		return doEdit(getEditId());
	}

	public String doEdit(Long id) {
		return doEdit("" + id);
	}

	public String doEdit(String id) {
		if (id != null && !"".equals(id) && editId == null) {
			this.editId = id;
			talk = talkService.read(Long.valueOf(id));
		}
		return "editTalk";
	}

	public String getEditId() {
		return editId;
	}

	public void setEditId(String editId) {
		this.editId = editId;
	}

	public Converter getIdConverter() {
		return new GenericIdConverter<Talk>(talkService);
	}

}

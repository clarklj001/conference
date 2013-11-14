package com.prodyna.conference.controller;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.convert.Converter;
import javax.inject.Inject;

import com.prodyna.conference.converter.GenericIdConverter;
import com.prodyna.conference.model.Room;
import com.prodyna.conference.model.Talk;
import com.prodyna.conference.service.RoomService;

@Model
public class EditRoomController extends BasicController {

	@Inject
	RoomService roomService;

	Room room;

	private TalkListHelper talkListHelper;

	@PostConstruct
	void postConstruct() {
		room = new Room();
	}

	public Long getId() {
		return room.getId();
	}

	public void setId(Long id) {
		room.setId(id);
	}

	public String getName() {
		return room.getName();
	}

	public void setName(String name) {
		room.setName(name);
	}

	public int getCapazitaet() {
		return room.getCapazitaet();
	}

	public void setCapazitaet(int capazitaet) {
		room.setCapazitaet(capazitaet);
	}

	public void doSave() {
		try {
			room = roomService.update(room);
		} catch (Exception e) {
			addErrorMessage(e);
		}
	}

	public String doDelete() {
		try {
			roomService.delete(room);
		} catch (Exception e) {
			addErrorMessage(e);
		}
		return "rooms?faces-redirect=true";
	}

	public String doNew() {
		setName("Raumname");
		setCapazitaet(20);
		return doEdit(room);
	}

	public String doEdit(Room room) {
		this.room = room;
		return "editRoom";
	}
	
	private List<Talk> getTalks() {
		List<Talk> listTalks;
		Long roomId = getRoom().getId();
		if (roomId != null) {
			boolean set = false;
			if (talkListHelper==null) {
				set = true;
			} else if (!roomId.equals(talkListHelper.getId())) {
				set = true;
			}
			if (set)  {
				talkListHelper = new TalkListHelper(roomService.listRoomTalks(getRoom()), getRoom().getId());
			}
			listTalks = talkListHelper.getTalks();
		} else {
			listTalks = Collections.emptyList();
		}
		return listTalks;
	}

	public List<Date> getDates() {
		List<Date> result;
		getTalks();
		if (talkListHelper!=null) {
			result = talkListHelper.getDates();
		} else {
			result = Collections.emptyList();
		}
		return result;
	}

	public List<Talk> getRoomTalks(Date d) {
		return talkListHelper.getTalksForDate(d);
	}

	public Converter getIdConverter() {
		return new GenericIdConverter<Room>(roomService);
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

}

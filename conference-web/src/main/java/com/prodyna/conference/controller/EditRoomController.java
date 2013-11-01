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
public class EditRoomController {

	@Inject
	RoomService roomService;

	Room room;

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
		room = roomService.update(room);
	}

	public String doDelete() {
		roomService.delete(room);
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

	public List<Date> getDates() {
		List<Date> result;
		if (getId() != null && getId() > 0) {
			Room read = roomService.read(getId());
			result = roomService.listRoomDates(read);
		} else {
			result = Collections.emptyList();
		}
		return result;
	}

	public List<Talk> getRoomTalks(Date d) {
		return roomService.listRoomTalksAtDate(room, d);
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

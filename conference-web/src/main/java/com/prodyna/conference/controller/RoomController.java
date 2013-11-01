package com.prodyna.conference.controller;

import java.util.Date;
import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import com.prodyna.conference.model.Room;
import com.prodyna.conference.model.Talk;
import com.prodyna.conference.service.RoomService;

@Model
public class RoomController {
	@Inject
	RoomService roomService;

	public List<Room> getRooms() {
		return roomService.listRooms();
	}

	public void doDeleteRoom(Room room) {
		roomService.delete(room);
	}

	public List<Talk> getRoomTalks(Room room) {
		return roomService.listRoomTalks(room);
	}

	public List<Date> getRoomDates(Room room) {
		return roomService.listRoomDates(room);
	}

	public List<Talk> getRoomTalksAtDate(Room room, Date date) {
		return roomService.listRoomTalksAtDate(room, date);
	}
}

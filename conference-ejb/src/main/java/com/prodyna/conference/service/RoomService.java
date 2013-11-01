package com.prodyna.conference.service;

import java.util.Date;
import java.util.List;

import com.prodyna.conference.model.Room;
import com.prodyna.conference.model.Talk;

public interface RoomService extends GenericCrudService<Room> {

	public List<Date> listRoomDates(Room r);

	public List<Talk> listRoomTalks(Room r);

	public List<Talk> listRoomTalksAtDate(Room r, Date d);

	public List<Room> listRooms();

}
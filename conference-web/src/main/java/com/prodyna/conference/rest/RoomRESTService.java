package com.prodyna.conference.rest;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import com.prodyna.conference.model.Room;
import com.prodyna.conference.service.RoomService;

@Path("room")
@RequestScoped
public class RoomRESTService {
	@Inject
	RoomService roomService;

	@GET
	@Path("rooms")
	public List<Room> getRooms() {
		return roomService.listRooms();
	}

	// TODO develop rest of methods
}

package com.prodyna.conference.test;

import java.util.List;

import javax.inject.Inject;

import junit.framework.Assert;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.prodyna.conference.model.Room;
import com.prodyna.conference.service.RoomService;

@RunWith(Arquillian.class)
public class RoomServiceTest {
	@Deployment
	public static Archive<?> createTestArchive() {
		return AbstractArqTest.createTestArchive();
	}

	@Inject
	RoomService roomService;

	@Test
	public void testCrud() {
		List<Room> listRooms1 = roomService.listRooms();

		Room room = new Room();
		int capazitaet = 30;
		String name = "Paris";
		room.setCapazitaet(capazitaet);
		room.setName(name);
		room = roomService.update(room);

		List<Room> listRooms2 = roomService.listRooms();

		Assert.assertEquals(listRooms1.size() + 1, listRooms2.size());
		Assert.assertNotNull(room);

		room = roomService.read(room.getId());
		Assert.assertEquals(name, room.getName());
		Assert.assertEquals(capazitaet, room.getCapazitaet());

		int capazitaet2 = 35;
		room.setCapazitaet(capazitaet2);
		room = roomService.update(room);

		room = roomService.read(room.getId());
		Assert.assertEquals(capazitaet2, room.getCapazitaet());

		roomService.delete(room);
		listRooms2 = roomService.listRooms();

		Assert.assertEquals(listRooms1.size(), listRooms2.size());

	}
}

package com.prodyna.conference.service;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.TypedQuery;

import com.prodyna.conference.common.monitoring.Monitored;
import com.prodyna.conference.common.monitoring.MonitoringInterceptor;
import com.prodyna.conference.model.Room;
import com.prodyna.conference.model.Talk;

@Monitored
@Stateless
@Interceptors({ MonitoringInterceptor.class })
public class RoomServiceBean extends GenericCrudServiceBean<Room> implements
		RoomService {
	public RoomServiceBean() {
		super(Room.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.prodyna.conference.service.RoomService#listRoomDates(com.prodyna.
	 * conference.model.Room)
	 */
	@Override
	public List<Date> listRoomDates(Room r) {
		TypedQuery<Date> query = entityManager.createNamedQuery(
				Room.READ_ROOM_DATES, Date.class);
		query.setParameter("roomId", r.getId());
		return query.getResultList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.prodyna.conference.service.RoomService#listRoomTalks(com.prodyna.
	 * conference.model.Room)
	 */
	@Override
	public List<Talk> listRoomTalks(Room r) {
		TypedQuery<Talk> query = entityManager.createNamedQuery(
				Room.READ_ROOM_TALKS, Talk.class);
		query.setParameter("roomId", r.getId());
		return query.getResultList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.prodyna.conference.service.RoomService#listRoomTalksAtDate(com.prodyna
	 * .conference.model.Room, java.util.Date)
	 */
	@Override
	public List<Talk> listRoomTalksAtDate(Room r, Date d) {
		TypedQuery<Talk> query = entityManager.createNamedQuery(
				Room.READ_ROOM_TALKS_AT_DATE, Talk.class);
		query.setParameter("roomId", r.getId());
		query.setParameter("date", d);

		return query.getResultList();
	}

	@Override
	public List<Room> listRooms() {
		TypedQuery<Room> query = entityManager.createNamedQuery(
				Room.READ_ALL_ROOMS, Room.class);
		return query.getResultList();
	}
}

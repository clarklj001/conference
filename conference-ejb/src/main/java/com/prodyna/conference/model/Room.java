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
@Table(name = "Room")
@NamedQueries({
		@NamedQuery(name = Room.READ_ALL_ROOMS, query = "select r from Room r"),
		@NamedQuery(name = Room.READ_ROOM_TALKS, query = "select t from Talk t where t.room.id=:roomId"),
		@NamedQuery(name = Room.READ_ROOM_TALKS_AT_DATE, query = "select t from Talk t where t.room.id=:roomId and t.startDate=:date"),
		@NamedQuery(name = Room.READ_ROOM_DATES, query = "select distinct t.startDate from Talk t where t.room.id=:roomId") })
public class Room extends AbstractIdHolder implements NameHolder {
	public static final String READ_ALL_ROOMS = "read_all_rooms";

	public static final String READ_ROOM_DATES = "read_room_dates";

	public static final String READ_ROOM_TALKS = "read_room_talks";
	public static final String READ_ROOM_TALKS_AT_DATE = "read_room_talks_at_date";

	@Id
	@GeneratedValue
	Long id;

	String name;
	int capazitaet;

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

	public int getCapazitaet() {
		return capazitaet;
	}

	public void setCapazitaet(int capazitaet) {
		this.capazitaet = capazitaet;
	}
}

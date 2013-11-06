package com.prodyna.conference.test;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.inject.Inject;

import junit.framework.Assert;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.prodyna.conference.model.Conference;
import com.prodyna.conference.model.Room;
import com.prodyna.conference.model.Speaker;
import com.prodyna.conference.model.Talk;
import com.prodyna.conference.service.ConferenceService;
import com.prodyna.conference.service.RoomService;
import com.prodyna.conference.service.SpeakerService;
import com.prodyna.conference.service.TalkService;

@RunWith(Arquillian.class)
public class TalkServiceTest {
	@Deployment
	public static Archive<?> createTestArchive() {
		return AbstractArqTest.createTestArchive();
	}

	@Inject
	TalkService talkService;

	@Inject
	private ConferenceService conferenceService;

	@Inject
	SpeakerService speakerService;

	@Inject
	RoomService roomService;

	private Calendar calendar;
	private Calendar calendar2;

	private Conference conf;

	private Room room;

	private Speaker speaker2;

	private Speaker speaker1;

	@Before
	public void before() {
		// set up conference
		calendar = new GregorianCalendar();
		calendar2 = (Calendar) calendar.clone();
		calendar2.add(Calendar.DATE, 7);
		conf = new Conference();
		conf.setAnfangsDatum(calendar.getTime());
		conf.setEndDatum(calendar2.getTime());
		conf.setName("name" + Math.random());
		conf.setKurzBeschreibung("kurzadkjfhskdhfk" + Math.random());
		conf = conferenceService.update(conf);

		speaker1 = new Speaker();
		speaker1.setName("blubb hans dampf" + Math.random());
		speaker1.setKurzBeschreibung("Kurzdskjfksdhf" + Math.random());
		speaker1 = speakerService.update(speaker1);

		speaker2 = new Speaker();
		speaker2.setName("blubb hans dampf" + Math.random());
		speaker2.setKurzBeschreibung("Kurzdskjfksdhf" + Math.random());
		speaker2 = speakerService.update(speaker2);

		room = new Room();
		room.setCapazitaet(10 + (int) (300.0 * Math.random()));
		room.setName("room" + Math.random());
		room = roomService.update(room);
	}

	@Test
	public void testCrud() {
		List<Talk> talks1 = conferenceService.listTalks(conf);

		Talk talk = new Talk();
		String name = "TAlkhdfjdshfk";
		talk.setName(name);
		talk.setDauer(90);
		talk.setStartDate(calendar.getTime());
		talk.setConference(conf);
		talk.setSpeakers(Arrays.asList(speaker1, speaker2));
		talk.setRoom(room);

		talk = talkService.update(talk);
		talk = talkService.read(talk.getId());
		Assert.assertTrue(talk.getSpeakers().contains(speaker1));
		Assert.assertTrue(talk.getSpeakers().contains(speaker2));

		talk.setSpeakers(Arrays.asList(speaker1));
		talkService.update(talk);
		talk = talkService.read(talk.getId());
		Assert.assertTrue(talk.getSpeakers().contains(speaker1));
		Assert.assertFalse(talk.getSpeakers().contains(speaker2));

		List<Talk> talks2 = conferenceService.listTalks(conf);
		Assert.assertEquals(talks1.size() + 1, talks2.size());

		talkService.delete(talk);
		talks2 = conferenceService.listTalks(conf);
		Assert.assertEquals(talks1.size(), talks2.size());

	}

	@Test
	public void testRoomOccupied() {
		// TODO implement
	}

	@Test
	public void testSpeakerOccupied() {
		// TODO implement
	}
}
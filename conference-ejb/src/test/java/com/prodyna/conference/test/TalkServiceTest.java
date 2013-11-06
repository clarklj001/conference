package com.prodyna.conference.test;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.ejb.EJBException;
import javax.inject.Inject;

import junit.framework.Assert;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.junit.After;
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

	private Room room1;

	private Speaker speaker2;

	private Speaker speaker1;

	private Room room2;

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

		room1 = new Room();
		room1.setCapazitaet(10 + (int) (300.0 * Math.random()));
		room1.setName("room" + Math.random());
		room1 = roomService.update(room1);

		room2 = new Room();
		room2.setCapazitaet(10 + (int) (300.0 * Math.random()));
		room2.setName("room2" + Math.random());
		room2 = roomService.update(room2);
	}

	@After
	public void after() {
		roomService.delete(room1.getId());
		speakerService.delete(speaker1);
		speakerService.delete(speaker2);
		conferenceService.delete(conf);
	}

	@Test
	public void testCrud() {
		List<Talk> talks1 = conferenceService.listTalks(conf);

		Talk talk = new Talk();
		String name = "TAlkhdfjdshfk";
		talk.setName(name);
		int dauer = 90;
		talk.setDauer(dauer);
		talk.setStartDate(calendar.getTime());
		talk.setConference(conf);
		talk.setSpeakers(Arrays.asList(speaker1, speaker2));
		talk.setRoom(room1);

		talk = talkService.update(talk);
		talk = talkService.read(talk.getId());
		Assert.assertEquals(calendar.getTime(), talk.getStartDate());
		Assert.assertEquals(dauer, talk.getDauer());
		Assert.assertEquals(room1, talk.getRoom());
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

	/**
	 * Tests creating talk such that it is not within conference timeframe.
	 */
	@Test(expected = EJBException.class)
	public void testOutOfConferenceBounds1() {
		Calendar earlierTime = (Calendar) calendar.clone();
		earlierTime.add(Calendar.HOUR_OF_DAY, -1);

		Talk talk = new Talk();
		String name = "TAlkhdfjdshfk";
		talk.setName(name);
		int dauer = 90;
		talk.setDauer(dauer);
		talk.setStartDate(earlierTime.getTime());
		talk.setConference(conf);
		talk.setSpeakers(Arrays.asList(speaker1, speaker2));
		talk.setRoom(room1);

		talk = talkService.update(talk);
	}

	/**
	 * Tests moving conference such that talk is outside conference timeframe.
	 */
	@Test(expected = EJBException.class)
	public void testOutOfConferenceBounds2() {
		Calendar laterTime = (Calendar) calendar.clone();
		laterTime.add(Calendar.HOUR_OF_DAY, 1);

		Conference confToBeUpdated = new Conference();
		confToBeUpdated.setAnfangsDatum(calendar.getTime());
		confToBeUpdated.setKurzBeschreibung("xxxabc");
		confToBeUpdated.setName("xxxy");
		confToBeUpdated.setEndDatum(calendar2.getTime());
		confToBeUpdated = conferenceService.update(confToBeUpdated);

		Talk talk = new Talk();
		String name = "TAlkhdfjdshfk";
		talk.setName(name);
		int dauer = 90;
		talk.setDauer(dauer);
		talk.setStartDate(calendar.getTime());
		talk.setConference(confToBeUpdated);
		talk.setSpeakers(Arrays.asList(speaker1, speaker2));
		talk.setRoom(room1);

		talk = talkService.update(talk);

		confToBeUpdated.setAnfangsDatum(laterTime.getTime());
		conferenceService.update(confToBeUpdated);
	}

	/**
	 * Tests for a failure if a speaker is planned for another talk at the same
	 * time.
	 */
	@Test(expected = EJBException.class)
	public void testRoomOccupied() {
		Calendar laterTime = (Calendar) calendar.clone();
		laterTime.add(Calendar.HOUR_OF_DAY, 1);

		Talk talk = new Talk();
		String name = "TAlkhdfjdshfk";
		talk.setName(name);
		int dauer = 90;
		talk.setDauer(dauer);
		talk.setStartDate(calendar.getTime());
		talk.setConference(conf);
		talk.setSpeakers(Arrays.asList(speaker1));
		talk.setRoom(room1);

		talk = talkService.update(talk);

		talk = new Talk();
		String name2 = "TAlkhdfjdshfk2";
		talk.setName(name2);
		int dauer2 = 90;
		talk.setDauer(dauer2);
		talk.setStartDate(laterTime.getTime());
		talk.setConference(conf);
		talk.setSpeakers(Arrays.asList(speaker2));
		talk.setRoom(room1);

		talk = talkService.update(talk);
	}

	/**
	 * Tests for a failure if a room is planned for another talk at the same
	 * time.
	 */
	@Test(expected = EJBException.class)
	public void testSpeakerOccupied() {
		Calendar laterTime = (Calendar) calendar.clone();
		laterTime.add(Calendar.HOUR_OF_DAY, 1);

		Talk talk = new Talk();
		String name = "TAlkhdfjdshfk";
		talk.setName(name);
		int dauer = 90;
		talk.setDauer(dauer);
		talk.setStartDate(calendar.getTime());
		talk.setConference(conf);
		talk.setSpeakers(Arrays.asList(speaker1));
		talk.setRoom(room1);

		talk = talkService.update(talk);

		talk = new Talk();
		String name2 = "TAlkhdfjdshfk2";
		talk.setName(name2);
		int dauer2 = 90;
		talk.setDauer(dauer2);
		talk.setStartDate(laterTime.getTime());
		talk.setConference(conf);
		talk.setSpeakers(Arrays.asList(speaker1));
		talk.setRoom(room2);

		talk = talkService.update(talk);

	}
}
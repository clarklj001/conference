package com.prodyna.conference.test;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.prodyna.conference.event.HelloEvent;
import com.prodyna.conference.model.AbstractIdHolder;
import com.prodyna.conference.model.BeschreibungHolder;
import com.prodyna.conference.model.Conference;
import com.prodyna.conference.model.IdHolder;
import com.prodyna.conference.model.Member;
import com.prodyna.conference.model.NameHolder;
import com.prodyna.conference.model.Room;
import com.prodyna.conference.model.Speaker;
import com.prodyna.conference.model.Talk;
import com.prodyna.conference.service.ConferenceService;
import com.prodyna.conference.service.ConferenceServiceBean;
import com.prodyna.conference.service.GenericCrudService;
import com.prodyna.conference.service.GenericCrudServiceBean;
import com.prodyna.conference.service.RoomService;
import com.prodyna.conference.service.RoomServiceBean;
import com.prodyna.conference.service.SpeakerService;
import com.prodyna.conference.service.SpeakerServiceBean;
import com.prodyna.conference.service.TalkService;
import com.prodyna.conference.service.TalkServiceBean;
import com.prodyna.conference.util.Resources;

@RunWith(Arquillian.class)
public class HelloWorldServiceTest {
	@Deployment
	public static Archive<?> createTestArchive() {
		return ShrinkWrap
				.create(WebArchive.class, "test.war")
				.addClasses(Member.class, BeschreibungHolder.class,
						Conference.class, AbstractIdHolder.class,
						IdHolder.class, NameHolder.class, Room.class,
						Speaker.class, Talk.class, ConferenceServiceBean.class,
						ConferenceService.class, GenericCrudServiceBean.class,
						GenericCrudService.class, RoomServiceBean.class,
						RoomService.class, SpeakerServiceBean.class,
						SpeakerService.class, TalkServiceBean.class,
						TalkService.class, Resources.class)
				.addClasses(HelloEvent.class)
				.addAsResource("META-INF/test-persistence.xml",
						"META-INF/persistence.xml")
				.addAsResource("META-INF/beans.xml")
				// Deploy our test datasource
				.addAsWebInfResource("test-ds.xml", "test-ds.xml");
	}

	@Inject
	private ConferenceService conferenceServiceBean;

	@Inject
	private RoomService roomServiceBean;

	@Inject
	private SpeakerService speakerServiceBean;

	@Inject
	private TalkService talkServiceBean;

	@Test
	public void testConferenceService() {
		// TODO
	}

	@Test
	public void testRoomService() {
		// TODO
	}

	@Test
	public void testSpeakerService() {
		// TODO
	}

	@Test
	public void testTalkService() {
		// TODO
	}
}
package com.prodyna.conference.test;

import java.util.List;

import javax.inject.Inject;

import junit.framework.Assert;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.prodyna.conference.model.Speaker;
import com.prodyna.conference.service.SpeakerService;

@RunWith(Arquillian.class)
public class SpeakerServiceTest {
	@Deployment
	public static Archive<?> createTestArchive() {
		return AbstractArqTest.createTestArchive();
	}

	@Inject
	SpeakerService speakerService;

	@Test
	public void testCrud() {
		List<Speaker> listSpeakers1 = speakerService.listSpeakers();

		Speaker speaker = new Speaker();
		String name = "Blubber Blubb";
		String kurzBeschreibung = "Kurzbeschreibung";
		speaker.setName(name);
		speaker.setKurzBeschreibung(kurzBeschreibung);
		speaker = speakerService.update(speaker);
		Assert.assertNotNull(speaker.getId());

		List<Speaker> listSpeakers2 = speakerService.listSpeakers();
		Assert.assertEquals(listSpeakers1.size() + 1, listSpeakers2.size());

		speaker = speakerService.read(speaker.getId());
		Assert.assertEquals(name, speaker.getName());
		Assert.assertEquals(kurzBeschreibung, speaker.getKurzBeschreibung());

		String name2 = "Blubb2";
		speaker.setName(name2);
		speakerService.update(speaker);

		speaker = speakerService.read(speaker.getId());
		Assert.assertEquals(name2, speaker.getName());

		speakerService.delete(speaker.getId());

		listSpeakers2 = speakerService.listSpeakers();
		Assert.assertEquals(listSpeakers1.size(), listSpeakers2.size());
	}
}
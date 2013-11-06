package com.prodyna.conference.test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.ejb.EJBException;
import javax.inject.Inject;

import junit.framework.Assert;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.prodyna.conference.model.Conference;
import com.prodyna.conference.service.ConferenceService;

@RunWith(Arquillian.class)
public class ConferenceServiceTest {
	@Deployment
	public static Archive<?> createTestArchive() {
		return AbstractArqTest.createTestArchive();
	}

	@Inject
	private ConferenceService conferenceService;

	@Test
	public void testCrud() {

		Conference conf = new Conference();
		Date anfangsDatum = new Date();
		conf.setAnfangsDatum(anfangsDatum);
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.add(Calendar.DATE, 7);
		conf.setEndDatum(calendar.getTime());
		String name = "Name";
		String kurzBeschreibung = "Kurzbeschreibung";
		conf.setName(name);
		conf.setKurzBeschreibung(kurzBeschreibung);
		conf = conferenceService.update(conf);

		Assert.assertNotNull(conf.getId());

		conf = conferenceService.read(conf.getId());
		Assert.assertEquals(name, conf.getName());
		Assert.assertEquals(kurzBeschreibung, conf.getKurzBeschreibung());
		Assert.assertEquals(anfangsDatum, conf.getAnfangsDatum());
		Assert.assertEquals(calendar.getTime(), conf.getEndDatum());

		conferenceService.delete(conf);

		Conference result = conferenceService.read(conf.getId());
		Assert.assertNull(result);
	}

	@Test(expected = EJBException.class)
	public void testWrongDuration() {
		Conference conf = new Conference();
		Date anfangsDatum = new Date();
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.add(Calendar.DATE, 7);
		conf.setEndDatum(anfangsDatum);
		conf.setAnfangsDatum(calendar.getTime());
		// wrong dates.
		String name = "Name";
		String kurzBeschreibung = "Kurzbeschreibung";
		conf.setName(name);
		conf.setKurzBeschreibung(kurzBeschreibung);
		conf = conferenceService.update(conf);
	}
}

package com.prodyna.conference.test;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.prodyna.conference.service.ConferenceService;

@RunWith(Arquillian.class)
public class ConferenceServiceTest {
	@Deployment
	public static Archive<?> createTestArchive() {
		return AbstractArqTest.createTestArchive();
	}

	@Inject
	private ConferenceService conferenceServiceBean;

	@Test
	public void testCrud() {

	}
}

package com.prodyna.conference.test;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.junit.Test;
import org.junit.runner.RunWith;

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

	}
}
package com.prodyna.conference.test;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.prodyna.conference.service.TalkService;

@RunWith(Arquillian.class)
public class TalkServiceTest {
	@Deployment
	public static Archive<?> createTestArchive() {
		return AbstractArqTest.createTestArchive();
	}

	@Inject
	TalkService talkService;

	@Test
	public void testCrud() {

	}
}
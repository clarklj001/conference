package com.prodyna.conference.test;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.prodyna.conference.service.RoomService;

@RunWith(Arquillian.class)
public class RoomServiceTest {
	@Deployment
	public static Archive<?> createTestArchive() {
		return AbstractArqTest.createTestArchive();
	}

	@Inject
	RoomService roomService;

	@Test
	public void testCrud() {

	}
}

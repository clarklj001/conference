package com.prodyna.conference.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ConferenceServiceTest.class, RoomServiceTest.class,
		SpeakerServiceTest.class, TalkServiceTest.class })
public class AllTests {

}

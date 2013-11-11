package com.prodyna.conference.service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.slf4j.Logger;

import com.prodyna.conference.common.queue.QueueConnectionHandler;

@Stateless
public class TalkJms {
	/**
	 * Name of queue.
	 */
	private static final String QUEUE_TALK_CHANGE = "queue/talkupdated";

	/**
	 * Logger instance.
	 */
	@Inject
	private Logger log;

	/**
	 * Queue connection factory.
	 */
	@Resource(lookup = "java:/ConnectionFactory")
	private ConnectionFactory queueConnectionFactory;

	/**
	 * Queue connection util.
	 */
	private QueueConnectionHandler queueConnectionHandler;

	/**
	 * Set up queue connection.
	 * 
	 * @throws NamingException
	 */
	@PostConstruct
	private void setupQueue() throws NamingException {
		queueConnectionHandler = new QueueConnectionHandler(
				queueConnectionFactory, new InitialContext(), QUEUE_TALK_CHANGE);
		queueConnectionHandler.connect();
	}

	@PreDestroy
	private void tearDownQueue() {
		queueConnectionHandler.disconnect();
	}

	public void sendMessage(String message) {
		try {
			queueConnectionHandler.sendMessage(message);
		} catch (JMSException e) {
			throw new RuntimeException(e);
		}
	}
}

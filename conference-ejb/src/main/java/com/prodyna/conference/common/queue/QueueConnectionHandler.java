package com.prodyna.conference.common.queue;

import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.TextMessage;
import javax.naming.InitialContext;

/**
 * Utitity for sending queue Messages, manages connection and disconnection.
 * 
 * @author prodyna
 * 
 */
public class QueueConnectionHandler {
	/**
	 * Initial context.
	 */
	private InitialContext initialContext;

	/**
	 * Queue connection factory.
	 */
	private QueueConnectionFactory queueConnectionFactory;

	/**
	 * Queue connection.
	 */
	private QueueConnection queueConnection;

	/**
	 * Queue session.
	 */
	private QueueSession queueSession;

	/**
	 * Name of queue.
	 */
	private String queueName;

	/**
	 * Queue sender.
	 */
	private QueueSender sender;

	/**
	 * Constructor taking the queue name.
	 * 
	 * @param iniialContext
	 * @param queueConnectionFactory
	 */
	public QueueConnectionHandler(
			QueueConnectionFactory queueConnectionFactory,
			InitialContext iniialContext, String queueName) {
		this.queueConnectionFactory = queueConnectionFactory;
		this.initialContext = iniialContext;
		this.queueName = queueName;
	}

	/**
	 * Set up queue connection.
	 */
	public void connect() {
		try {
			queueConnection = queueConnectionFactory.createQueueConnection();
			Queue queue = (Queue) initialContext.lookup(queueName);
			queueSession = queueConnection.createQueueSession(true,
					QueueSession.AUTO_ACKNOWLEDGE);
			sender = queueSession.createSender(queue);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Tear down queue connection.
	 */
	public void disconnect() {
		try {
			sender.close();
			queueSession.commit();
			queueSession.close();
			queueConnection.stop();
			queueConnection.close();
			queueSession = null;
		} catch (JMSException e) {
			throw new RuntimeException(e);
		}
	}

	public void sendMessage(String message) throws JMSException {
		boolean autoConnected;
		if (queueSession == null) {
			connect();
			autoConnected = true;
		} else {
			autoConnected = false;
		}
		TextMessage queueMessage = queueSession.createTextMessage(message);
		sender.send(queueMessage);
		if (autoConnected) {
			disconnect();
		}
	}
}
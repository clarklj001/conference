package com.prodyna.conference.common.queue;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.slf4j.Logger;

@MessageDriven(mappedName = "queue/talkupdated", activationConfig = {
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/talkupdated") })
public class TalkUpdatedMessageBean implements MessageListener {

	@Inject
	private Logger log;

	@Resource
	MessageDrivenContext mdc;

	public void onMessage(Message message) {
		if (message instanceof TextMessage) {
			try {
				TextMessage textMessage = (TextMessage) message;
				log.info(textMessage.getText());
			} catch (JMSException e) {
				log.error("Error while reading from log.", e);
			}
		} else {
			log.error("At the moment only TextMessage supported, got: "
					+ message.getClass());
		}
	}
}
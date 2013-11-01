package com.prodyna.conference.observer;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import java.util.logging.Logger;

import com.prodyna.conference.event.HelloEvent;

@ApplicationScoped
public class HelloObserver {
    @Inject
    private Logger log;
    
    int count = 0;
    
	public void helloObserve(@Observes HelloEvent event) {
		log.info("Hello Event "+event);
		count++;
	}
	
	public int getCount() {
		return count;
	}
}

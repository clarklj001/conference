package com.prodyna.conference.common.jmx;

import java.lang.management.ManagementFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.management.MBeanServer;
import javax.management.ObjectName;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * MBean Startup
 * 
 * @author prodyna
 * 
 */
@Startup
@Singleton
public class MBeanStartup {

	Logger log = LoggerFactory.getLogger(MBeanStartup.class);

	@Inject
	PerformanceCollectorMXBean performanceCollector;

	/**
	 * Register performance MXBean.
	 */
	@PostConstruct
	private void registerMBeans() {
		MBeanServer ms = ManagementFactory.getPlatformMBeanServer();
		try {
			ObjectName objectName = new ObjectName(
					PerformanceCollectorMXBean.OBJECT_NAME);
			ms.registerMBean(performanceCollector, objectName);
		} catch (Exception e) {
			log.error("Unable to register MBeans", e);
		}
	}

	/**
	 * Unregister/Destroy performance MXBean.
	 */
	@PreDestroy
	private void unregisterMBeans() {
		MBeanServer ms = ManagementFactory.getPlatformMBeanServer();
		try {
			ObjectName objectName = new ObjectName(
					PerformanceCollectorMXBean.OBJECT_NAME);
			ms.unregisterMBean(objectName);
		} catch (Exception e) {
			log.error("Unable to unregister MBeans", e);
		}
	}

}
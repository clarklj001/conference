package com.prodyna.conference.common.monitoring;

import java.lang.reflect.Method;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prodyna.conference.common.jmx.PerformanceCollectorMXBean;

@Interceptor
@Monitored
public class MonitoringInterceptor {

	@Inject
	PerformanceCollectorMXBean performanceCollector;

	Logger logger = LoggerFactory.getLogger(MonitoringInterceptor.class);

	@AroundInvoke
	public Object aroundInvoke(InvocationContext context) {
		Method method = context.getMethod();
		Object[] parameters = context.getParameters();
		Object result = null;
		boolean success = false;
		long duration;
		long startTime = 0;
		try {
			startTime = System.currentTimeMillis();
			result = context.proceed();
			duration = System.currentTimeMillis() - startTime;
			success = true;
		} catch (Exception e) {
			duration = System.currentTimeMillis() - startTime;
			throw new RuntimeException(e);
		}
		logger.info("duration=" + duration + " " + success);
		performanceCollector.addEntry(context, duration, success);
		return result;
	}
}

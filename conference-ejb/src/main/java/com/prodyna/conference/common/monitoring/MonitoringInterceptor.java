package com.prodyna.conference.common.monitoring;

import java.lang.reflect.Method;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MonitoringInterceptor {

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

		return result;
	}
}

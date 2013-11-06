package com.prodyna.conference.common.monitoring;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prodyna.conference.common.jmx.PerformanceCollector;
import com.prodyna.conference.common.jmx.PerformanceCollectorMXBean;

@Interceptor
@Monitored
public class MonitoringInterceptor {

	PerformanceCollectorMXBean performanceCollector;

	Logger logger = LoggerFactory.getLogger(MonitoringInterceptor.class);

	private PerformanceCollectorMXBean getPerformanceCollector() {
		if (performanceCollector == null) {
			performanceCollector = PerformanceCollector.getRegisteredInstance();
		}
		return performanceCollector;
	}

	@AroundInvoke
	public Object aroundInvoke(InvocationContext context) {
		Object result = null;
		boolean success = false;
		long duration;
		long startTime = 0;
		Exception toThrow = null;
		try {
			startTime = System.currentTimeMillis();
			result = context.proceed();
			duration = System.currentTimeMillis() - startTime;
			success = true;
		} catch (Exception e) {
			duration = System.currentTimeMillis() - startTime;
			toThrow = e;
		}
		String signature = signature(context);
		logger.info(signature + ": duration=" + duration + " " + success);
		getPerformanceCollector().addEntry(signature, duration, success);
		if (toThrow != null) {
			throw new RuntimeException(toThrow);
		}
		return result;
	}

	private static String signature(InvocationContext context) {
		StringBuilder signature = new StringBuilder();

		signature.append(context.getTarget().getClass().getName()).append(":");
		signature.append(context.getMethod().getName()).append("(");
		Class<?>[] types = context.getMethod().getParameterTypes();
		for (Class<?> class1 : types) {
			signature.append(class1.getName()).append(", ");
		}
		signature.append(")");

		return signature.toString();
	}
}

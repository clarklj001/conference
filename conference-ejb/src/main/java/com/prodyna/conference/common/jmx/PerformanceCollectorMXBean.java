package com.prodyna.conference.common.jmx;

import javax.interceptor.InvocationContext;

public interface PerformanceCollectorMXBean {

	String OBJECT_NAME = "com.prodyna.conference:service=PerformanceCollector";

	void addEntry(InvocationContext context, long duration, boolean success);

	PerformanceEntry[] getEntries();

	void reset();

}

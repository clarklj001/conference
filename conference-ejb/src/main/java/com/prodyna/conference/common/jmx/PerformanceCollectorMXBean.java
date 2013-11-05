package com.prodyna.conference.common.jmx;

import java.util.List;

public interface PerformanceCollectorMXBean {

	String OBJECT_NAME = "com.prodyna.conference:service=PerformanceCollector";

	void addEntry(String signature, long duration, boolean success);

	List<PerformanceEntry> getEntries();

	void reset();

}

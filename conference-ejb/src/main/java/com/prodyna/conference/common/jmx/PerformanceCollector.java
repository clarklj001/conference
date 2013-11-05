package com.prodyna.conference.common.jmx;

import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.management.MBeanServer;
import javax.management.MBeanServerInvocationHandler;
import javax.management.ObjectName;

public class PerformanceCollector implements PerformanceCollectorMXBean {
	Map<String, PerformanceEntry> map;

	@PostConstruct
	void postConstruct() {
		map = new HashMap<String, PerformanceEntry>();
	}

	@Override
	public void addEntry(String signature, long duration, boolean success) {
		PerformanceEntry entry = entryByContext(signature);
		entry.addCall(duration, success);
	}

	/**
	 * 
	 * @param context
	 * @return non-null entry
	 */
	private PerformanceEntry entryByContext(String signature) {
		PerformanceEntry entry = map.get(signature);

		if (entry == null) {
			entry = new PerformanceEntry(signature);
			map.put(signature, entry);
		}
		return entry;
	}

	@Override
	public List<PerformanceEntry> getEntries() {
		return new ArrayList<PerformanceEntry>(map.values());
	}

	@Override
	public void reset() {
		map.clear();
	}

	public static PerformanceCollectorMXBean getRegisteredInstance() {
		MBeanServer ms = ManagementFactory.getPlatformMBeanServer();
		PerformanceCollectorMXBean result = null;
		try {
			ObjectName objectName = new ObjectName(
					PerformanceCollectorMXBean.OBJECT_NAME);
			result = MBeanServerInvocationHandler.newProxyInstance(ms,
					objectName, PerformanceCollectorMXBean.class, false);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}

}

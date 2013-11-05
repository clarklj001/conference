package com.prodyna.conference.common.jmx;

import java.util.HashMap;
import java.util.Map;

import javax.interceptor.InvocationContext;

public class PerformanceCollector implements PerformanceCollectorMXBean {
	Map<String, PerformanceEntry> map = new HashMap<String, PerformanceEntry>();

	@Override
	public void addEntry(InvocationContext context, long duration,
			boolean success) {
		PerformanceEntry entry = entryByContext(context);
		entry.addCall(duration, success);
	}

	/**
	 * 
	 * @param context
	 * @return non-null entry
	 */
	private PerformanceEntry entryByContext(InvocationContext context) {
		String signature = signature(context);
		PerformanceEntry entry = map.get(signature);

		if (entry == null) {
			entry = new PerformanceEntry();
			map.put(signature, entry);
		}
		return entry;
	}

	private static String signature(InvocationContext context) {
		StringBuilder signature = new StringBuilder();

		signature.append(context.getMethod().getClass().getName()).append(":");
		signature.append(context.getMethod().getName()).append("(");
		Class<?>[] types = context.getMethod().getParameterTypes();
		for (Class<?> class1 : types) {
			signature.append(class1.getName()).append(", ");
		}
		signature.append(")");

		return signature.toString();
	}

	@Override
	public PerformanceEntry[] getEntries() {
		return map.entrySet().toArray(new PerformanceEntry[] {});
	}

	@Override
	public void reset() {
		map.clear();
	}

}

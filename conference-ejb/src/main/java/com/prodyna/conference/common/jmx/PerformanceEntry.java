package com.prodyna.conference.common.jmx;

/**
 * Entry for Performance
 * 
 * @author Michael Kirchmann, PRODYNA AG
 * 
 */
public class PerformanceEntry {

	private String name;
	private long sumCalls;
	private long sumCallDuration;
	private long sumFailures;
	private long maxDuration;
	private long minDuration;

	PerformanceEntry(String name) {
		this.name = name;
	}

	public void addCall(long duration, boolean success) {
		boolean max;
		boolean min;

		max = sumCalls == 0;
		min = max;

		if (maxDuration < duration) {
			max = true;
		}
		if (minDuration > duration) {
			min = true;
		}
		if (min) {
			minDuration = duration;
		}
		if (max) {
			maxDuration = duration;
		}

		sumCalls++;
		sumCallDuration += duration;
		if (!success) {
			sumFailures++;
		}
	}

	public double getAverage() {
		return (double) sumCallDuration / (double) sumCalls;
	}

	public long getSumCalls() {
		return sumCalls;
	}

	public long getSumCallDuration() {
		return sumCallDuration;
	}

	public long getSumFailures() {
		return sumFailures;
	}

	public long getMaxDuration() {
		return maxDuration;
	}

	public long getMinDuration() {
		return minDuration;
	}

	public String getName() {
		return name;
	}

}

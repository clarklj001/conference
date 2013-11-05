package com.prodyna.conference.common.jmx;

/**
 * Entry for Performance
 * 
 * @author prodyna
 * 
 */
public class PerformanceEntry {

	private long sumCalls;
	private long sumCallDuration;
	private long sumFailures;
	private long maxDuration;
	private long minDuration;

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

}

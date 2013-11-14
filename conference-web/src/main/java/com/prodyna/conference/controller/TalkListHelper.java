package com.prodyna.conference.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.prodyna.conference.model.Talk;

public class TalkListHelper {
	final List<Talk> talks;
	private Map<Date, List<Talk>> mapDateToTalk;
	
	private final Long id;
	
	public TalkListHelper(List<Talk> talks, Long id) {
		this.talks = talks;
		this.id = id;
	}
	
	public List<Date> getDates() {
		if (mapDateToTalk==null) {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			for (Talk talk : talks) {
				String string = simpleDateFormat.format(talk.getStartDate());
				try {
					Date parse = simpleDateFormat.parse(string);
					
					putTalk(parse,talk);
				} catch (ParseException e) {
					// ignore, shouldnt happen after format with same formatter
				}
			}
			if (mapDateToTalk==null) {
				mapDateToTalk = Collections.emptyMap();
			}
		}
		List<Date> result;
		result = new ArrayList<Date>(mapDateToTalk.keySet());
		return result;
	}
	
	public List<Talk> getTalksForDate(Date date) {
		return mapDateToTalk.get(date);
	}

	private void putTalk(Date parse, Talk talk) {
		if (mapDateToTalk==null) {
			mapDateToTalk = new HashMap<Date, List<Talk>>();
		}
		List<Talk> list = mapDateToTalk.get(parse);
		if (list==null) {
			list = new LinkedList<Talk>();
			mapDateToTalk.put(parse, list);
		}
		list.add(talk);
	}

	public Long getId() {
		return id;
	}

	public List<Talk> getTalks() {
		return talks;
	}
}

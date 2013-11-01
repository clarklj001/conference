package com.prodyna.conference.util;

import java.util.Map;

import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

public class ViewScope {
	private Map getViewMap() {
		FacesContext fctx = FacesContext.getCurrentInstance();
		UIViewRoot viewRoot = fctx.getViewRoot();
		return viewRoot.getViewMap(true);
	}

	public <T> T get(Class<T> clazz) {
		String name = clazz.getSimpleName();
		T result;
		Map viewMap = getViewMap();
		if (viewMap.containsKey(name)) {
			result = clazz.cast(viewMap.get(name));
		} else {
			try {
				result = clazz.newInstance();
				viewMap.put(name, result);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return result;
	}
}

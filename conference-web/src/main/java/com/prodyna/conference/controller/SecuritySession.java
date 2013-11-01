package com.prodyna.conference.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;

@Named
@ManagedBean
@SessionScoped
public class SecuritySession implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	boolean loggedIn;

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

}

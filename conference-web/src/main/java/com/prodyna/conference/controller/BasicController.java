package com.prodyna.conference.controller;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

public class BasicController {

	@Inject
	protected FacesContext facesContext;

	protected void addErrorMessage(Exception e) {
		FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,
				"Error " + e.getLocalizedMessage(), e.getLocalizedMessage());
		facesContext.addMessage("Error", fm);
	}
}

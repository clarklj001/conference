package com.prodyna.conference.controller;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import com.prodyna.conference.common.exception.ValidationException;

public class BasicController {

	@Inject
	protected FacesContext facesContext;

	protected void addErrorMessage(Throwable e) {
		String message = "";
		if (e.getCause() != null) {
			e = e.getCause();
		}

		if (e instanceof ValidationException) {
			message = ((ValidationException) e).getValidationErrorMessage();
		} else {
			message = e.getLocalizedMessage();
		}
		FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,
				"Error " + message, message);
		facesContext.addMessage("Error", fm);
	}
}

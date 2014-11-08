package com.prodyna.conference.common.exception;

/**
 * Exception for general Validation errors.
 * 
 * @author prodyna
 * 
 */
public class ValidationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String validationErrorMessage;

	public ValidationException(String validationErrorMessage) {
		this.validationErrorMessage = validationErrorMessage;
	}

	public String getValidationErrorMessage() {
		return validationErrorMessage;
	}
}

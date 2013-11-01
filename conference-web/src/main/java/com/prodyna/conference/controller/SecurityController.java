package com.prodyna.conference.controller;

import java.io.Serializable;
import java.util.Map;

import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Model
public class SecurityController implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String SECURITY_SESSION = "securitySession";
	String userName;
	String password;

	@Inject
	private FacesContext facesContext;

	SecuritySession securitySession;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String tryLogin() {
		String result = "login";
		if (userName.equals("admin") && password.equals("xxx")) {
			getSecuritySession().setLoggedIn(true);
			result = "index?faces-redirect=true";
		}
		return result;
	}

	public String tryLogout() {
		getSecuritySession().setLoggedIn(false);
		return "index?faces-redirect=true";
	}

	public boolean isLoggedIn() {
		return getSecuritySession().isLoggedIn();
	}

	private SecuritySession getSecuritySession() {
		if (securitySession == null) {
			Map<String, Object> map = facesContext.getExternalContext()
					.getSessionMap();
			securitySession = (SecuritySession) map.get(SECURITY_SESSION);
			if (securitySession == null) {
				securitySession = new SecuritySession();
				map.put(SECURITY_SESSION, securitySession);
			}
		}
		return securitySession;
	}

}

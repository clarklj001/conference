package com.prodyna.conference.converter;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import com.prodyna.conference.model.IdHolder;
import com.prodyna.conference.service.GenericCrudService;

public class GenericIdConverter<L extends IdHolder> implements Converter {

	GenericCrudService<L> crudService;

	Logger logger = Logger.getLogger("AbstractIdConverter");

	public GenericIdConverter(GenericCrudService<L> crudService) {
		this.crudService = crudService;
	}

	@Override
	public L getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		L result;
		if (arg2 != null) {
			try {
				Long l = Long.valueOf(arg2);
				result = crudService.read(l);
			} catch (Exception ex) {
				//
				logger.log(Level.WARNING, "error while converting", ex);
				result = null;
			}
		} else {
			result = null;
		}
		return result;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		String result = null;
		if (arg2 instanceof IdHolder) {
			result = "" + ((IdHolder) arg2).getId();
		}
		return result;
	}

	protected GenericCrudService<L> getCrudService() {
		return crudService;
	}

	protected void setCrudService(GenericCrudService<L> crudService) {
		this.crudService = crudService;
	}

}

package com.prodyna.conference.service;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.prodyna.conference.model.IdHolder;

public abstract class GenericCrudServiceBean<T extends IdHolder> implements
		GenericCrudService<T> {
	@Inject
	EntityManager entityManager;

	Class<T> clazz;

	public GenericCrudServiceBean(Class<T> clazz) {
		this.clazz = clazz;
	}

	public T read(Long id) {
		return entityManager.find(clazz, id);
	}

	public T update(T t) {
		if (isValid(t)) {
			if (t.getId() == null) {
				entityManager.persist(t);
				entityManager.flush();
				entityManager.refresh(t);
			} else {
				t = entityManager.merge(t);
			}
		}
		return t;
	}

	protected boolean isValid(T t) {
		// do nothing in base implementation
		return true;
	}

	public void delete(T t) {
		t = read(t.getId());
		entityManager.remove(t);
	}

	public void delete(Long id) {
		T t = read(id);
		entityManager.remove(t);
	}
}

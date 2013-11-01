package com.prodyna.conference.service;

public interface GenericCrudService<T> {
	public T update(T t);

	public void delete(T t);

	public void delete(Long id);

	public T read(Long id);
}

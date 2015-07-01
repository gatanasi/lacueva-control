package com.lacueva.control.dao;

import java.util.List;

public interface GenericDao<T> {

	public T create(final T t);

	public void delete(final Object id);

	public T find(final Object id);

	public T update(final T t);

	public List<T> getAll();
}
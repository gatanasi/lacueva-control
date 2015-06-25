package com.lacueva.control.dao;

public interface GenericDao<T> {
	/**
	 * Method that returns the number of entries from a table that meet some
	 * criteria (where clause params)
	 *
	 * @param params
	 *            sql parameters
	 * @return the number of records meeting the criteria
	 */
	public T create(final T t);

	public void delete(final Object id);

	public T find(final Object id);

	public T update(final T t);
}
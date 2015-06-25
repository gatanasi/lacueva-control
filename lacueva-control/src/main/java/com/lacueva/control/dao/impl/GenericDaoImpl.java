package com.lacueva.control.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.lacueva.control.dao.GenericDao;

public abstract class GenericDaoImpl<T> implements GenericDao<T> {

	@PersistenceContext
	protected EntityManager entityManager;

	private Class<T> type;

	@SuppressWarnings("unchecked")
	public GenericDaoImpl() {
		Type t = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) t;
		type = (Class) pt.getActualTypeArguments()[0];
	}

	@Override
	@Transactional
	public T create(final T t) {
		this.entityManager.persist(t);
		return t;
	}

	@Override
	@Transactional
	public void delete(final Object id) {
		this.entityManager.remove(this.entityManager.getReference(type, id));
	}

	@Override
	public T find(final Object id) {
		return (T) this.entityManager.find(type, id);
	}

	@Override
	@Transactional
	public T update(final T t) {
		return this.entityManager.merge(t);
	}
}
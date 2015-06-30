package com.lacueva.control.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.lacueva.control.dao.GenericDao;

public abstract class GenericDaoImpl<T> implements GenericDao<T> {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@PersistenceContext
	protected EntityManager entityManager;

	private Class<T> type;

	@SuppressWarnings("unchecked")
	public GenericDaoImpl() {
		Type t = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) t;
		type = (Class<T>) pt.getActualTypeArguments()[0];
	}

	@Override
	@Transactional
	public T create(final T t) {
		logger.info("Persisting " + type.getSimpleName());
		this.entityManager.persist(t);
		logger.info("Persisted with data= " + t.toString());
		return t;
	}

	@Override
	@Transactional
	public void delete(final Object id) {
		logger.info("Deleting " + type.getSimpleName() + " with ID= "
				+ id.toString());
		this.entityManager.remove(this.entityManager.getReference(type, id));
		logger.info("Deleted " + type.getSimpleName() + " with ID= "
				+ id.toString());
	}

	@Override
	public T find(final Object id) {
		logger.info("Finding " + type.getSimpleName() + " with ID= "
				+ id.toString());
		T t = (T) this.entityManager.find(type, id);
		if (t == null) {
			logger.info(type.getSimpleName() + " with ID= " + id.toString()
					+ " NOT FOUND");
		} else {
			logger.info("Found with data= " + t.toString());
		}
		return t;
	}

	@Override
	@Transactional
	public T update(final T t) {
		logger.info("Merging " + type.getSimpleName());
		T tM = (T) this.entityManager.merge(t);
		logger.info("Merged with data= " + tM.toString());
		return tM;
	}
}
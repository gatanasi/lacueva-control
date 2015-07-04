package com.lacueva.control.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

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
	public T create(final T obj) {
		logger.debug("Persisting " + type.getSimpleName());
		this.entityManager.persist(obj);
		logger.debug("Persisted with data= " + obj);
		return obj;
	}

	@Override
	@Transactional
	public void delete(final Object id) {
		logger.debug("Deleting " + type.getSimpleName() + " with ID= " + id);
		this.entityManager.remove(this.entityManager.getReference(type, id));
		logger.debug("Deleted " + type.getSimpleName() + " with ID= " + id);
	}

	@Override
	public T find(final Object id) {
		logger.debug("Finding " + type.getSimpleName() + " with ID= " + id);
		T t = (T) this.entityManager.find(type, id);
		if (t == null) {
			logger.debug(type.getSimpleName() + " with ID= " + id
					+ " NOT FOUND");
		} else {
			logger.debug("Found with data= " + t);
		}
		return t;
	}

	@Override
	@Transactional
	public T update(final T obj) {
		logger.debug("Merging " + type.getSimpleName());
		T tM = (T) this.entityManager.merge(obj);
		logger.debug("Merged with data= " + tM);
		return tM;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getAll() {
		logger.debug("Getting all " + type.getSimpleName());
		List<T> tF = (List<T>) this.entityManager.createQuery(
				"SELECT a FROM " + type.getSimpleName() + " a").getResultList();
		if (tF == null || tF.size() == 0) {
			logger.debug("No " + type.getSimpleName() + " FOUND");
		} else {
			logger.debug("Found with data= " + tF);
		}
		return tF;
	}
}
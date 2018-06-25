package com.yuan.iliya.guoshui.core.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yuan.iliya.guoshui.core.dao.BaseDao;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {
	private Class<?> clazz;
	
	public BaseDaoImpl() {
		Type type = this.getClass().getGenericSuperclass();
		clazz = (Class<T>)((ParameterizedType)type).getActualTypeArguments()[0];
	}

 	@Override
	public void save(T t) {
 		getHibernateTemplate().save(t);
	}

	@Override
	public void update(T t) {
		getHibernateTemplate().update(t);
	}

	@Override
	public void deleteById(Serializable id) {
		getHibernateTemplate().delete(getEntityById(id));
	}

	@Override
	public T getEntityById(Serializable id) {
		return  (T) getHibernateTemplate().get(clazz, id);
	}

	@Override
	public List<T> getAllEntities() {
		return (List<T>)getHibernateTemplate().loadAll(clazz);
	}

}

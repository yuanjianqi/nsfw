package com.yuan.iliya.guoshui.core.dao;

import java.io.Serializable;
import java.util.List;

public interface BaseDao<T> {
	public void save(T t);
	public void update(T t);
	public void deleteById(Serializable id);
	public T getEntityById(Serializable id);
	public List<T> getAllEntities();

}

package sn.esmt.gymManagement.models.consumer.dao;

import java.util.List;

import sn.esmt.gymManagement.exceptions.CrudDaoException;

public interface IRepository {
	
	public <T> T create(T entity) throws CrudDaoException;
	
	public <T> T read(int id, Class<T> entityClass) throws CrudDaoException;
	
	public <T> T update(T entity) throws CrudDaoException;
	
	public <T> void delete(int id, Class<T> entityClass) throws CrudDaoException;
	
	public <T> List<T> list(Class<T> entityClass) throws CrudDaoException;
}

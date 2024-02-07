package sn.esmt.gymManagement.models.consumer.dao;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import sn.esmt.gymManagement.models.business.config.HibernateSession;
import sn.esmt.gymManagement.models.consumer.exceptions.CrudDaoException;

public class MysqlHibernateRepository implements IRepository {
	
	private static Session session = null;
	
	private static Transaction transaction = null;
	
	public static Logger logger = LogManager.getLogger(MysqlHibernateRepository.class);
	
	private static MysqlHibernateRepository repository = null;
	
	private MysqlHibernateRepository() {}
	
	public static MysqlHibernateRepository getInstance() {
		return (repository == null) ? new MysqlHibernateRepository() : repository;
	}
	
	@Override
	public <T> T create(T entity) throws CrudDaoException {
		try {
			// create session
			session = HibernateSession.getSession();
			
			// open transaction object
			transaction = session.beginTransaction();
			logger.info("Begin transaction!");
			
			session.persist(entity);
			transaction.commit();
			logger.info("Record is successfull added!");
			
			return entity;
			
		} catch(Exception e) {
			throw new CrudDaoException("Error : " + e.getClass().getSimpleName() + " : " + e.getMessage());
		}
	}

	@Override
	public <T> T read(int id, Class<T> entityClass) throws CrudDaoException {
		T entity = null;
		try {
			session = HibernateSession.getSession();
			
			entity = session.find(entityClass, id);
			if (entity != null) logger.info("Record successfull readed!");
			else logger.error("Error while reading record!");
			
			return entity;
						
		} catch(Exception e) {
			throw new CrudDaoException("Error : " + e.getClass().getSimpleName() + " : " + e.getMessage());
		}
	}

	@Override
	public <T> T update(T entity) throws CrudDaoException {
		try {
			session = HibernateSession.getSession();
			
			transaction = session.beginTransaction();
			logger.info("Begin transaction!");
			
			session.persist(entity);
			transaction.commit();
			logger.info("Record is successfull updated!");
			
			return entity;
			
		} catch(Exception e) {
			throw new CrudDaoException("Error : " + e.getClass().getSimpleName() + " : " + e.getMessage());
		}
	}

	@Override
	public <T> void delete(int id, Class<T> entityClass) throws CrudDaoException {
		try {
			session = HibernateSession.getSession();
			
			transaction = session.beginTransaction();
			logger.info("Begin transaction");
			
			Object object = session.find(entityClass, id);
			if (object != null) {
				session.remove(object);
				logger.info("Record successfull removed!");
			} else {
				logger.error("Error while removing record");
			}
			
			transaction.commit();
		} catch(Exception e) {
			throw new CrudDaoException("Error : " + e.getClass().getSimpleName() + " : " + e.getMessage());
		}
	}

	@Override
	public <T> List<T> list(Class<T> entityClass) throws CrudDaoException {
		List<T> entities = null;
        try {
            session = HibernateSession.getSession();
            
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<T> cr = (CriteriaQuery<T>) cb.createQuery(entityClass);
            Root<T> root = (Root<T>) cr.from(entityClass);
            
            cr.select(root);

            Query<T> query = session.createQuery(cr);
            entities = query.getResultList();

        } catch (Exception e) {
			throw new CrudDaoException("Error : " + e.getClass().getSimpleName() + " : " + e.getMessage());
        }

        return entities;
	}

}

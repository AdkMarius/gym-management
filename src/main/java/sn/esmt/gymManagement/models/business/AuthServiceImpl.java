package sn.esmt.gymManagement.models.business;

import org.hibernate.Session;
import org.hibernate.query.Query;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import sn.esmt.gymManagement.exceptions.CrudDaoException;
import sn.esmt.gymManagement.models.beans.Client;
import sn.esmt.gymManagement.models.beans.Utilisateur;
import sn.esmt.gymManagement.models.business.config.HibernateSession;
import sn.esmt.gymManagement.payLoad.ApiResponse;
import sn.esmt.gymManagement.utils.Constants;

public class AuthServiceImpl implements AuthService {
	
	private static Session session = HibernateSession.getSession();
	
	private static AuthService authService;
	
	private AuthServiceImpl() {}
	
	public static AuthService getAuthServiceInstance() {
		return (authService == null) ? new AuthServiceImpl() : authService;
			
	}
	
	@Override
	public <T> ApiResponse loginUser(Class<T> entityClass, String email, String password) throws CrudDaoException {
		T user;
		try {
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<T> cr = cb.createQuery(entityClass);
			
			Root<T> root = cr.from(entityClass);
            cr.select(root).where(cb.and(cb.equal(root.get("email"), email), cb.equal(root.get("password"), password)));
			
			Query<T> query = session.createQuery(cr);
            user = (T) query.uniqueResult();
            
            if (entityClass == Utilisateur.class)
            	Constants.utilisateur = (Utilisateur) user;
            else
            	Constants.client = (Client) user;
		} catch(Exception e) {
			throw new CrudDaoException("Error : " + e.getClass().getSimpleName() + " : " + e.getMessage());
		}
		
		return new ApiResponse((user != null), (user != null) ? "User found!" : "User not found");
	}
}

package sn.esmt.gymManagement.models.business;

import sn.esmt.gymManagement.exceptions.CrudDaoException;
import sn.esmt.gymManagement.payLoad.ApiResponse;

public interface AuthService {
	public <T> ApiResponse loginUser(Class<T> myClass, String email, String password) throws CrudDaoException;
}

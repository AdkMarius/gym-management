package sn.esmt.gymManagement.models.business;

import java.util.List;

import sn.esmt.gymManagement.exceptions.CrudDaoException;
import sn.esmt.gymManagement.models.beans.Client;
import sn.esmt.gymManagement.models.beans.Privilege;
import sn.esmt.gymManagement.models.beans.Role;
import sn.esmt.gymManagement.models.beans.Utilisateur;

public interface AdminService {
	
	List<Privilege> getPrivileges() throws CrudDaoException;
	
	Privilege addPrivilege(Privilege privilege) throws CrudDaoException;
	
	Role addRole(Role role) throws CrudDaoException;
	
	List<Role> getRoles() throws CrudDaoException;
	
	int countRole() throws CrudDaoException;
	
	Utilisateur addUser(Utilisateur user) throws CrudDaoException;
	Utilisateur updateUser(int userId, Utilisateur user) throws CrudDaoException;

	List<Utilisateur> getUsers() throws CrudDaoException;

	void deleteUser(int id) throws CrudDaoException ;

    List<Client> getCustomers() throws CrudDaoException ;

	Client addCustomer(Client client) throws CrudDaoException ;
	Client updateCustomer(int customerId, Client client) throws CrudDaoException ;

	void deleteCustomer(int id) throws CrudDaoException ;
}

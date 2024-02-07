package sn.esmt.gymManagement.models.business;

import java.util.List;


import sn.esmt.gymManagement.models.beans.Privilege;
import sn.esmt.gymManagement.models.beans.Role;
import sn.esmt.gymManagement.models.consumer.dao.MysqlHibernateRepository;
import sn.esmt.gymManagement.models.consumer.exceptions.CrudDaoException;

public class AdminServiceImpl implements AdminService {

	private static MysqlHibernateRepository repository = MysqlHibernateRepository.getInstance();
	
	private static AdminService adminService;
	
	private AdminServiceImpl() {}
	
	public static AdminService getInstance() {
		return (adminService == null) ? new AdminServiceImpl() : adminService;
	}
	
	@Override
	public List<Privilege> getPrivileges() throws CrudDaoException {
		return repository.list(Privilege.class);
	}

	@Override
	public Privilege addPrivilege(Privilege privilege) throws CrudDaoException {
		return repository.create(privilege);
	}

	@Override
	public Role addRole(Role role) throws CrudDaoException {
		return repository.create(role);
	}

	@Override
	public List<Role> getRoles() throws CrudDaoException {
		return repository.list(Role.class);
	}

	@Override
	public int countRole() throws CrudDaoException {
		return getRoles().size();
	}

}

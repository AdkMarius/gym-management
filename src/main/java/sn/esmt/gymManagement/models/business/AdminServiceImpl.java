package sn.esmt.gymManagement.models.business;

import java.util.List;

import sn.esmt.gymManagement.exceptions.CrudDaoException;
import sn.esmt.gymManagement.models.beans.Client;
import sn.esmt.gymManagement.models.beans.Privilege;
import sn.esmt.gymManagement.models.beans.Role;
import sn.esmt.gymManagement.models.beans.Utilisateur;
import sn.esmt.gymManagement.models.consumer.dao.MysqlHibernateRepository;

public class AdminServiceImpl implements AdminService {

    private static MysqlHibernateRepository repository = MysqlHibernateRepository.getInstance();

    private static AdminService adminService;

    private AdminServiceImpl() {
    }

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

    @Override
    public Utilisateur addUser(Utilisateur user) throws CrudDaoException {
        return repository.create(user);
    }

    @Override
    public Utilisateur updateUser(int userId, Utilisateur user) throws CrudDaoException {
        return repository.update(user);
    }

    @Override
    public List<Utilisateur> getUsers() throws CrudDaoException {
        return repository.list(Utilisateur.class);
    }

    @Override
    public void deleteUser(int id) throws CrudDaoException {
        repository.delete(id, Utilisateur.class);
    }

    @Override
    public List<Client> getCustomers() throws CrudDaoException {
        return repository.list(Client.class);
    }

    @Override
    public Client addCustomer(Client client) throws CrudDaoException {
        return repository.create(client);
    }

    @Override
    public Client updateCustomer(int customerId, Client client) throws CrudDaoException {
        return repository.update(client);
    }

    @Override
    public void deleteCustomer(int id) throws CrudDaoException {
        repository.delete(id, Client.class);
    }

}

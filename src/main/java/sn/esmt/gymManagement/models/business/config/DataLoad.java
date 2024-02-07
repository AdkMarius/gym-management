package sn.esmt.gymManagement.models.business.config;

import sn.esmt.gymManagement.models.business.AdminService;
import sn.esmt.gymManagement.models.business.AdminServiceImpl;
import sn.esmt.gymManagement.models.consumer.exceptions.CrudDaoException;

public class DataLoad {
	
	private AdminService adminService = AdminServiceImpl.getInstance();
	
	private void loadRoles() throws CrudDaoException {
		int numberRoles = adminService.countRole();
		
		if (numberRoles != 0) {
			return;
		}
		
		
	}
}

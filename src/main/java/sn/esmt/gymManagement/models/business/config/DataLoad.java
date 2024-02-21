package sn.esmt.gymManagement.models.business.config;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import sn.esmt.gymManagement.exceptions.CrudDaoException;
import sn.esmt.gymManagement.models.beans.Privilege;
import sn.esmt.gymManagement.models.beans.Role;
import sn.esmt.gymManagement.models.beans.Utilisateur;
import sn.esmt.gymManagement.models.beans.enums.TypePrivilege;
import sn.esmt.gymManagement.models.beans.enums.TypeRole;
import sn.esmt.gymManagement.models.beans.enums.TypeUtilisateur;
import sn.esmt.gymManagement.models.business.AdminService;
import sn.esmt.gymManagement.models.business.AdminServiceImpl;

public class DataLoad {
	
	private static AdminService adminService = AdminServiceImpl.getInstance();
	
	private static Logger logger = LogManager.getLogger(DataLoad.class);
	
	public static void loadAllData() {
        new Thread(() -> {
            try {
                loadRole();
            } catch (CrudDaoException e) {
            	logger.error("Error while load sysadmin's data");
            }
        }).start();
    }
	
	private static void loadRole() throws CrudDaoException {
		int numberRoles = adminService.countRole();
		
		if (numberRoles != 0) {
			return;
		}
		
		List<Privilege> privilegeAdmin = new ArrayList<>();
		
		for (int i = 0; i < TypePrivilege.values().length; i++) {
			Privilege p = new Privilege(TypePrivilege.values()[i]);
			privilegeAdmin.add(p);
		}
		
		Role sysAdmin = new Role(TypeRole.SYSTEM_ADMIN, privilegeAdmin);
		adminService.addRole(sysAdmin);
		
		loadData(sysAdmin);
	}
	
	// create super admin
	private static void loadData(Role role) throws CrudDaoException {
      try {
          Utilisateur utilisateur = new Utilisateur();
          utilisateur.setFirstName("Robert");
          utilisateur.setLastName("DOSSOU");
          utilisateur.setEmail("sysadmin@esmt.sn");
          utilisateur.setPassword("passer");
          utilisateur.setUserType(TypeUtilisateur.SYSADMIN);
          utilisateur.addRole(role);
          adminService.addUser(utilisateur);
      } catch (Exception e) {
			throw new CrudDaoException("Error : " + e.getClass().getSimpleName() + " : " + e.getMessage());
      }
  }
}

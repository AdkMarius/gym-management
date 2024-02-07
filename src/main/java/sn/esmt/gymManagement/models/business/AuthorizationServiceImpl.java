package sn.esmt.gymManagement.models.business;

import sn.esmt.gymManagement.models.beans.Privilege;
import sn.esmt.gymManagement.models.beans.Role;
import sn.esmt.gymManagement.models.beans.Utilisateur;

public class AuthorizationServiceImpl implements AuthorizationService {

	@Override
	public boolean checkRBAC(Utilisateur user, Privilege privilege) {
		for (Role role : user.getListRole()) {
			if (role.check(privilege))
				return true;
		}
		
		return false;
	}

}

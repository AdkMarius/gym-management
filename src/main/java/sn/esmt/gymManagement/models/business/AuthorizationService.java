package sn.esmt.gymManagement.models.business;

import sn.esmt.gymManagement.models.beans.Privilege;
import sn.esmt.gymManagement.models.beans.Utilisateur;

public interface AuthorizationService {
	public boolean checkRBAC(Utilisateur user, Privilege privilege);
}

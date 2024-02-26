package sn.esmt.gymManagement.payLoad;

import sn.esmt.gymManagement.models.beans.enums.TypeUtilisateur;

public class TypeUtilisateurPayload {

    private TypeUtilisateur utilisateurType;
    private String name;

    public TypeUtilisateurPayload() {
    }

    public TypeUtilisateurPayload(TypeUtilisateur utilisateurType, String name) {
        this.utilisateurType = utilisateurType;
        this.name = name;
    }

    public TypeUtilisateur getUtilisateurType() {
        return utilisateurType;
    }

    public void setUtilisateurType(TypeUtilisateur utilisateurType) {
        this.utilisateurType = utilisateurType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}

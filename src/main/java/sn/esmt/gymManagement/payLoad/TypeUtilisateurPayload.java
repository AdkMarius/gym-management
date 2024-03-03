package sn.esmt.gymManagement.payLoad;

import javafx.scene.control.SingleSelectionModel;
import sn.esmt.gymManagement.models.beans.enums.TypeUtilisateur;

import java.util.Objects;

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

    @Override
    public int hashCode() {
        return Objects.hash(utilisateurType, name);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof TypeUtilisateurPayload))
            return false;

        TypeUtilisateurPayload other = (TypeUtilisateurPayload)obj;
        return this.utilisateurType == other.utilisateurType;
    }
}

package sn.esmt.gymManagement.controllers.admin.root;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sn.esmt.gymManagement.models.beans.Utilisateur;
import sn.esmt.gymManagement.payLoad.TypeUtilisateurPayload;
import sn.esmt.gymManagement.utils.Constants;

import java.util.Optional;

public class UserItemController {

    public Label initialsLabel;
    public Label lastNameLabel;
    public Label firstnameLabel;
    public Label emailLabel;
    public Label userTypeLabel;
    public MenuItem editGroupMenu;
    public MenuItem deleteGroupMenu;
    private Utilisateur utilisateur;

    public final static Logger logger = LogManager.getLogger(UserItemController.class);

    public void setUtilisateur(Utilisateur user) {
        this.utilisateur = user;
        this.populateData();
    }

    private void populateData() {
        // TODO: 2/25/2024  update user interface by setting user data
        this.initialsLabel.setText(this.utilisateur.getFirstName().charAt(0) + "" + this.utilisateur.getLastName().charAt(0));
        this.lastNameLabel.setText(this.utilisateur.getLastName());
        this.firstnameLabel.setText(this.utilisateur.getFirstName());
        Optional<TypeUtilisateurPayload> typeUtilisateur = Constants.typeUserPayloads.stream().filter(typeUtilisateurPayload -> typeUtilisateurPayload.getUtilisateurType() == this.utilisateur.getUserType()).findFirst();
        typeUtilisateur.ifPresent(typeUtilisateurPayload -> this.userTypeLabel.setText(typeUtilisateurPayload.getName()));
        this.emailLabel.setText(this.utilisateur.getEmail());
    }

    public void onEdit(ActionEvent actionEvent) {
    }

    public void onDelete(ActionEvent actionEvent) {
    }
}

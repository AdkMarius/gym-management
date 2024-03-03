package sn.esmt.gymManagement.controllers.admin.root;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sn.esmt.gymManagement.GymManagementApplication;
import sn.esmt.gymManagement.exceptions.CrudDaoException;
import sn.esmt.gymManagement.models.beans.Utilisateur;
import sn.esmt.gymManagement.models.beans.enums.TypeUtilisateur;
import sn.esmt.gymManagement.models.business.AdminService;
import sn.esmt.gymManagement.models.business.AdminServiceImpl;
import sn.esmt.gymManagement.payLoad.TypeUtilisateurPayload;
import sn.esmt.gymManagement.utils.Constants;
import sn.esmt.gymManagement.utils.RemoveListener;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public class UserItemController implements Initializable {

    public Label initialsLabel;
    public Label lastNameLabel;
    public Label firstnameLabel;
    public Label emailLabel;
    public Label userTypeLabel;
    public MenuItem activeGroupMenu;
    public MenuItem editGroupMenu;
    public MenuItem deleteGroupMenu;
    public AnchorPane unActivateTag;
    private Utilisateur utilisateur;
    private AdminService adminService;

    private RemoveListener<Utilisateur> refreshUI;

    public final static Logger logger = LogManager.getLogger(UserItemController.class);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.adminService = AdminServiceImpl.getInstance();
    }

    public void setUtilisateur(Utilisateur user) {
        this.utilisateur = user;
        this.populateData();
    }

    private void populateData() {
        if (this.utilisateur == null) {
            logger.error("Utilisateur is null");
            return;
        }

        if (this.utilisateur.isActive()) {
            unActivateTag.setVisible(false);
        }

        if (this.utilisateur.isActive() || (!this.utilisateur.isActive() && Constants.utilisateur.getUserType() == TypeUtilisateur.MANAGER)) {
            activeGroupMenu.setVisible(false);
        }


        this.initialsLabel.setText(this.utilisateur.getFirstName().charAt(0) + "" + this.utilisateur.getLastName().charAt(0));
        this.lastNameLabel.setText(this.utilisateur.getLastName());
        this.firstnameLabel.setText(this.utilisateur.getFirstName());
        Optional<TypeUtilisateurPayload> typeUtilisateur = Constants.createTypeUserPayloadsForDirector.stream().filter(typeUtilisateurPayload -> typeUtilisateurPayload.getUtilisateurType() == this.utilisateur.getUserType()).findFirst();
        typeUtilisateur.ifPresent(typeUtilisateurPayload -> this.userTypeLabel.setText(typeUtilisateurPayload.getName()));
        this.emailLabel.setText(this.utilisateur.getEmail());
    }

    public void onEdit(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(GymManagementApplication.class.getResource("admin/root/edit-user.fxml"));
            Parent parent = fxmlLoader.load();
            EditUserController dialogController = fxmlLoader.getController();
            dialogController.setUtilisateur(utilisateur);
            dialogController.loadData();
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setTitle("Modifification d'utilisateur");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResource("/assets/app-logo.png")).toExternalForm()));
            stage.setResizable(false);
            stage.setScene(scene);
            dialogController.setStage(stage);
            stage.showAndWait();
            if (dialogController.getUtilisateur() != null) {
                this.utilisateur = dialogController.getUtilisateur();
                this.populateData();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onDelete(ActionEvent actionEvent) {
        Constants
                .onConfirm("Confirmation", "Voulez-vous vraiment supprimer l'utilisateur " + this.utilisateur.getUserName(), "Oui", "Non")
                .ifPresent(s -> {
                    if (s == ButtonType.OK) {
                        new Thread(() -> {
                            try {
                                this.adminService.deleteUser(this.utilisateur.getId());
                                Platform.runLater(() -> {
                                    refreshUI.remove(this.utilisateur);
                                });
                            } catch (CrudDaoException e) {
                                e.printStackTrace();
                            }
                        }).start();
                    }
                });
    }

    public void onActivate(ActionEvent actionEvent) {
        this.onConfirm();
    }

    public void onConfirm() {
        Constants
                .onConfirm("Confirmation", "Voulez-vous vraiment activer l'utilisateur " + this.utilisateur.getUserName(), "Oui", "Non")
                .ifPresent(s -> {
                    if (s == ButtonType.OK) {
                        new Thread(() -> {
                            try {
                                this.utilisateur.setActive(true);
                                this.adminService.updateUser(this.utilisateur.getId(), this.utilisateur);
                                Platform.runLater(() -> {
                                    this.activeGroupMenu.setVisible(false);
                                    unActivateTag.setVisible(false);
                                });
                            } catch (CrudDaoException e) {
                                e.printStackTrace();
                            }
                        }).start();
                    }
                });
    }

    public void setRefreshUi(UserManagerController userManagerController) {
        this.refreshUI = userManagerController;
    }
}

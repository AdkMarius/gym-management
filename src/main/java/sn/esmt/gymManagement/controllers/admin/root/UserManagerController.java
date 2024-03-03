package sn.esmt.gymManagement.controllers.admin.root;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sn.esmt.gymManagement.GymManagementApplication;
import sn.esmt.gymManagement.exceptions.CrudDaoException;
import sn.esmt.gymManagement.models.beans.Utilisateur;
import sn.esmt.gymManagement.models.beans.enums.TypeUtilisateur;
import sn.esmt.gymManagement.models.business.AdminService;
import sn.esmt.gymManagement.models.business.AdminServiceImpl;
import sn.esmt.gymManagement.utils.Constants;
import sn.esmt.gymManagement.utils.RemoveListener;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class UserManagerController implements Initializable, RemoveListener<Utilisateur> {

    public ProgressIndicator loaderProgress;
    public VBox vBox;

    private AdminService adminService;

    List<Utilisateur> users = new ArrayList<>();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.adminService = AdminServiceImpl.getInstance();
    }

    public void loadData() {
        vBox.getChildren().clear();
        loaderProgress.setVisible(true);

        new Thread(() -> {
            try {
                this.users = this.adminService.getUsers();

                switch (Constants.utilisateur.getUserType()) {
                    case MANAGER:
                        this.users = this.users.stream().filter(user -> user.getUserType() == TypeUtilisateur.MANAGER || user.getUserType() == TypeUtilisateur.RECEPTIONIST).collect(Collectors.toList());
                        break;
                    case DIRECTOR:
                        this.users = this.users.stream().filter(user -> user.getUserType() != TypeUtilisateur.SYSADMIN).collect(Collectors.toList());
                        break;
                }

                this.users.forEach(user -> Platform.runLater(() -> this.addItem(user)));
            } catch (CrudDaoException e) {
                e.printStackTrace();
            } finally {
                loaderProgress.setVisible(false);
            }
        }).start();
    }

    private void addItem(Utilisateur user) {
        try {
            FXMLLoader loader = new FXMLLoader(GymManagementApplication.class.getResource("admin/root/user-item.fxml"));
            Parent userNode = loader.load();
            UserItemController itemController = loader.getController();
            itemController.setUtilisateur(user);
            itemController.setRefreshUi(this);
            vBox.getChildren().add(userNode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onAddUser(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(GymManagementApplication.class.getResource("admin/root/edit-user.fxml"));
            Parent parent = fxmlLoader.load();
            Utilisateur utilisateur = new Utilisateur();
            EditUserController dialogController = fxmlLoader.getController();
            dialogController.setUtilisateur(utilisateur);
            dialogController.loadData();
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setTitle("Ajout d'utilisateur");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResource("/assets/app-logo.png")).toExternalForm()));
            stage.setResizable(false);
            stage.setScene(scene);
            dialogController.setStage(stage);
            stage.showAndWait();
            if (dialogController.getUtilisateur() != null) {
                loadData();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(Utilisateur utilisateur) {
        this.loadData();
    }
}

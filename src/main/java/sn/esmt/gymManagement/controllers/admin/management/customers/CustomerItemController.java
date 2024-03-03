package sn.esmt.gymManagement.controllers.admin.management.customers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sn.esmt.gymManagement.GymManagementApplication;
import sn.esmt.gymManagement.exceptions.CrudDaoException;
import sn.esmt.gymManagement.models.beans.Client;
import sn.esmt.gymManagement.models.beans.Utilisateur;
import sn.esmt.gymManagement.models.business.AdminService;
import sn.esmt.gymManagement.models.business.AdminServiceImpl;
import sn.esmt.gymManagement.utils.CallBack;
import sn.esmt.gymManagement.utils.Constants;
import sn.esmt.gymManagement.utils.RemoveListener;
import sn.esmt.gymManagement.utils.SelectListener;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class CustomerItemController implements Initializable {

    public MenuItem viewDetailsGroupMenu;
    public MenuItem editGroupMenu;
    public MenuItem deleteGroupMenu;
    public VBox boxImage;
    public Label lastNameLabel;
    public Label firstnameLabel;
    public Label emailLabel;
    public Label phoneLabel;
    public Label addressLabel;
    public Label cniLabel;
    private Client client;

    private SelectListener<CustomerItemController, Client> selectListener;
    private AdminService adminService;

    private RemoveListener<Client> refreshUI;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.adminService = AdminServiceImpl.getInstance();
    }

    public void setListener(SelectListener<CustomerItemController, Client> selectListener) {
        this.selectListener = selectListener;
    }

    public void onOpenDetails(MouseEvent mouseEvent) {
        this.selectListener.onSelect(this, client);
    }

    public void setClient(Client client) {
        this.client = client;
        populateData();
    }

    public void setRefreshUI(RemoveListener<Client> refreshUI) {
        this.refreshUI = refreshUI;
    }

    private void populateData() {
        this.lastNameLabel.setText(this.client.getLastName());
        this.firstnameLabel.setText(this.client.getFirstName());
        this.emailLabel.setText(this.client.getEmail());
        this.phoneLabel.setText(this.client.getPhoneNumber());
        this.addressLabel.setText(this.client.getAddress());
        this.cniLabel.setText(this.client.getIdentifierPiece());
    }

    public void onViewDetails(ActionEvent actionEvent) {
        if (this.selectListener != null) {
            this.selectListener.onSelect(this, client);
        }
    }

    public void onEdit(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(GymManagementApplication.class.getResource("admin/management/customers/edit-customer.fxml"));
            Parent parent = fxmlLoader.load();
            EditCustomerController dialogController = fxmlLoader.getController();
            dialogController.setCustomer(client);
            dialogController.loadData();
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setTitle("Modification de client");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResource("/assets/app-logo.png")).toExternalForm()));
            stage.setResizable(false);
            stage.setScene(scene);
            dialogController.setStage(stage);
            stage.showAndWait();
            if (dialogController.getClient() != null) {
                this.client = dialogController.getClient();
                populateData();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onDelete(ActionEvent actionEvent) {
        Constants
                .onConfirm("Confirmation", "Voulez-vous vraiment supprimer le client " + this.client.getFullName(), "Oui", "Non")
                .ifPresent(s -> {
                    if (s == ButtonType.OK) {
                        new Thread(() -> {
                            try {
                                this.adminService.deleteCustomer(this.client.getId());
                                Platform.runLater(() -> {
                                    refreshUI.remove(this.client);
                                });
                            } catch (CrudDaoException e) {
                                e.printStackTrace();
                            }
                        }).start();
                    }
                });
    }
}

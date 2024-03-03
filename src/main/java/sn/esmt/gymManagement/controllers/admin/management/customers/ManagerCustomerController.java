package sn.esmt.gymManagement.controllers.admin.management.customers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sn.esmt.gymManagement.GymManagementApplication;
import sn.esmt.gymManagement.controllers.admin.root.UserItemController;
import sn.esmt.gymManagement.exceptions.CrudDaoException;
import sn.esmt.gymManagement.models.beans.Client;
import sn.esmt.gymManagement.models.beans.enums.TypeUtilisateur;
import sn.esmt.gymManagement.models.business.AdminService;
import sn.esmt.gymManagement.models.business.AdminServiceImpl;
import sn.esmt.gymManagement.utils.CallBack;
import sn.esmt.gymManagement.utils.Constants;
import sn.esmt.gymManagement.utils.RemoveListener;
import sn.esmt.gymManagement.utils.SelectListener;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class ManagerCustomerController implements Initializable, CallBack, SelectListener<CustomerItemController, Client>, RemoveListener<Client> {
    public AnchorPane contentFirst;
    public BorderPane contentBorder;
    public VBox vBox;
    public ProgressIndicator loaderProgress;

    private AdminService adminService;

    private CustomerDetailsController customerDetailsController;

    private List<Client> customers = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.adminService = AdminServiceImpl.getInstance();
    }

    public void loadData() {
        vBox.getChildren().clear();
        loaderProgress.setVisible(true);

        new Thread(() -> {
            try {
                this.customers = this.adminService.getCustomers();
                this.customers.forEach(customer -> Platform.runLater(() -> this.addItem(customer)));
            } catch (CrudDaoException e) {
                e.printStackTrace();
            } finally {
                loaderProgress.setVisible(false);
            }
        }).start();
    }

    private void addItem(Client customer) {
        try {
            FXMLLoader loader = new FXMLLoader(GymManagementApplication.class.getResource("admin/management/customers/customer-item.fxml"));
            Parent userNode = loader.load();
            CustomerItemController itemController = loader.getController();
            itemController.setClient(customer);
            itemController.setListener(this);
            itemController.setRefreshUI(this);
            vBox.getChildren().add(userNode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onAddCustomer(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(GymManagementApplication.class.getResource("admin/management/customers/edit-customer.fxml"));
            Parent parent = fxmlLoader.load();
            EditCustomerController dialogController = fxmlLoader.getController();
            dialogController.setCustomer(new Client());
            dialogController.loadData();
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setTitle("Ajout de client");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResource("/assets/app-logo.png")).toExternalForm()));
            stage.setResizable(false);
            stage.setScene(scene);
            dialogController.setStage(stage);
            stage.showAndWait();
            if (dialogController.getClient() != null) {
                loadData();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCall() {
        loadData();
        Constants.navController.setCallBack(null);
        Constants.navController.toggle(false);
        Constants.navController.setNavText("Gestion des clients");
        contentFirst.setVisible(true);
        contentBorder.setVisible(false);
    }

    @Override
    public void onSelect(CustomerItemController child, Client data) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(GymManagementApplication.class.getResource("admin/management/customers/customer-details.fxml"));
            Parent parent = fxmlLoader.load();
            customerDetailsController = fxmlLoader.getController();
            customerDetailsController.setClient(data);
            contentBorder.setCenter(parent);
            contentFirst.setVisible(false);
            contentBorder.setVisible(true);

            Constants.navController.toggle(true);
            Constants.navController.setNavText("Details -  Client : " + data.getUsername());
            Constants.navController.setCallBack(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(Client client) {
        loadData();
    }
}

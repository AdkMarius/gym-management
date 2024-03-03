package sn.esmt.gymManagement.controllers.admin.management;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import sn.esmt.gymManagement.GymManagementApplication;
import sn.esmt.gymManagement.controllers.admin.management.customers.ManagerCustomerController;
import sn.esmt.gymManagement.utils.Constants;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ManagementController implements Initializable {

    public ToggleButton dashboardBtn;
    public ToggleButton customerBtn;

    public ToggleGroup dashMenu;
    public Label adminName;

    public BorderPane containerPane;
    public AnchorPane navParent;

    private Parent dashboardManager;
    private Parent customerManager;
    private ManagerDashboardController managerDashboardController;
    private ManagerCustomerController managerCustomerController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initNav();
        this.adminName.setText(Constants.utilisateur.getFirstName() + " " + Constants.utilisateur.getLastName() + " - Admin");
        dashboardBtn.setSelected(true);
    }

    private void initNav() {
        try {
            FXMLLoader loader = new FXMLLoader(GymManagementApplication.class.getResource("admin/management/nav.fxml"));
            Parent root = loader.load();
            NavController navController = loader.getController();
            Constants.navController = navController;
            navController.initNav();
            navParent.getChildren().clear();
            AnchorPane.setTopAnchor(root, 0.0);
            AnchorPane.setRightAnchor(root, 0.0);
            AnchorPane.setLeftAnchor(root, 0.0);
            AnchorPane.setBottomAnchor(root, 0.0);
            navParent.getChildren().add(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onDashboard(ActionEvent actionEvent) {
        if (dashboardManager == null) {
            try {
                FXMLLoader loader = new FXMLLoader(GymManagementApplication.class.getResource("admin/management/manager-dashboard.fxml"));
                dashboardManager = loader.load();
                managerDashboardController = loader.getController();
                managerDashboardController.loadData();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else managerDashboardController.loadData();

        containerPane.setCenter(dashboardManager);

        navigation("Dashboard");
    }

    public void onManageCustomer(ActionEvent actionEvent) {
        if (customerManager == null) {
            try {
                FXMLLoader loader = new FXMLLoader(GymManagementApplication.class.getResource("admin/management/customers/manager-customer.fxml"));
                customerManager = loader.load();
                managerCustomerController = loader.getController();
                managerCustomerController.loadData();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else managerCustomerController.loadData();

        containerPane.setCenter(customerManager);

        navigation("Gestion des clients");
    }

    public void onLogout(ActionEvent actionEvent) {
        Constants.onLogout(actionEvent);
    }

    private void navigation(String title) {
        Constants.navController.setNavText(title);
        Constants.navController.toggle(false);
        Constants.navController.setCallBack(null);
    }
}

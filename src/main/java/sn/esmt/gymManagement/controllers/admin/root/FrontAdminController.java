package sn.esmt.gymManagement.controllers.admin.root;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import sn.esmt.gymManagement.GymManagementApplication;
import sn.esmt.gymManagement.utils.Constants;

public class FrontAdminController implements Initializable {

    public ToggleButton dashboardBtn;
    public ToggleButton userBtn;

    public ToggleGroup dashMenu;
    public Label adminName;

    public Label title;

    public BorderPane containerPane;
    public AnchorPane navParent;

    private Parent dashboardManager;
    private Parent userManager;
    private DashboardManagerController dashboardManagerController;
    private UserManagerController userManagerController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.adminName.setText(Constants.utilisateur.getFirstName() + " " + Constants.utilisateur.getLastName() + " - Admin");
        dashboardBtn.setSelected(true);
    }


    public void onDashboard(ActionEvent actionEvent) {
        this.title.setText("Dashboard");
        if (dashboardManager == null) {
            try {
                FXMLLoader loader = new FXMLLoader(GymManagementApplication.class.getResource("admin/root/dashboard-manager.fxml"));
                dashboardManager = loader.load();
                dashboardManagerController = loader.getController();
                dashboardManagerController.loadData();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else dashboardManagerController.loadData();

        containerPane.setCenter(dashboardManager);
    }

    public void onManageUser(ActionEvent actionEvent) {
        this.title.setText("Gestion des utilisateurs");
        if (userManager == null) {
            try {
                FXMLLoader loader = new FXMLLoader(GymManagementApplication.class.getResource("admin/root/user-manager.fxml"));
                userManager = loader.load();
                userManagerController = loader.getController();
                userManagerController.loadData();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else userManagerController.loadData();

        containerPane.setCenter(userManager);
    }

    public void onLogout(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}

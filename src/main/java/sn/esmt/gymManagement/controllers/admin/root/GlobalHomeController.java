package sn.esmt.gymManagement.controllers.admin.root;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import sn.esmt.gymManagement.GymManagementApplication;
import sn.esmt.gymManagement.utils.Constants;

public class GlobalHomeController implements Initializable {

    public Button btnHome;
    public Button btnConfig;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void onLoadConfiguration(ActionEvent actionEvent) throws IOException {
        onOpenInterfaces(actionEvent, "admin/root/front-admin.fxml", "Configuration");
    }

    public void onGoHome(ActionEvent actionEvent) {
        onOpenInterfaces(actionEvent, "admin/management/management.fxml", "Gyms Management");
    }

    private void onOpenInterfaces(ActionEvent actionEvent, String url, String title) {
        try {
            Constants.closeStage(actionEvent);
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResource("/assets/app-logo.png")).toExternalForm()));
            stage.setResizable(false);

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(GymManagementApplication.class.getResource(url));
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}

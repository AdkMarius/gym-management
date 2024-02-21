package sn.esmt.gymManagement.controllers.admin.root;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sn.esmt.gymManagement.GymManagementApplication;
import sn.esmt.gymManagement.utils.Constants;

public class GlobalHomeController implements Initializable {

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	@FXML
	private void onLoadConfiguration(ActionEvent actionEvent) throws IOException {
		Constants.closeStage(actionEvent);
		
		Stage stage = new Stage();
		stage.setTitle("Settings");
		stage.setResizable(false);
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(GymManagementApplication.class.getResource("admin/root/front-admin.fxml"));
		Scene scene = new Scene(loader.load());
		
		stage.setScene(scene);
		stage.show();
	}
	
}

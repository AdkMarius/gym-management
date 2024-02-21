package sn.esmt.gymManagement.controllers.admin.root;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import sn.esmt.gymManagement.utils.Constants;

public class FrontAdminController implements Initializable {

	@FXML
	private Label adminName;
	
	@FXML
	private Label title;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.adminName.setText(Constants.utilisateur.getFirstName() + " " + Constants.utilisateur.getLastName() + " - Admin");
		this.title.setText("Dashboard");
	}
}

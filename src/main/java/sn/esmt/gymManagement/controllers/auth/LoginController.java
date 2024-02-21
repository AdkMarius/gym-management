package sn.esmt.gymManagement.controllers.auth;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sn.esmt.gymManagement.GymManagementApplication;
import sn.esmt.gymManagement.models.beans.Client;
import sn.esmt.gymManagement.models.beans.Privilege;
import sn.esmt.gymManagement.models.beans.Utilisateur;
import sn.esmt.gymManagement.models.beans.enums.TypePrivilege;
import sn.esmt.gymManagement.models.business.AuthService;
import sn.esmt.gymManagement.models.business.AuthServiceImpl;
import sn.esmt.gymManagement.payLoad.ApiResponse;
import sn.esmt.gymManagement.utils.Constants;

public class LoginController implements Initializable {
	
	public AnchorPane anchorPane;
	
	public TextField emailField;
	
	public Label emailError;
	
	public PasswordField passwordField;
	
	public Label passwordError;
	
	public Label errorLabel;
		
	public Button submitButton;
	
	public ProgressIndicator loaderProgress;
	
	private AuthService authService;
	
    public final static Logger logger = LogManager.getLogger(LoginController.class);
    
    public AuthService getAuthService() {
		return authService;
	}

	public void setAuthService(AuthService authService) {
		this.authService = authService;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.setAuthService(AuthServiceImpl.getAuthServiceInstance());
	}
	
	@FXML
	private void onLogin(ActionEvent actionEvent) {
		boolean hasError = false;
		emailError.setVisible(false);
		passwordError.setVisible(false);
		
		String email = emailField.getText().trim();
		String password = passwordField.getText().trim();
		
		if (email == null) {
			emailError.setText("Ce champ est obligatoire");
			emailError.setVisible(true);
			hasError = true;
		}
				
		if (password == null) {
			passwordError.setText("Ce champ est obligatoire");
			passwordError.setVisible(true);
			hasError = true;
		}
		 
		if (hasError)
			return ;
		
		loaderProgress.setVisible(true);
		submitButton.setVisible(false);
		submitButton.managedProperty().bind(submitButton.visibleProperty());
		
		new Thread(() -> {
			try {
				ApiResponse empApiResponse = authService.loginUser(Utilisateur.class, email, password);
				
				if (empApiResponse.isSuccess()) {
					logger.info(empApiResponse.getMessage());
					this.loadPrivileges();
					
					Platform.runLater(() -> {
                        Constants.closeStage(actionEvent);
                        if (Constants.USER_PRIVILEGE.size() == TypePrivilege.values().length) {
                        	// display super admin interface 
                        	try {
								this.openAdminHone();
							} catch (IOException e) {
								logger.error("Error while open user interface");
							}
                        } else {
                        	// display employee interface
                        }
                    }); 
				} else {
					
					ApiResponse clientApiResponse = authService.loginUser(Client.class, emailField.getText().trim(), passwordField.getText().trim());

					if (clientApiResponse.isSuccess()) {
						logger.info(clientApiResponse.getMessage());
						
						Platform.runLater(() -> {
	                        Constants.closeStage(actionEvent);
	                        // display the user interface
						});
					} else {
						this.showError("Login ou mot de passe incorrect. Veuillez réessayer!");
					}
				}
			} catch(Exception e) {
				logger.error("Error while connecting to database");
			}
		}).start();
	}

	private void loadPrivileges() {
		Constants.utilisateur.getListRole().forEach(role -> {
            Constants.USER_PRIVILEGE.addAll(role.getListPrivilege().stream().map(Privilege::getPrivilege).collect(Collectors.toList()));
        });
	}
	
	private void showError(String message) {
        Platform.runLater(() -> {
            errorLabel.setText(message);
            errorLabel.setVisible(true);

            loaderProgress.setVisible(false);
            submitButton.setVisible(true);
        });
	 }
	
	private void openAdminHone() throws IOException {
			
			Stage stage = new Stage();
			stage.setTitle("Gym Management - Admin Home");
			//stage.getIcons().add(new Image(""));
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(GymManagementApplication.class.getResource("admin/root/global-home.fxml"));
			Scene scene = new Scene(loader.load(), 800, 700);
			
			stage.setScene(scene);
			stage.setResizable(true);
			stage.show();
	}

}

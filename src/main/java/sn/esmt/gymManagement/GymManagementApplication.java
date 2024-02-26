package sn.esmt.gymManagement;

import java.io.IOException;
import java.util.Objects;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sn.esmt.gymManagement.models.business.config.DataLoad;

public class GymManagementApplication extends Application 
{
	private Stage primaryStage;
	private AnchorPane rootLayout;
	
    public static void main( String[] args )
    {
    	launch(args);
    }

	@Override
	public void start(Stage primaryStage) throws Exception {
		DataLoad.loadAllData();
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Login");
		this.primaryStage.setResizable(true);
		//this.primaryStage.getIcons().add(new Image(""));
		
		this.showMainView();
	}

	private void showMainView() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(GymManagementApplication.class.getResource("auth/login.fxml"));
		primaryStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResource("/assets/app-logo.png")).toExternalForm()));

		this.rootLayout = (AnchorPane) loader.load();
		
		Scene scene = new Scene(rootLayout);
		this.primaryStage.setScene(scene);
		this.primaryStage.show();
 	}
}

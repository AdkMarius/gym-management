package sn.esmt.gymManagement;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Login");
		this.primaryStage.setResizable(false);
		//this.primaryStage.getIcons().add(new Image(""));
		
		this.showMainView();
	}

	private void showMainView() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(GymManagementApplication.class.getResource("auth/login.fxml"));
		this.rootLayout = (AnchorPane) loader.load();
		
		Scene scene = new Scene(rootLayout);
		this.primaryStage.setScene(scene);
		this.primaryStage.show();
	}
}

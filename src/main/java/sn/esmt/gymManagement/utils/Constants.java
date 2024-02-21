package sn.esmt.gymManagement.utils;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;
import sn.esmt.gymManagement.models.beans.Client;
import sn.esmt.gymManagement.models.beans.Role;
import sn.esmt.gymManagement.models.beans.Utilisateur;
import sn.esmt.gymManagement.models.beans.enums.TypePrivilege;

public class Constants {
	public static List<Role> USER_ROLE = new ArrayList<>();
	
	public static List<TypePrivilege> USER_PRIVILEGE = new ArrayList<>();

    public static Utilisateur utilisateur;
    
    public static Client client;
    
    public static void closeStage(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}

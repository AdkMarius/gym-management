package sn.esmt.gymManagement.utils;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;
import sn.esmt.gymManagement.models.beans.Client;
import sn.esmt.gymManagement.models.beans.Role;
import sn.esmt.gymManagement.models.beans.Utilisateur;
import sn.esmt.gymManagement.models.beans.enums.TypePrivilege;
import sn.esmt.gymManagement.models.beans.enums.TypeUtilisateur;
import sn.esmt.gymManagement.payLoad.TypeUtilisateurPayload;

public class Constants {
	public static List<Role> USER_ROLE = new ArrayList<>();
	
	public static List<TypePrivilege> USER_PRIVILEGE = new ArrayList<>();

    public static Utilisateur utilisateur;
    
    public static Client client;

    public static final List<TypeUtilisateurPayload> typeUserPayloads = new ArrayList<>(List.of(
            new TypeUtilisateurPayload(TypeUtilisateur.MANAGER, "Manager"),
            new TypeUtilisateurPayload(TypeUtilisateur.RECEPTIONIST, "Receptionniste"),
            new TypeUtilisateurPayload(TypeUtilisateur.SYSADMIN, "Admin systèmes"),
            new TypeUtilisateurPayload(TypeUtilisateur.DIRECTOR, "Directeur")));
    
    public static void closeStage(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public static String formatText(String text) {
        return new String(text.getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);
    }
}

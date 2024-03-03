package sn.esmt.gymManagement.utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import sn.esmt.gymManagement.GymManagementApplication;
import sn.esmt.gymManagement.controllers.admin.management.NavController;
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

    public static NavController navController;

    public static List<ActionEvent> actionEvents = new ArrayList<>();

    public static final List<TypeUtilisateurPayload> createTypeUserPayloadsForManager = new ArrayList<>(List.of(
            new TypeUtilisateurPayload(TypeUtilisateur.MANAGER, "Manager"),
            new TypeUtilisateurPayload(TypeUtilisateur.RECEPTIONIST, "Receptionniste")));

    public static final List<TypeUtilisateurPayload> createTypeUserPayloadsForDirector = new ArrayList<>(List.of(
            new TypeUtilisateurPayload(TypeUtilisateur.MANAGER, "Manager"),
            new TypeUtilisateurPayload(TypeUtilisateur.RECEPTIONIST, "Receptionniste"),
            new TypeUtilisateurPayload(TypeUtilisateur.DIRECTOR, "Directeur")));

    public static final List<TypeUtilisateurPayload> createTypeUserPayloadsAll = new ArrayList<>(List.of(
            new TypeUtilisateurPayload(TypeUtilisateur.MANAGER, "Manager"),
            new TypeUtilisateurPayload(TypeUtilisateur.RECEPTIONIST, "Receptionniste"),
            new TypeUtilisateurPayload(TypeUtilisateur.SYSADMIN, "Sys admin"),
            new TypeUtilisateurPayload(TypeUtilisateur.DIRECTOR, "Directeur")));


    public static void closeStage(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public static String formatText(String text) {
        return new String(text.getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);
    }

    public static Optional<ButtonType> onConfirm(String header, String content, String okText, String cancelText) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(header);
        alert.setContentText(content);
        ((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText(okText);
        ((Button) alert.getDialogPane().lookupButton(ButtonType.CANCEL)).setText(cancelText);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(Objects.requireNonNull(Constants.class.getResource("/css/style.css")).toExternalForm());
        return alert.showAndWait();
    }

    public static Optional<ButtonType> onShowInformation(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(header);
        alert.setContentText(content);
//        ((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("Ok");
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(Objects.requireNonNull(Constants.class.getResource("/css/style.css")).toExternalForm());
        return alert.showAndWait();
    }

    public static void logout() {
        Constants.USER_ROLE = new ArrayList<>();
        Constants.USER_PRIVILEGE = new ArrayList<>();
        Constants.utilisateur = null;
        Constants.client = null;
    }

    public static void onLogout(ActionEvent actionEvent) {

        Constants.actionEvents.forEach(actionEvent1 -> {
            Constants.onClose(actionEvent1);
        });

        Constants.onClose(actionEvent);

        try {
            Constants.logout();
            Stage stage = new Stage();
            stage.setTitle("Settings");
            stage.getIcons().add(new Image(Objects.requireNonNull(Constants.class.getResource("/assets/app-logo.png")).toExternalForm()));
            stage.setResizable(false);

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(GymManagementApplication.class.getResource("auth/login.fxml"));
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void onClose(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}

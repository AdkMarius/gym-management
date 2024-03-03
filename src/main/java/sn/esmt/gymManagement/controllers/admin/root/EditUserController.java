package sn.esmt.gymManagement.controllers.admin.root;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import sn.esmt.gymManagement.exceptions.CrudDaoException;
import sn.esmt.gymManagement.models.beans.Role;
import sn.esmt.gymManagement.models.beans.Utilisateur;
import sn.esmt.gymManagement.models.beans.enums.TypeUtilisateur;
import sn.esmt.gymManagement.models.business.AdminService;
import sn.esmt.gymManagement.models.business.AdminServiceImpl;
import sn.esmt.gymManagement.payLoad.TypeUtilisateurPayload;
import sn.esmt.gymManagement.utils.Constants;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class EditUserController implements Initializable {

    public Label formTitleLabel;
    public TextField lastNameField;
    public Label lastNameLabelError;
    public TextField firstNameField;
    public Label firstNameLabelError;
    public TextField emailField;
    public Label emailLabelError;
    public PasswordField passwordField;
    public Label passwordError;
    public ComboBox<TypeUtilisateurPayload> userTypeCombo;
    public Label userTypeLabelError;
    public AnchorPane anchorRole;
    public ProgressIndicator loaderProgress;
    public HBox actionHBoxBtn;
    public Button btnAdd;
    public Button btnCancel;
    public Label roleErrorLabel;
    public AnchorPane roleContainer;
    private Utilisateur utilisateur;
    private AdminService adminService;
    private List<Role> roles = new ArrayList<>();
    private final List<Role> rolesList = new ArrayList<>();
    int cols = 2, colCnt = 0, rowCnt = 0;
    private Stage stage;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.adminService = AdminServiceImpl.getInstance();

        this.userTypeCombo.getItems().clear();

        switch (Constants.utilisateur.getUserType()) {
            case DIRECTOR:
            case SYSADMIN:
                this.userTypeCombo.getItems().addAll(Constants.createTypeUserPayloadsForDirector);
                break;
            case MANAGER:
                this.userTypeCombo.getItems().addAll(Constants.createTypeUserPayloadsForManager);
                break;
        }
    }

    public void loadData() {
        try {
            this.roles = this.adminService.getRoles();
            this.loadRoleItem();
        } catch (CrudDaoException e) {
            e.printStackTrace();
        }
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
        if (this.utilisateur.getId() == 0) {
            roleContainer.setVisible(false);
            roleContainer.managedProperty().bind(roleContainer.visibleProperty());
        } else {
            btnAdd.setText("Modifier");
            roleContainer.setVisible(true);
            firstNameField.setText(utilisateur.getFirstName());
            lastNameField.setText(utilisateur.getLastName());
            emailField.setText(utilisateur.getEmail());
            passwordField.setText(utilisateur.getPassword());
            TypeUtilisateurPayload createTypeUserPayloadsForManager = Constants.createTypeUserPayloadsForManager.stream().filter(s -> s.getUtilisateurType() == utilisateur.getUserType()).findFirst().orElse(null);
            userTypeCombo.getSelectionModel().select(createTypeUserPayloadsForManager);
        }
    }

    public void setStage(Stage stage) {
        stage.setOnCloseRequest(event -> utilisateur = null);
    }

    private void loadRoleItem() {
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 5, 5, 5));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        cols = 1;
        colCnt = 0;
        rowCnt = 0;
        for (Role role : this.roles) {
            CheckBox checkBox = new CheckBox(role.getRole().name());
            checkBox.setPrefWidth(250);
            if ((utilisateur.getId() != 0) && utilisateur.getListRole().stream().anyMatch(privilege1 -> privilege1.getId() == role.getId())) {
                checkBox.setSelected(true);
                rolesList.add(role);
            }
            gridPane.add(checkBox, colCnt, rowCnt);
            colCnt++;
            checkBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue) {
                    rolesList.add(role);
                } else {
                    rolesList.remove(role);
                }
            });
            if (colCnt > cols) {
                rowCnt++;
                colCnt = 0;
            }
        }
        anchorRole.getChildren().add(gridPane);
    }

    public void onAddUser(ActionEvent actionEvent) {
        boolean hasError = false;
        emailLabelError.setText("");
        firstNameLabelError.setText("");
        lastNameLabelError.setText("");
        userTypeLabelError.setText("");
        passwordError.setText("");


        if (firstNameField.getText().trim().isEmpty()) {
            hasError = true;
            firstNameLabelError.setText(Constants.formatText("Veuillez remplir le champ"));
        }

        if (lastNameField.getText().trim().isEmpty()) {
            hasError = true;
            lastNameLabelError.setText(Constants.formatText("Veuillez remplir le champ"));
        }

        if (userTypeCombo.getSelectionModel().getSelectedIndex() == -1) {
            hasError = true;
            userTypeLabelError.setText(Constants.formatText("Veuillez remplir le champ"));
        }

        if (passwordField.getText().trim().isEmpty()) {
            hasError = true;
            passwordError.setText(Constants.formatText("Veuillez remplir le champ"));
        }


//        if (rolesList.size() == 0) {
//            hasError = true;
//            roleErrorLabel.setText(Constants.formatText("Veuillez choisir au moins un role"));
//        }

        if (hasError) return;

        loaderProgress.setVisible(true);
        actionHBoxBtn.setVisible(false);
        actionHBoxBtn.managedProperty().bind(actionHBoxBtn.visibleProperty());


        String usertype = String.valueOf(userTypeCombo.getValue());
        Optional<TypeUtilisateurPayload> typeOptional = Optional.empty();
        if (!usertype.trim().isEmpty()) {
            typeOptional = Constants.createTypeUserPayloadsForDirector.stream().filter(s -> s.getName().equals(usertype)).findFirst();
        }
        typeOptional.ifPresent(s -> {
            this.utilisateur.setUserType(s.getUtilisateurType());
        });


        this.utilisateur.setLastName(lastNameField.getText().trim());
        this.utilisateur.setFirstName(firstNameField.getText().trim());
        this.utilisateur.setPassword(passwordField.getText().trim());
        this.utilisateur.setEmail(emailField.getText().trim());

        // todo : associate Role to the user manually

        // this.utilisateur.setListRole(this.rolesList);

        if (Constants.utilisateur.getUserType() == TypeUtilisateur.DIRECTOR || Constants.utilisateur.getUserType() == TypeUtilisateur.SYSADMIN) {
            this.utilisateur.setActive(true);
        }

        new Thread(() -> {
            // TODO: 2/25/2024 Make DB request here 
            try {
                if (this.utilisateur.getId() == 0) {
                    this.utilisateur = this.adminService.addUser(this.utilisateur);
                } else {
                    this.utilisateur = this.adminService.updateUser(this.utilisateur.getId(), this.utilisateur);
                }

                Platform.runLater(() -> Constants.closeStage(actionEvent));
            } catch (CrudDaoException e) {
                e.printStackTrace();
            } finally {
                loaderProgress.setVisible(false);
            }
        }).start();


    }

    public void onCancel(ActionEvent actionEvent) {
        Constants.closeStage(actionEvent);
    }
}

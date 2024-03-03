package sn.esmt.gymManagement.controllers.admin.management.customers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import sn.esmt.gymManagement.exceptions.CrudDaoException;
import sn.esmt.gymManagement.models.beans.Client;
import sn.esmt.gymManagement.models.beans.enums.GenderType;
import sn.esmt.gymManagement.models.business.AdminService;
import sn.esmt.gymManagement.models.business.AdminServiceImpl;
import sn.esmt.gymManagement.payLoad.GenderPayload;
import sn.esmt.gymManagement.utils.Constants;

import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

public class EditCustomerController implements Initializable {

    public TextField responseEmailField;
    public Label responseEmailLabelError;
    public TextField firstNameField;
    public Label firstNameLabelError;
    public TextField lastNameField;
    public Label lastNameLabelError;
    public TextField responsePhoneField;
    public Label responsePhoneLabelError;
    public ComboBox<GenderPayload> sexCombo;
    public Label sexLabelError;
    public TextField cardNumberField;
    public Label cardNumberLabelError;
    public TextField addressField;
    public Label addressLabelError;
    public Label birthdateLabelError;
    public DatePicker birthdateField;
    public ProgressIndicator loaderProgress;
    public HBox actionHBoxBtn;
    public Button btnAdd;
    public Button btnEdit;
    public Label formTitleLabel;
    private Client client;
    private AdminService adminService;

    private final List<GenderPayload> genderPayloadList = new ArrayList<>(List.of(new GenderPayload(GenderType.FEMALE, "Feminin"), new GenderPayload(GenderType.MALE, "Masculin")));

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.adminService = AdminServiceImpl.getInstance();
        this.sexCombo.getItems().addAll(genderPayloadList);
    }

    public void setCustomer(Client client) {
        this.client = client;

        if (this.client.getId() != 0) {
            autoFillFields();
        }
    }

    private void autoFillFields() {
        btnAdd.setText("Modifier");
        formTitleLabel.setText("Modification du client");
        firstNameField.setText(this.client.getFirstName());
        lastNameField.setText(this.client.getLastName());
        responseEmailField.setText(this.client.getEmail());
        addressField.setText(this.client.getAddress());
        responsePhoneField.setText(this.client.getPhoneNumber());
        cardNumberField.setText(this.client.getIdentifierPiece());
        birthdateField.setValue(Instant.ofEpochMilli(this.client.getDateOfBirth().getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate());
        sexCombo.getSelectionModel().select(this.genderPayloadList.stream().filter(genderPayload -> genderPayload.getGenderType() == this.client.getGender()).findFirst().get());
    }

    public void loadData() {
    }

    public void setStage(Stage stage) {
        stage.setOnCloseRequest(event -> client = null);
    }

    public Client getClient() {
        return client;
    }

    public void onAddCustomer(ActionEvent actionEvent) {
        boolean hasError = false;
        responseEmailLabelError.setText("");
        firstNameLabelError.setText("");
        lastNameLabelError.setText("");
        sexLabelError.setText("");
        responsePhoneLabelError.setText("");
        cardNumberLabelError.setText("");
        addressLabelError.setText("");
        birthdateLabelError.setText("");

        if (responseEmailField.getText().trim().isEmpty()) {
            hasError = true;
            responseEmailLabelError.setText("Veuillez remplir le champ");
        }

        if (firstNameField.getText().trim().isEmpty()) {
            hasError = true;
            firstNameLabelError.setText("Veuillez remplir le champ");
        }

        if (lastNameField.getText().trim().isEmpty()) {
            hasError = true;
            lastNameLabelError.setText("Veuillez remplir le champ");
        }

        if (sexCombo.getSelectionModel().getSelectedIndex() == -1) {
            hasError = true;
            sexLabelError.setText("Veuillez remplir le champ");
        }

        if (responsePhoneField.getText().trim().isEmpty()) {
            hasError = true;
            responsePhoneLabelError.setText("Veuillez remplir le champ");
        }

        if (cardNumberField.getText().trim().isEmpty()) {
            hasError = true;
            cardNumberLabelError.setText("Veuillez remplir le champ");
        }

        if (addressField.getText().trim().isEmpty()) {
            hasError = true;
            addressLabelError.setText("Veuillez remplir le champ");
        }

        if (hasError) return;


        loaderProgress.setVisible(true);
        actionHBoxBtn.setVisible(false);
        actionHBoxBtn.managedProperty().bind(actionHBoxBtn.visibleProperty());

        String socialStat = String.valueOf(sexCombo.getValue());
        Optional<GenderPayload> socialOptionalS = Optional.empty();
        if (!socialStat.trim().isEmpty()) {
            socialOptionalS = genderPayloadList.stream().filter(s -> s.getName().equals(socialStat)).findFirst();
        }
        socialOptionalS.ifPresent(s -> {
            this.client.setGender(s.getGenderType());
        });

        this.client.setLastName(lastNameField.getText().trim());
        this.client.setFirstName(firstNameField.getText().trim());
        this.client.setEmail(responseEmailField.getText().trim());
        this.client.setPhoneNumber(responsePhoneField.getText().trim());
        this.client.setAddress(addressField.getText().trim());
        this.client.setIdentifierPiece(cardNumberField.getText().trim());
        if (this.client.getId() == 0) {
            this.client.setPassword(this.client.getLastName() + "123");
            this.client.setFirstConnection(true);
        }

        if (birthdateField.getValue() != null) {
            this.client.setDateOfBirth(Date.from(birthdateField.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
        }

        new Thread(() -> {
            try {
                if (this.client.getId() == 0) {
                    this.client = this.adminService.addCustomer(this.client);
                    Platform.runLater(() -> {
                        Constants.onShowInformation("Information", "Le mot de passe par defaut du client " + this.client.getFullName() + " est " + this.client.getPassword())
                                .ifPresent(s -> {
                                    Constants.closeStage(actionEvent);
                                });
                    });
                } else {
                    this.client = this.adminService.updateCustomer(this.client.getId(), this.client);
                    Platform.runLater(() -> Constants.closeStage(actionEvent));
                }


            } catch (CrudDaoException e) {
                e.printStackTrace();
            } finally {
                loaderProgress.setVisible(true);
            }
        }).start();

    }

    public void onCancel(ActionEvent actionEvent) {
        Constants.closeStage(actionEvent);
    }
}

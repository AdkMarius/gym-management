package sn.esmt.gymManagement.controllers.admin.management;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import sn.esmt.gymManagement.utils.CallBack;
import sn.esmt.gymManagement.utils.Constants;

import java.net.URL;
import java.util.ResourceBundle;


public class NavController implements Initializable {
    public Label title;
    public Label userTypeLabel;
    public Button backBtn;

    private CallBack callBack;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void initNav() {
        title.setText("Dashboard");
        backBtn.setVisible(false);
        backBtn.managedProperty().bind(backBtn.visibleProperty());
        userTypeLabel.setText(Constants.createTypeUserPayloadsForDirector.stream().filter(payload -> payload.getUtilisateurType() == Constants.utilisateur.getUserType()).findFirst().get().getName());
    }

    public void onLogout(ActionEvent actionEvent) {
        Constants.onLogout(actionEvent);
    }

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    public void onBack(ActionEvent actionEvent) {
        if (this.callBack != null) {
            this.callBack.onCall();
        }
    }

    public void toggle(boolean value) {
        if (value) {
            backBtn.setVisible(true);
        } else {
            backBtn.setVisible(false);
            backBtn.managedProperty().bind(backBtn.visibleProperty());
        }
    }

    public void setNavText(String text) {
        title.setText(text);
    }
}

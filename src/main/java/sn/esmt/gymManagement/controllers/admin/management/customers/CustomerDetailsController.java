package sn.esmt.gymManagement.controllers.admin.management.customers;

import javafx.fxml.Initializable;
import sn.esmt.gymManagement.models.beans.Client;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomerDetailsController implements Initializable {

    private Client client;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setClient(Client client) {
        this.client = client;
    }
}

module sn.esmt.gymManagement {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires org.apache.logging.log4j.core;
    exports sn.esmt.gymManagement;
    opens sn.esmt.gymManagement.models.beans;
    opens sn.esmt.gymManagement.controllers.auth;
    opens sn.esmt.gymManagement.controllers.admin.root;
    opens sn.esmt.gymManagement.controllers.admin.management;
    opens sn.esmt.gymManagement.controllers.admin.management.customers;
    opens sn.esmt.gymManagement.controllers.client;
}

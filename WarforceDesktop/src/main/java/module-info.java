module com.devlab.warforce {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires java.net.http;

    opens com.devlab.warforce to javafx.fxml;
    opens com.devlab.warforce.model to com.google.gson;

    exports com.devlab.warforce;
    exports com.devlab.warforce.model;
    exports com.devlab.warforce.enumeration;
}
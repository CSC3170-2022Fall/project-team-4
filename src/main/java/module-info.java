module com.example.gui_dbms {
    requires javafx.controls;
    requires javafx.fxml;
    requires junit;


    opens com.example.gui_dbms to javafx.fxml;
    exports com.example.gui_dbms;
}

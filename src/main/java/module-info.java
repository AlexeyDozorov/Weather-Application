module com.example.firstfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;
    requires org.json;


    opens com.example.firstfx to javafx.fxml;
    exports com.example.firstfx;
    exports com.example.firstfx.Constants;
    opens com.example.firstfx.Constants to javafx.fxml;
    exports com.example.firstfx.JDBC;
    opens com.example.firstfx.JDBC to javafx.fxml;

}
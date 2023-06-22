module edp.herbaty.herbaty {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.httpcomponents.client5.httpclient5.fluent;
    requires org.json;
    requires org.xerial.sqlitejdbc;
    requires java.sql;


    opens edp.herbaty.herbaty to javafx.fxml;
    exports edp.herbaty.herbaty;
}
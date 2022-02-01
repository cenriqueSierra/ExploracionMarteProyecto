module com.espol.proyectopoo2 {
    requires javafx.controls;
    requires javafx.swing;
    requires javafx.fxml;
    requires java.base;
    opens com.espol.proyectopoo2.modelo to javafx.base;
    opens com.espol.proyectopoo2 to javafx.fxml;
    exports com.espol.proyectopoo2;
}

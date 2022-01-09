module com.espol.proyectopoo2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.espol.proyectopoo2 to javafx.fxml;
    exports com.espol.proyectopoo2;
}

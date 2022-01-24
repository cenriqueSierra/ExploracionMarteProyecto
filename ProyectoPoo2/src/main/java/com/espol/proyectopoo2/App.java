package com.espol.proyectopoo2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App Clase que inicia el entorno grafico
 */
public class App extends Application {

    private static Scene scene;

    /**
     * 
     * @param stage
     * @throws IOException 
     */
    @Override
    public void start(Stage stage) throws IOException {
        stage.setMinHeight(620);
        stage.setMinWidth(850);
        scene = new Scene(loadFXML("VistaInicial"), 1200, 700);
        stage.setScene(scene);
        stage.show();
    }

    
    /**
     * Cargar la vista y toma su escena
     * @param fxml
     * @throws IOException 
     */
    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    /**
     * Metodo que carga una vista
     * @param fxml
     * @return Objeto de tipo Parent
     * @throws IOException 
     */
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    
    
    /**
     * 
     * @param args 
     */
    public static void main(String[] args) {
        launch();
    }
    
    /**
     * Metodo que permite cambiar de vista capturando sus posibles excepciones
     * @param archivofxml nombre del archivo que contiene una vista
     */
    public static void cambioVista(String archivofxml){
        try{
            App.setRoot(archivofxml);
        }catch(IOException ioex){
            ioex.printStackTrace();
        }
    }

}
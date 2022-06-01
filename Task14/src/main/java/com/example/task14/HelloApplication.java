package com.example.task14;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

/**
 * @version 1.0.0
 * @author Веретенников Никита
 */
public class HelloApplication extends Application {
    private static Scene scene;

    /**Главный метод
     * @param stage сцена
     * @throws IOException исключение*/
    @Override
    public void start(Stage stage) throws IOException {
        String icon = "src\\main\\java\\com\\example\\task14\\Images\\icon.jpg";
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("playerCreate.fxml"));
        scene = new Scene(fxmlLoader.load(), 533, 340);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.getIcons().add(new Image(new File(icon).toURI().toString()));
        stage.show();
        stage.setTitle("Игра Шредингера");
    }

    /**назначение окну друго сцены
     * @param fxml названиесцены
     * @throws Exception исключение*/
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    /**создание интерфейса из файла
     * @param fxml название сцены
     * @throws Exception исключение*/
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
}
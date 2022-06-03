package com.example.schrodinger_game;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * @version 1.0.0
 * @author Веретенников Никита
 */
public class SettingsWindow implements Initializable {
    /**контейнер вариантов дополнительной игры*/
    public ComboBox extraComboBox;
    /**варианты дополнительной игры*/
    public ObservableList<Integer> extraVariant = FXCollections.observableArrayList(2,3,4,5,6,7,8,9,10);

    /** Начало работы
     * @param url url
     * @param resourceBundle пакет ресурсов
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        extraComboBox.setItems(extraVariant);
        try {
            readSettings();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**кнопка возврата к созданию игрока
     * @param actionEvent событие действия
     * @throws IOException исключение*/
    public void backButtonClick(ActionEvent actionEvent) throws IOException {
        HelloApplication.setRoot("playerCreate");
    }

    /**кнопка сохранения изменений
     * @param actionEvent событие действия
     * @throws IOException исключение*/
    public void saveButtonClick(ActionEvent actionEvent) throws IOException {
        writeSettings();
        HelloApplication.setRoot("playerCreate");
    }

    /**чтение настроек из файла
     * @throws IOException исключение*/
    public void readSettings() throws IOException {
        FileReader read = new FileReader("src\\main\\java\\com\\example\\schrodinger_game\\database\\settings.txt");
        Scanner scan = new Scanner(read);
        extraComboBox.setValue(scan.next());
        read.close();
    }

    /**запись настроек в файл
     * @throws IOException исключение*/
    public void writeSettings() throws IOException {
        FileWriter writer = new FileWriter("src\\main\\java\\com\\example\\schrodinger_game\\database\\settings.txt", false);
        writer.append(extraComboBox.getValue().toString());
        writer.close();
    }
}

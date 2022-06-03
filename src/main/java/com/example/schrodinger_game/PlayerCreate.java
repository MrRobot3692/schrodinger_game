package com.example.schrodinger_game;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * @version 1.0.0
 * @author Веретенников Никита
 */
public class PlayerCreate implements Initializable {
    /**имя игрока*/
    public TextField playerNameText;
    /**список игроков*/
    public Text playerListText;

    /** Начало работы
     * @param url url
     * @param resourceBundle пакет ресурсов
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            readList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**кнопка создания игрока
     * @param actionEvent событие действия
     * @throws IOException исключение*/
    public void readyButtonClick(ActionEvent actionEvent) throws IOException {
        if(playerNameText.getText() != "" && playerNameText.getText() != null){
            HelloController.name = playerNameText.getText();
            HelloApplication.setRoot("hello-view");
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Ошибка");
            alert.setContentText("Введите имя");
            alert.show();
        }
    }

    /**чтение игроков в список
     * @throws IOException исключение*/
    public void readList() throws IOException {
        FileReader read = new FileReader("src\\main\\java\\com\\example\\schrodinger_game\\database\\players.txt");
        Scanner scan = new Scanner(read);
        while (scan.hasNextLine()){
            playerListText.setText(playerListText.getText() + scan.next() + ": " + scan.next() + " монет(ы)" + "\n");
        }
        read.close();
    }

    /**кнопка настроек
     * @param actionEvent событие действия
     * @throws IOException исключение*/
    public void settingButtonClick(ActionEvent actionEvent) throws IOException {
        HelloApplication.setRoot("settingsWindow");
    }
}

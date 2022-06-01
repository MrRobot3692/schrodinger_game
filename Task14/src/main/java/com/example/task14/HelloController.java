package com.example.task14;

import javafx.animation.ScaleTransition;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventTarget;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * @version 1.0.0
 * @author Веретенников Никита
 */
public class HelloController implements Initializable {

    /**лейблы*/
    public Label MoneyCount, TryCount, notifyLabel;
    /**картинки*/
    public ImageView imgOne, imgTwo, imgThree;
    /**имя игрока*/
    public Label playerNameLabel;
    Player player1;
    ActionChain action;
    /**результат игры*/
    public static boolean result = false;
    /**дополнительная игры*/
    public int extraGame;
    /**имя игрока*/
    public static String name;
    fileWork file = new fileWork();

    HashMap<String, Integer> playerList = new HashMap<>();

    Media sound;
    MediaPlayer mediaPlayer;
    String src = "src\\main\\java\\com\\example\\task14\\";
    String waitImage = src + "Images\\wait.jpg";
    String loseImage = src + "Images\\lose.jpg";
    String winImage = src +  "Images\\win.jpg";
    String winSound = src + "sounds\\winSound.mp3";
    String selectSound = src + "sounds\\selectSound.mp3";
    String loseSound = src + "sounds\\loseSound.mp3";
    String paySound = src + "sounds\\coinSound.mp3";
    String errorSound = src + "sounds\\errorSound.mp3";


    /** Начало работы
     * @param url url
     * @param resourceBundle пакет ресурсов
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        resetImg();
        initializePlayer();
        try { readSettings(); }
        catch (IOException e) { e.printStackTrace(); }
        MoneyCount.setText(player1.getNumber().toString());
        TryCount.setText(player1.getCount().toString());
        playerNameLabel.setText(player1.getName());
    }

    /** чтение настроек из файла
     * @throws IOException исключение*/
    public void readSettings() throws IOException {
        FileReader read = new FileReader("src\\main\\java\\com\\example\\task14\\database\\settings.txt");
        Scanner scan = new Scanner(read);
        extraGame = scan.nextInt();
        read.close();
    }

    /**создание игрока*/
    public void initializePlayer(){
        readPlayer();
        if(playerList.containsKey(name)){
            player1 = new Player(name, playerList.get(name));
        } else {
            player1 = new Player(name, 0);
            playerList.put(player1.getName(), player1.getNumber());
            writePlayer();
        }
    }

    /**кнопка начала игры
     * @param actionEvent событие действия*/
    public void startButtonClick(ActionEvent actionEvent) {
        if(!moneyCheck()) { return; }
        addTry();
    }

    /**кнопка начала игры
     * @param actionEvent событие действия*/
    public void payButtonClick(ActionEvent actionEvent) {
        playSound(paySound);
        playerList.put(player1.getName(), player1.getNumber());
        player1.addNumber(1);
        MoneyCount.setText(player1.getNumber().toString());
        TryCount.setText(player1.getCount().toString());
    }

    /**кнопка перехода на сцену выбора игрока
     * @param actionEvent событие действия
     * @throws IOException исключение*/
    public void changePlayerClick(ActionEvent actionEvent) throws IOException {
        playerList.put(player1.getName(), player1.getNumber());
        writePlayer();
        HelloApplication.setRoot("playerCreate");
    }

    /**добавить попытку игроку*/
    public void addTry(){
        enableImg();
        playerList.put(player1.getName(), player1.getNumber());
        playSound(paySound);
        player1.addCount(1);
        MoneyCount.setText(player1.getNumber().toString());
        TryCount.setText(player1.getCount().toString());
    }

    /**открытие коробки
     * @param mouseEvent событие мыши*/
    public void openCaseClick(MouseEvent mouseEvent) {
        EventTarget pressed = mouseEvent.getTarget();
        if(!tryCheck()) {
            return;
        }
        resetImg();
        action = new ActionChain();
        if (pressed == imgOne) gameResult(imgOne);
        if (pressed == imgTwo) gameResult(imgTwo);
        if (pressed == imgThree) gameResult(imgThree);
        playSound(selectSound);
        TryCount.setText(player1.getCount().toString());
    }

    /**рзультат игры
     * @param img картинка*/
    public void gameResult(ImageView img){
        resetImg();
        disableImg();
        delay(1000, () -> enableImg());
        playAnim(1000,1,1,1.2,1.2,img);
        if(result){//победа
            delay(1000, () -> img.setImage(new Image(new File(winImage).toURI().toString())));
            delay(2000, () -> MoneyCount.setText(player1.getNumber().toString()));
            delay(1000, () -> player1.addNumber(3));
            delay(1000, () -> playSound(winSound));
            delay(1000, () -> moneyWinAnim());
            player1.setFault(0);
        } else {//проигрышь
            delay(1000, () -> img.setImage(new Image(new File(loseImage).toURI().toString())));
            delay(1000, () -> playSound(loseSound));
            player1.addFault(1);
            if(player1.getFault()>=extraGame){
                delay(1000, () -> extraGame_start());
            }
        }
    }

    /**дополнительная игра*/
    public void extraGame_start(){
        playSound(errorSound);
        showAlert("Шанс!", "Вам выпал шанс, на вас счет зачислена дополнительная попытка. Удачи!");
        player1.setFault(0);
        addTry();
    }

    /** проверка попыток у игрока
     * @return true или false*/
    public boolean tryCheck(){
        if(! player1.play(1)) {
            showAlert("Бросьте деньги", "");
            playSound(errorSound);
            disableImg();
            action=null;
            return false;
        } else {
            return true;
        }
    }

    /** проверка денег у игрока
     * @return true или false*/
    public boolean moneyCheck(){
        if(! player1.pay(1)) {
            showAlert("Купите больше денег", "");
            playSound(errorSound);
            disableImg();
            action=null;
            return false;
        } else {
            return true;
        }
    }

    /** анимация выигрыша денег*/
    public void moneyWinAnim(){
        notifyLabel.setText("+3 денег");
        playAnim(500,1,1,1.2,1.2,notifyLabel);
        delay(1000, () -> notifyLabel.setText(""));
    }

    /** задержка
     * @param millis время задержки
     * @param continuation действие которое нужно выполнить*/
    public static void delay(long millis, Runnable continuation) {
        Task<Void> sleeper = new Task<>() {
            @Override
            protected Void call() {
                try { Thread.sleep(millis); }
                catch (InterruptedException e) { System.err.println(); }
                return null;
            }
        };
        sleeper.setOnSucceeded(event -> continuation.run());
        new Thread(sleeper).start();
    }

    /** сброс изображений*/
    public void resetImg(){
        playAnim(1,1,1,1,1,imgOne);
        playAnim(1,1,1,1,1,imgTwo);
        playAnim(1,1,1,1,1,imgThree);
        imgOne.setImage(new Image(new File(waitImage).toURI().toString()));
        imgTwo.setImage(new Image(new File(waitImage).toURI().toString()));
        imgThree.setImage(new Image(new File(waitImage).toURI().toString()));
    }

    /** отключение клика на изображения*/
    public void disableImg(){
        imgOne.setDisable(true);
        imgTwo.setDisable(true);
        imgThree.setDisable(true);
    }

    /** включение клика на изображения*/
    public void enableImg(){
        imgOne.setDisable(false);
        imgTwo.setDisable(false);
        imgThree.setDisable(false);
    }

    /** проигрывание звука
     * @param name звук который нужно проиграть*/
    public void playSound(String name){
        sound = new Media(new File(name).toURI().toString());
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }

    /** проигрывание анимации
     * @param duration продолжительность анимации
     * @param fromX значение по Х откуда начинать анимацию
     * @param fromY значение по Y откуда начинать анимацию
     * @param toX значение по X куда проигрывать анимацию
     * @param toY значение по Y куда проигрывать анимацию
     * @param node объект на который применяется анимация*/
    public void playAnim(int duration, int fromX, int fromY, double toX, double toY, Node node){
        ScaleTransition scaleTransition = new ScaleTransition();
        scaleTransition.setDuration(Duration.millis(duration));
        scaleTransition.setFromX(fromX);
        scaleTransition.setFromY(fromY);
        scaleTransition.setToX(toX);
        scaleTransition.setToY(toY);
        scaleTransition.setCycleCount(1);
        scaleTransition.setNode(node);
        scaleTransition.play();
    }

    /** запись данных о игроке в файл*/
    public void writePlayer(){
        try {
            file.write(playerList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** чтение данных о игроке из файла*/
    public void readPlayer(){
        try {
            file.read(playerList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** создание окна уведомления
     * @param name название окна
     * @param content содержание окна*/
    public void showAlert(String name, String content){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(name);
        alert.setContentText(content);
        alert.show();
    }
}
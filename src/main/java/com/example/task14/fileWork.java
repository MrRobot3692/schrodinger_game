package com.example.task14;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @version 1.0.0
 * @author Веретенников Никита
 */
public class fileWork {
    HashMap<String, Integer> playerList = new HashMap<>();

    /**запись данных о игроках в документ
     * @param playerList список игроков
     * @throws IOException исключение*/
    public void write(HashMap<String, Integer> playerList) throws IOException {
        this.playerList = playerList;
        String str = playerList.toString();
        FileWriter writer = new FileWriter("src\\main\\java\\com\\example\\task14\\database\\players.txt", false);
        writer.append(str.replace('=', ' ').substring(1,str.length()-1).replace(", ", "\n").trim());
        writer.close();
    }

    /**чтение данных о игроках из документа
     * @param playerList список игроков
     * @throws IOException исключение*/
    public void read(HashMap<String, Integer> playerList) throws IOException {
        this.playerList = playerList;
        FileReader read = new FileReader("src\\main\\java\\com\\example\\task14\\database\\players.txt");
        Scanner scan = new Scanner(read);
        while (scan.hasNextLine()){
            playerList.put(scan.next(), scan.nextInt());
        }
        read.close();
    }
}

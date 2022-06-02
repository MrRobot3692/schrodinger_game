package com.example.task14;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class PlayerTest {

    private Player player;

    /**
     * Метод запускаемый перед тестом
     */
    @BeforeMethod
    public void setUp() {
        player = new Player("name", 10);
    }

    /**
     * Метод запускаемый после теста
     */
    @AfterMethod
    public void tearDown() {
        player = null;
    }


    /**
     * Тестирование оплаты
     */
    @Test
    public void testPay() {
        player.pay(1);
        assertEquals(player.getNumber(), Integer.valueOf(9));
    }

    /**
     * Тестирование получения количества попыток
     */
    @Test
    public void testGetCount() {
        player.addCount(1);
        assertEquals(player.getCount(), Integer.valueOf(1));
    }
}
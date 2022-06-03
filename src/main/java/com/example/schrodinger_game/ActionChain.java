package com.example.schrodinger_game;

import java.util.Random;

/**
 * @version 1.0.0
 * @author Веретенников Никита
 */
public class ActionChain {
    Handler chain;
    /**вариант выигрыша*/
    public static int SUCCESS = 1;
    /**вариант проигрыша*/
    public static int LOSS = 2;
    Random generate;
    final int NUMHANDLER = 3;

    /** Главный метод*/
    public ActionChain(){
        generate = new Random();
        buildChain();
        getRandomNum();
    }

    /** построение цепочки*/
    public void buildChain(){
        chain = new NegativeHandler(new PositiveHandler(null));
    }

    /** генерация рандомного числа
     * @return да или нет*/
    public boolean getRandomNum(){
        int type = generate.nextInt(NUMHANDLER);
        return processAction(type);
    }

    /** запуск цепочки
     * @param a рандомное число
     * @return да или нет*/
    public boolean processAction(Integer a){
        boolean contin = chain.process(1+a%NUMHANDLER);
        if(1+a%NUMHANDLER != 1){
            HelloController.result = false;
        } else {
            HelloController.result = true;
        }
        return contin;
    }
}

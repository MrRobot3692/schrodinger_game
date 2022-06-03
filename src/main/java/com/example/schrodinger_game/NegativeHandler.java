package com.example.schrodinger_game;

/**
 * @version 1.0.0
 * @author Веретенников Никита
 */
public class NegativeHandler extends Handler{

    /**Главный метод
     * @param processor интерфейс*/
    public NegativeHandler(Handler processor) {
        super(processor);
    }

    /**обработка негативного результата
     * @param request случайное число
     * @return содержание*/
    public boolean process(Integer request) {
        if (request != ActionChain.LOSS || request != 3){
            return super.process(request);
        } else {
            return false;
        }
    }
}

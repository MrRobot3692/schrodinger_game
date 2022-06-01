package com.example.task14;

/**
 * @version 1.0.0
 * @author Веретенников Никита
 */
public class PositiveHandler extends Handler{

    /**Главный метод
    * @param processor интерфейс*/
    public PositiveHandler(Handler processor) {
        super(processor);
    }

    /**обработка позитивного результата
     * @param request случайное число
     * @return содержание*/
    public boolean process(Integer request) {
        if (request != ActionChain.SUCCESS){
            return super.process(request);
        } else {
            return false;
        }
    }
}

package com.example.schrodinger_game;

/**
 * @version 1.0.0
 * @author Веретенников Никита
 */
public abstract class Handler  {
    private Handler processor;

    /**Главный метод
     * @param processor интерфейс*/
    public Handler(Handler processor){
        this.processor = processor;
    }

    /**отправка запроса в цепочку
     * @param request рандомное число
     * @return да или нет*/
    public boolean process(Integer request){
        if(processor != null) {
            return processor.process(request);
        } else {
            return true;
        }
    }
}

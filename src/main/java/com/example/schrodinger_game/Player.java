package com.example.schrodinger_game;

/**
 * @version 1.0.0
 * @author Веретенников Никита
 */
public class Player {
    private String name;
    private Integer count;
    private Integer number;
    private Integer fault;

    /**Главный метод
     * @param name имя игрока
     * @param number колчество монет игрока*/
    public Player(String name, Integer number) {
        this.name = name;
        this.number = number;
        count = 0;
        fault = 0;
    }

    /**Оплата
     * @param number сумма
     * @return содержание*/
    public boolean pay(Integer number) {
        if(number <= this.number) {
            this.number-=number;
            return true;
        }
        else return false;
    }

    /**Снятие попытки
     * @param count попытка
     * @return содержание*/
    public boolean play(Integer count) {
        if(count <= this.count) {
            this.count-=count;
            return true;
        }
        else return false;
    }

    /**Получить количество попыток
     * @return попытки*/
    public Integer getCount() {
        return count;
    }

    /**Получить количество монет
     * @return монеты*/
    public Integer getNumber() {
        return number;
    }

    /**добавить монет
     * @param number количество монет*/
    public void addNumber(Integer number) {
        this.number+= number;
    }

    /**добавить попытки
     * @param count количество попыток*/
    public void addCount(Integer count) {
        this.count += count;
    }

    /**получить проигрыши игрока
     * @return проигрыши*/
    public Integer getFault() {
        return fault;
    }

    /**добавить проигрыши
     * @param fault количество проигрышей*/
    public void addFault(Integer fault) {
        this.fault += fault;
    }

    /**установить проигрыши
     * @param fault количество проигрышей*/
    public void setFault(Integer fault) {
        this.fault = fault;
    }

    /**получить имя игрока
     * @return имя*/
    public String getName() {
        return name;
    }
}

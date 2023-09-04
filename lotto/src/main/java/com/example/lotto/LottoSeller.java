package com.example.lotto;

public class LottoSeller {

    public static final int PRICE = 1000;

    public int calculatePurchasedLotto(int money) throws IllegalStateException{
        if(money <= 0){
            throw new IllegalStateException("money can not less or equal than zero");
        }

        return money/PRICE;
    }
}

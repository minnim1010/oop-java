package com.example.lotto.service;

/*
* 구매한 로또의 개수를 반환한다.
*/
public class LottoSeller {
    public static final int PRICE = 1000;

    public int calculatePurchasedLottos(int money) throws IllegalStateException {
        if (money <= 0) {
            throw new IllegalStateException("구입 금액은 0 이하여야 합니다.");
        }

        return money / PRICE;
    }
}

package com.example.lotto.service;

/*
* 구매한 로또의 개수를 반환한다.
*/
public class LottoSeller {
    public static final String PURCHASED_AMOUNT_LESS_THAN_1000_ERROR =
        "[Error] 현재 구매 금액 %d원: 구매 금액은 1000원 이상이어야 합니다.";
    public static final int PRICE = 1000;

    public int calculatePurchasedLottos(int amount) {
        if (amount < 1000) {
            throw new IllegalArgumentException(
                String.format(PURCHASED_AMOUNT_LESS_THAN_1000_ERROR, amount));
        }

        return amount / PRICE;
    }
}

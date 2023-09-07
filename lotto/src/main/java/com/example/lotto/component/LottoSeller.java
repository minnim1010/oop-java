package com.example.lotto.component;

import com.example.lotto.constants.LottoConstants;
import com.example.lotto.model.PurchaseAmount;

/*
 * 구매한 로또의 개수를 반환한다.
 */
public class LottoSeller {

    public int getTotalPurchasedLottoCount(PurchaseAmount amount) {
        return amount.getAmount() / LottoConstants.PRICE;
    }
}

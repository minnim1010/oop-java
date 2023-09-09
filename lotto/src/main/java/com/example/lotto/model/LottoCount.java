package com.example.lotto.model;

import com.example.lotto.constants.LottoConstants;
import com.example.lotto.validator.CommonValidator;

public class LottoCount {
    private final int count;

    public LottoCount(int count) {
        CommonValidator.validatePositiveNumberOrZero(count);

        this.count = count;
    }

    public static LottoCount of(PurchaseAmount amount) {
        return new LottoCount(amount.getAmount() / LottoConstants.PRICE);
    }

    public int getLottoCount() {
        return count;
    }
}

package com.example.lotto.model;

import com.example.lotto.validator.CommonValidator;

public class TotalPurchasedLottoCount {
    private final int lottoCount;

    public TotalPurchasedLottoCount(int lottoCount) {
        CommonValidator.validatePositiveNumber(lottoCount);
        this.lottoCount = lottoCount;
    }

    public int getLottoCount() {
        return lottoCount;
    }
}

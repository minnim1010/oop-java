package com.example.lotto.model;

import com.example.lotto.validator.CommonValidator;

public class PurchasedLottoCount {
    private final int lottoCount;

    public PurchasedLottoCount(int lottoCount) {
        CommonValidator.validatePositiveNumber(lottoCount);
        this.lottoCount = lottoCount;
    }

    public int getLottoCount() {
        return lottoCount;
    }
}

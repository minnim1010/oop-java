package com.example.lotto.model;

import com.example.lotto.validator.CommonValidator;

public class LottoCount {
    private final int count;

    public LottoCount(int count) {
        CommonValidator.validatePositiveNumberOrZero(count);
        this.count = count;
    }

    public int getLottoCount() {
        return count;
    }
}

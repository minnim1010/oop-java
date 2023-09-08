package com.example.lotto.vo;

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

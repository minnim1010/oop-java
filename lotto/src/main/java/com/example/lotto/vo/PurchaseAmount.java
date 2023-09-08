package com.example.lotto.vo;

import com.example.lotto.validator.LottoValidator;

public class PurchaseAmount {

    int amount;

    public PurchaseAmount(int amount) {
        LottoValidator.validateLottoPurchaseAmount(amount);

        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PurchaseAmount amount1 = (PurchaseAmount) o;

        return amount == amount1.amount;
    }

    @Override
    public int hashCode() {
        return amount;
    }
}

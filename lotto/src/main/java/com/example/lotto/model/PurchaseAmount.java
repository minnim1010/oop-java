package com.example.lotto.model;

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
}

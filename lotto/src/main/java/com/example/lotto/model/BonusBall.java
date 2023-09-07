package com.example.lotto.model;

import com.example.lotto.validator.LottoValidator;

public class BonusBall {
    private final int number;

    public BonusBall(int number) {
        LottoValidator.validateLottoNumberRange(number);
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}

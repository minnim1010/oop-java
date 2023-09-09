package com.example.lotto.model;

import com.example.lotto.validator.CommonValidator;

public class LottoStatistics {
    private final WinningRank winningRank;
    private final double profitRate;

    public LottoStatistics(WinningRank winningRank, double profitRate) {
        CommonValidator.validateNotNull(winningRank);
        CommonValidator.validatePositiveNumberOrZero(profitRate);

        this.winningRank = winningRank;
        this.profitRate = profitRate;
    }

    public WinningRank getWinningRank() {
        return winningRank;
    }

    public double getProfitRate() {
        return profitRate;
    }
}

package com.example.lotto.model;

import com.example.lotto.validator.CommonValidator;

import java.util.Arrays;

public class LottoStatistics {
    private final LottoWinningRank lottoWinningRank;
    private final Long reward;
    private final Double profitRate;

    private LottoStatistics(LottoWinningRank lottoWinningRank, PurchaseAmount purchaseAmount) {
        CommonValidator.validateNotNull(lottoWinningRank);
        CommonValidator.validateNotNull(purchaseAmount);

        this.lottoWinningRank = lottoWinningRank;
        this.reward = calculateReward();
        this.profitRate = calculateProfitRate(purchaseAmount);
    }

    public static LottoStatistics create(
        LottoWinningRank lottoWinningRank, PurchaseAmount purchaseAmount) {
        return new LottoStatistics(lottoWinningRank, purchaseAmount);
    }

    public long calculateReward() {
        return Arrays.stream(LottoRank.values())
            .mapToLong(lottoRank ->
                lottoWinningRank.getCountBy(lottoRank) * lottoRank.getReward())
            .sum();
    }

    public double calculateProfitRate(PurchaseAmount purchaseAmount) {
        return (double) reward / purchaseAmount.getAmount();
    }

    public LottoWinningRank getLottoWinningRank() {
        return lottoWinningRank;
    }

    public Double getProfitRate() {
        return profitRate;
    }

    public Long getReward() {
        return reward;
    }
}

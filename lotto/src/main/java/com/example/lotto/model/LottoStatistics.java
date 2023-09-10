package com.example.lotto.model;

import com.example.lotto.validator.CommonValidator;

import java.util.Arrays;

public final class LottoStatistics {
    private final LottoWinningRankResult lottoWinningRankResult;
    private final Long reward;
    private final Double profitRate;

    private LottoStatistics(LottoWinningRankResult lottoWinningRankResult, PurchaseAmount purchaseAmount) {
        CommonValidator.validateNotNull(lottoWinningRankResult);
        CommonValidator.validateNotNull(purchaseAmount);

        this.lottoWinningRankResult = lottoWinningRankResult;
        this.reward = calculateReward();
        this.profitRate = calculateProfitRate(purchaseAmount);
    }

    public static LottoStatistics create(
        LottoWinningRankResult lottoWinningRankResult, PurchaseAmount purchaseAmount) {
        return new LottoStatistics(lottoWinningRankResult, purchaseAmount);
    }

    public long calculateReward() {
        return Arrays.stream(LottoRank.values())
            .mapToLong(lottoRank ->
                lottoWinningRankResult.getCountBy(lottoRank) * lottoRank.getReward())
            .sum();
    }

    public double calculateProfitRate(PurchaseAmount purchaseAmount) {
        return (double) reward / purchaseAmount.getAmount();
    }

    public LottoWinningRankResult getLottoWinningRank() {
        return lottoWinningRankResult;
    }

    public Double getProfitRate() {
        return profitRate;
    }

    public Long getReward() {
        return reward;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LottoStatistics that = (LottoStatistics) o;

        if (!lottoWinningRankResult.equals(that.lottoWinningRankResult)) return false;
        if (!reward.equals(that.reward)) return false;
        return profitRate.equals(that.profitRate);
    }

    @Override
    public int hashCode() {
        int result = lottoWinningRankResult.hashCode();
        result = 31 * result + reward.hashCode();
        result = 31 * result + profitRate.hashCode();
        return result;
    }
}

package com.example.lotto.service;

import com.example.lotto.domain.Lotto;
import com.example.lotto.domain.LottoPurchaseResult;
import com.example.lotto.domain.LottoRank;

import java.util.List;
import java.util.Optional;

/*
* 당첨 통계를 계산한다.
*/
public class WinningStatisticsCalculator {

    public static final int HIGHEST_MATCH_COUNT = LottoRank.FIRST.getMatchCount();
    public static final int LOWEST_MATCH_COUNT = LottoRank.FIFTH.getMatchCount();
    private final int amount;
    private final LottoPurchaseResult lottoPurchaseResult;

    private int reward;
    private double profitRate;

    public WinningStatisticsCalculator(int amount) {
        this.amount = amount;
        lottoPurchaseResult = new LottoPurchaseResult();
    }

    public int getReward() {
        return reward;
    }

    public double getProfitRate() {
        return profitRate;
    }

    public void calculate(Lotto winningLotto, int bonusBall, List<Lotto> lottos) {
        for (Lotto lotto : lottos) {
            int cnt = countMatchNumber(winningLotto, lotto);
            boolean hasBonusBall = hasBonusBall(bonusBall, lotto);
            updateResult(cnt, hasBonusBall);
        }

        calculateReward();
        calculateProfitRate();
    }

    private int countMatchNumber(Lotto winningLotto, Lotto lotto) {
        return (int) lotto.getNumbers().stream()
            .filter(winningLotto::contains)
            .count();
    }

    private boolean hasBonusBall(int bonusBall, Lotto lotto) {
        return lotto.contains(bonusBall);
    }

    private void updateResult(int cnt, boolean hasBonusBall) {
        getRank(cnt, hasBonusBall)
            .ifPresent(lottoPurchaseResult::update);
    }

    private Optional<LottoRank> getRank(int cnt, boolean hasBonusBall) {
        if (cnt != LottoRank.SECOND.getMatchCount())
            hasBonusBall = false;
        return LottoRank.from(cnt, hasBonusBall);
    }

    private void calculateReward() {
        for (LottoRank rank : LottoRank.values()) {
            reward += (int) (lottoPurchaseResult.get(rank) * rank.getReward());
        }
    }

    private void calculateProfitRate() {
        profitRate = (double) reward / amount;
    }

    public LottoPurchaseResult getRankResult() {
        return this.lottoPurchaseResult;
    }
}

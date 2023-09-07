package com.example.lotto.component;

import com.example.lotto.domain.LottoRank;
import com.example.lotto.domain.WinningRank;
import com.example.lotto.model.PurchasedLottos;
import com.example.lotto.model.WinningLottoTicket;

/*
 * 당첨 통계를 계산한다.
 */
public class LottoStatsCalculator {

    private final WinningRankCalculator winningRankCalculator = new WinningRankCalculator();
    private WinningRank calculatedWinningRank;

    public void init(WinningLottoTicket winningLottoTicket, PurchasedLottos lottos) {
        this.calculatedWinningRank = winningRankCalculator.calculate(winningLottoTicket, lottos);
    }

    public WinningRank getCalculatedWinningRank() {
        return this.calculatedWinningRank;
    }

    public long calculateReward() {
        long reward = 0L;
        for (LottoRank lottoRank : LottoRank.values()) {
            reward += (int) (calculatedWinningRank.get(lottoRank) * lottoRank.getReward());
        }
        return reward;
    }

    public double calculateProfitRate(long reward, int amount) {
        return (double) reward / amount;
    }
}

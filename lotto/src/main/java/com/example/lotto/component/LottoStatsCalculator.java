package com.example.lotto.component;

import com.example.lotto.domain.LottoRank;
import com.example.lotto.vo.PurchaseAmount;
import com.example.lotto.vo.PurchasedLottos;
import com.example.lotto.vo.WinningLottoTicket;

import java.util.Arrays;

/*
 * 당첨 통계를 계산한다.
 */
public class LottoStatsCalculator {

    private final WinningRankCalculator winningRankCalculator = new WinningRankCalculator();

    public void init(WinningLottoTicket winningLottoTicket, PurchasedLottos lottos) {
        winningRankCalculator.calculate(winningLottoTicket, lottos);
    }

    public WinningRank getCalculatedWinningRank() {
        return winningRankCalculator.getWinningRank();
    }

    public long calculateReward() {
        WinningRank calculatedWinningRank = winningRankCalculator.getWinningRank();

        return Arrays.stream(LottoRank.values())
            .mapToLong(lottoRank ->
                calculatedWinningRank.getCount(lottoRank) * lottoRank.getReward())
            .sum();
    }

    public double calculateProfitRate(long reward, PurchaseAmount purchaseAmount) {
        return (double) reward / purchaseAmount.getAmount();
    }
}

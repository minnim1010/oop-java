package com.example.lotto.component;

import com.example.lotto.model.*;

/*
 * 구매한 로또와 당첨 로또를 비교하여 당첨 순위를 계산한다.
 */
public class WinningRankCalculator {

    private final WinningRank winningRank;

    public WinningRankCalculator() {
        this.winningRank = new WinningRank();
    }

    public WinningRank getWinningRank() {
        return winningRank;
    }

    public WinningRank calculate(WinningLottoTicket winningLottoTicket,
                                 PurchasedLottos lottos) {
        for (Lotto lotto : lottos.getLottos()) {
            int matchCnt = winningLottoTicket.countMatchNumber(lotto);
            boolean hasBonusBall = lotto.contains(winningLottoTicket.getBonusBall());
            updateResult(matchCnt, hasBonusBall);
        }
        return this.winningRank;
    }

    private void updateResult(int matchCnt, boolean hasBonusBall) {
        LottoRank.findByMatchCountAndBonusBall(matchCnt, hasBonusBall)
            .ifPresent(winningRank::increase);
    }
}

package com.example.lotto.component;

import com.example.lotto.domain.Lotto;
import com.example.lotto.domain.LottoRank;
import com.example.lotto.domain.WinningRank;
import com.example.lotto.model.BonusBall;
import com.example.lotto.model.PurchasedLottos;
import com.example.lotto.model.WinningLottoTicket;

import java.util.Optional;

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
            int matchCnt = countMatchNumber(winningLottoTicket.getWinningLotto(), lotto);
            boolean hasBonusBall = hasBonusBall(winningLottoTicket.getBonusBall(), lotto);
            updateResult(matchCnt, hasBonusBall);
        }
        return this.winningRank;
    }

    private int countMatchNumber(Lotto winningLotto, Lotto lotto) {
        return (int) lotto.getNumbers().stream()
            .filter(num -> isMatch(winningLotto, num))
            .count();
    }

    private boolean isMatch(Lotto lotto, int number) {
        return lotto.contains(number);
    }

    private boolean hasBonusBall(BonusBall bonusBall, Lotto lotto) {
        return lotto.contains(bonusBall.getNumber());
    }

    private void updateResult(int matchCnt, boolean hasBonusBall) {
        findLottoRank(matchCnt, hasBonusBall).ifPresent(winningRank::increase);
    }

    private Optional<LottoRank> findLottoRank(int matchCnt, boolean hasBonusBall) {
        if (matchCnt != LottoRank.SECOND.getMatchCount())
            hasBonusBall = false;
        return LottoRank.findByMatchCountAndBonusBall(matchCnt, hasBonusBall);
    }
}

package com.example.lotto.component;

import com.example.lotto.domain.Lotto;
import com.example.lotto.domain.LottoRank;
import com.example.lotto.vo.PurchasedLottos;
import com.example.lotto.vo.WinningLottoTicket;

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
            boolean hasBonusBall = lotto.contains(winningLottoTicket.getBonusBall().getNumber());
            updateResult(matchCnt, hasBonusBall);
        }
        return this.winningRank;
    }

    private int countMatchNumber(Lotto winningLotto, Lotto lotto) {
        return (int) lotto.getNumbers().stream()
            .filter(winningLotto::contains)
            .count();
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

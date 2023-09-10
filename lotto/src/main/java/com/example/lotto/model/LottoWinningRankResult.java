package com.example.lotto.model;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

public final class LottoWinningRankResult {
    private final Map<LottoRank, Integer> winningRankResult = new EnumMap<>(LottoRank.class);

    private LottoWinningRankResult(WinningLottoTicket winningLottoTicket, PurchasedLottos lottos) {
        Arrays.stream(LottoRank.values())
            .forEach(lottoRank -> winningRankResult.put(lottoRank, 0));

        for (Lotto lotto : lottos.getLottos()) {
            int matchCnt = winningLottoTicket.countMatchNumber(lotto);
            boolean hasBonusBall = lotto.contains(winningLottoTicket.getBonusBall());
            LottoRank.findBy(matchCnt, hasBonusBall)
                .ifPresent(this::increase);
        }
    }

    public static LottoWinningRankResult create(
        WinningLottoTicket winningLottoTicket, PurchasedLottos lottos) {
        return new LottoWinningRankResult(winningLottoTicket, lottos);
    }

    public Map<LottoRank, Integer> getWinningRankResult() {
        return winningRankResult;
    }

    public int getCountBy(LottoRank lottoRank) {
        return winningRankResult.get(lottoRank);
    }

    private void increase(LottoRank lottoRank) {
        Integer next = this.winningRankResult.get(lottoRank) + 1;
        winningRankResult.put(lottoRank, next);
    }
}

package com.example.lotto.model;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

public class LottoWinningRank {
    private final Map<LottoRank, Integer> rank = new EnumMap<>(LottoRank.class);

    private LottoWinningRank(WinningLottoTicket winningLottoTicket, PurchasedLottos lottos) {
        Arrays.stream(LottoRank.values())
            .forEach(lottoRank -> rank.put(lottoRank, 0));

        for (Lotto lotto : lottos.getLottos()) {
            int matchCnt = winningLottoTicket.countMatchNumber(lotto);
            boolean hasBonusBall = lotto.contains(winningLottoTicket.getBonusBall());
            LottoRank.findBy(matchCnt, hasBonusBall)
                .ifPresent(this::increase);
        }
    }

    public static LottoWinningRank create(
        WinningLottoTicket winningLottoTicket, PurchasedLottos lottos) {
        return new LottoWinningRank(winningLottoTicket, lottos);
    }

    public Map<LottoRank, Integer> getRank() {
        return rank;
    }

    public int getCountBy(LottoRank lottoRank) {
        return rank.get(lottoRank);
    }

    private void increase(LottoRank lottoRank) {
        Integer next = this.rank.get(lottoRank) + 1;
        rank.put(lottoRank, next);
    }
}

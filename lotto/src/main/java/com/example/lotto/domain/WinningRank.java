package com.example.lotto.domain;

import java.util.EnumMap;
import java.util.Map;

public class WinningRank {
    private final Map<LottoRank, Integer> rank = new EnumMap<>(LottoRank.class);

    public WinningRank() {
        for (LottoRank lottoRank : LottoRank.values()) {
            rank.put(lottoRank, 0);
        }
    }

    public Map<LottoRank, Integer> getRank() {
        return rank;
    }

    public void update(LottoRank lottoRank) {
        Integer update = this.rank.get(lottoRank) + 1;
        rank.put(lottoRank, update);
    }

    public int getCount(LottoRank lottoRank) {
        return rank.get(lottoRank);
    }
}

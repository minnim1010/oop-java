package com.example.lotto.domain;

import java.util.EnumMap;
import java.util.Map;

public class LottoPurchaseResult {
    private final Map<LottoRank, Integer> rank;

    public LottoPurchaseResult() {
        this.rank = new EnumMap<>(LottoRank.class);
        for (LottoRank lottoRank : LottoRank.values()) {
            rank.put(lottoRank, 0);
        }
    }

    public Map<LottoRank, Integer> getRank() {
        return rank;
    }

    public void update(LottoRank lottoRank) {
        Integer update = rank.get(lottoRank) + 1;
        rank.put(lottoRank, update);
    }

    public int get(LottoRank lottoRank) {
        return rank.get(lottoRank);
    }
}

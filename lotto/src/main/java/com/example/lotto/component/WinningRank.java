package com.example.lotto.component;

import com.example.lotto.domain.LottoRank;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

public class WinningRank {
    private final Map<LottoRank, Integer> rank = new EnumMap<>(LottoRank.class);

    public WinningRank() {
        Arrays.stream(LottoRank.values())
            .forEach(lottoRank -> rank.put(lottoRank, 0));
    }

    public Map<LottoRank, Integer> getRank() {
        return rank;
    }

    public void increase(LottoRank lottoRank) {
        Integer next = this.rank.get(lottoRank) + 1;
        rank.put(lottoRank, next);
    }

    public int getCount(LottoRank lottoRank) {
        return rank.get(lottoRank);
    }
}

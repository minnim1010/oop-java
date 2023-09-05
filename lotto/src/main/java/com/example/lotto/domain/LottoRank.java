package com.example.lotto.domain;

import java.util.Arrays;
import java.util.Optional;

public enum LottoRank {
    FIFTH(3, false, 5000),
    FOURTH(4, false, 50000),
    THIRD(5, false, 1500000),
    SECOND(5, true, 30000000),
    FIRST(6, false, 2000000000);

    private final int matchCount;
    private final boolean hasBonusBall;
    private final long reward;

    LottoRank(int matchCount, boolean hasBonusBall, int reward) {
        this.matchCount = matchCount;
        this.hasBonusBall = hasBonusBall;
        this.reward = reward;
    }

    public static Optional<LottoRank> from(int matchCount, boolean hasBonusBall) {
        return Arrays.stream(LottoRank.values())
            .filter(lottoRank -> lottoRank.matchCount == matchCount
                && lottoRank.hasBonusBall() == hasBonusBall)
            .findFirst();
    }

    public long getReward() {
        return reward;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public boolean hasBonusBall() {
        return hasBonusBall;
    }
}

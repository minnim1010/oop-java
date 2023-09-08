package com.example.lotto.domain;

import java.util.Arrays;
import java.util.Optional;

public enum LottoRank {
    FIFTH(3, false, 5000, "3개 일치 (5000원)- "),
    FOURTH(4, false, 50000, "4개 일치 (50000원)- "),
    THIRD(5, false, 1500000, "5개 일치 (1500000원)- "),
    SECOND(5, true, 30000000, "5개 일치, 보너스 볼 일치(30000000원) - "),
    FIRST(6, false, 2000000000, "6개 일치 (2000000000원)- ");

    private final int matchCount;
    private final boolean hasBonusBall;
    private final long reward;
    private final String message;

    LottoRank(int matchCount, boolean hasBonusBall, int reward, String message) {
        this.matchCount = matchCount;
        this.hasBonusBall = hasBonusBall;
        this.reward = reward;
        this.message = message;
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

    public String getMessage() {
        return message;
    }

    public static Optional<LottoRank> findByMatchCountAndBonusBall(int matchCount, boolean hasBonusBall) {
        return Arrays.stream(LottoRank.values())
            .filter(lottoRank -> lottoRank.matchCount == matchCount
                && lottoRank.hasBonusBall() == hasBonusBall)
            .findFirst();
    }
}

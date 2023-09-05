package com.example.lotto.domain;

import java.util.Arrays;
import java.util.Optional;

public enum LottoReward {
    MATCH_THREE(3, 5000),
    MATCH_FOUR(4, 50000),
    MATCH_FIVE(5, 1500000),
    MATCH_SIX(6, 2000000000);

    private final int matchCount;
    private final int reward;

    LottoReward(int matchCount, int reward) {
        this.matchCount = matchCount;
        this.reward = reward;
    }

    public int getReward() {
        return reward;
    }

    public static int getReward(int matchCount) {
        Optional<LottoReward> lottoReward = Arrays.stream(values())
            .filter(e -> e.matchCount == matchCount)
            .findFirst();

        return lottoReward.map(LottoReward::getReward).orElse(0);
    }
}

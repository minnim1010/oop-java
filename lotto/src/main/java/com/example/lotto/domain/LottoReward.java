package com.example.lotto.domain;

import java.util.Arrays;
import java.util.Optional;

public enum LottoReward {
    FIRST(6, 2000000000),
    SECOND(5, 1500000),
    THIRD(4, 50000),
    FOURTH(3, 5000);

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

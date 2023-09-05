package com.example.lotto.domain;

import java.util.Arrays;
import java.util.Optional;

public enum RewardPerMatchingCount {
    MATCH_THREE(3, 5000),
    MATCH_FOUR(4, 50000),
    MATCH_FIVE(5, 1500000),
    MATCH_SIX(6, 2000000000);

    private final int matchingCount;
    private final int reward;

    RewardPerMatchingCount(int matchingCount, int reward) {
        this.matchingCount = matchingCount;
        this.reward = reward;
    }

    public int getReward() {
        return reward;
    }

    public static int getReward(int matchingCount) {
        Optional<RewardPerMatchingCount> matchingReward = Arrays.stream(values())
            .filter(e -> e.matchingCount == matchingCount)
            .findFirst();

        return matchingReward.map(RewardPerMatchingCount::getReward).orElse(0);
    }
}

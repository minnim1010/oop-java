package com.example.lotto;

public enum RewardPerMatchingCount {
    MATCH_THREE(5000),
    MATCH_FOUR(50000),
    MATCH_FIVE(1500000),
    MATCH_SIX(2000000000);

    private final int reward;

    RewardPerMatchingCount(int reward) {
        this.reward = reward;
    }

    public int getReward() {
        return reward;
    }
}

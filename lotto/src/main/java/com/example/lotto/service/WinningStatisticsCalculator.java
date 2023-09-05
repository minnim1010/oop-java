package com.example.lotto.service;

import com.example.lotto.domain.RewardPerMatchingCount;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WinningStatisticsCalculator {

    public static final int MIN_MATCHING_COUNT_FOR_REWARD = 3;
    public static final int MAX_MATCHING_COUNT_FOR_REWARD = 6;

    private final List<Integer> winningNumbers;
    private final Map<Integer, Integer> matchCnt;
    private int reward;

    public WinningStatisticsCalculator(List<Integer> winningNumbers) {
        this.winningNumbers = winningNumbers;
        matchCnt = new HashMap<>();
        for (int i = MIN_MATCHING_COUNT_FOR_REWARD; i <= MAX_MATCHING_COUNT_FOR_REWARD; i++) {
            matchCnt.put(i, 0);
        }
    }

    public void calculate(List<List<Integer>> lottos) {
        for (List<Integer> lotto : lottos) {
            int cnt = countMatchingNumber(lotto);
            updateMatchCnt(cnt);
        }

        calculateReward();
    }

    private void updateMatchCnt(int matchingCnt) {
        if (MIN_MATCHING_COUNT_FOR_REWARD <= matchingCnt
                && matchingCnt <= MAX_MATCHING_COUNT_FOR_REWARD) {
            int updateCnt = matchCnt.get(matchingCnt) + 1;
            matchCnt.put(matchingCnt, updateCnt);
        }
    }

    private int countMatchingNumber(List<Integer> lotto) {
        int matchingCnt = 0;
        for (int number : lotto) {
            matchingCnt += isMatch(number) ? 1 : 0;
        }
        return matchingCnt;
    }

    private boolean isMatch(int number) {
        return winningNumbers.contains(number);
    }

    private void calculateReward() {
        for (int i = MIN_MATCHING_COUNT_FOR_REWARD; i <= MAX_MATCHING_COUNT_FOR_REWARD; i++) {
            reward += matchCnt.get(i) * RewardPerMatchingCount.getReward(i);
        }
    }

    public Map<Integer, Integer> getMatchCnt() {
        return this.matchCnt;
    }

    public int getReward() {
        return reward;
    }
}

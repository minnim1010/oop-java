package com.example.lotto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WinningCalculator {

    private final List<Integer> winningNumbers;
    private final Map<Integer, Integer> matchCnt;
    private int reward;

    public WinningCalculator(List<Integer> winningNumbers) {
        this.winningNumbers = winningNumbers;
        matchCnt = new HashMap<>();
        matchCnt.put(3, 0);
        matchCnt.put(4, 0);
        matchCnt.put(5, 0);
        matchCnt.put(6, 0);
    }

    public void calculate(List<List<Integer>> lottos) {
        for (List<Integer> lotto : lottos) {
            int cnt = countMatchingNumber(lotto);
            updateMatchCnt(cnt);
        }

        calculateReward();
    }

    private void updateMatchCnt(int cnt) {
        if(3 <= cnt && cnt <= 6){
            matchCnt.put(cnt, matchCnt.get(cnt) + 1);
        }
    }

    private int countMatchingNumber(List<Integer> lotto) {
        int matchingCnt = 0;
        for (int number : lotto) {
            matchingCnt += isMatch(number) ? 1 : 0;
        }
        return matchingCnt;
    }

    private boolean isMatch(int number){
        return winningNumbers.contains(number);
    }

    private void calculateReward() {
        reward = matchCnt.get(3) * RewardPerMatchingCount.MATCH_THREE.getReward()
            + matchCnt.get(4) * RewardPerMatchingCount.MATCH_FOUR.getReward()
            + matchCnt.get(5) * RewardPerMatchingCount.MATCH_FIVE.getReward()
            + matchCnt.get(6) * RewardPerMatchingCount.MATCH_SIX.getReward();
    }

    public Map<Integer, Integer> getMatchCnt() {
        return this.matchCnt;
    }

    public int getReward() {
        return reward;
    }
}

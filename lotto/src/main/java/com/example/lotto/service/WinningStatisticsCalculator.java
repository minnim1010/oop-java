package com.example.lotto.service;

import com.example.lotto.domain.Lotto;
import com.example.lotto.domain.LottoReward;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
* 당첨 통계를 계산한다.
*/
public class WinningStatisticsCalculator {

    public static final int MIN_MATCHING_COUNT_FOR_REWARD = 3;
    public static final int MAX_MATCHING_COUNT_FOR_REWARD = 6;

    private final Lotto winningLotto;
    private final Map<Integer, Integer> matchCnt;
    private int reward;

    public WinningStatisticsCalculator(Lotto winningLotto) {
        this.winningLotto = winningLotto;
        
        matchCnt = new HashMap<>();
        for (int i = MIN_MATCHING_COUNT_FOR_REWARD; i <= MAX_MATCHING_COUNT_FOR_REWARD; i++) {
            matchCnt.put(i, 0);
        }
    }

    public void calculate(List<Lotto> lottos) {
        for (Lotto lotto : lottos) {
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

    private int countMatchingNumber(Lotto lotto) {
        return (int) lotto.getNumbers().stream()
            .filter(winningLotto::contains)
            .count();
    }

    private void calculateReward() {
        for (int cnt = MIN_MATCHING_COUNT_FOR_REWARD; cnt <= MAX_MATCHING_COUNT_FOR_REWARD; cnt++) {
            reward += matchCnt.get(cnt) * LottoReward.getReward(cnt);
        }
    }

    public Map<Integer, Integer> getMatchCnt() {
        return this.matchCnt;
    }

    public int getReward() {
        return reward;
    }
}

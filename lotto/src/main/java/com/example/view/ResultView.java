package com.example.view;

import com.example.io.CommandLineIo;

import java.util.Map;

public class ResultView {

    public static final String PURCHASED_LOTTO_MSG = "%s개를 구매했습니다.";

    public static final String WINNING_STATISTICS = "당첨 통계\n---------";
    public static final String WINNING_MATCH_THREE = "3개 일치 (5000원)- %s개";
    public static final String WINNING_MATCH_FOUR = "4개 일치 (50000원)- %s개";
    public static final String WINNING_MATCH_FIVE = "5개 일치 (1500000원)- %s개";
    public static final String WINNING_MATCH_SIX = "6개 일치 (2000000000원)- %s개";
    public static final String WINNING_PROFIT_RATE = "총 수익률은 %.2f입니다.";

    public void purchasedLotto(int lottoNum){
        CommandLineIo.output(String.format(PURCHASED_LOTTO_MSG, lottoNum));
    }

    public void winningStatistics(Map<Integer, Integer> matchCnt, int reward, int purchasedAmount) {
        CommandLineIo.output(WINNING_STATISTICS);

        CommandLineIo.output(String.format(WINNING_MATCH_THREE, matchCnt.get(3)));
        CommandLineIo.output(String.format(WINNING_MATCH_FOUR, matchCnt.get(4)));
        CommandLineIo.output(String.format(WINNING_MATCH_FIVE, matchCnt.get(5)));
        CommandLineIo.output(String.format(WINNING_MATCH_SIX, matchCnt.get(6)));

        CommandLineIo.output(String.format(WINNING_PROFIT_RATE, (double) reward/purchasedAmount));
    }
}

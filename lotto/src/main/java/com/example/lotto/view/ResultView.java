package com.example.lotto.view;

import com.example.io.CommandLineOutput;
import com.example.lotto.domain.Lotto;

import java.util.List;
import java.util.Map;

public class ResultView {

    public static final String PURCHASED_LOTTO_MSG = "%s개를 구매했습니다.";

    public static final String WINNING_STATISTICS = "당첨 통계\n---------";
    public static final String WINNING_MATCH_THREE = "3개 일치 (5000원)- %s개";
    public static final String WINNING_MATCH_FOUR = "4개 일치 (50000원)- %s개";
    public static final String WINNING_MATCH_FIVE = "5개 일치 (1500000원)- %s개";
    public static final String WINNING_MATCH_SIX = "6개 일치 (2000000000원)- %s개";
    public static final String WINNING_PROFIT_RATE = "총 수익률은 %.2f입니다.";

    public void showPurchasedLotto(int lottoNum) {
        CommandLineOutput.output(String.format(PURCHASED_LOTTO_MSG, lottoNum));
    }

    public void showPurchasedLottos(List<Lotto> lottos) {
        lottos.stream()
            .forEach(lotto -> CommandLineOutput.output(lotto.toString()));
    }

    public void showWinningStatistics(Map<Integer, Integer> matchCnt,
                                      int reward,
                                      int purchasedAmount) {
        CommandLineOutput.output(WINNING_STATISTICS);

        CommandLineOutput.output(String.format(WINNING_MATCH_THREE, matchCnt.get(3)));
        CommandLineOutput.output(String.format(WINNING_MATCH_FOUR, matchCnt.get(4)));
        CommandLineOutput.output(String.format(WINNING_MATCH_FIVE, matchCnt.get(5)));
        CommandLineOutput.output(String.format(WINNING_MATCH_SIX, matchCnt.get(6)));

        CommandLineOutput.output(
            String.format(WINNING_PROFIT_RATE, (double) reward/purchasedAmount));
    }
}

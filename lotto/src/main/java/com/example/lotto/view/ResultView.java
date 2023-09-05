package com.example.lotto.view;

import com.example.io.CommandLineOutput;
import com.example.lotto.domain.Lotto;
import com.example.lotto.domain.LottoPurchaseResult;
import com.example.lotto.domain.LottoRank;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.List;

public class ResultView {

    public static final String PURCHASED_LOTTO_MSG = "%s개를 구매했습니다.";

    public static final String WINNING_STATISTICS = "당첨 통계\n---------";
    public static final String LOTTO_MATCH_RESULT = "%d개 일치%s (%s원)- %d개";
    public static final String WINNING_PROFIT_RATE = "총 수익률은 %.2f입니다.";

    public void showPurchasedLotto(int lottoNum) {
        CommandLineOutput.output(String.format(PURCHASED_LOTTO_MSG, lottoNum));
    }

    public void showPurchasedLottos(List<Lotto> lottos) {
        lottos
            .forEach(lotto -> CommandLineOutput.output(lotto.toString()));
    }

    public void showWinningStatistics(LottoPurchaseResult result,
                                      double profitRate) {
        NumberFormat numberFormat = NumberFormat.getInstance();

        CommandLineOutput.output(WINNING_STATISTICS);

        Arrays.stream(LottoRank.values())
            .forEach(rank -> CommandLineOutput.output(String.format(
                LOTTO_MATCH_RESULT,
                rank.getMatchCount(),
                getWinningStatisticsDetailString(rank),
                numberFormat.format(rank.getReward()),
                result.get(rank))));

        CommandLineOutput.output(String.format(WINNING_PROFIT_RATE, profitRate));
    }

    private String getWinningStatisticsDetailString(LottoRank lottoRank) {
        if (lottoRank.equals(LottoRank.SECOND))
            return ", 보너스 볼 일치";
        return "";
    }
}

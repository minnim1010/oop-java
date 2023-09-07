package com.example.lotto.view;

import com.example.io.CommandLineOutput;
import com.example.lotto.domain.LottoRank;
import com.example.lotto.model.LottoStatistics;
import com.example.lotto.model.PurchasedLottos;
import com.example.lotto.model.TotalPurchasedLottoCount;

import java.text.NumberFormat;
import java.util.Arrays;

public class ResultView {

    public static final String PURCHASED_LOTTO_MSG = "%s개를 구매했습니다.";

    public static final String WINNING_STATISTICS = "당첨 통계\n---------";
    public static final String LOTTO_MATCH_RESULT = "%d개 일치%s (%s원)- %d개";
    public static final String WINNING_PROFIT_RATE = "총 수익률은 %.2f입니다.";

    NumberFormat numberFormat = NumberFormat.getInstance();

    public void showPurchasedLotto(TotalPurchasedLottoCount lottoCount) {
        CommandLineOutput.output(String.format(PURCHASED_LOTTO_MSG, lottoCount));
    }

    public void showPurchasedLottos(PurchasedLottos purchasedLottos) {
        purchasedLottos.getLottos()
            .forEach(lotto -> CommandLineOutput.output(lotto.toString()));
    }

    public void showWinningStatistics(LottoStatistics lottoStatistics) {
        CommandLineOutput.output(WINNING_STATISTICS);

        Arrays.stream(LottoRank.values())
            .forEach(rank -> CommandLineOutput.output(String.format(
                LOTTO_MATCH_RESULT,
                rank.getMatchCount(),
                getWinningStatisticsDetailString(rank),
                numberFormat.format(rank.getReward()),
                lottoStatistics.getWinningRank().get(rank))));

        CommandLineOutput.output(String.format(WINNING_PROFIT_RATE, lottoStatistics.getProfitRate()));
    }

    private String getWinningStatisticsDetailString(LottoRank lottoRank) {
        if (lottoRank.equals(LottoRank.SECOND))
            return ", 보너스 볼 일치";
        return "";
    }
}

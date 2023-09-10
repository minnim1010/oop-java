package com.example.lotto.view;

import com.example.io.CommandLineOutput;
import com.example.lotto.model.LottoCount;
import com.example.lotto.model.LottoRank;
import com.example.lotto.model.LottoStatistics;
import com.example.lotto.model.PurchasedLottos;

import java.util.Arrays;

public class OutputView {

    public static final String PURCHASED_LOTTO_MSG = "수동으로 %d장, 자동으로 %d장을 구매했습니다.";

    public static final String WINNING_STATISTICS = "당첨 통계\n---------";
    public static final String WINNING_PROFIT_RATE = "총 수익률은 %.2f입니다.";

    public void showPurchasedLottoCount(LottoCount manualLottoCnt, LottoCount autoLottoCnt) {
        CommandLineOutput.output(String.format(
            PURCHASED_LOTTO_MSG, manualLottoCnt.getLottoCount(), autoLottoCnt.getLottoCount()));
    }

    public void showPurchasedLottos(PurchasedLottos purchasedLottos) {
        purchasedLottos.getLottos()
            .forEach(lotto -> CommandLineOutput.output(lotto.toString()));
    }

    public void showWinningStatistics(LottoStatistics lottoStatistics) {
        CommandLineOutput.output(WINNING_STATISTICS);

        Arrays.stream(LottoRank.values())
            .forEach(rank -> CommandLineOutput.output(
                rank.getMessage() + lottoStatistics.getLottoWinningRank().getCountBy(rank) + "개"));

        CommandLineOutput.output(String.format(WINNING_PROFIT_RATE, lottoStatistics.getProfitRate()));
    }
}

package com.example.lotto.service;

import com.example.lotto.component.LottoProvider;
import com.example.lotto.component.LottoStatsCalculator;
import com.example.lotto.constants.LottoConstants;
import com.example.lotto.domain.Lotto;
import com.example.lotto.model.*;

import java.util.ArrayList;
import java.util.List;

public class LottoService {
    private final LottoProvider lottoProvider = new LottoProvider();
    private final LottoStatsCalculator lottoStatsCalculator = new LottoStatsCalculator();

    public TotalPurchasedLottoCount getTotalPurchasedLottoCount(PurchaseAmount amount) {
        int lottoCount = amount.getAmount() / LottoConstants.PRICE;
        return new TotalPurchasedLottoCount(lottoCount);
    }

    public PurchasedLottos getLottos(TotalPurchasedLottoCount totalPurchasedLottoCount) {
        int lottoCnt = totalPurchasedLottoCount.getLottoCount();

        List<Lotto> lottos = new ArrayList<>(lottoCnt);
        for (int i = 0; i < lottoCnt; i++) {
            lottos.add(lottoProvider.createLotto());
        }
        return new PurchasedLottos(lottos);
    }

    public LottoStatistics calculateStatistics(Lotto winningLotto,
                                               BonusBall bonusBall,
                                               PurchasedLottos lottos,
                                               PurchaseAmount amount) {
        lottoStatsCalculator.init(winningLotto, bonusBall, lottos);

        double profitRate = lottoStatsCalculator.calculateProfitRate(
            lottoStatsCalculator.calculateReward(), amount.getAmount());

        return new LottoStatistics(
            lottoStatsCalculator.getCalculatedWinningRank(),
            profitRate);
    }
}

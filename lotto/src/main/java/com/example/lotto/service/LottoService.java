package com.example.lotto.service;

import com.example.lotto.component.LottoGenerator;
import com.example.lotto.component.LottoStatsCalculator;
import com.example.lotto.constants.LottoConstants;
import com.example.lotto.domain.Lotto;
import com.example.lotto.model.*;

import java.util.ArrayList;
import java.util.List;

public class LottoService {
    private final LottoGenerator lottoGenerator = new LottoGenerator();
    private final LottoStatsCalculator lottoStatsCalculator = new LottoStatsCalculator();

    public LottoCount getLottoCount(PurchaseAmount amount) {
        int lottoCount = amount.getAmount() / LottoConstants.PRICE;
        return new LottoCount(lottoCount);
    }

    public PurchasedLottos getLottos(LottoCount totalLottoCount,
                                     LottoCount manualLottoCount,
                                     List<Lotto> manualLottos) {
        int totalLottoCnt = totalLottoCount.getLottoCount();
        List<Lotto> lottos = new ArrayList<>(totalLottoCnt);

        lottos.addAll(manualLottos);
        lottos.addAll(getAutoLottos(totalLottoCnt - manualLottoCount.getLottoCount()));

        return new PurchasedLottos(lottos);
    }

    private List<Lotto> getAutoLottos(int autoLottoCount) {
        List<Lotto> lottos = new ArrayList<>(autoLottoCount);
        for (int i = 0; i < autoLottoCount; i++) {
            lottos.add(lottoGenerator.createAutoLotto());
        }
        return lottos;
    }

    public LottoStatistics calculateStatistics(WinningLottoTicket winningLottoTicket,
                                               PurchasedLottos lottos,
                                               PurchaseAmount amount) {
        lottoStatsCalculator.init(winningLottoTicket, lottos);

        double profitRate = lottoStatsCalculator.calculateProfitRate(
            lottoStatsCalculator.calculateReward(), amount);

        return new LottoStatistics(
            lottoStatsCalculator.getCalculatedWinningRank(),
            profitRate);
    }
}

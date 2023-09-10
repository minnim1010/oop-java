package com.example.lotto.service;

import com.example.lotto.model.*;

import java.util.ArrayList;
import java.util.List;

public class LottoService {

    public PurchasedLottos getLottos(LottoCount totalLottoCount,
                                     LottoCount manualLottoCount,
                                     PurchasedLottos manualLottos) {
        int totalLottoCnt = totalLottoCount.getLottoCount();
        int autoLottoCnt = totalLottoCnt - manualLottoCount.getLottoCount();

        List<Lotto> lottos = new ArrayList<>(totalLottoCnt);
        lottos.addAll(manualLottos.getLottos());
        lottos.addAll(getAutoLottos(autoLottoCnt));

        return new PurchasedLottos(lottos);
    }

    private List<Lotto> getAutoLottos(int autoLottoCount) {
        List<Lotto> lottos = new ArrayList<>(autoLottoCount);
        for (int i = 0; i < autoLottoCount; i++) {
            lottos.add(AutoLotto.create());
        }
        return lottos;
    }

    public LottoStatistics calculateStatistics(
        WinningLottoTicket ticket, PurchasedLottos lottos, PurchaseAmount amount) {
        LottoWinningRank lottoWinningRank = LottoWinningRank.create(ticket, lottos);

        return LottoStatistics.create(lottoWinningRank, amount);
    }
}

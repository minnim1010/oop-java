package com.example.lotto.controller;

import com.example.lotto.domain.Lotto;
import com.example.lotto.model.*;
import com.example.lotto.service.LottoService;
import com.example.lotto.view.ErrorView;
import com.example.lotto.view.InputView;
import com.example.lotto.view.ResultView;

import java.util.List;

public class LottoController {
    private final InputView inputView = new InputView();
    private final ResultView resultView = new ResultView();
    private final ErrorView errorView = new ErrorView();

    private final LottoService lottoService = new LottoService();

    private PurchaseAmount purchaseAmount;

    public void run() {
        try {
            PurchasedLottos purchasedLottos = getLottos();
            WinningLottoTicket winningLottoTicket = getWinningLottoTicket();
            LottoStatistics lottoStatistics = getLottoStatistics(
                winningLottoTicket, purchasedLottos, purchaseAmount);
            printWinningStatistics(lottoStatistics);
        } catch (IllegalArgumentException ex) {
            errorView.showIllegalArgumentException(ex);
        }
    }

    public PurchasedLottos getLottos() {
        purchaseAmount = inputView.readPurchaseAmount();
        LottoCount totalLottoCnt = lottoService.getLottoCount(purchaseAmount);

        LottoCount manualLottoCnt = inputView.readManualLottoCount();
        List<Lotto> manualLottos = inputView.readManualLottos(manualLottoCnt);
        resultView.showPurchasedLottoCount(
            manualLottoCnt,
            new LottoCount(totalLottoCnt.getLottoCount() - manualLottoCnt.getLottoCount()));

        PurchasedLottos purchasedLottos = lottoService.getLottos(
            totalLottoCnt, manualLottoCnt, manualLottos);
        resultView.showPurchasedLottos(purchasedLottos);

        return purchasedLottos;
    }

    public WinningLottoTicket getWinningLottoTicket() {
        Lotto winningLotto = inputView.readWinningLotto();
        BonusBall bonusBall = inputView.readBonusBall();

        return new WinningLottoTicket(winningLotto, bonusBall);
    }

    public LottoStatistics getLottoStatistics(WinningLottoTicket winningLotto,
                                              PurchasedLottos lottos,
                                              PurchaseAmount amount) {
        return lottoService.calculateStatistics(winningLotto, lottos, amount);
    }

    public void printWinningStatistics(LottoStatistics lottoStatistics) {
        resultView.showWinningStatistics(lottoStatistics);
    }
}
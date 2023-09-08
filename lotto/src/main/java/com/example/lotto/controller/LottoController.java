package com.example.lotto.controller;

import com.example.lotto.domain.Lotto;
import com.example.lotto.service.LottoService;
import com.example.lotto.view.ErrorView;
import com.example.lotto.view.InputView;
import com.example.lotto.view.OutputView;
import com.example.lotto.vo.*;

import java.util.List;

public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;
    private final ErrorView errorView;
    private final LottoService lottoService;

    public LottoController(InputView inputView,
                           OutputView outputView,
                           ErrorView errorView,
                           LottoService lottoService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.errorView = errorView;
        this.lottoService = lottoService;
    }

    private PurchaseAmount purchaseAmount;

    public void run() {
        try {
            PurchasedLottos purchasedLottos = buyLotto();
            WinningLottoTicket winningLottoTicket = getWinningLottoTicket();
            getLottoStatistics(winningLottoTicket, purchasedLottos, purchaseAmount);
        } catch (IllegalArgumentException ex) {
            errorView.showIllegalArgumentException(ex);
        }
    }

    public PurchasedLottos buyLotto() {
        purchaseAmount = inputView.readPurchaseAmount();
        LottoCount totalLottoCnt = lottoService.getLottoCount(purchaseAmount);

        LottoCount manualLottoCnt = inputView.readManualLottoCount();
        List<Lotto> manualLottos = inputView.readManualLottos(manualLottoCnt);
        outputView.showPurchasedLottoCount(
            manualLottoCnt, new LottoCount(
                totalLottoCnt.getLottoCount() - manualLottoCnt.getLottoCount()));

        PurchasedLottos purchasedLottos = lottoService.getLottos(
            totalLottoCnt, manualLottoCnt, manualLottos);
        outputView.showPurchasedLottos(purchasedLottos);

        return purchasedLottos;
    }

    public WinningLottoTicket getWinningLottoTicket() {
        Lotto winningLotto = inputView.readWinningLotto();
        BonusBall bonusBall = inputView.readBonusBall();

        return new WinningLottoTicket(winningLotto, bonusBall);
    }

    public void getLottoStatistics(
        WinningLottoTicket winningLotto, PurchasedLottos lottos, PurchaseAmount amount) {
        LottoStatistics lottoStatistics = lottoService.calculateStatistics(
            winningLotto, lottos, amount);
        outputView.showWinningStatistics(lottoStatistics);
    }
}
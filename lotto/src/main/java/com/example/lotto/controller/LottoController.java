package com.example.lotto.controller;

import com.example.converter.BasicConverter;
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
        String input = inputView.readPurchaseAmount();
        purchaseAmount = new PurchaseAmount(BasicConverter.convertStringToInt(input));
        PurchasedLottoCount lottoCount = lottoService.getPurchasedLottoCount(purchaseAmount);
        resultView.showPurchasedLotto(lottoCount);

        PurchasedLottos purchasedLottos = lottoService.getLottos(lottoCount);
        resultView.showPurchasedLottos(purchasedLottos);

        return purchasedLottos;
    }

    public WinningLottoTicket getWinningLottoTicket() {
        String winningLottoNumbersInput = inputView.readWinningLottoNumbers();
        List<Integer> numbers = BasicConverter
            .convertStringToIntegerList(winningLottoNumbersInput);
        Lotto winningLotto = new Lotto(numbers);

        String bonusBallInput = inputView.readBonusBall();
        int bonusBallNumber = BasicConverter.convertStringToInt(bonusBallInput);
        BonusBall bonusBall = new BonusBall(bonusBallNumber);

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
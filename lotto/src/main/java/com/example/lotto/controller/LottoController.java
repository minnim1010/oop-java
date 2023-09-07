package com.example.lotto.controller;

import com.example.converter.InputTypeConverter;
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

    private PurchaseAmount amount;

    public void run() {
        try {
            PurchasedLottos purchasedLottos = getLottos();
            WinningLottoTicket winningLottoTicket = getWinningLottoTicket();
            LottoStatistics lottoStatistics = getLottoStatistics(
                winningLottoTicket, purchasedLottos, amount);
            printWinningStatistics(lottoStatistics);
        } catch (IllegalArgumentException ex) {
            errorView.showIllegalArgumentException(ex);
        }
    }

    private PurchasedLottos getLottos() {
        String input = inputView.readPurchaseAmount();
        amount = new PurchaseAmount(InputTypeConverter.convertStringToInt(input));

        TotalPurchasedLottoCount lottoCount = lottoService.getTotalPurchasedLottoCount(amount);

        resultView.showPurchasedLotto(lottoCount);

        PurchasedLottos purchasedLottos = lottoService.getLottos(lottoCount);
        resultView.showPurchasedLottos(purchasedLottos);

        return purchasedLottos;
    }

    private WinningLottoTicket getWinningLottoTicket() {
        String winningLottoNumbersInput = inputView.readWinningLottoNumbers();
        List<Integer> numbers = InputTypeConverter
            .convertStringToIntegerList(winningLottoNumbersInput);
        Lotto winningLotto = new Lotto(numbers);

        String bonusBallInput = inputView.readBonusBall();
        int bonusBallNumber = InputTypeConverter.convertStringToInt(bonusBallInput);
        BonusBall bonusBall = new BonusBall(bonusBallNumber);

        return new WinningLottoTicket(winningLotto, bonusBall);
    }

    private LottoStatistics getLottoStatistics(WinningLottoTicket winningLotto,
                                               PurchasedLottos lottos,
                                               PurchaseAmount amount) {
        return lottoService.calculateStatistics(winningLotto, lottos, amount);
    }

    private void printWinningStatistics(LottoStatistics lottoStatistics) {
        resultView.showWinningStatistics(lottoStatistics);
    }
}
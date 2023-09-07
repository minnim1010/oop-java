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
            Lotto winningLotto = getWinningNumbers();
            BonusBall bonusBall = getBonusBall();
            LottoStatistics lottoStatistics = getLottoStatistics(
                winningLotto, bonusBall, purchasedLottos, amount);
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

    private Lotto getWinningNumbers() {
        String winningLottoNumbers = inputView.readWinningLottoNumbers();
        List<Integer> numbers = InputTypeConverter
            .convertStringToIntegerList(winningLottoNumbers);

        return new Lotto(numbers);
    }

    private BonusBall getBonusBall() {
        String bonusBall = inputView.readBonusBall();
        int bonusBallNumber = InputTypeConverter.convertStringToInt(bonusBall);
        return new BonusBall(bonusBallNumber);
    }

    private LottoStatistics getLottoStatistics(Lotto winningLotto,
                                               BonusBall bonusBall,
                                               PurchasedLottos lottos,
                                               PurchaseAmount amount) {
        return lottoService.calculateStatistics(
            winningLotto, bonusBall, lottos, amount);
    }

    private void printWinningStatistics(LottoStatistics lottoStatistics) {
        resultView.showWinningStatistics(lottoStatistics);
    }
}
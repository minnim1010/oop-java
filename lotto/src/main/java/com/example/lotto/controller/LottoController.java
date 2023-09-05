package com.example.lotto.controller;

import com.example.converter.InputTypeConverter;
import com.example.lotto.domain.Lotto;
import com.example.lotto.service.LottoProvider;
import com.example.lotto.service.LottoSeller;
import com.example.lotto.service.WinningStatisticsCalculator;
import com.example.lotto.view.ErrorView;
import com.example.lotto.view.InputView;
import com.example.lotto.view.ResultView;

import java.util.List;

public class LottoController {
    private final InputView inputView = new InputView();
    private final ResultView resultView = new ResultView();
    private final ErrorView errorView = new ErrorView();

    private final LottoSeller lottoSeller = new LottoSeller();
    private final LottoProvider lottoProvider = new LottoProvider();
    private WinningStatisticsCalculator winningStatisticsCalculator;

    private int amount;

    public void run() {
        List<Lotto> lottos;
        Lotto winningLotto;
        int bonusBall;

        try {
            int lottoNum = buyLottos();
            lottos = getLottos(lottoNum);
            winningLotto = getWinningNumbers();
            bonusBall = getBonusBall();
        } catch (IllegalArgumentException ex) {
            errorView.showIllegalArgumentException(ex);
            return;
        }

        calculateWinningStatistics(winningLotto, bonusBall, lottos);
        printWinningStatistics();
    }

    private int buyLottos() {
        String input = inputView.inputPurchaseAmount();
        amount = InputTypeConverter.convertStringToInt(input);

        int lottoNum = lottoSeller.calculatePurchasedLottos(amount);

        resultView.showPurchasedLotto(lottoNum);
        return lottoNum;
    }

    private List<Lotto> getLottos(int lottoNum) {
        List<Lotto> lottos = lottoProvider.getLottos(lottoNum);
        resultView.showPurchasedLottos(lottos);

        return lottos;
    }

    private Lotto getWinningNumbers() {
        String winningLottoNumbers = inputView.inputWinningLottoNumbers();
        List<Integer> numbers = InputTypeConverter
            .convertStringToIntegerList(winningLottoNumbers);

        return new Lotto(numbers);
    }

    private int getBonusBall() {
        String bonusBall = inputView.inputBonusBall();
        return InputTypeConverter.convertStringToInt(bonusBall);
    }

    private void calculateWinningStatistics(Lotto winningLotto, int bonusBall, List<Lotto> lottos) {
        winningStatisticsCalculator = new WinningStatisticsCalculator(amount);
        winningStatisticsCalculator.calculate(winningLotto, bonusBall, lottos);
    }

    private void printWinningStatistics() {
        resultView.showWinningStatistics(
            winningStatisticsCalculator.getRankResult(),
            winningStatisticsCalculator.getProfitRate());
    }
}
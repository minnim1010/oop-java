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

    private int money;

    public void run() {
        List<Lotto> lottos;
        Lotto winningLotto;

        try {
            int lottoNum = buyLottos();
            lottos = getLottos(lottoNum);
            winningLotto = getWinningNumbers();
        } catch (IllegalArgumentException ex) {
            errorView.showIllegalArgumentException(ex);
            return;
        }

        calculateWinningStatistics(winningLotto, lottos);
        printWinningStatistics();
    }

    private int buyLottos() {
        String input = inputView.inputPurchaseAmount();
        money = InputTypeConverter.convertStringToInt(input);

        int lottoNum = lottoSeller.calculatePurchasedLottos(money);

        resultView.showPurchasedLotto(lottoNum);
        return lottoNum;
    }

    private List<Lotto> getLottos(int lottoNum) {
        List<Lotto> lottos = lottoProvider.getLottos(lottoNum);
        resultView.showPurchasedLottos(lottos);

        return lottos;
    }

    private Lotto getWinningNumbers() {
        String input = inputView.inputWinningLottoNumbers();
        List<Integer> numbers = InputTypeConverter.convertStringToIntegerList(input);

        return new Lotto(numbers);
    }

    private void calculateWinningStatistics(Lotto winningLotto, List<Lotto> lottos) {
        winningStatisticsCalculator = new WinningStatisticsCalculator(winningLotto);
        winningStatisticsCalculator.calculate(lottos);
    }

    private void printWinningStatistics() {
        resultView.showWinningStatistics(
            winningStatisticsCalculator.getMatchCnt(),
            winningStatisticsCalculator.getReward(),
            money);
    }
}
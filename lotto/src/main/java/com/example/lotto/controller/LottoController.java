package com.example.lotto.controller;

import com.example.converter.InputTypeConverter;
import com.example.lotto.service.LottoProvider;
import com.example.lotto.service.LottoSeller;
import com.example.lotto.service.WinningLottoProvider;
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
    private final WinningLottoProvider winningLottoProvider = new WinningLottoProvider();
    private WinningStatisticsCalculator winningStatisticsCalculator;

    private int money;

    public void run() {
        List<List<Integer>> lottos;
        List<Integer> winningNumbers;

        try {
            int lottoNum = buyLottos();
            lottos = getLottos(lottoNum);
            winningNumbers = getWinningNumbers();
        } catch (IllegalArgumentException ex) {
            errorView.showIllegalArgumentException(ex);
            return;
        }

        calculateWinningStatistics(winningNumbers, lottos);
        printWinningStatistics();
    }

    private int buyLottos() {
        String input = inputView.inputPurchaseAmount();
        money = InputTypeConverter.convertStringToInt(input);

        int lottoNum = lottoSeller.calculatePurchasedLottos(money);

        resultView.purchasedLotto(lottoNum);
        return lottoNum;
    }

    private List<List<Integer>> getLottos(int lottoNum) {
        return lottoProvider.getLottos(lottoNum);
    }

    private List<Integer> getWinningNumbers() {
        String input = inputView.inputWinningLottoNumbers();
        return winningLottoProvider.getWinningNumbers(input);
    }

    private void calculateWinningStatistics(List<Integer> winningNumbers, List<List<Integer>> lottos) {
        winningStatisticsCalculator = new WinningStatisticsCalculator(winningNumbers);
        winningStatisticsCalculator.calculate(lottos);
    }

    private void printWinningStatistics() {
        resultView.winningStatistics(
            winningStatisticsCalculator.getMatchCnt(), winningStatisticsCalculator.getReward(), money);
    }
}
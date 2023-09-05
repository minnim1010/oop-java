package com.example.lotto.controller;

import com.example.lotto.service.LottoProvider;
import com.example.lotto.service.LottoSeller;
import com.example.lotto.service.WinningStatisticsCalculator;
import com.example.lotto.service.WinningNumberProvider;
import com.example.lotto.view.InputView;
import com.example.lotto.view.ResultView;

import java.util.List;

public class LottoController {
    private final InputView inputView = new InputView();
    private final ResultView resultView = new ResultView();

    private final LottoSeller lottoSeller = new LottoSeller();
    private final LottoProvider lottoProvider = new LottoProvider();
    private final WinningNumberProvider winningNumberProvider = new WinningNumberProvider();
    private WinningStatisticsCalculator winningStatisticsCalculator;

    private int money;
    private List<List<Integer>> lottos;
    private List<Integer> winningNumbers;


    public void run() {
        int lottoNum = buyLottos();
        lottos = getLottos(lottoNum);

        winningNumbers = getWinningNumbers();

        calculateWinningStatistics();
        printWinningStatistics();
    }

    private int buyLottos() {
        String input = inputView.inputPurchaseAmount();

        this.money = Integer.parseInt(input);
        int lottoNum = lottoSeller.calculatePurchasedLottos(money);

        resultView.purchasedLotto(lottoNum);
        return lottoNum;
    }

    private List<List<Integer>> getLottos(int lottoNum) {
        return lottoProvider.getLottos(lottoNum);
    }

    private List<Integer> getWinningNumbers() {
        String input = inputView.inputWinningLottoNumbers();
        return winningNumberProvider.getWinningNumbers(input);
    }

    private void calculateWinningStatistics() {
        winningStatisticsCalculator = new WinningStatisticsCalculator(winningNumbers);
        winningStatisticsCalculator.calculate(lottos);
    }

    private void printWinningStatistics() {
        resultView.winningStatistics(
            winningStatisticsCalculator.getMatchCnt(), winningStatisticsCalculator.getReward(), money);
    }
}

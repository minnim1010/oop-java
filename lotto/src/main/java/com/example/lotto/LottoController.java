package com.example.lotto;

import com.example.view.InputView;
import com.example.view.ResultView;

import java.util.ArrayList;
import java.util.List;

public class LottoController {

    

    
    private final InputView inputView = new InputView();
    private final ResultView resultView = new ResultView();

    private final LottoSeller lottoSeller = new LottoSeller();
    private final LottoProvider lottoProvider = new LottoProvider();
    private final WinningNumberProvider winningNumberProvider = new WinningNumberProvider();
    private WinningCalculator winningCalculator;

    private int money;
    private List<List<Integer>> lottos;
    private List<Integer> winningNumbers;


    public void run(){
        int lottoNum = buyLottos();
        lottos = getLottos(lottoNum);

        winningNumbers = setWinningNumbers();

        calculateWinningStatistics();
        resultView.winningStatistics(winningCalculator.getMatchCnt(), winningCalculator.getReward(), money);
    }

    private int buyLottos() {
        String input = inputView.inputPurchaseAmount();

        this.money = Integer.parseInt(input);
        int lottoNum = lottoSeller.calculatePurchasedLotto(money);

        resultView.purchasedLotto(lottoNum);

        return lottoNum;
    }

    private List<List<Integer>> getLottos(int lottoNum) {
        List<List<Integer>> lottos = new ArrayList<>(6);
        for (int i = 0; i< lottoNum; ++i){
            lottos.add(lottoProvider.getLotto());
        }

        return lottos;
    }

    private List<Integer> setWinningNumbers() {
        String input = inputView.inputWinningLottoNumbers();

        return winningNumberProvider.getWinningNumbers(input);
    }

    private void calculateWinningStatistics() {
        winningCalculator = new WinningCalculator(winningNumbers);
        winningCalculator.calculate(lottos);
    }
}

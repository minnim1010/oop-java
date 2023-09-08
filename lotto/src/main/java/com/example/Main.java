package com.example;

import com.example.lotto.controller.LottoController;
import com.example.lotto.service.LottoService;
import com.example.lotto.view.ErrorView;
import com.example.lotto.view.InputView;
import com.example.lotto.view.OutputView;

public class Main {
    public static void main(String[] args) {
        LottoController lottoController = new LottoController(
            new InputView(), new OutputView(), new ErrorView(), new LottoService());
        lottoController.run();
    }
}
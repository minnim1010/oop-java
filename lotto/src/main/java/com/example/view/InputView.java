package com.example.view;

import com.example.io.CommandLineIo;

public class InputView {

    public static final String INPUT_BUY_LOTTO_MSG = "구입금액을 입력해 주세요.";
    public static final String INPUT_WINNING_LOTTO_NUMBERS = "지난 주 당첨 번호를 입력해 주세요.";

    public String inputPurchaseAmount(){
        CommandLineIo.output(INPUT_BUY_LOTTO_MSG);
        return CommandLineIo.input();
    }

    public String inputWinningLottoNumbers(){
        CommandLineIo.output(INPUT_WINNING_LOTTO_NUMBERS);
        return CommandLineIo.input();
    }
}

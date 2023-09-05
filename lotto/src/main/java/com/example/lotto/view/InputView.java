package com.example.lotto.view;

import com.example.io.CommandLineInput;
import com.example.io.CommandLineOutput;

public class InputView {

    public static final String INPUT_BUY_LOTTO_MSG = "구입금액을 입력해 주세요.";
    public static final String INPUT_WINNING_LOTTO_NUMBERS = "지난 주 당첨 번호를 입력해 주세요.";
    public static final String INPUT_BONUS_BALL = "보너스 볼을 입력해 주세요.";

    private String input() {
        return CommandLineInput.input();
    }

    public String inputPurchaseAmount(){
        CommandLineOutput.output(INPUT_BUY_LOTTO_MSG);
        return input();
    }

    public String inputWinningLottoNumbers(){
        CommandLineOutput.output(INPUT_WINNING_LOTTO_NUMBERS);
        return input();
    }

    public String inputBonusBall() {
        CommandLineOutput.output(INPUT_BONUS_BALL);
        return input();
    }
}

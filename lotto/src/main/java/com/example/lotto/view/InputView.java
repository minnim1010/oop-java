package com.example.lotto.view;

import com.example.converter.BasicConverter;
import com.example.io.CommandLineInput;
import com.example.io.CommandLineOutput;
import com.example.lotto.domain.Lotto;
import com.example.lotto.vo.BonusBall;
import com.example.lotto.vo.LottoCount;
import com.example.lotto.vo.PurchaseAmount;

import java.util.ArrayList;
import java.util.List;

public class InputView {

    private static final String INPUT_BUY_LOTTO_MSG = "구입금액을 입력해 주세요.";
    private static final String INPUT_WINNING_LOTTO_NUMBERS = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String INPUT_BONUS_BALL = "보너스 볼을 입력해 주세요.";
    private static final String INPUT_MANUALLY_LOTTO_COUNT = "수동으로 구매할 로또 수를 입력해 주세요.";
    private static final String INPUT_MANUALLY_LOTTO_NUMBERS = "수동으로 구매할 번호를 입력해 주세요.";

    private String input() {
        return CommandLineInput.input();
    }

    public PurchaseAmount readPurchaseAmount() {
        CommandLineOutput.output(INPUT_BUY_LOTTO_MSG);
        int purchaseAmount = BasicConverter.convertStringToInt(input());

        return new PurchaseAmount(purchaseAmount);
    }

    public Lotto readWinningLotto() {
        CommandLineOutput.output(INPUT_WINNING_LOTTO_NUMBERS);
        List<Integer> lottoNumbers = BasicConverter.convertStringToIntegerList(input(), ",");

        return new Lotto(lottoNumbers);
    }

    public BonusBall readBonusBall() {
        CommandLineOutput.output(INPUT_BONUS_BALL);
        int bonusBall = BasicConverter.convertStringToInt(input());

        return new BonusBall(bonusBall);
    }

    public LottoCount readManualLottoCount() {
        CommandLineOutput.output(INPUT_MANUALLY_LOTTO_COUNT);
        int manualLottoCount = BasicConverter.convertStringToInt(input());

        return new LottoCount(manualLottoCount);
    }

    public List<Lotto> readManualLottos(LottoCount manualLottoCnt) {
        CommandLineOutput.output(INPUT_MANUALLY_LOTTO_NUMBERS);

        List<Lotto> manualLottos = new ArrayList<>(manualLottoCnt.getLottoCount());
        for (int i = 0; i < manualLottoCnt.getLottoCount(); ++i) {
            List<Integer> lottoNumbers = BasicConverter.convertStringToIntegerList(input(), ",");
            manualLottos.add(new Lotto(lottoNumbers));
        }

        return manualLottos;
    }
}

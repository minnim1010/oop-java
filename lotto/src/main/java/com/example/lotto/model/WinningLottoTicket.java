package com.example.lotto.model;

import com.example.lotto.validator.CommonValidator;
import com.example.lotto.validator.LottoValidator;

public class WinningLottoTicket {
    private final Lotto winningLotto;
    private final int bonusBall;

    public WinningLottoTicket(Lotto winningLotto, int bonusBall) {
        CommonValidator.validateNotNull(winningLotto);
        LottoValidator.validateLottoNumberRange(bonusBall);
        LottoValidator.validateWinningLottoNumbersNotContainBonusBall(winningLotto, bonusBall);

        this.winningLotto = winningLotto;
        this.bonusBall = bonusBall;
    }

    public Lotto getWinningLotto() {
        return winningLotto;
    }

    public int getBonusBall() {
        return bonusBall;
    }

    public int countMatchNumber(Lotto lotto) {
        return (int) lotto.getNumbers().stream()
            .filter(winningLotto::contains)
            .count();
    }
}

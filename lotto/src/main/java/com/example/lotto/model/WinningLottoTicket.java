package com.example.lotto.model;

import com.example.lotto.domain.Lotto;
import com.example.lotto.validator.LottoValidator;

public class WinningLottoTicket {
    private final Lotto winningLotto;
    private final BonusBall bonusBall;

    public WinningLottoTicket(Lotto winningLotto, BonusBall bonusBall) {
        LottoValidator.validateWinningLottoNumbersNotContainBonusBall(winningLotto, bonusBall);
        this.winningLotto = winningLotto;
        this.bonusBall = bonusBall;
    }

    public Lotto getWinningLotto() {
        return winningLotto;
    }

    public BonusBall getBonusBall() {
        return bonusBall;
    }
}

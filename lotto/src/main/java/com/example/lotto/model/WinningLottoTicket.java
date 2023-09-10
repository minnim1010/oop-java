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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WinningLottoTicket that = (WinningLottoTicket) o;

        if (bonusBall != that.bonusBall) return false;
        return winningLotto.equals(that.winningLotto);
    }

    @Override
    public int hashCode() {
        int result = winningLotto.hashCode();
        result = 31 * result + bonusBall;
        return result;
    }
}

package com.example.lotto.vo;

import com.example.lotto.domain.Lotto;
import com.example.lotto.validator.CommonValidator;

import java.util.List;

public class PurchasedLottos {
    List<Lotto> lottos;

    public PurchasedLottos(List<Lotto> lottos) {
        CommonValidator.validateNotNull(lottos);

        this.lottos = lottos;
    }

    public List<Lotto> getLottos() {
        return lottos;
    }
}

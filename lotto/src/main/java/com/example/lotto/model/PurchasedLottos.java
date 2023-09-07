package com.example.lotto.model;

import com.example.lotto.domain.Lotto;

import java.util.List;

public class PurchasedLottos {
    List<Lotto> lottos;

    public PurchasedLottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public List<Lotto> getLottos() {
        return lottos;
    }
}

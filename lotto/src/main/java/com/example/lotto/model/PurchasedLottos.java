package com.example.lotto.model;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PurchasedLottos that = (PurchasedLottos) o;

        return lottos.equals(that.lottos);
    }

    @Override
    public int hashCode() {
        return lottos.hashCode();
    }
}

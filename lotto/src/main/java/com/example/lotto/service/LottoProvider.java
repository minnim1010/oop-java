package com.example.lotto.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
* 무작위 숫자 6개로 이루어진 로또를 주어진 개수만큼 발급한다.
*/
public class LottoProvider {
    public static final int LOTTO_SIZE = 6;
    public static final int LOTTO_MAX_NUMBER = 45;

    private final Random rand = new Random();

    public List<List<Integer>> getLottos(int size) {
        List<List<Integer>> lottos = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            lottos.add(getLotto());
        }
        return lottos;
    }

    private List<Integer> getLotto() {
        List<Integer> lotto = new ArrayList<>();
        for (int i = 0; i < LOTTO_SIZE; ++i) {
            lotto.add(getLottoNumber());
        }
        return lotto;
    }

    private int getLottoNumber() {
        return rand.nextInt(LOTTO_MAX_NUMBER + 1);
    }
}

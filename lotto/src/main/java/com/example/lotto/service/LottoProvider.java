package com.example.lotto.service;

import com.example.lotto.domain.Lotto;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/*
 * 무작위, 중복 없는 숫자 6개로 이루어진 로또를 주어진 개수만큼 발급한다.
 */
public class LottoProvider {
    public static final int LOTTO_SIZE = 6;
    public static final int LOTTO_MAX_NUMBER = 45;

    private final Random rand = new Random();

    public List<Lotto> getLottos(int size) {
        List<Lotto> lottos = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            lottos.add(createLotto());
        }
        return lottos;
    }

    private Lotto createLotto() {
        List<Integer> lottoNumbers = new ArrayList<>();
        while (lottoNumbers.size() < LOTTO_SIZE) {
            addLottoNumber(lottoNumbers);
        }
        lottoNumbers.sort(Comparator.comparingInt(a -> a));

        return new Lotto(lottoNumbers);
    }

    private void addLottoNumber(List<Integer> lottoNumbers) {
        int number = getLottoNumber();
        while (lottoNumbers.contains(number)) {
            number = getLottoNumber();
        }

        lottoNumbers.add(number);
    }

    private int getLottoNumber() {
        return rand.nextInt(LOTTO_MAX_NUMBER + 1);
    }
}

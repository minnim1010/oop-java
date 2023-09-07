package com.example.lotto.component;

import com.example.lotto.constants.LottoConstants;
import com.example.lotto.domain.Lotto;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/*
 * 무작위, 중복 없는 숫자 6개로 이루어진 로또를 주어진 개수만큼 발급한다.
 */
public class LottoProvider {

    private final Random rand = new Random();

    public Lotto createLotto() {
        List<Integer> lottoNumbers = new ArrayList<>();
        for (int i = 0; i < LottoConstants.NUMBERS_SIZE; i++) {
            addNonDuplicatedNumber(lottoNumbers);
        }
        lottoNumbers.sort(Comparator.comparingInt(a -> a));

        return new Lotto(lottoNumbers);
    }

    private void addNonDuplicatedNumber(List<Integer> lottoNumbers) {
        int number = getLottoNumber();
        while (lottoNumbers.contains(number)) {
            number = getLottoNumber();
        }
        lottoNumbers.add(number);
    }

    private int getLottoNumber() {
        return rand.nextInt(LottoConstants.MAX_NUMBER) + 1;
    }
}

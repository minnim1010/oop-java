package com.example.lotto.model;

import com.example.lotto.constants.LottoConstants;
import com.example.lotto.util.RandomUtil;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AutoLotto extends Lotto {
    private AutoLotto(List<Integer> numbers) {
        super(numbers);
    }

    public static AutoLotto create() {
        List<Integer> lottoNumbers = new ArrayList<>();
        for (int i = 0; i < LottoConstants.NUMBERS_SIZE; i++) {
            addNonDuplicatedNumber(lottoNumbers);
        }
        lottoNumbers.sort(Comparator.comparingInt(a -> a));

        return new AutoLotto(lottoNumbers);
    }

    private static void addNonDuplicatedNumber(List<Integer> lottoNumbers) {
        int number = getLottoNumber();
        while (lottoNumbers.contains(number)) {
            number = getLottoNumber();
        }
        lottoNumbers.add(number);
    }

    private static int getLottoNumber() {
        return RandomUtil.getRandomPositiveNumber(LottoConstants.MAX_NUMBER);
    }
}

package com.example.lotto.validator;

import com.example.lotto.constants.LottoConstants;

import java.util.List;
import java.util.Optional;

public class LottoValidator {
    private static final String LOTTO_SIZE_EXCEPTION = "[Error] 현재 로또 번호 자릿수 %d: 로또 번호는 %d자리입니다.";
    private static final String LOTTO_NUMBER_RANGE_EXCEPTION =
        "[Error] 유효하지 않은 로또 번호 %d: 로또 번호는 %d부터 %d 사이의 숫자여야 합니다.";
    private static final String PURCHASED_AMOUNT_LESS_THAN_A_LOTTO_PRICE_EXCEPTION =
        "[Error] 현재 구매 금액 %d원: 구매 금액은 %s원 이상이어야 합니다.";

    private LottoValidator() {
    }

    public static void validateLottoSize(List<Integer> numbers) {
        if (numbers.size() != LottoConstants.NUMBERS_SIZE) {
            throw new IllegalArgumentException(
                String.format(LOTTO_SIZE_EXCEPTION,
                    numbers.size(), LottoConstants.NUMBERS_SIZE));
        }
    }

    public static void validateLottoNumberRange(int number) {
        if (number < LottoConstants.MIN_NUMBER || LottoConstants.MAX_NUMBER < number) {
            throw new IllegalArgumentException(
                String.format(
                    LOTTO_NUMBER_RANGE_EXCEPTION,
                    number, LottoConstants.MIN_NUMBER, LottoConstants.MAX_NUMBER));
        }
    }

    public static void validateLottoNumberRange(List<Integer> numbers) {
        Optional<Integer> overRangeNum = numbers.stream()
            .filter(num -> num < LottoConstants.MIN_NUMBER || LottoConstants.MAX_NUMBER < num)
            .findFirst();

        if (overRangeNum.isPresent()) {
            throw new IllegalArgumentException(
                String.format(
                    LOTTO_NUMBER_RANGE_EXCEPTION,
                    overRangeNum.get(), LottoConstants.MIN_NUMBER, LottoConstants.MAX_NUMBER));
        }
    }

    public static void validateLottoPurchaseAmount(int amount) {
        if (amount < LottoConstants.PRICE) {
            throw new IllegalArgumentException(
                String.format(PURCHASED_AMOUNT_LESS_THAN_A_LOTTO_PRICE_EXCEPTION,
                    amount,
                    LottoConstants.PRICE));
        }
    }
}

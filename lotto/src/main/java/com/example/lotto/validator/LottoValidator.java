package com.example.lotto.validator;

import com.example.lotto.constants.LottoConstants;
import com.example.lotto.model.Lotto;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

public class LottoValidator {
    private static final String LOTTO_SIZE_EXCEPTION =
        "현재 로또 번호 자릿수 %d: 로또 번호는 %d자리입니다.";
    private static final String LOTTO_NUMBER_RANGE_EXCEPTION =
        "유효하지 않은 로또 번호 %d: 로또 번호는 %d부터 %d 사이의 숫자여야 합니다.";
    private static final String PURCHASED_AMOUNT_LESS_THAN_A_LOTTO_PRICE_EXCEPTION =
        "현재 구매 금액 %d원: 구매 금액은 %s원 이상이어야 합니다.";
    private static final String DUPLICATED_NUMBERS_EXCEPTION =
        "로또 번호들은 서로 중복되지 않아야 합니다.";
    private static final String DUPLICATED_BETWEEN_BONUSBALL_AND_WINNING_LOTTO_EXCEPTION =
        "보너스볼과 로또 번호는 중복되지 않아야 합니다.";
    private static final String NON_ASCEDNING_SORTED_EXCEPTION =
        "로또 번호들은 오름차순으로 정렬되어야 합니다.";

    private LottoValidator() {
    }

    public static void validateLottoPurchaseAmount(int amount) {
        if (amount < LottoConstants.PRICE) {
            throw new IllegalArgumentException(
                String.format(PURCHASED_AMOUNT_LESS_THAN_A_LOTTO_PRICE_EXCEPTION,
                    amount,
                    LottoConstants.PRICE));
        }
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

    public static void validateNonDuplicateNumbers(List<Integer> numbers) {
        HashSet<Integer> nonDuplicatedNumbers = new HashSet<>(numbers);
        if (nonDuplicatedNumbers.size() != LottoConstants.NUMBERS_SIZE) {
            throw new IllegalArgumentException(DUPLICATED_NUMBERS_EXCEPTION);
        }
    }

    public static void validateAscendingSorted(List<Integer> numbers) {
        boolean isSorted = IntStream.range(0, LottoConstants.NUMBERS_SIZE - 1)
            .noneMatch(i -> numbers.get(i) > numbers.get(i + 1));
        if (!isSorted) {
            throw new IllegalArgumentException(NON_ASCEDNING_SORTED_EXCEPTION);
        }
    }

    public static void validateWinningLottoNumbersNotContainBonusBall(Lotto winningLotto,
                                                                      int bonusBall) {
        if (winningLotto.contains(bonusBall)) {
            throw new IllegalArgumentException(DUPLICATED_BETWEEN_BONUSBALL_AND_WINNING_LOTTO_EXCEPTION);
        }
    }

}

package baseball.domain;

import baseball.constants.BaseballGame;

import java.util.*;

public class BaseballNumber {

    private final List<Digit> digits;

    private BaseballNumber(Digit[] digits) {
        checkDigitsLength(digits);
        checkUniqueDigits(digits);
        this.digits = new ArrayList<>();
        this.digits.addAll(Arrays.asList(digits));
    }

    public static BaseballNumber create(Digit[] digits) {
        return new BaseballNumber(digits);
    }

    private void checkDigitsLength(Digit[] digits) {
        if (digits.length != BaseballGame.BASEBALL_NUMBER_LENGTH)
            throw new IllegalStateException(
                String.format("현재 길이 %d: 숫자 야구는 세 개의 숫자로 구성 되어야 합니다.", digits.length));
    }

    private void checkUniqueDigits(Digit[] digits) {
        Set<Integer> exists = new HashSet<>();

        for (Digit digit : digits) {
            checkDuplicated(digit, exists);
        }
    }

    private void checkDuplicated(Digit digit, Set<Integer> exists) {
        int value = digit.getValue();
        if (exists.contains(value)) {
            throw new IllegalStateException(
                String.format("중복 숫자 %d: 숫자 야구의 숫자들은 서로 달라야 합니다.", value));
        }
        exists.add(value);
    }

    public List<Digit> getDigits() {
        return Collections.unmodifiableList(digits);
    }
}

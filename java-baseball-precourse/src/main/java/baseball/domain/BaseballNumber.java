package baseball.domain;

import java.util.*;

public class BaseballNumber {

    public static final int LENGTH = 3;

    private static final String WRONG_LENGTH_ERROR_MSG = "현재 길이 %d: 숫자 야구는 세 개의 숫자로 구성 되어야 합니다.";
    private static final String DUPLICATION_ERROR_MSG = "중복 숫자 %d: 숫자 야구의 숫자들은 서로 달라야 합니다.";

    private final List<Digit> digits;

    private BaseballNumber(List<Digit> digits) {
        checkDigitsLength(digits);
        checkUniqueDigits(digits);
        this.digits = new ArrayList<>(digits);
    }

    public static BaseballNumber create(List<Digit> digits) {
        return new BaseballNumber(digits);
    }

    private void checkDigitsLength(List<Digit> digits) {
        int size = digits.size();
        if (size != LENGTH)
            throw new IllegalArgumentException(
                String.format(WRONG_LENGTH_ERROR_MSG, size));
    }

    private void checkUniqueDigits(List<Digit> digits) {
        Set<Integer> exists = new HashSet<>();

        for (Digit digit : digits) {
            checkDuplicated(exists, digit);
        }
    }

    private void checkDuplicated(Set<Integer> exists, Digit digit) {
        int value = digit.getValue();
        if (exists.contains(value)) {
            throw new IllegalArgumentException(
                String.format(DUPLICATION_ERROR_MSG, value));
        }
        exists.add(value);
    }

    public List<Digit> getDigits() {
        return Collections.unmodifiableList(digits);
    }

    public List<BaseballResultType> match(BaseballNumber guess) {
        List<BaseballResultType> result = new ArrayList<>();

        for (int i = 0; i < LENGTH; i++) {
            Digit answerDigit = digits.get(i);
            boolean numberMatch = false;
            boolean positionMatch = false;

            for (int j = 0; j < LENGTH; j++) {
                Digit guessDigit = guess.getDigits().get(j);
                if (answerDigit.equals(guessDigit)) {
                    numberMatch = true;
                    if (i == j) {
                        positionMatch = true;
                    }
                }
            }
            BaseballResultType resultType = BaseballResultType.findBy(numberMatch, positionMatch);
            result.add(resultType);
        }

        return result;
    }

    @Override
    public String toString() {
        return "BaseballNumber" + digits;
    }
}

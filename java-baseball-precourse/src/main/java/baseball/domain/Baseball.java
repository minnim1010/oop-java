package baseball.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Baseball {

    public static final int LENGTH = 3;
    public static final String REGEX = "^[1-9]{3}$";

    private final List<Number> numbers;

    private Baseball(List<Number> numbers) {
        Validator.validateLength(numbers);
        Validator.validateUniqueNumbers(numbers);

        this.numbers = new ArrayList<>(numbers);
    }

    public static Baseball create(List<Number> numbers) {
        return new Baseball(numbers);
    }

    public List<Number> getNumbers() {
        return Collections.unmodifiableList(numbers);
    }

    public List<BaseballResultType> match(Baseball guess) {
        List<BaseballResultType> result = new ArrayList<>();

        for (int i = 0; i < LENGTH; i++) {
            Number answerNumber = numbers.get(i);
            Number guessNumber = guess.getNumbers().get(i);

            BaseballResultType resultType = getMatchResult(answerNumber, guessNumber);
            if (resultType != null) {
                result.add(resultType);
            }
        }

        return result;
    }

    private BaseballResultType getMatchResult(Number answerNumber, Number guessNumber) {
        if (answerNumber.equals(guessNumber)) {
            return BaseballResultType.STRIKE;
        }

        if (numbers.contains(guessNumber)) {
            return BaseballResultType.BALL;
        }

        return null;
    }

    @Override
    public String toString() {
        return "Baseball" + numbers;
    }

    private static class Validator {

        private static final String WRONG_LENGTH_ERROR_MSG = "현재 길이 %d: 숫자 야구는 세 개의 숫자로 구성 되어야 합니다.";
        private static final String DUPLICATION_ERROR_MSG = "중복 숫자 %d: 숫자 야구의 숫자들은 서로 달라야 합니다.";

        public static void validateLength(List<Number> numbers) {
            int size = numbers.size();
            if (size != LENGTH) {
                throw new IllegalArgumentException(String.format(WRONG_LENGTH_ERROR_MSG, size));
            }
        }

        public static void validateUniqueNumbers(List<Number> numbers) {
            Set<Number> exists = new HashSet<>();

            for (Number number : numbers) {
                checkDuplicated(exists, number);

                exists.add(number);
            }
        }

        private static void checkDuplicated(Set<Number> exists, Number number) {
            if (exists.contains(number)) {
                throw new IllegalArgumentException(String.format(DUPLICATION_ERROR_MSG, number));
            }
        }
    }
}

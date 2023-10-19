package baseball.domain;

import java.util.*;

public class Baseball {

    public static final int LENGTH = 3;

    private static final String WRONG_LENGTH_ERROR_MSG = "현재 길이 %d: 숫자 야구는 세 개의 숫자로 구성 되어야 합니다.";
    private static final String DUPLICATION_ERROR_MSG = "중복 숫자 %d: 숫자 야구의 숫자들은 서로 달라야 합니다.";

    private final List<Number> numbers;

    private Baseball(List<Number> numbers) {
        checkLength(numbers);
        checkUniqueNumbers(numbers);
        this.numbers = new ArrayList<>(numbers);
    }

    public static Baseball create(List<Number> numbers) {
        return new Baseball(numbers);
    }

    private void checkLength(List<Number> numbers) {
        int size = numbers.size();
        if (size != LENGTH)
            throw new IllegalArgumentException(String.format(WRONG_LENGTH_ERROR_MSG, size));
    }

    private void checkUniqueNumbers(List<Number> numbers) {
        Set<Integer> exists = new HashSet<>();

        for (Number number : numbers) {
            checkDuplicated(exists, number);
        }
    }

    private void checkDuplicated(Set<Integer> exists, Number number) {
        int value = number.getValue();
        if (exists.contains(value)) {
            throw new IllegalArgumentException(String.format(DUPLICATION_ERROR_MSG, value));
        }
        exists.add(value);
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

            result.add(resultType);
        }

        return result;
    }

    private BaseballResultType getMatchResult(Number answerNumber, Number guessNumber) {
        BaseballResultType resultType = BaseballResultType.NOTHING;

        if (answerNumber.equals(guessNumber)) {
            resultType = BaseballResultType.STRIKE;
        } else if (numbers.contains(guessNumber)) {
            resultType = BaseballResultType.BALL;
        }

        return resultType;
    }

    @Override
    public String toString() {
        return "Baseball" + numbers;
    }
}

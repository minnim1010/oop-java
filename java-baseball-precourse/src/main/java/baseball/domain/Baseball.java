package baseball.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Baseball {

    public static final int LENGTH = 3;

    private final List<BaseballNumber> baseballNumbers;

    private Baseball(List<BaseballNumber> baseballNumbers) {
        Validator.validateLength(baseballNumbers);
        Validator.validateUniqueNumbers(baseballNumbers);

        this.baseballNumbers = new ArrayList<>(baseballNumbers);
    }

    public static Baseball create(List<BaseballNumber> baseballNumbers) {
        return new Baseball(baseballNumbers);
    }

    public List<BaseballNumber> getNumbers() {
        return Collections.unmodifiableList(baseballNumbers);
    }

    public BaseballResult match(Baseball guess) {
        List<BaseballResultType> result = new ArrayList<>();

        for (int i = 0; i < LENGTH; i++) {
            BaseballNumber answerNumber = this.baseballNumbers.get(i);
            BaseballNumber guessNumber = guess.getNumbers().get(i);
            addMatchResult(result, answerNumber, guessNumber);
        }

        return BaseballResult.create(result);
    }

    private void addMatchResult(List<BaseballResultType> result, BaseballNumber answerNumber,
                                BaseballNumber guessNumber) {
        if (addIfStrike(result, answerNumber, guessNumber)) {
            return;
        }
        addIfBall(result, guessNumber);
    }

    private boolean addIfStrike(List<BaseballResultType> result, BaseballNumber answerNumber,
                                BaseballNumber guessNumber) {
        if (answerNumber.equals(guessNumber)) {
            result.add(BaseballResultType.STRIKE);
            return true;
        }
        return false;
    }

    private void addIfBall(List<BaseballResultType> result, BaseballNumber guessNumber) {
        if (this.baseballNumbers.contains(guessNumber)) {
            result.add(BaseballResultType.BALL);
        }
    }

    @Override
    public String toString() {
        return "Baseball" + baseballNumbers;
    }

    private static class Validator {

        private static final String WRONG_LENGTH_ERROR_MSG =
                "길이 %d: 숫자 야구의 숫자 길이는 3입니다.";
        private static final String DUPLICATED_ERROR_MSG = "중복 숫자 %s: 숫자 야구의 숫자들은 서로 달라야 합니다.";

        public static void validateLength(List<BaseballNumber> baseballNumbers) {
            int size = baseballNumbers.size();
            if (!isValidSize(size)) {
                throw new IllegalArgumentException(String.format(WRONG_LENGTH_ERROR_MSG, size));
            }
        }

        private static boolean isValidSize(int size) {
            return size == LENGTH;
        }

        public static void validateUniqueNumbers(List<BaseballNumber> baseballNumbers) {
            Set<BaseballNumber> exists = new HashSet<>();

            for (BaseballNumber number : baseballNumbers) {
                checkDuplicateNumber(exists, number);
                exists.add(number);
            }
        }

        private static void checkDuplicateNumber(Set<BaseballNumber> exists,
                                                 BaseballNumber number) {
            if (exists.contains(number)) {
                throw new IllegalArgumentException(String.format(DUPLICATED_ERROR_MSG, number));
            }
        }
    }
}

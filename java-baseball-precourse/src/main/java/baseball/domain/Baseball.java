package baseball.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Baseball {

    public static final int LENGTH = 3;
    public static final String REGEX = "^[1-9]{3}$";

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

    public List<BaseballResultType> match(Baseball guess) {
        List<BaseballResultType> result = new ArrayList<>();

        for (int i = 0; i < LENGTH; i++) {
            BaseballNumber answerBaseballNumber = baseballNumbers.get(i);
            BaseballNumber guessBaseballNumber = guess.getNumbers().get(i);

            BaseballResultType resultType = getMatchResult(answerBaseballNumber,
                guessBaseballNumber);
            if (resultType != null) {
                result.add(resultType);
            }
        }

        return result;
    }

    private BaseballResultType getMatchResult(
        BaseballNumber answerBaseballNumber, BaseballNumber guessBaseballNumber) {
        if (answerBaseballNumber.equals(guessBaseballNumber)) {
            return BaseballResultType.STRIKE;
        }

        if (baseballNumbers.contains(guessBaseballNumber)) {
            return BaseballResultType.BALL;
        }

        return null;
    }

    @Override
    public String toString() {
        return "Baseball" + baseballNumbers;
    }

    private static class Validator {

        private static final String WRONG_LENGTH_ERROR_MSG = "현재 길이 %d: 숫자 야구는 세 개의 숫자로 구성 되어야 합니다.";
        private static final String DUPLICATION_ERROR_MSG = "중복 숫자 %d: 숫자 야구의 숫자들은 서로 달라야 합니다.";

        public static void validateLength(List<BaseballNumber> baseballNumbers) {
            int size = baseballNumbers.size();
            if (size != LENGTH) {
                throw new IllegalArgumentException(String.format(WRONG_LENGTH_ERROR_MSG, size));
            }
        }

        public static void validateUniqueNumbers(List<BaseballNumber> baseballNumbers) {
            Set<BaseballNumber> exists = new HashSet<>();

            for (BaseballNumber baseballNumber : baseballNumbers) {
                checkDuplicated(exists, baseballNumber);

                exists.add(baseballNumber);
            }
        }

        private static void checkDuplicated(Set<BaseballNumber> exists,
            BaseballNumber baseballNumber) {
            if (exists.contains(baseballNumber)) {
                throw new IllegalArgumentException(String.format(DUPLICATION_ERROR_MSG,
                    baseballNumber));
            }
        }
    }
}

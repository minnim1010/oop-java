package baseball.validator;

import baseball.model.Baseball;
import baseball.model.BaseballNumber;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BaseballValidator {

    private static final String WRONG_LENGTH_ERROR_MSG = "숫자 야구의 숫자 길이는 3입니다.";
    private static final String DUPLICATED_ERROR_MSG = "숫자 야구의 숫자들은 서로 달라야 합니다.";

    private BaseballValidator() {
        throw new AssertionError();
    }

    public static void validateLength(List<BaseballNumber> baseballNumbers) {
        if (baseballNumbers.size() != Baseball.LENGTH) {
            throw new IllegalArgumentException(WRONG_LENGTH_ERROR_MSG);
        }
    }

    public static void validateUniqueNumbers(List<BaseballNumber> baseballNumbers) {
        Set<BaseballNumber> uniqueNumbers = new HashSet<>(baseballNumbers);

        if (uniqueNumbers.size() != baseballNumbers.size()) {
            throw new IllegalArgumentException(DUPLICATED_ERROR_MSG);
        }
    }
}

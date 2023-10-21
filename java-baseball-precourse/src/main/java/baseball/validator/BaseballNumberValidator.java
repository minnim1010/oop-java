package baseball.validator;

import baseball.model.BaseballNumber;

public class BaseballNumberValidator {

    private static final String WRONG_RANGE_ERROR_MSG = "각 숫자는 1 이상 9 이하여야 합니다.";

    private BaseballNumberValidator() {
        throw new AssertionError();
    }

    public static void validateRange(int value) {
        if (!isValidRange(value)) {
            throw new IllegalArgumentException(WRONG_RANGE_ERROR_MSG);
        }
    }

    private static boolean isValidRange(int value) {
        return BaseballNumber.MIN <= value && value <= BaseballNumber.MAX;
    }
}

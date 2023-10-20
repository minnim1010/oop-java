package baseball.domain;

public class BaseballNumber {

    public static final int MIN = 1;
    public static final int MAX = 9;

    private final int value;

    public BaseballNumber(int value) {
        Validator.validateRange(value);

        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BaseballNumber baseballNumber = (BaseballNumber) o;

        return value == baseballNumber.value;
    }

    @Override
    public int hashCode() {
        return value;
    }

    @Override
    public String toString() {
        return String.format("%d", value);
    }

    private static class Validator {

        private static final String WRONG_RANGE_ERROR_MSG =
            "각 숫자는 " + MIN + " 이상 " + MAX + " 이하여야 합니다.";

        public static void validateRange(int value) {
            if (!isValidRange(value)) {
                throw new IllegalArgumentException(WRONG_RANGE_ERROR_MSG);
            }
        }

        private static boolean isValidRange(int value) {
            return MIN <= value && value <= MAX;
        }
    }
}

package baseball.model;

public class BaseballNumber {

    public static final int MIN = 1;
    public static final int MAX = 9;

    private final int number;

    private BaseballNumber(int number) {
        Validator.validateRange(number);

        this.number = number;
    }

    public static BaseballNumber create(int value) {
        return new BaseballNumber(value);
    }

    public int getNumber() {
        return number;
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

        return number == baseballNumber.number;
    }

    @Override
    public int hashCode() {
        return number;
    }

    @Override
    public String toString() {
        return String.format("%d", number);
    }

    private static class Validator {

        private static final String WRONG_RANGE_ERROR_MSG =
                "각 숫자는 1 이상 9 이하여야 합니다.";

        public static void validateRange(int value) {
            if (!isValidRange(value)) {
                throw new IllegalArgumentException(WRONG_RANGE_ERROR_MSG);
            }
        }

        private static boolean isValidRange(int value) {
            return (MIN <= value && value <= MAX);
        }
    }
}

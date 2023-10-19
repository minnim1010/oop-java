package baseball.domain;

public class Number {

    public static final int MIN = 1;
    public static final int MAX = 9;

    private static final String WRONG_RANGE_ERROR_MSG =
        "각 숫자는 " + MIN + " 이상 " + MAX + " 이하여야 합니다.";

    private final int value;

    public Number(int value) {
        checkValidRange(value);

        this.value = value;
    }

    private void checkValidRange(int value) {
        if (value < MIN || MAX < value)
            throw new IllegalArgumentException(WRONG_RANGE_ERROR_MSG);
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Number number = (Number) o;

        return value == number.value;
    }

    @Override
    public int hashCode() {
        return value;
    }

    @Override
    public String toString() {
        return String.format("%d", value);
    }
}

package baseball.model;

import baseball.validator.BaseballNumberValidator;

public class BaseballNumber {

    public static final int MIN = 1;
    public static final int MAX = 9;

    private final int number;

    private BaseballNumber(int number) {
        BaseballNumberValidator.validateRange(number);

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
}

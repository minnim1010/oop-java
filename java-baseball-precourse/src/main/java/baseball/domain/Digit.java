package baseball.domain;

import baseball.constants.BaseballGame;

public class Digit {

    private final int value;

    public Digit(int value) {
        checkValidRange(value);
        this.value = value;
    }

    private void checkValidRange(int value){
        if(value < BaseballGame.MIN_DIGIT || BaseballGame.MAX_DIGIT < value)
            throw new IllegalArgumentException("각 숫자는 0 이상 9 이하여야 합니다.");
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Digit digit = (Digit) o;

        return value == digit.value;
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

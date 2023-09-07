package com.example.lotto.validator;

public class CommonValidator {
    private static final String POSITIVE_NUMBER_EXCEPTION = "양수만 입력되어야 합니다.";
    private static final String NULL_EXCEPTION = "NULL일 수 없습니다.";
    private CommonValidator() {
    }

    public static <T> void validateNotNull(T object) {
        if (object == null) {
            throw new IllegalArgumentException(NULL_EXCEPTION);
        }
    }

    public static <T extends Number> void validatePositiveNumber(T number) {
        if (number.doubleValue() <= 0) {
            throw new IllegalArgumentException(
                String.format(POSITIVE_NUMBER_EXCEPTION));
        }
    }
}

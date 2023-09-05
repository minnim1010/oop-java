package com.example.converter;

public class InputTypeConverter {
    public static final String CANNOT_CONVERT_STRING_TO_INT =
        "[Error] 현재 입력값 %s: 입력값은 숫자만 포함해야 합니다.";

    private InputTypeConverter() {
    }

    public static int convertStringToInt(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException(
                String.format(CANNOT_CONVERT_STRING_TO_INT, str));
        }
    }
}

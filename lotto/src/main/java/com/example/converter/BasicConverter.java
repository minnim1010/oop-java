package com.example.converter;

import java.util.Arrays;
import java.util.List;
import java.util.regex.PatternSyntaxException;
import java.util.stream.Collectors;

public class BasicConverter {
    public static final String CANNOT_CONVERT_STRING_TO_INT =
        "현재 입력값 %s: 입력값은 숫자만 포함해야 합니다.";
    public static final String CANNOT_SPLIT_STRING_TO_INTEGER_TOKEN =
        "현재 입력값 %s: 각 숫자들은 ', '로 구분되어야 합니다.";

    private BasicConverter() {
    }

    public static int convertStringToInt(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException(
                String.format(CANNOT_CONVERT_STRING_TO_INT, str), ex);
        }
    }

    public static List<Integer> convertStringToIntegerList(String str, String regex) {
        String[] split = getSplitString(str, regex);

        return Arrays.stream(split)
            .map(BasicConverter::convertStringToInt)
            .collect(Collectors.toList());
    }

    public static String[] getSplitString(String str, String regex) {
        String trimmedString = str.replace(" ", "");
        try {
            return trimmedString.replace(" ", "").split(regex);
        } catch (PatternSyntaxException ex) {
            throw new IllegalArgumentException(
                String.format(CANNOT_SPLIT_STRING_TO_INTEGER_TOKEN, str), ex);
        }
    }
}

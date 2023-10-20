package baseball.validator;

public class InputValidator {

    protected static final String WRONG_INPUT_ERROR_MSG = "올바르지 않은 입력입니다.";

    protected InputValidator() {
    }

    public static void validateNotBlank(String input) {
        if (isBlank(input)) {
            throw new IllegalArgumentException(WRONG_INPUT_ERROR_MSG);
        }
    }

    private static boolean isBlank(String input) {
        return input == null || input.isBlank();
    }
}

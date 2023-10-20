package baseball.validator;

import baseball.domain.Baseball;

public class BaseballGameInputValidator extends InputValidator {

    private static final String RESTART_INPUT_REGEX = "[12]";

    private BaseballGameInputValidator() {
    }

    public static void validateBaseball(String input) {
        if (!input.matches(Baseball.REGEX)) {
            throw new IllegalArgumentException(WRONG_INPUT_ERROR_MSG);
        }
    }

    public static void validateRestartInput(String input) {
        if (!input.matches(RESTART_INPUT_REGEX)) {
            throw new IllegalArgumentException(WRONG_INPUT_ERROR_MSG);
        }
    }
}

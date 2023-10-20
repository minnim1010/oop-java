package baseball.validator;

public class BaseballGameInputValidator extends InputValidator {

    public static final String BASEBALL_REGEX = "^[1-9]{3}$";
    private static final String RESTART_INPUT_REGEX = "[12]";

    private BaseballGameInputValidator() {
    }

    public static void validateBaseball(String input) {
        if (!input.matches(BASEBALL_REGEX)) {
            throw new IllegalArgumentException(WRONG_INPUT_ERROR_MSG);
        }
    }

    public static void validateRestartInput(String input) {
        if (!input.matches(RESTART_INPUT_REGEX)) {
            throw new IllegalArgumentException(WRONG_INPUT_ERROR_MSG);
        }
    }
}

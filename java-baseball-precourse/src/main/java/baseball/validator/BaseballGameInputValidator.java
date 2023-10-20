package baseball.validator;

public class BaseballGameInputValidator {

    private static final String BASEBALL_INPUT_REGEX = "^[1-9]{3}$";
    private static final String RESTART_INPUT_REGEX = "[12]";

    private static final String BASEBALL_INPUT_ERROR_MSG = "1-9에 해당하는 3개 숫자를 입력해주세요.";
    private static final String RESTART_INPUT_ERROR_MSG = "1 또는 2를 입력해주세요.";

    // 인스턴스화 방지
    private BaseballGameInputValidator() {
        throw new AssertionError();
    }

    public static void validateBaseball(String input) {
        if (!input.matches(BASEBALL_INPUT_REGEX)) {
            throw new IllegalArgumentException(BASEBALL_INPUT_ERROR_MSG);
        }
    }

    public static void validateRestartInput(String input) {
        if (!input.matches(RESTART_INPUT_REGEX)) {
            throw new IllegalArgumentException(RESTART_INPUT_ERROR_MSG);
        }
    }
}

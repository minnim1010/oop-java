package baseball.validator;

import java.util.regex.Pattern;

public class BaseballGameInputValidator {

    private static final Pattern BASEBALL_INPUT_PATTERN = Pattern.compile("^[1-9]{3}$");
    private static final Pattern RESTART_INPUT_PATTERN = Pattern.compile("[12]");

    private static final String BASEBALL_INPUT_ERROR_MSG = "1-9에 해당하는 3개 숫자를 입력해주세요.";
    private static final String RESTART_INPUT_ERROR_MSG = "1 또는 2를 입력해주세요.";

    // 인스턴스화 방지
    private BaseballGameInputValidator() {
        throw new AssertionError();
    }

    public static void validateBaseball(String input) {
        if (!BASEBALL_INPUT_PATTERN.matcher(input).matches()) {
            throw new IllegalArgumentException(BASEBALL_INPUT_ERROR_MSG);
        }
    }

    public static void validateRestartInput(String input) {
        if (!RESTART_INPUT_PATTERN.matcher(input).matches()) {
            throw new IllegalArgumentException(RESTART_INPUT_ERROR_MSG);
        }
    }
}

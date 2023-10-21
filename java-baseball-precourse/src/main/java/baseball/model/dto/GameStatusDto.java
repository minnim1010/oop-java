package baseball.model.dto;

import baseball.constants.GameStatus;
import baseball.validator.InputValidator;
import java.util.regex.Pattern;

public record GameStatusDto(String status) {

    public GameStatusDto {
        InputValidator.validateNotBlank(status);
        Validator.validateRestartInput(status);
    }

    public boolean isRestart() {
        return this.status.equals(GameStatus.RESTART.getCode());
    }

    private static class Validator {

        private static final Pattern INPUT_RESTART_PATTERN = Pattern.compile("[12]");
        private static final String INPUT_RESTART_ERROR_MSG = "1 또는 2를 입력해주세요.";

        public static void validateRestartInput(String input) {
            if (!INPUT_RESTART_PATTERN.matcher(input).matches()) {
                throw new IllegalArgumentException(INPUT_RESTART_ERROR_MSG);
            }
        }
    }
}

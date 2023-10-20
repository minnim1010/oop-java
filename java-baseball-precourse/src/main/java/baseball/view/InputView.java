package baseball.view;

import baseball.util.IoUtil;
import baseball.validator.InputValidator;

public class InputView {

    public String input(String message) {
        outputMessageByMode(message, true);

        String input = IoUtil.input();
        InputValidator.validateNotBlank(input);

        return input;
    }

    private void outputMessageByMode(String message, boolean outputLineMode) {
        if (outputLineMode) {
            IoUtil.outputLine(message);
            return;
        }
        IoUtil.output(message);
    }

    public String input(String message, boolean outputLineMode) {
        outputMessageByMode(message, outputLineMode);

        String input = IoUtil.input();
        InputValidator.validateNotBlank(input);

        return input;
    }
}

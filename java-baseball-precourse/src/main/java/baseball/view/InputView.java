package baseball.view;

import baseball.util.IoUtil;
import baseball.validator.InputValidator;

public class InputView {

    private InputView() {
    }

    public static String input(String message) {
        IoUtil.outputLine(message);

        String input = IoUtil.input();
        InputValidator.validateNotBlank(input);

        return input;
    }

    public static String input(String message, boolean outputLineMode) {
        outputMessageByMode(message, outputLineMode);

        String input = IoUtil.input();
        InputValidator.validateNotBlank(input);

        return input;
    }

    private static void outputMessageByMode(String message, boolean outputLineMode) {
        if (outputLineMode) {
            IoUtil.outputLine(message);
            return;
        }
        IoUtil.output(message);
    }
}

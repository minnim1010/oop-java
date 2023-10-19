package baseball.view;

import baseball.constants.Message;
import baseball.domain.Baseball;
import baseball.util.ConvertUtil;
import baseball.util.IoUtil;

public class InputView {

    private static final String RESTART = "1";
    private static final String EXIT = "2";
    private static final String WRONG_INPUT_ERROR_MSG = "올바르지 않은 입력입니다.";

    private InputView() {
    }

    public static Baseball getBaseball() {
        IoUtil.output(Message.INPUT_BASEBALL_NUMBER);
        String input = IoUtil.input();

        return ConvertUtil.toBaseball(input);
    }

    public static boolean askForReplay() {
        IoUtil.outputNewLine(Message.RESTART_GAME);
        String input = IoUtil.input();

        validInput(input);

        return input.equals(RESTART);
    }

    private static void validInput(String input) {
        if (!(input.equals(RESTART) || input.equals(EXIT))) {
            throw new IllegalArgumentException(WRONG_INPUT_ERROR_MSG);
        }
    }
}

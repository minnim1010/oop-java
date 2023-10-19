package baseball.view;

import baseball.constants.Message;
import baseball.domain.BaseballResult;
import baseball.util.IoUtil;

public class OutputView {

    private OutputView() {
    }

    public static void printClearMsg() {
        IoUtil.outputNewLine(Message.CORRECT_ANSWER);
    }

    public static void printResultMsg(BaseballResult baseballResult) {
        IoUtil.outputNewLine(baseballResult.toString());
    }
}

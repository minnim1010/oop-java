package baseball.view;

import baseball.util.IoUtil;

public class OutputView {

    private OutputView() {
    }

    public static void outputLine(String message) {
        IoUtil.outputLine(message);
    }
}

package baseball.controller;

import baseball.constants.Message;
import baseball.domain.Baseball;
import baseball.domain.BaseballResult;
import baseball.service.BaseballService;
import baseball.service.BaseballServiceImpl;
import baseball.util.ConvertUtil;
import baseball.util.IoUtil;

public class BaseballController {

    private final BaseballService service = new BaseballServiceImpl();

    public void run() {
        while (true) {
            Baseball answer = service.createAnswerBaseball();

            while (true) {
                Baseball guess = getBaseball();

                BaseballResult baseballResult = service.calculateResult(answer, guess);
                IoUtil.outputNewLine(baseballResult.toString());

                if (baseballResult.isCorrect()) {
                    break;
                }
            }

            IoUtil.outputNewLine(Message.CORRECT_ANSWER);
            IoUtil.outputNewLine(Message.RESTART_GAME);
            String input = IoUtil.input();
            if (input.equals("2")) {
                break;
            }
        }
    }

    private Baseball getBaseball() {
        IoUtil.output(Message.INPUT_BASEBALL_NUMBER);
        String input = IoUtil.input();

        return ConvertUtil.toBaseball(input);
    }
}

package baseball.controller;

import baseball.constants.Message;
import baseball.domain.Baseball;
import baseball.domain.BaseballResult;
import baseball.service.BaseballService;
import baseball.service.BaseballServiceImpl;
import baseball.util.ConvertUtil;
import baseball.util.IoUtil;

public class BaseballController {

    private static final String RESTART = "1";
    private static final String EXIT = "2";
    private static final String WRONG_INPUT_ERROR_MSG = "올바르지 않은 입력입니다. 1 또는 2를 입력해주세요.";

    private final BaseballService service = new BaseballServiceImpl();

    private static void printClearMsg() {
        IoUtil.outputNewLine(Message.CORRECT_ANSWER);
    }

    private static boolean checkRerun() {
        IoUtil.outputNewLine(Message.RESTART_GAME);
        String input = IoUtil.input();

        if (!(input.equals(RESTART) || input.equals(EXIT))) {
            throw new IllegalArgumentException(WRONG_INPUT_ERROR_MSG);
        }

        return input.equals(RESTART);
    }

    public void run() {
        boolean willRun = true;

        while (willRun) {
            Baseball answer = service.createAnswerBaseball();

            guessAnswer(answer);

            printClearMsg();

            willRun = checkRerun();
        }
    }

    private void guessAnswer(Baseball answer) {
        boolean guessedAnswer = false;

        while (!guessedAnswer) {
            Baseball guess = getBaseball();

            BaseballResult baseballResult = service.calculateResult(answer, guess);
            IoUtil.outputNewLine(baseballResult.toString());

            guessedAnswer = baseballResult.isCorrect();
        }
    }

    private Baseball getBaseball() {
        IoUtil.output(Message.INPUT_BASEBALL_NUMBER);
        String input = IoUtil.input();

        return ConvertUtil.toBaseball(input);
    }
}

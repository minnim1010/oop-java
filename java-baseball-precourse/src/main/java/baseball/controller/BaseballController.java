package baseball.controller;

import baseball.domain.Baseball;
import baseball.domain.BaseballResult;
import baseball.service.BaseballService;
import baseball.service.BaseballServiceImpl;
import baseball.view.InputView;
import baseball.view.OutputView;

public class BaseballController {

    private final BaseballService service = new BaseballServiceImpl();

    public void run() {
        do {
            Baseball answer = service.createAnswerBaseball();

            guessAnswer(answer);

            OutputView.printClearMsg();
        } while (InputView.askForReplay());
    }

    private void guessAnswer(Baseball answer) {
        boolean isAnswer = false;

        while (!isAnswer) {
            Baseball guess = InputView.getBaseball();

            BaseballResult baseballResult = service.calculateResult(answer, guess);
            OutputView.printResultMsg(baseballResult);

            isAnswer = baseballResult.isCorrect();
        }
    }
}

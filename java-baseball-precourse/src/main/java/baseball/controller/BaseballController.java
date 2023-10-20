package baseball.controller;

import baseball.constants.Message;
import baseball.domain.Baseball;
import baseball.domain.BaseballResult;
import baseball.service.BaseballService;
import baseball.service.BaseballServiceImpl;
import baseball.util.ConvertUtil;
import baseball.validator.BaseballGameInputValidator;
import baseball.view.InputView;
import baseball.view.OutputView;

public class BaseballController {

    public static final String RESTART = "1";

    private final BaseballService service = new BaseballServiceImpl();

    public void run() {
        boolean play = true;

        while (play) {
            Baseball answer = getAnswerBaseball();
            playGame(answer);
            play = askForReplay();
        }
    }

    private Baseball getAnswerBaseball() {
        return service.createAnswerBaseball();
    }

    private void playGame(Baseball answer) {
        boolean isClear = false;

        while (!isClear) {
            Baseball guess = getGuessBaseball();
            isClear = checkResult(answer, guess);
        }

        OutputView.outputLine(Message.GAME_CLEAR);
    }

    private Baseball getGuessBaseball() {
        String baseballInput = InputView.input(Message.INPUT_BASEBALL_NUMBER, false);
        BaseballGameInputValidator.validateBaseball(baseballInput);

        return ConvertUtil.toBaseball(baseballInput);
    }

    private boolean checkResult(Baseball answer, Baseball guess) {
        BaseballResult baseballResult = service.calculateResult(answer, guess);
        OutputView.outputLine(baseballResult.toString());

        return baseballResult.isCorrect();
    }

    private boolean askForReplay() {
        String input = InputView.input(Message.RESTART_GAME);
        BaseballGameInputValidator.validateRestartInput(input);

        return input.equals(RESTART);
    }
}

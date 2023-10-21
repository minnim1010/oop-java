package baseball.controller;

import baseball.constants.Message;
import baseball.model.Baseball;
import baseball.model.BaseballResult;
import baseball.model.dto.BaseballDto;
import baseball.model.dto.GameStatusDto;
import baseball.service.BaseballService;
import baseball.view.InputView;
import baseball.view.OutputView;

public class BaseballController {

    private final InputView inputView;
    private final OutputView outputView;
    private final BaseballService baseballService;

    public BaseballController(InputView inputView, OutputView outputView,
                              BaseballService baseballService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.baseballService = baseballService;
    }

    public void run() {
        boolean start = true;

        while (start) {
            Baseball answer = getAnswerBaseball();
            playGame(answer);
            start = askForRestart();
        }
    }

    private Baseball getAnswerBaseball() {
        return baseballService.createAnswerBaseball();
    }

    private void playGame(Baseball answer) {
        boolean isClear = false;

        while (!isClear) {
            Baseball guess = getGuessBaseball();
            isClear = checkResult(answer, guess);
        }

        outputView.outputLine(Message.GAME_CLEAR);
    }

    private Baseball getGuessBaseball() {
        String baseballInput = inputView.input(Message.INPUT_BASEBALL_NUMBER, false);
        BaseballDto baseballDto = new BaseballDto(baseballInput);

        return baseballDto.toBaseball();
    }

    private boolean checkResult(Baseball answer, Baseball guess) {
        BaseballResult baseballResult = baseballService.calculateResult(answer, guess);
        outputView.outputLine(baseballResult.getResultMessage());

        return baseballResult.isCorrect();
    }

    private boolean askForRestart() {
        String gameStatusInput = inputView.input(Message.RESTART_GAME);
        GameStatusDto gameStatusDto = new GameStatusDto(gameStatusInput);

        return gameStatusDto.isRestart();
    }
}

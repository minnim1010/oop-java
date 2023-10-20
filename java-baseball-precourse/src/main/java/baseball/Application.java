package baseball;

import baseball.controller.BaseballController;
import baseball.service.BaseballService;
import baseball.service.BaseballServiceImpl;
import baseball.view.InputView;
import baseball.view.OutputView;

public class Application {

    public static void main(String[] args) {
        BaseballController baseballController = createBaseballController();

        baseballController.run();
    }

    private static BaseballController createBaseballController() {
        final InputView inputView = new InputView();
        final OutputView outputView = new OutputView();
        final BaseballService baseballService = new BaseballServiceImpl();

        return new BaseballController(inputView, outputView, baseballService);
    }
}

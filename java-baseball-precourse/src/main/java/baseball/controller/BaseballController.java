package baseball.controller;

import baseball.constants.Message;
import baseball.domain.BaseballNumber;
import baseball.domain.BaseballResult;
import baseball.domain.BaseballResultType;
import baseball.service.BaseballService;
import baseball.service.BaseballServiceImpl;
import baseball.util.ConvertUtil;
import baseball.util.IoUtil;

public class BaseballController {

    private final BaseballService service = new BaseballServiceImpl();

    public void run() {
        boolean isRunning = true;

        while (isRunning) {
            BaseballNumber answer = service.createRandomBaseballNumber();

            BaseballNumber guess = getBaseballNumber();
            BaseballResult baseballResult = service.calculateResult(answer, guess);

            if (baseballResult.isCorrect()) {
                IoUtil.outputNewLine(Message.CORRECT_ANSWER);
                IoUtil.outputNewLine(Message.RESTART_GAME);
                String input = IoUtil.input();
                if (input.equals("2")) {
                    isRunning = false;
                }
            } else {
                String resultMessage = getResultMsg(baseballResult);
                IoUtil.outputNewLine(resultMessage);
            }
        }
    }

    private BaseballNumber getBaseballNumber() {
        IoUtil.output(Message.INPUT_BASEBALL_NUMBER);
        String input = IoUtil.input();

        return ConvertUtil.toBaseballNumber(input);
    }

    private String getResultMsg(BaseballResult baseballResult) {
        int ballCnt = baseballResult.getResult().get(BaseballResultType.BALL);
        int strikeCnt = baseballResult.getResult().get(BaseballResultType.STRIKE);

        if (ballCnt == 0 && strikeCnt == 0) {
            return Message.NOTHING;
        }

        StringBuilder sb = new StringBuilder();

        if (ballCnt != 0) {
            sb.append(ballCnt)
                .append(Message.BALL);
        }

        if (strikeCnt != 0) {
            sb.append(strikeCnt)
                .append(Message.STRIKE);
        }

        return sb.toString();
    }
}

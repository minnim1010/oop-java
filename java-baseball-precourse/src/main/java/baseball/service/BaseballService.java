package baseball.service;

import baseball.model.Baseball;
import baseball.model.BaseballResult;

public interface BaseballService {

    Baseball createAnswerBaseball();

    BaseballResult calculateResult(Baseball answer, Baseball guess);
}

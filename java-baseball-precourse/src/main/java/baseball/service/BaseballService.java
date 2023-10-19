package baseball.service;

import baseball.domain.Baseball;
import baseball.domain.BaseballResult;

public interface BaseballService {

    Baseball createAnswerBaseball();

    BaseballResult calculateResult(Baseball answer, Baseball guess);
}

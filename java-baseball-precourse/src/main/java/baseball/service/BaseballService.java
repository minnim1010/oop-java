package baseball.service;

import baseball.domain.BaseballNumber;
import baseball.domain.BaseballResult;

public interface BaseballService {

    BaseballNumber createRandomBaseballNumber();

    BaseballResult calculateResult(BaseballNumber answer, BaseballNumber guess);
}

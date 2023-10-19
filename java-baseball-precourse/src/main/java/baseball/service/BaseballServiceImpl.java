package baseball.service;

import baseball.constants.BaseballGame;
import baseball.domain.BaseballNumber;
import baseball.domain.BaseballResult;
import baseball.domain.BaseballResultType;
import baseball.domain.Digit;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.List;
import java.util.stream.Collectors;

public class BaseballServiceImpl implements BaseballService {

    @Override
    public BaseballNumber createRandomBaseballNumber() {
        List<Integer> uniqueNumbers = Randoms.pickUniqueNumbersInRange(
            BaseballGame.MIN_DIGIT, BaseballGame.MAX_DIGIT,
            BaseballGame.BASEBALL_NUMBER_LENGTH);

        List<Digit> uniqueDigits = uniqueNumbers.stream()
            .map(Digit::new)
            .collect(Collectors.toList());

        return BaseballNumber.create(uniqueDigits);
    }

    @Override
    public BaseballResult calculateResult(BaseballNumber answer, BaseballNumber guess) {
        List<BaseballResultType> resultTypeList = answer.match(guess);
        return BaseballResult.create(resultTypeList);
    }
}

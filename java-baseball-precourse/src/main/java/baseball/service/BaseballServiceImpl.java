package baseball.service;

import baseball.constants.BaseballGame;
import baseball.domain.BaseballNumber;
import baseball.domain.BaseballResult;
import baseball.domain.BaseballResultType;
import baseball.domain.Digit;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BaseballServiceImpl implements BaseballService {

    @Override
    public BaseballNumber createRandomBaseballNumber() {
        List<Integer> uniqueNumbers = getUniqueNumbers();
        List<Digit> uniqueDigits = convertToDigits(uniqueNumbers);

        return BaseballNumber.create(uniqueDigits);
    }

    private List<Digit> convertToDigits(List<Integer> uniqueNumbers) {
        return uniqueNumbers.stream()
            .map(Digit::new)
            .collect(Collectors.toList());
    }

    private List<Integer> getUniqueNumbers() {
        List<Integer> uniqueNumbers = new ArrayList<>();

        while (uniqueNumbers.size() < BaseballGame.BASEBALL_NUMBER_LENGTH) {
            int number = Randoms.pickNumberInRange(BaseballGame.MIN_DIGIT, BaseballGame.MAX_DIGIT);
            if (!uniqueNumbers.contains(number)) {
                uniqueNumbers.add(number);
            }
        }

        return uniqueNumbers;
    }

    @Override
    public BaseballResult calculateResult(BaseballNumber answer, BaseballNumber guess) {
        List<BaseballResultType> resultTypeList = answer.match(guess);
        return BaseballResult.create(resultTypeList);
    }
}

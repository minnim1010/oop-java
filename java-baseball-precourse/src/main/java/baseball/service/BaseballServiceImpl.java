package baseball.service;

import baseball.domain.Baseball;
import baseball.domain.BaseballResult;
import baseball.domain.BaseballResultType;
import baseball.domain.Number;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BaseballServiceImpl implements BaseballService {

    @Override
    public Baseball createAnswerBaseball() {
        List<Number> uniqueNumbers = getUniqueNumbers();
        return Baseball.create(uniqueNumbers);
    }

    private List<Number> getUniqueNumbers() {
        Set<Integer> uniqueNumbers = new HashSet<>();

        while (uniqueNumbers.size() < Baseball.LENGTH) {
            int number = Randoms.pickNumberInRange(Number.MIN, Number.MAX);
            uniqueNumbers.add(number);
        }

        return uniqueNumbers.stream()
            .map(Number::new)
            .toList();
    }

    @Override
    public BaseballResult calculateResult(Baseball answer, Baseball guess) {
        List<BaseballResultType> resultTypeList = answer.match(guess);
        return BaseballResult.create(resultTypeList);
    }
}

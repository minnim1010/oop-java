package baseball.service;

import baseball.domain.Baseball;
import baseball.domain.BaseballNumber;
import baseball.domain.BaseballResult;
import baseball.domain.BaseballResultType;
import camp.nextstep.edu.missionutils.Randoms;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BaseballServiceImpl implements BaseballService {

    @Override
    public Baseball createAnswerBaseball() {
        List<BaseballNumber> uniqueBaseballNumbers = getUniqueNumbers();
        return Baseball.create(uniqueBaseballNumbers);
    }

    private List<BaseballNumber> getUniqueNumbers() {
        Set<Integer> uniqueNumbers = new HashSet<>();

        while (uniqueNumbers.size() < Baseball.LENGTH) {
            int number = Randoms.pickNumberInRange(BaseballNumber.MIN, BaseballNumber.MAX);
            uniqueNumbers.add(number);
        }

        return uniqueNumbers.stream()
            .map(BaseballNumber::new)
            .toList();
    }

    @Override
    public BaseballResult calculateResult(Baseball answer, Baseball guess) {
        List<BaseballResultType> resultTypeList = answer.match(guess);
        return BaseballResult.create(resultTypeList);
    }
}

package baseball.model;

import baseball.constants.BaseballResultType;
import baseball.validator.BaseballValidator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Baseball {

    public static final int LENGTH = 3;

    private final List<BaseballNumber> baseballNumbers;

    private Baseball(List<BaseballNumber> baseballNumbers) {
        BaseballValidator.validateLength(baseballNumbers);
        BaseballValidator.validateUniqueNumbers(baseballNumbers);

        this.baseballNumbers = new ArrayList<>(baseballNumbers);
    }

    public static Baseball create(List<BaseballNumber> baseballNumbers) {
        return new Baseball(baseballNumbers);
    }

    public List<BaseballNumber> getNumbers() {
        return Collections.unmodifiableList(baseballNumbers);
    }

    public BaseballResult match(Baseball guess) {
        List<BaseballResultType> result = new ArrayList<>();

        for (int i = 0; i < LENGTH; i++) {
            BaseballNumber answerNumber = this.baseballNumbers.get(i);
            BaseballNumber guessNumber = guess.getNumbers().get(i);
            addMatchResult(result, answerNumber, guessNumber);
        }

        return BaseballResult.create(result);
    }

    private void addMatchResult(List<BaseballResultType> result, BaseballNumber answerNumber,
                                BaseballNumber guessNumber) {
        if (addIfStrike(result, answerNumber, guessNumber)) {
            return;
        }
        addIfBall(result, guessNumber);
    }

    private boolean addIfStrike(List<BaseballResultType> result, BaseballNumber answerNumber,
                                BaseballNumber guessNumber) {
        if (answerNumber.equals(guessNumber)) {
            result.add(BaseballResultType.STRIKE);
            return true;
        }
        return false;
    }

    private void addIfBall(List<BaseballResultType> result, BaseballNumber guessNumber) {
        if (this.baseballNumbers.contains(guessNumber)) {
            result.add(BaseballResultType.BALL);
        }
    }

    @Override
    public String toString() {
        return "Baseball" + baseballNumbers;
    }
}

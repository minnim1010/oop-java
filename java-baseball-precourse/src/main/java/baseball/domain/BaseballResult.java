package baseball.domain;

import baseball.constants.BaseballGame;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class BaseballResult {
    private final EnumMap<BaseballResultType, Integer> result =
        new EnumMap<>(BaseballResultType.class);

    private BaseballResult(List<BaseballResultType> resultTypes) {
        checkLength(resultTypes);

        Arrays.stream(BaseballResultType.values())
            .forEach(type -> result.put(type, 0));

        resultTypes.forEach(this::increase);
    }

    public static BaseballResult create(List<BaseballResultType> resultTypes) {
        return new BaseballResult(resultTypes);
    }

    private void increase(BaseballResultType type) {
        int increasedNum = result.get(type) + 1;
        result.put(type, increasedNum);
    }

    private void checkLength(List<BaseballResultType> resultTypes) {
        int size = resultTypes.size();
        if (size != BaseballGame.BASEBALL_NUMBER_LENGTH) {
            throw new IllegalStateException(
                String.format("현재 결과값 개수 %d: 결과 값은 3개여야 합니다.", size));
        }
    }

    public Map<BaseballResultType, Integer> getResult() {
        return new EnumMap<>(result);
    }

    public boolean isCorrect() {
        int strikeCnt = result.get(BaseballResultType.STRIKE);

        return (strikeCnt == BaseballGame.BASEBALL_NUMBER_LENGTH);
    }
}

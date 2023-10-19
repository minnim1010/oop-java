package baseball.domain;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class BaseballResult {

    private static final String WRONG_LENGTH_ERROR_MSG = "현재 결과값 개수 %d: 결과 값은 3개여야 합니다.";

    private final EnumMap<BaseballResultType, Integer> countByType =
        new EnumMap<>(BaseballResultType.class);

    private BaseballResult(List<BaseballResultType> resultTypes) {
        checkLength(resultTypes);

        Arrays.stream(BaseballResultType.values()).forEach(type -> countByType.put(type, 0));

        resultTypes.forEach(this::increase);
    }

    public static BaseballResult create(List<BaseballResultType> resultTypes) {
        return new BaseballResult(resultTypes);
    }

    private void increase(BaseballResultType type) {
        int increasedNum = countByType.get(type) + 1;
        countByType.put(type, increasedNum);
    }

    private void checkLength(List<BaseballResultType> resultTypes) {
        int size = resultTypes.size();
        if (size != Baseball.LENGTH) {
            throw new IllegalArgumentException(String.format(WRONG_LENGTH_ERROR_MSG, size));
        }
    }

    public Map<BaseballResultType, Integer> getResult() {
        return new EnumMap<>(countByType);
    }

    public boolean isCorrect() {
        int strikeCnt = countByType.get(BaseballResultType.STRIKE);

        return (strikeCnt == Baseball.LENGTH);
    }
}

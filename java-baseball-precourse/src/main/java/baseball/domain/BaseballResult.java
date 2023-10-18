package baseball.domain;

import baseball.constants.BaseballGame;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

public class BaseballResult {
    private final EnumMap<BaseballResultType, Integer> result =
        new EnumMap<>(BaseballResultType.class);

    private BaseballResult(BaseballResultType[] resultTypes) {
        checkLength(resultTypes);

        Arrays.stream(BaseballResultType.values())
            .forEach(type -> result.put(type, 0));

        Arrays.stream(resultTypes)
            .forEach(this::increase);
    }

    private void checkLength(BaseballResultType[] resultTypes){
        if(resultTypes.length != BaseballGame.BASEBALL_NUMBER_LENGTH){
            throw new IllegalStateException(
                String.format("현재 결과값 개수 %d: 결과 값은 3개여야 합니다.", resultTypes.length));
        }
    }

    private void increase(BaseballResultType type) {
        int increasedNum = result.get(type) + 1;
        result.put(type, increasedNum);
    }

    public static BaseballResult create(BaseballResultType[] resultTypes) {
        return new BaseballResult(resultTypes);
    }

    public Map<BaseballResultType, Integer> getResult() {
        return new EnumMap<>(result);
    }
}

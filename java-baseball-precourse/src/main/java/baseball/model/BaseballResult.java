package baseball.model;

import baseball.constants.BaseballResultType;
import baseball.constants.Message;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class BaseballResult {

    private final EnumMap<BaseballResultType, Integer> resultTypeCounts =
            new EnumMap<>(BaseballResultType.class);

    private BaseballResult(List<BaseballResultType> resultTypes) {
        Validator.validateLength(resultTypes);

        Arrays.stream(BaseballResultType.values())
                .forEach(type -> resultTypeCounts.put(type, 0));

        resultTypes.forEach(this::increase);
    }

    public static BaseballResult create(List<BaseballResultType> resultTypes) {
        return new BaseballResult(resultTypes);
    }

    private void increase(BaseballResultType type) {
        int increasedNum = resultTypeCounts.get(type) + 1;
        resultTypeCounts.put(type, increasedNum);
    }

    public Map<BaseballResultType, Integer> getResult() {
        return new EnumMap<>(resultTypeCounts);
    }

    public boolean isCorrect() {
        int strikeCnt = resultTypeCounts.get(BaseballResultType.STRIKE);

        return (strikeCnt == Baseball.LENGTH);
    }

    public String getResultMessage() {
        int ballCnt = resultTypeCounts.get(BaseballResultType.BALL);
        int strikeCnt = resultTypeCounts.get(BaseballResultType.STRIKE);
        if (isNothing(ballCnt, strikeCnt)) {
            return Message.NOTHING;
        }

        return createResultMessage(ballCnt, strikeCnt);
    }

    private boolean isNothing(int ballCnt, int strikeCnt) {
        return (ballCnt == 0 && strikeCnt == 0);
    }

    private String createResultMessage(int ballCnt, int strikeCnt) {
        StringBuilder sb = new StringBuilder();

        appendResult(sb, Message.BALL, ballCnt);
        appendResult(sb, Message.STRIKE, strikeCnt);

        return sb.toString().trim();
    }

    private void appendResult(StringBuilder sb, String label, int count) {
        if (count != 0) {
            sb.append(count)
                    .append(label)
                    .append(" ");
        }
    }

    private static class Validator {

        private static final String WRONG_LENGTH_ERROR_MSG =
                "결과값 %d개: 결과값은 3개 이하여야 합니다.";

        public static void validateLength(List<BaseballResultType> resultTypes) {
            int length = resultTypes.size();
            if (!isValidLength(length)) {
                throw new IllegalArgumentException(String.format(WRONG_LENGTH_ERROR_MSG, length));
            }
        }

        private static boolean isValidLength(int length) {
            return length <= Baseball.LENGTH;
        }
    }
}

package baseball.domain;

import java.util.Arrays;

public enum BaseballResultType {

    NOTHING(false, false),
    BALL(true, false),
    STRIKE(true, true);

    private final boolean numberMatch;
    private final boolean positionMatch;

    BaseballResultType(boolean numberMatch, boolean positionMatch) {
        this.numberMatch = numberMatch;
        this.positionMatch = positionMatch;
    }

    public static BaseballResultType findBy(boolean numberMatch, boolean positionMatch) {
        return Arrays.stream(BaseballResultType.values())
            .filter(type -> numberMatch == type.numberMatch && positionMatch == type.positionMatch)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException(
                String.format("숫자 매칭 여부 %s, 위치 매칭 여부 %s: 해당 결과 타입을 찾을 수 없습니다.",
                    numberMatch, positionMatch)));
    }
}

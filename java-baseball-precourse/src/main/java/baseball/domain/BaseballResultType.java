package baseball.domain;

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
}

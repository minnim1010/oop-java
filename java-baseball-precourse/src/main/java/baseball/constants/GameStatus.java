package baseball.constants;

public enum GameStatus {
    RESTART("1"),
    END("2");

    private final String code;

    GameStatus(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}

package baseball.constants;

public class Message {

    public static final String INPUT_BASEBALL_NUMBER = "숫자를 입력해주세요 : ";
    public static final String GAME_CLEAR = "3개의 숫자를 모두 맞히셨습니다! 게임 종료";
    public static final String RESTART_GAME = "게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.";
    public static final String BALL = "볼";
    public static final String STRIKE = "스트라이크";
    public static final String NOTHING = "낫싱";

    // 인스턴스화 방지
    private Message() {
        throw new AssertionError();
    }
}

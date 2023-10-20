package baseball.util;

import camp.nextstep.edu.missionutils.Console;

public class IoUtil {

    // 인스턴스화 방지
    private IoUtil() {
        throw new AssertionError();
    }

    public static String input() {
        return Console.readLine();
    }

    public static void output(String msg) {
        System.out.print(msg);
    }

    public static void outputLine(String msg) {
        System.out.println(msg);
    }
}

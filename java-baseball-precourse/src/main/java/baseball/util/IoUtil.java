package baseball.util;

import camp.nextstep.edu.missionutils.Console;

public class IoUtil {

    private IoUtil() {
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

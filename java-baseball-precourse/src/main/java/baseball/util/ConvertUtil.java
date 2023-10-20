package baseball.util;

import baseball.domain.Baseball;
import baseball.domain.BaseballNumber;
import java.util.Arrays;
import java.util.List;

public class ConvertUtil {

    private ConvertUtil() {
        throw new AssertionError();
    }

    public static Baseball toBaseball(String baseball) {
        String[] split = baseball.split("");

        List<BaseballNumber> baseballNumbers = Arrays.stream(split)
            .map(Integer::valueOf)
            .map(BaseballNumber::create)
            .toList();

        return Baseball.create(baseballNumbers);
    }
}

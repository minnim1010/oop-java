package baseball.util;

import baseball.domain.Baseball;
import baseball.domain.Number;

import java.util.Arrays;
import java.util.List;

public class ConvertUtil {

    private ConvertUtil() {
    }

    public static Baseball toBaseball(String baseball) {
        String[] split = baseball.split("");

        List<Number> numbers = Arrays.stream(split)
            .map(Integer::valueOf)
            .map(Number::new)
            .toList();

        return Baseball.create(numbers);
    }
}

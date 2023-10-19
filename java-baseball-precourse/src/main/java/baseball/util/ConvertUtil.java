package baseball.util;

import baseball.domain.BaseballNumber;
import baseball.domain.Digit;

import java.util.Arrays;
import java.util.List;

public class ConvertUtil {

    private ConvertUtil() {
    }

    public static BaseballNumber toBaseballNumber(String baseballNumber) {
        String[] split = baseballNumber.split("");

        List<Digit> digits = Arrays.stream(split)
            .map(Integer::valueOf)
            .map(Digit::new)
            .toList();

        return BaseballNumber.create(digits);
    }
}

package baseball.util;

import baseball.constants.BaseballGame;
import baseball.domain.BaseballNumber;
import baseball.domain.Digit;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ConvertUtil {

    private ConvertUtil() {
    }

    public static BaseballNumber toBaseballNumber(String baseballNumber) {
        String[] split = baseballNumber.split("");
        if (split.length != BaseballGame.BASEBALL_NUMBER_LENGTH) {
            throw new IllegalArgumentException("현재 숫자 개수 %s: 세 개 숫자여야 합니다.");
        }

        List<Digit> digits = Arrays.stream(split)
            .map(Integer::valueOf)
            .map(Digit::new)
            .collect(Collectors.toList());

        return BaseballNumber.create(digits);
    }
}

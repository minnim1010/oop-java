package baseball.model.dto;

import baseball.model.Baseball;
import baseball.model.BaseballNumber;
import baseball.validator.InputValidator;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public record BaseballDto(String baseball) {

    public BaseballDto {
        InputValidator.validateNotBlank(baseball);
        Validator.validateBaseball(baseball);
    }

    public Baseball toBaseball() {
        String[] split = this.baseball.split("");

        List<BaseballNumber> baseballNumbers = Arrays.stream(split)
                .map(Integer::valueOf)
                .map(BaseballNumber::create)
                .toList();

        return Baseball.create(baseballNumbers);
    }

    private static class Validator {

        private static final Pattern INPUT_BASEBALL_PATTERN = Pattern.compile("^(?!.*(.).*\\1)[1-9]{3}$");
        private static final String INPUT_BASEBALL_ERROR_MSG = "1 이상 9 이하의 서로 다른 3개 숫자를 입력해주세요.";

        public static void validateBaseball(String input) {
            if (!INPUT_BASEBALL_PATTERN.matcher(input).matches()) {
                throw new IllegalArgumentException(INPUT_BASEBALL_ERROR_MSG);
            }
        }
    }
}

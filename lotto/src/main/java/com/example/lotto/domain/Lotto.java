package com.example.lotto.domain;

import com.example.lotto.service.LottoProvider;

import java.util.List;
import java.util.Optional;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException(
                String.format("[Error] 현재 로또 번호 자릿수 %s: 로또 번호는 6자리입니다.", numbers.size()));
        }

        Optional<Integer> overRangeNum = numbers.stream()
            .filter(num -> num < LottoProvider.LOTTO_MIN_NUMBER || LottoProvider.LOTTO_MAX_NUMBER < num)
            .findFirst();
        if (overRangeNum.isPresent()) {
            throw new IllegalArgumentException(
                String.format(
                    "[Error] 유효하지 않은 로또 번호 %d: 로또 번호는 1부터 45 사이의 숫자여야 합니다."
                    , overRangeNum.get()));
        }
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public boolean contains(int number) {
        return numbers.contains(number);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        numbers.forEach(i -> sb.append(i).append(", "));
        sb.delete(sb.length() - 2, sb.length());
        sb.append("]");

        return sb.toString();
    }
}

package com.example.lotto.model;

import com.example.lotto.validator.LottoValidator;

import java.util.List;
import java.util.Objects;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        LottoValidator.validateLottoSize(numbers);
        LottoValidator.validateLottoNumberRange(numbers);
        LottoValidator.validateNonDuplicateNumbers(numbers);
        LottoValidator.validateAscendingSorted(numbers);
        this.numbers = numbers;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public boolean contains(int number) {
        return numbers.contains(number);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lotto lotto = (Lotto) o;
        return Objects.equals(numbers, lotto.numbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numbers);
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

package com.example.lotto;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LottoProvider {
    public static final int LOTTO_SIZE = 6;
    public static final int LOTTO_MAX_NUMBER = 45;

    Random rand = new Random();

    private int getLottoNumber(){
        return rand.nextInt(LOTTO_MAX_NUMBER+1);
    }

    public List<Integer> getLotto() {
        List<Integer> lotto = new ArrayList<>();

        for (int i=0; i<LOTTO_SIZE; ++i){
            lotto.add(getLottoNumber());
        }

        return lotto;
    }
}

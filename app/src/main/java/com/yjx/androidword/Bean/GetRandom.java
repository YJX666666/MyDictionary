package com.yjx.androidword.Bean;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class GetRandom {

    private static Set<Integer> sSetReturn;
    private static Random sRandom = new Random();
    private static int sI;

    //传入最大值获取4个不同的随机数
    public static Set<Integer> get(int max) {
        sSetReturn = new HashSet<>();

        while (sSetReturn.size() <= 4) {
            sI = sRandom.nextInt(max) % (max);
            sSetReturn.add(sI);
        }

        return sSetReturn;
    }

}
package com.yjx.androidword.Utils;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class RandomUtils {

    //获取三个随机非正确答案的下标
    public static List<Integer> get3Random(Context context, int index) {
        Random random = new Random();
        List<Integer> list = new ArrayList<>();
        Set<Integer> set = new HashSet<>();

        int max = SQLiteUtils.cursorCount(context);
        while (set.size() < 3) {
            // [0,max]
            int i = random.nextInt(max);
            //不能和当前的单词相同
            if (i != index)
                set.add(i);
        }
        Object[] numbers = set.toArray();
        for (Object object : numbers) {
            list.add((Integer) object);
        }
        return list;
    }

}

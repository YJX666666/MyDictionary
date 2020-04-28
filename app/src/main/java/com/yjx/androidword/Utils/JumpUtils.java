package com.yjx.androidword.Utils;

import android.content.Context;
import android.content.Intent;

public class JumpUtils {

    private static Intent sIntent;

    public static void To(Context context, Class<?> cla) {
        sIntent = new Intent(context, cla);
        context.startActivity(sIntent);
    }

}

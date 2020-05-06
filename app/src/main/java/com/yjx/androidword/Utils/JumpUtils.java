package com.yjx.androidword.Utils;

import android.content.Context;
import android.content.Intent;

public class JumpUtils {

    public static void To(Context context, Class<?> cla) {
        Intent sIntent = new Intent(context, cla);
        context.startActivity(sIntent);
    }

}

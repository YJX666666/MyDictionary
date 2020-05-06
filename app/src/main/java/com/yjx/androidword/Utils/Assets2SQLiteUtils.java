package com.yjx.androidword.Utils;

import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 读取Assets目录下数据库的工具类
 * */
public class Assets2SQLiteUtils {

    public static String getPath(Context context,String bookName) {
        //packagename包名
        final String packageBame = "com.yjx.androidword";
        // assets 中的  xxx.db 文件的名字
        final String db_name = bookName;
        final String filePath = "data/data/" + packageBame + "/databases/" + db_name;
        final String pathStr = "data/data/" + packageBame + "/databases";

        System.out.println("filePath:" + filePath);
        File dbFile = new File(filePath);
        if (dbFile.exists()) {
            return filePath;
        } else {
            File path = new File(pathStr);
            path.mkdir();
            try {
                InputStream is = context.getClass().getClassLoader().getResourceAsStream("assets/" + db_name);

                FileOutputStream fos = new FileOutputStream(dbFile);
                byte[] buffer = new byte[10240];
                int count = 0;
                while ((count = is.read(buffer)) > 0) {
                    fos.write(buffer, 0, count);
                }
                fos.flush();
                fos.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
            return filePath;
        }
    }
}


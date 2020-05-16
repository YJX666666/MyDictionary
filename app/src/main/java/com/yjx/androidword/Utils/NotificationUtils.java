package com.yjx.androidword.Utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.view.Gravity;

import com.yjx.androidword.R;

import static android.content.Context.NOTIFICATION_SERVICE;

public class NotificationUtils {

    private static final String ID = "channel_1";
    private static final String NAME = "小词典温馨提示";

    /**
     * 踩坑
     * 通知栏在Android8以后需要创建一个NotificationChannel对象
     * NotificationChannel对象的 id 用来调用NotificationChannel对象
     * 当实例化Notification时，Context后面的参数就是 id
     */
//    public static void show(Context context, String title, String msg, Class<?> cla) {
//        if {
//            NotificationManager manager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
//            PendingIntent pi = PendingIntent.getActivity(context, 0, new Intent(context, cla), 0);
//            if (Build.VERSION.SDK_INT >= 26) {//这里是API26以上的方法  TODO BUG：虚拟机显示，真机不显示
//                NotificationChannel channel = new NotificationChannel(ID, NAME, NotificationManager.IMPORTANCE_HIGH);
//                manager.createNotificationChannel(channel);
//                Notification notification = new Notification.Builder(context, ID)
//                        .setContentTitle(title)
//                        .setContentText(msg)
//                        .setSmallIcon(R.mipmap.img_icon)
//                        .setAutoCancel(true)
//                        .setContentIntent(pi)
//                        .setColor(Color.GREEN)
//                        .build();
//                manager.notify(1, notification);
//            } else {//这里是API26以下
//                Notification notification = new Notification.Builder(context)
//                        .setContentTitle(title)
//                        .setContentText(msg)
//                        .setSmallIcon(R.mipmap.img_icon)
//                        .setFullScreenIntent(pi, true)
//                        .setDefaults(Notification.DEFAULT_ALL)
//                        .setColor(Color.GREEN)
//                        .build();
//                manager.notify(1, notification);
//            }
//        }
//
//    }
    public static void show(Context context, String title, String msg, Class<?> cla) {
        if (Build.VERSION.SDK_INT >= 26) {//这里是API26以上的方法
            ToastUtils.show(context, msg, Gravity.CENTER);
        } else {//这里是API26以下
            NotificationManager manager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
            PendingIntent pi = PendingIntent.getActivity(context, 0, new Intent(context, cla), 0);
            Notification notification = new Notification.Builder(context)
                    .setContentTitle(title)
                    .setContentText(msg)
                    .setSmallIcon(R.mipmap.img_icon)
                    .setFullScreenIntent(pi, true)
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setColor(Color.GREEN)
                    .build();
            manager.notify(1, notification);
        }
    }

}

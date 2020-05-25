package com.yjx.androidword.Utils;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;

import androidx.core.app.NotificationManagerCompat;

import com.yjx.androidword.R;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * 通知栏消息工具类
 * <p>
 * 适配安卓8.0+
 */
public class NotificationUtils {

    private static final String ID = "smallDictionary";
    private static final String NAME = "小词典";

    public static void show(Context context, String title, String msg, Class<?> cla) {

        if (intent(context)) {
            NotificationManager manager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
            PendingIntent pi = PendingIntent.getActivity(context, 0, new Intent(context, cla), 0);
            Notification notification = null;
            if (Build.VERSION.SDK_INT >= 26) {//这里是API26以上的方法
                NotificationChannel channel = new NotificationChannel(ID, NAME, NotificationManager.IMPORTANCE_HIGH);
                manager.createNotificationChannel(channel);
                notification = new Notification.Builder(context, ID)
                        .setContentTitle(title)
                        .setContentText(msg)
                        .setSmallIcon(R.mipmap.img_icon)
                        .setTicker(msg)
                        .setFullScreenIntent(pi, true)
                        .setColor(Color.GREEN)
                        .build();
            } else {//这里是API26以下
                notification = new Notification.Builder(context)
                        .setContentTitle(title)
                        .setContentText(msg)
                        .setTicker(msg)
                        .setSmallIcon(R.mipmap.img_icon)
                        .setFullScreenIntent(pi, true)
                        .setDefaults(Notification.DEFAULT_ALL)
                        .setColor(Color.GREEN)
                        .build();
            }
            manager.notify(1, notification);
        }

    }

    private static boolean intent(Context context) {
        if (!NotificationManagerCompat.from(context).areNotificationsEnabled()) {
            Intent localIntent = new Intent();
            //直接跳转到应用通知设置的代码：
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {//8.0及以上
                localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                localIntent.setData(Uri.fromParts("package", context.getPackageName(), null));
            } else if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0以上到8.0以下
                localIntent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
                localIntent.putExtra("app_package", context.getPackageName());
                localIntent.putExtra("app_uid", context.getApplicationInfo().uid);
            } else {
                //4.4以下没有从app跳转到应用通知设置页面的Action，可考虑跳转到应用详情页面,
                localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                localIntent.setData(Uri.fromParts("package", context.getPackageName(), null));
            }
            context.startActivity(localIntent);
            ToastUtils.show(context, "请打开小词典的通知权限，否则会有很多重要消息漏掉噢！");
            return false;
        }
        return true;
    }

}

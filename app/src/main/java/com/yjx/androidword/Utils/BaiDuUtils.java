package com.yjx.androidword.Utils;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @Time : 2020/5/16 22:44
 * @Author : Android_小黑
 * @File : BaiDuUtils.java
 * @Software : Android Studio
 */
public class BaiDuUtils {
    // 请求地址
    private static final String URL = "https://fanyi-api.baidu.com/api/trans/vip/translate";
    // 应用id
    private static final String APP_ID = "";
    // 密钥
    private static final String KEY = "";
    // 随机数对象
    private static Random sRandom = new Random();

    /**
     * 中英互译（自动检测）
     *
     * @param q 待翻译文本
     */
    public static String translate(String q) {
        // 源语言
        String from = "";
        // 译文语言
        String to = "";
        // 字符长度和字符的字节长度相等，则说明没有中文
        if (q.length() == q.getBytes().length) {
            from = "en";
            to = "zh";
        } else {
            from = "zh";
            to = "en";
        }
        // 翻译结果
        String result = "";
        // 随机数
        String salt = String.valueOf(sRandom.nextInt(100));
        // 合成签名
        String strSign = APP_ID + q + salt + KEY;
        // 将sign串转化成MD5
        String sign = MD5Utils.MD5(strSign);
        // 实例化OkHttpClient对象
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("q", q)
                .add("from", from)
                .add("to", to)
                .add("appid", APP_ID)
                .add("salt", salt)
                .add("sign", sign)
                .build();
        Request request = new Request.Builder()
                .url(URL)
                .post(requestBody)
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            JSONObject jsonObject = JSON.parseObject(response.body().string());
            JSONArray array = jsonObject.getJSONArray("trans_result");
            for (int i = 0; i < array.size(); i++) {
                JSONObject object = (JSONObject) array.get(i);
                result = object.getString("dst");
                return result;
            }
        } catch (IOException e) {
            Log.d("BaiDuUtils", "translate: 失败！");
            return "请求错误";
        }
        return result;
    }

    /**
     * 多语言翻译
     *
     * @param q    待翻译文本
     * @param from 源语言类型
     * @param to   目标语言类型
     */
    public static String translate(String q, String from, String to) {
        // 从Spinner获取过来的是中文，所以需要转化成代码
        from = getLanguage(from);
        to = getLanguage(to);
        // 翻译结果
        String result = "";
        // 随机数
        String salt = String.valueOf(sRandom.nextInt(100));
        // 合成签名
        String strSign = APP_ID + q + salt + KEY;
        // 将sign串转化成MD5
        String sign = MD5Utils.MD5(strSign);
        // 实例化OkHttpClient对象
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("q", q)
                .add("from", from)
                .add("to", to)
                .add("appid", APP_ID)
                .add("salt", salt)
                .add("sign", sign)
                .build();
        Request request = new Request.Builder()
                .url(URL)
                .post(requestBody)
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            JSONObject jsonObject = JSON.parseObject(response.body().string());
            JSONArray array = jsonObject.getJSONArray("trans_result");
            for (int i = 0; i < array.size(); i++) {
                JSONObject object = (JSONObject) array.get(i);
                result = object.getString("dst");
                return result;
            }
        } catch (IOException e) {
            Log.d("BaiDuUtils", "translate: 失败！");
            return "请求错误";
        }
        return result;
    }


    // 通过传入中文，返回对应的语言代码
    private static String getLanguage(String key) {
        Map<String, String> map = new HashMap<>();
        map.put("自动检测", "auto");
        map.put("中文", "zh");
        map.put("英文", "en");
        map.put("粤语", "yue");
        map.put("文言文", "wyw");
        map.put("日语", "jp");
        map.put("韩语", "kor");
        map.put("法语", "fra");
        map.put("西班牙语", "spa");
        map.put("泰语", "th");
        map.put("阿拉伯语", "ara");
        map.put("俄语", "ru");
        map.put("葡萄牙语", "pt");
        map.put("德语", "de");
        map.put("意大利语", "it");
        map.put("希腊语", "el");
        map.put("荷兰语", "nl");
        map.put("波兰语", "pl");
        map.put("保加利亚语", "bul");
        map.put("爱沙尼亚语", "est");
        map.put("丹麦语", "dan");
        map.put("芬兰语", "fin");
        map.put("捷克语", "cs");
        map.put("罗马尼亚语", "rom");
        map.put("斯洛文尼亚语", "slo");
        map.put("瑞典语", "swe");
        map.put("匈牙利语", "hu");
        map.put("繁体中文", "cht");
        map.put("越南语", "vie");
        return map.get(key);
    }

}

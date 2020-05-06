package com.yjx.androidword.Utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class TranslateUtils {

//    {"translation": [{
//        "translated": [{
//            "src-tokenized": [["love"]],
//            "score": -1,
//                    "rank": 0,
//                    "text": "爱"
//        }],
//        "translationId": "1fad6088da18476596c3fe22f2cccfaa"
//    }]}

    private static String text;

    public static String en2zh(String enlish) {
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("from", "en")
                .add("to", "zh")
                .add("app_kid", "5eb178402c769")
                .add("app_key", "")
                .add("text", enlish)
                .build();
        Request request = new Request.Builder()
                .url("http://api.yeekit.com/dotranslate.php")
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                JSONObject jsonObject = JSON.parseObject(response.body().string());
                JSONArray array = jsonObject.getJSONArray("translation");
                JSONObject object = (JSONObject) array.get(0);
                JSONArray array1 = object.getJSONArray("translated");
                for (int j = 0; j < array1.size(); j++) {
                    JSONObject object1 = (JSONObject) array1.get(j);
                    text = object1.getString("text");
                }
            }
        });
        return text;
    }

    /**
     * 译云API
     * 请求方式 POST
     * 中译英接口
     */
    public static String zh2en(String chinese) {
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("from", "zh")
                .add("to", "en")
                .add("app_kid", "5eb178402c769")
                .add("app_key", "")
                .add("text", chinese)
                .build();
        Request request = new Request.Builder()
                .url("http://api.yeekit.com/dotranslate.php")
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                JSONObject jsonObject = JSON.parseObject(response.body().string());
                JSONArray array = jsonObject.getJSONArray("translation");
                JSONObject object = (JSONObject) array.get(0);
                JSONArray array1 = object.getJSONArray("translated");
                for (int j = 0; j < array1.size(); j++) {
                    JSONObject object1 = (JSONObject) array1.get(j);
                    text = object1.getString("text");
                }
            }
        });

        return text;
    }

}

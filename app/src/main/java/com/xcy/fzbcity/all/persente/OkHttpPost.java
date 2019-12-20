package com.xcy.fzbcity.all.persente;

import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpPost {

    public String data;
    public String url;
    public boolean login = false;

    public OkHttpPost(String url, boolean login) {
        this.url = url;
        this.login = login;
    }

    public OkHttpPost(String url) {
        this.url = url;
    }

    public String post(){
        Log.i("mj","走一");
        OkHttpClient okHttpClient = new OkHttpClient();
        Log.i("mj","走二");
        RequestBody requestBody = new FormBody.Builder()
                .add("key", "value")
                .build();
        Log.i("mj","走三");
        final okhttp3.Request request = new okhttp3.Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        Log.i("mj","走四");
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("ghfh", "testHttpPost ... onFailure() e=" + e);
                Log.i("mmm","请求不到数据");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                data = response.body().string();
                Log.i("CXQ","测试data内" + data);

            }
        });


        if (login){
            while (true){
                if (data != null) {
                    return data;
                }
            }
        }else {
            try {
                Thread.sleep(1000);
                if (data != null) {
                    return data;
                }else {
                    data = "{\n" +
                            "  \"code\": \"0\",\n" +
                            "  \"msg\": \"\",\n" +
                            "  \"data\": \"\"\n" +
                            "}";
                    return data;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

    }
}
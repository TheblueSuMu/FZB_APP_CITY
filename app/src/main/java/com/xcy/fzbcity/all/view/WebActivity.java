package com.xcy.fzbcity.all.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.modle.ProjectTalkToolShareBean;
import com.xcy.fzbcity.all.persente.StatusBar;
import com.xcy.fzbcity.all.service.MyService;
import com.xcy.fzbcity.all.utils.CommonUtil;
import com.xcy.fzbcity.all.utils.ToastUtil;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WebActivity extends AllActivity {
    private TextView title;
    private RelativeLayout back;
    private ImageView share;
    private List<String> arraylist;
    private WebView web_webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_activity);
        init_No_Network();
    }

    private void init_No_Network(){
        boolean networkAvailable = CommonUtil.isNetworkAvailable(this);
        if (networkAvailable) {
            init();
        } else {
            RelativeLayout all_no_network = findViewById(R.id.all_no_network);
            Button all_no_reload = findViewById(R.id.all_no_reload);

            all_no_network.setVisibility(View.VISIBLE);
            all_no_reload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                    startActivity(getIntent());
                }
            });
            ToastUtil.showToast(this, "当前无网络，请检查网络后再进行登录");
        }
    }

    private void init(){

        StatusBar.makeStatusBarTransparent(this);

        web_webview = findViewById(R.id.web_webview);

        title = findViewById(R.id.webview_title);
        back = findViewById(R.id.webview_return);
        share = findViewById(R.id.webview_share);

        Intent intent = getIntent();
        final String webUrl = intent.getStringExtra("webUrl");
        final String titleUrl = intent.getStringExtra("title");

        title.setText(titleUrl);

        Log.i("海报","项目海报："+webUrl);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        builder.addConverterFactory(GsonConverterFactory.create());
        Retrofit build = builder.build();
        MyService myService = build.create(MyService.class);
        Observable<ProjectTalkToolShareBean> sellingPoints = myService.getProjectTalkToolShare(FinalContents.getUserID(), FinalContents.getTalkToolId());
        sellingPoints.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ProjectTalkToolShareBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(final ProjectTalkToolShareBean projectTalkToolShareBean) {
                        if (titleUrl.equals("海报详情")) {
                            web_webview.setVisibility(View.VISIBLE);
                            Log.i("网址加载","http://admin.fangzuobiao.com:88/expandingCustomersDetail?userId="+FinalContents.getUserID()+"&talkToolId="+FinalContents.getTalkToolId());
                            WebSettings mWebSettings = web_webview.getSettings();

//                            mWebSettings.setJavaScriptEnabled(true);//是否允许JavaScript脚本运行，默认为false
                            mWebSettings.setDomStorageEnabled(true);//开启本地DOM存储
                            mWebSettings.setJavaScriptCanOpenWindowsAutomatically(true);//设置js可以直接打开窗口，如window.open()，默认为false
                            mWebSettings.setJavaScriptEnabled(true);//是否允许JavaScript脚本运行，默认为false。设置true时，会提醒可能造成XSS漏洞
                            mWebSettings.setSupportZoom(true);//是否可以缩放，默认true
                            mWebSettings.setBuiltInZoomControls(false);//是否显示缩放按钮，默认false
                            mWebSettings.setUseWideViewPort(false);//设置此属性，可任意比例缩放。大视图模式
                            mWebSettings.setLoadWithOverviewMode(true);//和setUseWideViewPort(true)一起解决网页自适应问题
                            mWebSettings.setAppCacheEnabled(true);//是否使用缓存
//                            mWebSettings.setDomStorageEnabled(true);//开启本地DOM存储
                            mWebSettings.setLoadsImagesAutomatically(true); // 加载图片
                            mWebSettings.setMediaPlaybackRequiresUserGesture(false);//播放音频，多媒体需要用户手动？设置为false为可自动播放
                            web_webview.setLongClickable(true);
                            web_webview.loadUrl(FinalContents.getImageUrl()+"/expandingCustomersDetail?userId="+FinalContents.getUserID()+"&talkToolId="+FinalContents.getTalkToolId());

                        } else if (titleUrl.equals("卖点详情")) {
                            web_webview.setVisibility(View.VISIBLE);
                            web_webview.setVisibility(View.VISIBLE);
                            Log.i("网址加载","http://admin.fangzuobiao.com:88/expandingCustomersDetail?userId="+FinalContents.getUserID()+"&talkToolId="+FinalContents.getTalkToolId());
                            WebSettings mWebSettings = web_webview.getSettings();

//                            mWebSettings.setJavaScriptEnabled(true);//是否允许JavaScript脚本运行，默认为false
                            mWebSettings.setDomStorageEnabled(true);//开启本地DOM存储
                            mWebSettings.setJavaScriptCanOpenWindowsAutomatically(true);//设置js可以直接打开窗口，如window.open()，默认为false
                            mWebSettings.setJavaScriptEnabled(true);//是否允许JavaScript脚本运行，默认为false。设置true时，会提醒可能造成XSS漏洞
                            mWebSettings.setSupportZoom(true);//是否可以缩放，默认true
                            mWebSettings.setBuiltInZoomControls(false);//是否显示缩放按钮，默认false
                            mWebSettings.setUseWideViewPort(false);//设置此属性，可任意比例缩放。大视图模式
                            mWebSettings.setLoadWithOverviewMode(true);//和setUseWideViewPort(true)一起解决网页自适应问题
                            mWebSettings.setAppCacheEnabled(true);//是否使用缓存
//                            mWebSettings.setDomStorageEnabled(true);//开启本地DOM存储
                            mWebSettings.setLoadsImagesAutomatically(true); // 加载图片
                            mWebSettings.setMediaPlaybackRequiresUserGesture(false);//播放音频，多媒体需要用户手动？设置为false为可自动播放
                            web_webview.setLongClickable(true);
                            web_webview.loadUrl(FinalContents.getImageUrl()+"/expandingCustomersDetail?userId="+FinalContents.getUserID()+"&talkToolId="+FinalContents.getTalkToolId());
                        }
                        share.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                FinalContents.showShare(projectTalkToolShareBean.getData().getTalkToolInfo().getTitle(),webUrl,titleUrl,FinalContents.getImageUrl()+projectTalkToolShareBean.getData().getTalkToolInfo().getShareIcon(),webUrl,WebActivity.this);
                            }
                        });

                        web_webview.setOnLongClickListener(new View.OnLongClickListener() {
                            @Override
                            public boolean onLongClick(View v) {
                                Log.i("webView网页","长按事件");
                                return true;
                            }
                        });

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("MyCL", "项目卖点错误信息：" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}

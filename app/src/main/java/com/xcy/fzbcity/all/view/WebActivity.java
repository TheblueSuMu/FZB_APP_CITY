package com.xcy.fzbcity.all.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.modle.ProjectTalkToolShareBean;
import com.xcy.fzbcity.all.persente.StatusBar;
import com.xcy.fzbcity.all.service.MyService;
import com.xcy.fzbcity.all.utils.CommonUtil;
import com.xcy.fzbcity.all.utils.Item_Share;
import com.xcy.fzbcity.all.utils.ToastUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
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

    private int num = -1;
    private String imgURl;//图片的URL地址
    private static final int SAVE_SUCCESS = 0;//保存图片成功
    private static final int SAVE_FAILURE = 1;//保存图片失败
    private static final int SAVE_BEGIN = 2;//开始保存图片
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SAVE_BEGIN:
                    ToastUtil.showToast(WebActivity.this, "开始保存图片...");
//                    mSaveBtn.setClickable(false);
                    break;
                case SAVE_SUCCESS:
                    ToastUtil.showToast(WebActivity.this, "图片保存成功,请到相册查找...");
                    num = 1;
//                    mSaveBtn.setClickable(true);
                    break;
                case SAVE_FAILURE:
                    ToastUtil.showToast(WebActivity.this, "图片保存失败,请稍后再试...");
//                    mSaveBtn.setClickable(true);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_activity);
        init_No_Network();
    }

    private void init_No_Network() {
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

    private void init() {

        StatusBar.makeStatusBarTransparent(this);

        web_webview = findViewById(R.id.web_webview);

        title = findViewById(R.id.webview_title);
        back = findViewById(R.id.webview_return);
        share = findViewById(R.id.webview_share);

        Intent intent = getIntent();
        final String webUrl = intent.getStringExtra("webUrl");
        final String titleUrl = intent.getStringExtra("title");

        title.setText(titleUrl);

        Log.i("海报", "项目海报：" + webUrl);

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
                            Log.i("网址加载", "http://admin.fangzuobiao.com:88/expandingCustomersDetail?userId=" + FinalContents.getUserID() + "&talkToolId=" + FinalContents.getTalkToolId());
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
                            web_webview.loadUrl(FinalContents.getImageUrl() + "/expandingCustomersDetail?userId=" + FinalContents.getUserID() + "&talkToolId=" + FinalContents.getTalkToolId());

                        } else if (titleUrl.equals("卖点详情")) {
                            web_webview.setVisibility(View.VISIBLE);
                            web_webview.setVisibility(View.VISIBLE);
                            Log.i("网址加载", "http://admin.fangzuobiao.com:88/expandingCustomersDetail?userId=" + FinalContents.getUserID() + "&talkToolId=" + FinalContents.getTalkToolId());
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
                            web_webview.loadUrl(FinalContents.getImageUrl() + "/expandingCustomersDetail?userId=" + FinalContents.getUserID() + "&talkToolId=" + FinalContents.getTalkToolId());
                        }
                        share.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Item_Share.initDaown(WebActivity.this,projectTalkToolShareBean.getData().getTalkToolInfo().getTitle(), webUrl, titleUrl,
                                        FinalContents.getImageUrl() + projectTalkToolShareBean.getData().getTalkToolInfo().getShareIcon(), webUrl);
                            }
                        });

                        web_webview.setOnLongClickListener(new View.OnLongClickListener() {
                            @Override
                            public boolean onLongClick(View v) {
                                Log.i("webView网页", "长按事件");

                                WebView.HitTestResult result = web_webview.getHitTestResult();
                                if (result != null) {
                                    int type = result.getType();
                                    if (type == WebView.HitTestResult.IMAGE_TYPE || type == WebView.HitTestResult.SRC_IMAGE_ANCHOR_TYPE) {
                                        final String imgurl = result.getExtra();
                                        Log.i("webView网页", "imgurl:" + imgurl);

                                        AlertDialog.Builder builder = new AlertDialog.Builder(WebActivity.this);
                                        View myView = LayoutInflater.from(WebActivity.this).inflate(R.layout.bottom_popwindow, null);
                                        builder.setView(myView);

                                        Button btn_take_photo = myView.findViewById(R.id.btn_take_photo);
                                        Button btn_take_photo_S = myView.findViewById(R.id.btn_take_photo_S);

                                        final AlertDialog dialog = builder.show();
                                        dialog.getWindow().setBackgroundDrawableResource(R.drawable.report_shape_s);
                                        dialog.setCanceledOnTouchOutside(true);
                                        Window window = dialog.getWindow();
                                        window.setGravity(Gravity.BOTTOM);

                                        btn_take_photo.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                new Thread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        imgURl = imgurl;
                                                        Log.i("分割图片", "图片151：" + imgURl);
                                                        mHandler.obtainMessage(SAVE_BEGIN).sendToTarget();
                                                        Bitmap bp = returnBitMap(imgURl);
                                                        Log.i("MyCL", "bp：" + bp);
                                                        saveImageToPhotos(WebActivity.this, bp);
                                                    }
                                                }).start();
                                                dialog.dismiss();
                                            }
                                        });
                                        btn_take_photo_S.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                dialog.dismiss();
                                            }
                                        });

                                    }
                                }



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


    /**
     * 保存二维码到本地相册
     */
    private void saveImageToPhotos(Context context, Bitmap bmp) {
        // 首先保存图片

        Log.i("分割图片", "图片151：" + bmp);

        File appDir = new File(Environment.getExternalStorageDirectory(), "Boohee");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = System.currentTimeMillis() + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 其次把文件插入到系统图库
//        try {
//            MediaStore.Images.Media.insertImage(context.getContentResolver(),
//                    file.getAbsolutePath(), fileName, null);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//            mHandler.obtainMessage(SAVE_FAILURE).sendToTarget();
//            return;
//        }
        // 最后通知图库更新
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri uri = Uri.fromFile(file);
        intent.setData(uri);
        context.sendBroadcast(intent);
        mHandler.obtainMessage(SAVE_SUCCESS).sendToTarget();
    }

    /**
     * 将URL转化成bitmap形式
     *
     * @param url
     * @return bitmap type
     */
    public final static Bitmap returnBitMap(String url) {
        URL myFileUrl;
        Bitmap bitmap = null;
        Log.i("MyCL", "1");
        try {
            myFileUrl = new URL(url);
            Log.i("MyCL", "2");
            HttpURLConnection conn;
            Log.i("MyCL", "3");
            conn = (HttpURLConnection) myFileUrl.openConnection();
            Log.i("MyCL", "4");
            conn.setDoInput(true);
            Log.i("MyCL", "5");
            conn.connect();
            Log.i("MyCL", "6");
            InputStream is = conn.getInputStream();
            Log.i("MyCL", "7");
            bitmap = BitmapFactory.decodeStream(is);
            Log.i("MyCL", "8");
        } catch (MalformedURLException e) {
            Log.i("MyCL", "9");
            e.printStackTrace();
        } catch (IOException e) {
            Log.i("MyCL", "10");
            e.printStackTrace();
        }
        Log.i("MyCL", "11");
        return bitmap;
    }

}

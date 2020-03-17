package com.xcy.fzbcity.all.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.modle.NewsDetailsBean;
import com.xcy.fzbcity.all.persente.StatusBar;
import com.xcy.fzbcity.all.service.MyService;
import com.xcy.fzbcity.all.utils.CommonUtil;
import com.xcy.fzbcity.all.utils.Item_Share;
import com.xcy.fzbcity.all.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WebViewActivity extends AllActivity {
    private WebView webView;
    private TextView title;
    private LinearLayout back;
    private LinearLayout web_f1;
    private TextView layout_title;
    private TextView web_time;
    private TextView web_content;
    private String time = "1";
    private LinearLayout web_bottom;
    private TextView web_more;
    private TextView web_call;
    private LinearLayout web_share;
    private RelativeLayout web_bottom_two;
    private TextView web_more_two;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        init_No_Network();
    }

    private void init_No_Network(){
        boolean networkAvailable = CommonUtil.isNetworkAvailable(this);
        if (networkAvailable) {
            StatusBar.makeStatusBarTransparent(this);

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
        webView = findViewById(R.id.webview);
        web_bottom_two = findViewById(R.id.web_bottom_two);
        web_more_two = findViewById(R.id.web_more_two);
        title = findViewById(R.id.web_title);
        back = findViewById(R.id.web_return);
        web_f1 = findViewById(R.id.web_fl);
        layout_title = findViewById(R.id.web_layout_title);
        web_time = findViewById(R.id.web_time);
        web_content = findViewById(R.id.web_content);
        web_bottom = findViewById(R.id.web_bottom);
        web_more = findViewById(R.id.web_more);
        web_call = findViewById(R.id.web_call);
        web_share = findViewById(R.id.web_share);

        if (FinalContents.getCityID().equals(FinalContents.getOldCityId())) {
            web_bottom.setVisibility(View.VISIBLE);
            web_bottom_two.setVisibility(View.GONE);
        }else {
            web_bottom.setVisibility(View.GONE);
            web_bottom_two.setVisibility(View.VISIBLE);
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Intent intent = getIntent();
        String stringExtra = intent.getStringExtra("title");
        String webviewUrl = intent.getStringExtra("webview");
        time = intent.getStringExtra("time");
        String content = intent.getStringExtra("content");

        title.setText(stringExtra);
        if (time == null) {
            web_share.setVisibility(View.VISIBLE);
            web_f1.setVisibility(View.GONE);
            webView.loadDataWithBaseURL(null, webviewUrl, "text/html", "utf-8", null);
            web_bottom.setVisibility(View.VISIBLE);
            Log.i("轮播图详情数据","新闻："+FinalContents.getNewID());
            Retrofit.Builder builder = new Retrofit.Builder();
            builder.baseUrl(FinalContents.getBaseUrl());
            builder.addConverterFactory(GsonConverterFactory.create());
            builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
            Retrofit build = builder.build();
            MyService fzbInterface = build.create(MyService.class);
//            Log.i("轮播图详情数据","getNewID："+FinalContents.getNewID());
//            Log.i("轮播图详情数据","getCityID："+FinalContents.getCityID());
//            Log.i("轮播图详情数据","getUserID："+FinalContents.getUserID());
            Observable<NewsDetailsBean> userMessage = fzbInterface.getNewsDetails(FinalContents.getCityID(),FinalContents.getUserID(),FinalContents.getNewID());
            userMessage.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<NewsDetailsBean>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @SuppressLint("WrongConstant")
                        @Override
                        public void onNext(final NewsDetailsBean newsDetailsBean) {

                            Log.i("轮播图详情数据","走一波");
                            if (newsDetailsBean.getData().getIsProject() == 1) {
                                web_bottom.setVisibility(View.VISIBLE);
                            }else {
                                web_bottom.setVisibility(View.GONE);
                            }
                            if(newsDetailsBean.getData().getProject().getId().equals("")){
                                web_bottom_two.setVisibility(View.GONE);
                                web_bottom.setVisibility(View.GONE);
                            }else {

                            }
                            web_share.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Log.i("轮播图详情数据","点击分享");
                                    Log.i("轮播图详情数据","图片"+FinalContents.getImageUrl()+newsDetailsBean.getData().getShareImg());
                                    Item_Share.initDaown(WebViewActivity.this,newsDetailsBean.getData().getTitle(),FinalContents.getAdminUrl()+"/NewsSharing?userId="+FinalContents.getUserID()+"&id="+FinalContents.getNewID(),newsDetailsBean.getData().getProject().getProjectName(),FinalContents.getImageUrl()+newsDetailsBean.getData().getShareImg(),FinalContents.getAdminUrl()+"/NewsSharing?userId="+FinalContents.getUserID()+"&id="+FinalContents.getNewID());
                                }
                            });
                            web_call.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    final List<String> list = new ArrayList<>();
                                    for (int i = 0; i < newsDetailsBean.getData().getAttList().size(); i++) {
                                        list.add(newsDetailsBean.getData().getAttList().get(i).getName());
                                    }
                                    //      监听选中
                                    OptionsPickerView pvOptions = new OptionsPickerBuilder(view.getContext(), new OnOptionsSelectListener() {
                                        @Override
                                        public void onOptionsSelect(int options1, int option2, int options3, View v) {
                                            //               返回的分别是三个级别的选中位置
                                            //              展示选中数据
                                            Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + newsDetailsBean.getData().getAttList().get(options1).getPhone()));//跳转到拨号界面，同时传递电话号码
                                            startActivity(dialIntent);
                                        }
                                    })
                                            .setSelectOptions(0)//设置选择第一个
                                            .setOutSideCancelable(false)//点击背的地方不消失
                                            .build();//创建
                                    //      把数据绑定到控件上面
                                    pvOptions.setPicker(list);
                                    //      展示
                                    pvOptions.show();

                                }
                            });


                        }

                        @Override
                        public void onError(Throwable e) {
                            web_bottom_two.setVisibility(View.GONE);
                            web_bottom.setVisibility(View.GONE);
                            Log.i("轮播图详情数据","错误"+e);

                        }

                        @Override
                        public void onComplete() {

                        }
                    });

        }else {
            web_share.setVisibility(View.GONE);
            web_bottom.setVisibility(View.GONE);
            web_f1.setVisibility(View.VISIBLE);
            layout_title.setText(stringExtra);
            web_time.setText(time);
            web_content.setText("       "+content);
        }



        web_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Log.i("详情","项目中ID"+FinalContents.getProjectID());
                Log.i("详情","用户中ID"+FinalContents.getUserID());
                Intent intent1 = new Intent(WebViewActivity.this,ProjectDetails.class);
                startActivity(intent1);
            }
        });

        web_more_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Log.i("详情","项目中ID"+FinalContents.getProjectID());
                Log.i("详情","用户中ID"+FinalContents.getUserID());
                Intent intent1 = new Intent(WebViewActivity.this,ProjectDetails.class);
                startActivity(intent1);
            }
        });
    }

}

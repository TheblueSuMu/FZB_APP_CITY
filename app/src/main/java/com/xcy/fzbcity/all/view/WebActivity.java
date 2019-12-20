package com.xcy.fzbcity.all.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.adapter.WebAdapter;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.modle.ProjectTalkToolShareBean;
import com.xcy.fzbcity.all.persente.StatusBar;
import com.xcy.fzbcity.all.service.MyService;
import com.xcy.fzbcity.all.utils.CommonUtil;
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

public class WebActivity extends AllActivity {
    private TextView title;
    private RelativeLayout back;
    private ImageView share;
    private TextView web_layout_title;
    private TextView web_time;
    private TextView web_content;
    private RecyclerView web_rv;
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

        web_layout_title = findViewById(R.id.web_layout_title);
        web_time = findViewById(R.id.web_time);
        web_content = findViewById(R.id.web_content);
        web_rv = findViewById(R.id.web_rv);
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
                        web_layout_title.setText(projectTalkToolShareBean.getData().getTalkToolInfo().getTitle());
                        web_time.setText(projectTalkToolShareBean.getData().getTalkToolInfo().getCreateDate());
                        if (titleUrl.equals("海报详情")) {
                            web_webview.setVisibility(View.GONE);
                            web_content.setVisibility(View.VISIBLE);
                            web_rv.setVisibility(View.VISIBLE);
                            web_content.setText(projectTalkToolShareBean.getData().getTalkToolInfo().getContent());

                            if (projectTalkToolShareBean.getData().getTalkToolInfo().getImg().equals("")) {

                            }else {
                                arraylist = new ArrayList<>();
                                String[] a  = projectTalkToolShareBean.getData().getTalkToolInfo().getImg().split("[|]");
                                for (int i = 0; i < a.length; i++){
                                    arraylist.add(a[i]);
                                }
                                LinearLayoutManager layoutManager = new LinearLayoutManager(WebActivity.this);
                                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                                web_rv.setLayoutManager(layoutManager);
                                WebAdapter webAdapter = new WebAdapter(arraylist);
                                webAdapter.setImageUrl(projectTalkToolShareBean.getData().getTalkToolInfo().getImg());
                                web_rv.setAdapter(webAdapter);
                                webAdapter.notifyDataSetChanged();
                            }
                        } else if (titleUrl.equals("卖点详情")) {
                            web_webview.setVisibility(View.VISIBLE);
                            web_content.setVisibility(View.GONE);
                            web_rv.setVisibility(View.GONE);

                            web_webview.loadDataWithBaseURL(null, projectTalkToolShareBean.getData().getTalkToolInfo().getContent(), "text/html", "utf-8", null);
                        }


                        share.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                FinalContents.showShare(projectTalkToolShareBean.getData().getTalkToolInfo().getTitle(),webUrl,titleUrl,FinalContents.getImageUrl()+projectTalkToolShareBean.getData().getTalkToolInfo().getShareIcon(),webUrl,WebActivity.this);
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

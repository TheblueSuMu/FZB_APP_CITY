package com.xcy.fzbcity.all.view;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.adapter.HousingSupermarketAdapter;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.api.RedEnvelopesAllTalk;
import com.xcy.fzbcity.all.modle.AppletWechatImageBean;
import com.xcy.fzbcity.all.modle.SupermarketBean;
import com.xcy.fzbcity.all.service.MyService;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.onekeyshare.ShareContentCustomizeCallback;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class CirclOfFriendsAssistantAppletActivity extends AllActivity implements View.OnClickListener {

    private RelativeLayout circle_of_friends_assistant_applet_return;
    private ImageView circle_of_friends_assistant_applet_background;
    private TextView circle_of_friends_assistant_applet_name;
    private ImageView circle_of_friends_assistant_applet_image;
    private Button circle_of_friends_assistant_applet_generalize;
    private ScrollView circle_of_friends_assistant_applet_scrollview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_of_friends_assistant_applet);
        initfvb();
    }

    private void initfvb(){
        circle_of_friends_assistant_applet_return = findViewById(R.id.circle_of_friends_assistant_applet_return);
        circle_of_friends_assistant_applet_background = findViewById(R.id.circle_of_friends_assistant_applet_background);
        circle_of_friends_assistant_applet_name = findViewById(R.id.circle_of_friends_assistant_applet_name);
        circle_of_friends_assistant_applet_image = findViewById(R.id.circle_of_friends_assistant_applet_image);
        circle_of_friends_assistant_applet_generalize = findViewById(R.id.circle_of_friends_assistant_applet_generalize);
        circle_of_friends_assistant_applet_scrollview = findViewById(R.id.circle_of_friends_assistant_applet_scrollview);

        circle_of_friends_assistant_applet_return.setOnClickListener(this);
        circle_of_friends_assistant_applet_generalize.setOnClickListener(this);

        Glide.with(CirclOfFriendsAssistantAppletActivity.this).load(FinalContents.getImageUrl() + RedEnvelopesAllTalk.getAppletImage()).into(circle_of_friends_assistant_applet_background);
        circle_of_friends_assistant_applet_name.setText(RedEnvelopesAllTalk.getUserName()+"  "+RedEnvelopesAllTalk.getUserPhone());
        initData();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.circle_of_friends_assistant_applet_return :
                //      TODO    返回
                finish();
                break;
            case R.id.circle_of_friends_assistant_applet_generalize :
                //      TODO    推广按钮
                scrollViewScreenShot(circle_of_friends_assistant_applet_scrollview);
                Glide.with(CirclOfFriendsAssistantAppletActivity.this).load(scrollViewScreenShot(circle_of_friends_assistant_applet_scrollview)).into(circle_of_friends_assistant_applet_background);
                break;

        }
    }

    private void showShare(String platform) {
        OnekeyShare oneKeyShare = new OnekeyShare();
        //指定分享的平台，如果为空，还是会调用九宫格的平台列表界面
        if (platform != null && oneKeyShare != null) {
            oneKeyShare.setPlatform(platform);
            oneKeyShare.setShareContentCustomizeCallback(new ShareContentCustomizeCallback() {
                @Override
                public void onShare(Platform platform,
                                    cn.sharesdk.framework.Platform.ShareParams paramsToShare) {

                    paramsToShare.setShareType(Platform.SHARE_WXMINIPROGRAM);
                    oneKeyShare.setImageData(scrollViewScreenShot(circle_of_friends_assistant_applet_scrollview));
                }
            });
            oneKeyShare.show(CirclOfFriendsAssistantAppletActivity.this);
        }
    }


    /**
     * 获取scrollview的截屏
     */
    public static Bitmap scrollViewScreenShot(ScrollView scrollView) {
        int h = 0;
        Bitmap bitmap = null;
        for (int i = 0; i < scrollView.getChildCount(); i++) {
            h += scrollView.getChildAt(i).getHeight();
            scrollView.getChildAt(i).setBackgroundColor(Color.parseColor("#ffffff"));
        }
        bitmap = Bitmap.createBitmap(scrollView.getWidth(), h, Bitmap.Config.RGB_565);
        final Canvas canvas = new Canvas(bitmap);
        scrollView.draw(canvas);
        return bitmap;
    }

    //      TODO    推广获客
    private void initData(){
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        final Observable<AppletWechatImageBean> code = fzbInterface.getAppletWechatImage(FinalContents.getUserID());
        code.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AppletWechatImageBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(AppletWechatImageBean appletWechatImageBean) {
                        Glide.with(CirclOfFriendsAssistantAppletActivity.this).load(appletWechatImageBean.getData().getQrFilePath()).into(circle_of_friends_assistant_applet_image);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("推广获客", "推广获客错误信息:" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}

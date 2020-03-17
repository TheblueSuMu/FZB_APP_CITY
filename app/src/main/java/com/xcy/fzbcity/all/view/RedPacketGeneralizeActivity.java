package com.xcy.fzbcity.all.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.api.RedEnvelopesAllTalk;
import com.xcy.fzbcity.all.modle.CheckRedbagPayProjectBean;
import com.xcy.fzbcity.all.modle.RedbagShareBean;
import com.xcy.fzbcity.all.service.MyService;
import com.xcy.fzbcity.all.utils.ToastUtil;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RedPacketGeneralizeActivity extends AllActivity implements View.OnClickListener {

    private ImageView redPacketGeneralizeBackgroundImage;
    private ImageView redPacketGeneralizeWeChatImage;
    private ImageView redPacketGeneralizeCirclOfFriendsImage;
    private ImageView redPacketGeneralizeSaveImage;
    private TextView redPacketGeneralizeWeChatTV;
    private TextView redPacketGeneralizeCirclOfFriendsTV;
    private TextView redPacketGeneralizeSaveTV;
    private RelativeLayout redPacketGeneralizeReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_red_packet_generalize);
        initfvb();
    }

    private void initfvb(){
        redPacketGeneralizeReturn = findViewById(R.id.redPacketGeneralizeReturn);
        redPacketGeneralizeBackgroundImage = findViewById(R.id.redPacketGeneralizeBackgroundImage);
        redPacketGeneralizeWeChatImage = findViewById(R.id.redPacketGeneralizeWeChatImage);
        redPacketGeneralizeCirclOfFriendsImage = findViewById(R.id.redPacketGeneralizeCirclOfFriendsImage);
        redPacketGeneralizeSaveImage = findViewById(R.id.redPacketGeneralizeSaveImage);
        redPacketGeneralizeWeChatTV = findViewById(R.id.redPacketGeneralizeWeChatTV);
        redPacketGeneralizeCirclOfFriendsTV = findViewById(R.id.redPacketGeneralizeCirclOfFriendsTV);
        redPacketGeneralizeSaveTV = findViewById(R.id.redPacketGeneralizeSaveTV);
        initData();

        redPacketGeneralizeReturn.setOnClickListener(this);
        redPacketGeneralizeWeChatImage.setOnClickListener(this);
        redPacketGeneralizeCirclOfFriendsImage.setOnClickListener(this);
        redPacketGeneralizeSaveImage.setOnClickListener(this);
        redPacketGeneralizeWeChatTV.setOnClickListener(this);
        redPacketGeneralizeCirclOfFriendsTV.setOnClickListener(this);
        redPacketGeneralizeSaveTV.setOnClickListener(this);
    }

    //  TODO     红包推广
    private void initData(){
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        final Observable<RedbagShareBean> code = fzbInterface.getRedbagShare(FinalContents.getUserID(),"");
        code.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RedbagShareBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RedbagShareBean redbagShareBean) {
                        Glide.with(RedPacketGeneralizeActivity.this).load(redbagShareBean.getData().getProjectShareImagePaths().get(0)).into(redPacketGeneralizeBackgroundImage);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("红包推广", "红包推广错误信息:" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.redPacketGeneralizeReturn :
                //  TODO     返回
                finish();
                break;
            case R.id.redPacketGeneralizeWeChatImage :
                //  TODO     微信好友分享
                break;
            case R.id.redPacketGeneralizeCirclOfFriendsImage :
                //  TODO     朋友圈分享
                break;
            case R.id.redPacketGeneralizeSaveImage :
                //  TODO     保存图片
                break;
            case R.id.redPacketGeneralizeWeChatTV :
                //  TODO     微信好友分享
                break;
            case R.id.redPacketGeneralizeCirclOfFriendsTV :
                //  TODO     朋友圈分享
                break;
            case R.id.redPacketGeneralizeSaveTV :
                //  TODO     保存图片
                break;

        }
    }
}

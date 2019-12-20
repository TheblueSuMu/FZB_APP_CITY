package com.xcy.fzbcity.all.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.adapter.PhotoTileAdapter;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.modle.PhotoBean;
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

public class PhotoTileActivity extends AllActivity {
    private LinearLayout photo_img;
    private RecyclerView photoTileRv;
    private List<String> array = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_tile);
        init_No_Network();
    }

    private void init_No_Network(){
        boolean networkAvailable = CommonUtil.isNetworkAvailable(this);
        if (networkAvailable) {
            initView();
            initRv();
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


    private void initView() {

        StatusBar.makeStatusBarTransparent(this);
        photo_img = findViewById(R.id.photo_tile_back);
        photoTileRv = findViewById(R.id.photo_tile_rv);

        photo_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @SuppressLint("WrongConstant")
    private void initRv(){
        Log.i("格式","用户名："+FinalContents.getUserID());
        Log.i("格式","项目名："+FinalContents.getProjectID());

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<PhotoBean> clientFragment = fzbInterface.getProjectPhoto(FinalContents.getUserID(), FinalContents.getProjectID());
        clientFragment.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PhotoBean>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PhotoBean photoBean) {
                        for (int i = 0;i < photoBean.getSlideImgList().size();i++){
                            array.add(FinalContents.getImageUrl() + photoBean.getSlideImgList().get(i));
                        }
                        List<PhotoBean.DataBean> list = photoBean.getData();
                        LinearLayoutManager layoutManager = new LinearLayoutManager(PhotoTileActivity.this);
                        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                        photoTileRv.setLayoutManager(layoutManager);
                        PhotoTileAdapter recyclerAdapter = new PhotoTileAdapter(list);
                        recyclerAdapter.setArray(array);
                        photoTileRv.setAdapter(recyclerAdapter);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("MyCL", "相册" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}

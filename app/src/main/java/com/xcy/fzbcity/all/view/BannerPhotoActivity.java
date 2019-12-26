package com.xcy.fzbcity.all.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.fragment.PhotoFragment;
import com.xcy.fzbcity.all.modle.PhotoBean;
import com.xcy.fzbcity.all.persente.StatusBar;
import com.xcy.fzbcity.all.service.MyService;
import com.xcy.fzbcity.all.utils.CommonUtil;
import com.xcy.fzbcity.all.utils.ToastUtil;
import com.xcy.fzbcity.shopping_guide.adapter.BaseFragmentAdapter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BannerPhotoActivity extends AllActivity {

    List<String> list;
    ViewPager viewpager;
    TextView photo_message;
    TabLayout banner_photo_tab_layout;
    RelativeLayout banner_photo_img;
    private List<String> message;
    private List<String> name;
    private List<String> ifname;
    private List<Fragment> mFragments;
    private int index = -1;
    private String[] mTitles = new String[]{};
    private List<PhotoBean.DataBean> data;
    private PhotoBean photoBean1;
    private String url = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner_photo);
        init_No_Network();
    }

    private void init_No_Network(){
        boolean networkAvailable = CommonUtil.isNetworkAvailable(this);
        if (networkAvailable) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            index = getIntent().getIntExtra("index", 0);

            viewpager = findViewById(R.id.viewpager);
            photo_message = findViewById(R.id.photo_message);
            banner_photo_tab_layout = findViewById(R.id.banner_photo_tab_layout);
            banner_photo_img = findViewById(R.id.banner_photo_img);

            initData();
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
            ToastUtil.showToast(this,"当前无网络，请检查网络后再进行登录");
        }
    }

    private void initData() {

        StatusBar.makeStatusBarTransparent(this);

        list = new ArrayList<>();
        message = new ArrayList<>();
        name = new ArrayList<>();
        ifname = new ArrayList<>();
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<PhotoBean> photoBean = fzbInterface.getPhotoBean(FinalContents.getUserID(), FinalContents.getProjectID());
        photoBean.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PhotoBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PhotoBean photoBean) {
                        photoBean1 = photoBean;
                        data = photoBean.getData();
                        for (int i = 0; i < photoBean.getData().size(); ++i) {
                            for (int j = 0; j < photoBean.getData().get(i).getDataList().size(); ++j) {
                                ifname.add(photoBean.getData().get(i).getTypeName());
                                message.add(photoBean.getData().get(i).getDataList().get(j).getRemarks() + "");
                            }
                            name.add(photoBean.getData().get(i).getTypeName());
                        }
                        for (int k = 0;k < photoBean.getSlideImgList().size();k++){
                            list.add(FinalContents.getImageUrl() + photoBean.getSlideImgList().get(k));
                        }
                        initView();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("MyCL", "BannerPhotoActivity错误信息：" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initView() {
        banner_photo_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        for (int j = 0; j < name.size(); ++j) {
            banner_photo_tab_layout.addTab(banner_photo_tab_layout.newTab().setText(name.get(j)));
        }

        mFragments = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            mFragments.add(PhotoFragment.newInstance(list.get(i)));
        }
        photo_message.setText(index + 1 + "/" + mFragments.size() + "       " + message.get(index));
        BaseFragmentAdapter adapter = new BaseFragmentAdapter(getSupportFragmentManager(), mFragments, mTitles);
        viewpager.setAdapter(adapter);
        viewpager.setCurrentItem(index);
        viewpager.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.i("下标","");
                return true;
            }
        });
        viewpager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                int currentPosition = position;
                photo_message.setText(currentPosition + 1 + "/" + mFragments.size() + "       " + message.get(position));

                for (int i = 0; i < name.size(); ++i) {
                    if (name.get(i).equals(ifname.get(position))) {
                        banner_photo_tab_layout.getTabAt(i).select();
                    }
                }
            }
        });

        for (int i = 0; i < name.size(); ++i) {
            if (name.get(i).equals(ifname.get(index))) {
                banner_photo_tab_layout.getTabAt(i).select();
            }
        }



        banner_photo_tab_layout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                for(int i = 0;i < data.size();i++){
                    if (tab.getText().toString().equals(data.get(i).getTypeName())){
                        boolean whever = true;
                        String url = FinalContents.getImageUrl()+data.get(i).getDataList().get(0).getImgPath();
                        for (int k = 0;k < list.size(); k++){
                            if (list.get(k).equals(url)) {
                                if (whever) {
                                    viewpager.setCurrentItem(k);
                                    photo_message.setText(k + 1 + "/" + mFragments.size() + "       " + message.get(k));
                                    whever = false;
                                }
                            }
                        }

                    }
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }


}

package com.xcy.fzbcity.all.view;

import android.annotation.SuppressLint;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.adapter.ImageAdapter;
import com.xcy.fzbcity.all.adapter.MessageCommentAdapter;
import com.xcy.fzbcity.all.api.AndroidBug5497Workaround;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.database.LikeNumBean;
import com.xcy.fzbcity.all.modle.CommentBean;
import com.xcy.fzbcity.all.modle.DynamicDetailsBean;
import com.xcy.fzbcity.all.persente.SingleClick;
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

public class MessageCommentActivity extends AllActivity implements View.OnClickListener {

    RelativeLayout comment_return;
    ImageView comment_buddha;
    TextView comment_title;
    TextView comment_message;
    RecyclerView comment_rv;
    EditText comment_et;
    ImageView comment_zan_img;
    ImageView comment_fb_img;
    ImageView particulars_xiao_img;
    private List<DynamicDetailsBean.DataBean.CommentListBean> commentList;
    private MessageCommentAdapter adapter;
    private String headPortrait;
    private String title;
    private String message;
    private String img;
    private TextView particulars_xiao_pinglun;
    private String isLike;
    int num = 0;
    private TextView comment_time;
    private String time;
    private RecyclerView comment_img_rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_comment);
        AndroidBug5497Workaround.assistActivity(this);
        init_No_Network();
    }


    private void init_No_Network() {
        boolean networkAvailable = CommonUtil.isNetworkAvailable(this);
        if (networkAvailable) {
            initView();
            initET();
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

    private void initET() {
        comment_et.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {

                if (KeyEvent.KEYCODE_ENTER == i && KeyEvent.ACTION_DOWN == keyEvent.getAction()) {
                    Log.i("MyCL", "重复打印");
                    //处理事件
                    if (num == 0) {
                        num = 1;
                        initDataEt();
                    }
                    return true;
                }
                return false;
            }

        });

    }

    private void initView() {
//        TODO 透明状态栏
        StatusBar.makeStatusBarTransparent(this);

        comment_img_rv = findViewById(R.id.comment_img_rv);
        particulars_xiao_pinglun = findViewById(R.id.particulars_xiao_pinglun);
        comment_return = findViewById(R.id.comment_return);
        comment_buddha = findViewById(R.id.comment_buddha);
        comment_time = findViewById(R.id.comment_time);
        comment_title = findViewById(R.id.comment_title);
        comment_message = findViewById(R.id.comment_message);
        comment_rv = findViewById(R.id.comment_rv);
        comment_et = findViewById(R.id.comment_et);
        comment_zan_img = findViewById(R.id.comment_zan_img);
        comment_fb_img = findViewById(R.id.comment_fb_img);
        particulars_xiao_img = findViewById(R.id.particulars_xiao_img);

        Glide.with(this).load(R.mipmap.logo_garden).into(particulars_xiao_img);

        comment_return.setOnClickListener(this);
        comment_zan_img.setOnClickListener(this);
        comment_fb_img.setOnClickListener(this);

        headPortrait = getIntent().getStringExtra("headPortrait");
        title = getIntent().getStringExtra("title");
        time = getIntent().getStringExtra("time");
        message = getIntent().getStringExtra("message");
        img = getIntent().getStringExtra("img");
        isLike = getIntent().getStringExtra("isLike");


        if (img.equals("")) {
            comment_img_rv.setVisibility(View.GONE);
        } else {
            comment_img_rv.setVisibility(View.VISIBLE);
            List<String> arraylist = new ArrayList<>();
            String[] a  = img.split("[|]");
            for (int i = 0; i < a.length; i++){
                arraylist.add(a[i]);
            }
            GridLayoutManager layoutManager = new GridLayoutManager(MessageCommentActivity.this,3);
            layoutManager.setOrientation(GridLayoutManager.VERTICAL);
            comment_img_rv.setLayoutManager(layoutManager);
            ImageAdapter imageAdapter = new ImageAdapter(arraylist);
            imageAdapter.setImageUrl(img);
            comment_img_rv.setAdapter(imageAdapter);
            imageAdapter.notifyDataSetChanged();
        }
//        if (isLike.equals("1")){
//            Glide.with(MessageCommentActivity.this).load(R.mipmap.icon_2).into(comment_zan_img);
//        }else {
//            Glide.with(MessageCommentActivity.this).load(R.mipmap.icon_like).into(comment_zan_img);
//        }

        Glide.with(MessageCommentActivity.this).load(FinalContents.getImageUrl() + headPortrait).into(comment_buddha);
        if (message.equals("")) {
            comment_message.setVisibility(View.GONE);
        } else {
            comment_message.setVisibility(View.VISIBLE);
        }

        comment_title.setText(title);
        comment_time.setText(time);
        comment_message.setText(message);

        initData();

    }

    private void initDataEt() {
        String et = comment_et.getText().toString();
        if (et.equals("")) {
            ToastUtil.showToast(MessageCommentActivity.this, "评论内容不能为空");
            num = 0;
        } else {
            Retrofit.Builder builder = new Retrofit.Builder();
            builder.baseUrl(FinalContents.getBaseUrl());
            builder.addConverterFactory(GsonConverterFactory.create());
            builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
            Retrofit build = builder.build();
            MyService fzbInterface = build.create(MyService.class);
            Observable<CommentBean> dynamicDetailsBean = fzbInterface.getHousesDynamicCommentLike("2", et, FinalContents.getTargetId(), FinalContents.getUserID(), FinalContents.getUserID());
            dynamicDetailsBean.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<CommentBean>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(CommentBean commentBean) {
                            if (commentBean.getData().getStatus().equals("2")) {
                                ToastUtil.showToast(MessageCommentActivity.this, commentBean.getData().getMessage());
                                comment_et.setText("");
                                num = 0;
                            } else {
                                ToastUtil.showToast(MessageCommentActivity.this, "发布失败");
                                comment_et.setText("");
                            }
                            initData();
                            hideInput();
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.i("MyCL", "错误信息：" + e.getMessage());
                        }

                        @Override
                        public void onComplete() {

                        }
                    });

        }
    }

    @SuppressLint("WrongConstant")
    private void initData() {

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<DynamicDetailsBean> dynamicDetailsBean1 = fzbInterface.getDynamicDetailsBean(FinalContents.getTargetId(), FinalContents.getUserID());
        dynamicDetailsBean1.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DynamicDetailsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DynamicDetailsBean dynamicDetailsBean) {
                        LinearLayoutManager manager = new LinearLayoutManager(MessageCommentActivity.this);
                        manager.setOrientation(LinearLayoutManager.VERTICAL);
                        comment_rv.setLayoutManager(manager);

                        commentList = dynamicDetailsBean.getData().getCommentList();
                        particulars_xiao_pinglun.setText("全部" + commentList.size() + "条评论");
                        adapter = new MessageCommentAdapter();
                        adapter.setCommentList(commentList);
                        comment_rv.setAdapter(adapter);

                        if (dynamicDetailsBean.getData().getHousesDynamic().getIsLike().equals("0")) {
                            Glide.with(MessageCommentActivity.this).load(R.mipmap.icon_2).into(comment_zan_img);
                        } else {
                            Glide.with(MessageCommentActivity.this).load(R.mipmap.icon_like).into(comment_zan_img);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("MyCL", "错误信息：" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @SingleClick(1000)
    @Override
    public void onClick(View view) {

        switch (view.getId()) {
//            TODO  返回上一层
            case R.id.comment_return:
                finish();
                break;
//                TODO 点赞
            case R.id.comment_zan_img:
//        TODO 改

                Retrofit.Builder builder = new Retrofit.Builder();
                builder.baseUrl(FinalContents.getBaseUrl());
                builder.addConverterFactory(GsonConverterFactory.create());
                builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
                Retrofit build = builder.build();
                MyService fzbInterface = build.create(MyService.class);
                Observable<LikeNumBean> likeNum = fzbInterface.getLikeNum(FinalContents.getTargetId(), FinalContents.getUserID(), "1");

                likeNum.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<LikeNumBean>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(LikeNumBean likeNumBean) {
                                if (likeNumBean.getData().getMessage().equals("点赞成功")) {
                                    Glide.with(MessageCommentActivity.this).load(R.mipmap.icon_like).into(comment_zan_img);
                                } else {
                                    Glide.with(MessageCommentActivity.this).load(R.mipmap.icon_2).into(comment_zan_img);
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.i("点赞", "楼盘动态点赞错误信息：" + e.getMessage());
                            }

                            @Override
                            public void onComplete() {

                            }
                        });
                break;
//                TODO 复制
            case R.id.comment_fb_img:

                ClipboardManager clip = (ClipboardManager) MessageCommentActivity.this.getSystemService(Context.CLIPBOARD_SERVICE);
                clip.setText(message);
                ToastUtil.showToast(MessageCommentActivity.this, "复制成功");

                break;
        }

    }
}

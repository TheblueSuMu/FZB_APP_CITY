package com.xcy.fzbcity.all.view;

import android.annotation.SuppressLint;
import android.content.ClipboardManager;
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
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xcy.fzbcity.all.persente.MyLinearLayoutManager;
import com.xcy.fzbcity.all.utils.ToastUtil;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.adapter.CommentListAdapter;
import com.xcy.fzbcity.all.adapter.ImageAdapter;
import com.xcy.fzbcity.all.api.AndroidBug5497Workaround;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.modle.CircleBean;
import com.xcy.fzbcity.all.modle.EconomicCircleBean;
import com.xcy.fzbcity.all.modle.TotalZanBean;
import com.xcy.fzbcity.all.persente.SingleClick;
import com.xcy.fzbcity.all.persente.StatusBar;
import com.xcy.fzbcity.all.service.MyService;
import com.xcy.fzbcity.all.utils.CommonUtil;
import com.xcy.fzbcity.all.utils.ToastUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//TODO 经济圈详情
public class EconomicCircleParticularsActivity extends AllActivity implements View.OnClickListener {

    ImageView particulars_buddha;
    RelativeLayout particulars_return;
    ImageView particulars_xiao_img;
    ImageView particulars_zan_img;
    ImageView particulars_fb_img;
    TextView particulars_title;
    TextView particulars_message;
    TextView particulars_time;
    RecyclerView particulars_rv_comment;
    EditText particulars_et_comment;

    CommentListAdapter adapter;
    private List<EconomicCircleBean.DataBean.CommentListBean> commentList;
    private EconomicCircleBean.DataBean.CircleBean circle;

    int[] zan = {R.mipmap.icon_2, R.mipmap.icon_like};
    private String likeNum;
    private String isLike;
    int flag = 0;
    private EconomicCircleBean economicCircleBean;
    private TextView particulars_xiao_size;
    private boolean whehter = true;
    private RecyclerView particulars_img_rv;

    private int num;
    private String url;
    private String imgURl;//图片的URL地址
    private static final int SAVE_SUCCESS = 0;//保存图片成功
    private static final int SAVE_FAILURE = 1;//保存图片失败
    private static final int SAVE_BEGIN = 2;//开始保存图片
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SAVE_BEGIN:
                    ToastUtil.showToast(EconomicCircleParticularsActivity.this, "开始保存图片...");
//                    mSaveBtn.setClickable(false);
                    break;
                case SAVE_SUCCESS:
                    ToastUtil.showToast(EconomicCircleParticularsActivity.this, "图片保存成功,请到相册查找...");
//                    mSaveBtn.setClickable(true);
                    break;
                case SAVE_FAILURE:
                    ToastUtil.showToast(EconomicCircleParticularsActivity.this, "图片保存失败,请稍后再试...");
//                    mSaveBtn.setClickable(true);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_economic_circle_particulars);
        init_No_Network();
    }

    private void init_No_Network() {
        boolean networkAvailable = CommonUtil.isNetworkAvailable(this);
        if (networkAvailable) {
            AndroidBug5497Workaround.assistActivity(this);
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

        StatusBar.makeStatusBarTransparent(this);

        particulars_et_comment.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (KeyEvent.KEYCODE_ENTER == i && KeyEvent.ACTION_DOWN == keyEvent.getAction()) {
                    //处理事件
                    if (flag == 0) {
                        String s = particulars_et_comment.getText().toString();
                        if (s.equals("")) {
                            ToastUtil.showToast(EconomicCircleParticularsActivity.this, "评论不能为空");
                            flag = 0;
                        } else {
                            flag = 1;
                            if (whehter) {
                                Retrofit.Builder builder = new Retrofit.Builder();
                                builder.baseUrl(FinalContents.getBaseUrl());
                                builder.addConverterFactory(GsonConverterFactory.create());
                                builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
                                Retrofit build = builder.build();
                                MyService fzbInterface = build.create(MyService.class);
                                Observable<CircleBean> circleBean = fzbInterface.getCircleBean("2", s, FinalContents.getEconomicCircleID(), FinalContents.getUserID(), FinalContents.getUserID());
                                circleBean.subscribeOn(Schedulers.io())
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribe(new Observer<CircleBean>() {
                                            @Override
                                            public void onSubscribe(Disposable d) {

                                            }

                                            @Override
                                            public void onNext(CircleBean circleBean) {
                                                circleBean.getData().getMessage();
                                                if (circleBean.getMsg().equals("成功")) {
                                                    ToastUtil.showToast(EconomicCircleParticularsActivity.this, circleBean.getData().getMessage());
                                                    particulars_et_comment.setText("");
                                                    initData();
                                                    flag = 0;

                                                } else {
                                                    ToastUtil.showToast(EconomicCircleParticularsActivity.this, "评论失败");
                                                    particulars_et_comment.setText("");
                                                }
                                                initData();
                                                hideInput();
                                            }

                                            @Override
                                            public void onError(Throwable e) {
                                                Log.i("MyCL", "EconomicCircleParticularsActivity错误信息：" + e.getMessage());
                                            }

                                            @Override
                                            public void onComplete() {

                                            }
                                        });
                                whehter = false;
                            }
                        }
                    }
                    return true;
                }
                return false;
            }

        });


    }


    private void initView() {

        particulars_xiao_size = findViewById(R.id.particulars_xiao_size);
        particulars_img_rv = findViewById(R.id.particulars_img_rv);
        particulars_buddha = findViewById(R.id.particulars_buddha);
        particulars_return = findViewById(R.id.particulars_return);
        particulars_xiao_img = findViewById(R.id.particulars_xiao_img);
        particulars_zan_img = findViewById(R.id.particulars_zan_img);
        particulars_fb_img = findViewById(R.id.particulars_fb_img);
        particulars_title = findViewById(R.id.particulars_title);
        particulars_message = findViewById(R.id.particulars_message);
        particulars_time = findViewById(R.id.particulars_time);
        particulars_rv_comment = findViewById(R.id.particulars_rv_comment);
        particulars_et_comment = findViewById(R.id.particulars_et_comment);

        particulars_zan_img.setOnClickListener(this);
        particulars_fb_img.setOnClickListener(this);
        particulars_return.setOnClickListener(this);

        initData();

    }

    private void initData() {
        Log.i("我的经济圈", "用户：" + FinalContents.getUserID());
        Log.i("我的经济圈", "id：" + FinalContents.getEconomicCircleID());

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<EconomicCircleBean> economicCircleBean = fzbInterface.getEconomicCircleBean(FinalContents.getUserID(), FinalContents.getEconomicCircleID());
        economicCircleBean.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<EconomicCircleBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(EconomicCircleBean economicCircleBean) {
                        circle = economicCircleBean.getData().getCircle();
//        TODO 解析完成后的操作

                        String imgUrl = circle.getImgUrl();

                        //        TODO 图片加载
                        if (imgUrl.equals("")) {
                            particulars_img_rv.setVisibility(View.GONE);
                        } else {
                            particulars_img_rv.setVisibility(View.VISIBLE);
                            List<String> arraylist = new ArrayList<>();
                            String[] a = imgUrl.split("[|]");
                            for (int i = 0; i < a.length; i++) {
                                arraylist.add(a[i]);
                            }
                            GridLayoutManager layoutManager = new GridLayoutManager(EconomicCircleParticularsActivity.this, 3);
                            layoutManager.setOrientation(GridLayoutManager.VERTICAL);
                            particulars_img_rv.setLayoutManager(layoutManager);
                            ImageAdapter imageAdapter = new ImageAdapter(arraylist);
                            imageAdapter.setImageUrl(imgUrl);
                            particulars_img_rv.setAdapter(imageAdapter);
                            imageAdapter.notifyDataSetChanged();
                        }

                        FinalContents.setTargetId(circle.getId());
//        TODO 头图像
                        Glide.with(EconomicCircleParticularsActivity.this).load(FinalContents.getImageUrl() + circle.getCreateByPhoto()).into(particulars_buddha);
//        TODO 姓名
                        particulars_title.setText(circle.getCreateByName());
                        particulars_time.setText(circle.getCreateDate());
//        TODO 内容
                        if (circle.getContent().equals("")) {
                            particulars_message.setVisibility(View.GONE);
                        } else {
                            particulars_message.setVisibility(View.VISIBLE);
                        }
                        particulars_message.setText(circle.getContent());
//        TODO 评论记录
                        commentList = economicCircleBean.getData().getCommentList();
                        if (commentList.toString().equals("")) {

                        } else {
                            initLinear();
                        }

                        flag = 0;
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("MyCL", "EconomicCircleParticularsActivity错误信息：" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @SuppressLint("WrongConstant")
    private void initLinear() {
        particulars_xiao_size.setText("全部" + commentList.size() + "条评论");
        MyLinearLayoutManager manager = new MyLinearLayoutManager(EconomicCircleParticularsActivity.this);
        manager.setScrollEnabled(false);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        particulars_rv_comment.setLayoutManager(manager);
//        TODO 适配器
        adapter = new CommentListAdapter();
        particulars_rv_comment.setNestedScrollingEnabled(false);
        adapter.setCommentList(commentList);
        particulars_rv_comment.setAdapter(adapter);
        whehter = true;
    }

    @SingleClick(1000)
    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.particulars_return:
                finish();
                break;
//            TODO 赞
            case R.id.particulars_zan_img:

                Retrofit.Builder builder = new Retrofit.Builder();
                builder.baseUrl(FinalContents.getBaseUrl());
                builder.addConverterFactory(GsonConverterFactory.create());
                builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
                Retrofit build = builder.build();
                MyService fzbInterface = build.create(MyService.class);
                Observable<TotalZanBean> totalLikeNum = fzbInterface.getTotalLikeNum(FinalContents.getTargetId(), FinalContents.getUserID(), "1");
                totalLikeNum.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<TotalZanBean>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(TotalZanBean totalZanBean) {
//                                if (totalZanBean.getData().getStatus().equals("0")) {
//                                    particulars_like.setText(totalZanBean.getData().getLikeNum() + "");
//                                    particulars_zan.setImageResource(zan[1]);
//                                } else {
//                                    particulars_zan.setImageResource(zan[0]);
//                                    particulars_like.setText(totalZanBean.getData().getLikeNum() + "");
//                                }
                                ToastUtil.showLongToast(EconomicCircleParticularsActivity.this, totalZanBean.getData().getMessage());
                                initData();
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.i("经济圈点赞", "点赞错误信息：" + e.getMessage());
                            }

                            @Override
                            public void onComplete() {

                            }
                        });

                break;
//              TODO 评论
            case R.id.particulars_fb_img:

                ClipboardManager clip = (ClipboardManager) EconomicCircleParticularsActivity.this.getSystemService(Context.CLIPBOARD_SERVICE);
                clip.setText(particulars_message.getText().toString() + "");


                if (circle.getImgUrl().equals("")) {

                }else {
                    String UrlImage = circle.getImgUrl();
                    final String[] a = circle.getImgUrl().split("[|]");
                    for (int i = 0; i < a.length; i++) {
                        Log.i("分割图片", "图片：" + a[i]);
                        final int finalI = i;
                        Log.i("分割图片", "图片151：" + imgURl);
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                url = a[finalI];
                                imgURl = FinalContents.getImageUrl() + url;
                                mHandler.obtainMessage(SAVE_BEGIN).sendToTarget();
                                Bitmap bp = returnBitMap(imgURl);
                                Log.i("MyCL", "bp：" + bp);
                                saveImageToPhotos(EconomicCircleParticularsActivity.this, bp);
                            }
                        }).start();
                    }
                }
                ToastUtil.showLongToast(EconomicCircleParticularsActivity.this, "复制成功");
                num = 0;

                break;

        }

    }


    /**
     * 保存二维码到本地相册
     */
    private void saveImageToPhotos(Context context, Bitmap bmp) {
        // 首先保存图片
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

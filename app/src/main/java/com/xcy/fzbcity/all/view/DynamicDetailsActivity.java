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
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.xcy.fzbcity.all.utils.ToastUtil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.adapter.Dynamic2Adapter;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.modle.Dynamic2Bean;
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

import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DynamicDetailsActivity extends AppCompatActivity implements View.OnClickListener, Dynamic2Adapter.PingLun, Dynamic2Adapter.FuZhi, Dynamic2Adapter.LianxiClick {

    TextView dynamic_details_tv;
    RelativeLayout dynamic_details_return_img;

    RecyclerView dynamic_rv;
    private Dynamic2Bean.DataBean dynamicBeanData;
    private List<Dynamic2Bean.DataBean.RowsBean> list;
    private Dynamic2Adapter recyclerAdapter;

    private int j;
    private int num;
    List<String> lists;
    private String imgURl;//图片的URL地址
    private static final int SAVE_SUCCESS = 0;//保存图片成功
    private static final int SAVE_FAILURE = 1;//保存图片失败
    private static final int SAVE_BEGIN = 2;//开始保存图片
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SAVE_BEGIN:
                    ToastUtil.showToast(DynamicDetailsActivity.this, "开始保存图片...");
//                    mSaveBtn.setClickable(false);
                    break;
                case SAVE_SUCCESS:
                    ToastUtil.showToast(DynamicDetailsActivity.this, "图片保存成功,请到相册查找...");
//                    mSaveBtn.setClickable(true);
                    break;
                case SAVE_FAILURE:
                    ToastUtil.showToast(DynamicDetailsActivity.this, "图片保存失败,请稍后再试...");
//                    mSaveBtn.setClickable(true);
                    break;
            }
        }
    };
    private String url;
    private ImageView all_no_information;
    private String dynamicName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_details);
        init_No_Network();
    }

    private void init_No_Network() {
        StatusBar.makeStatusBarTransparent(this);
        boolean networkAvailable = CommonUtil.isNetworkAvailable(this);
        if (networkAvailable) {
            ButterKnife.bind(this);

            initView();
        } else {
            RelativeLayout all_no_network = findViewById(R.id.dynamic_details_no_network);
            Button all_no_reload = findViewById(R.id.dynamic_details_reload);

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

    private void initView() {
        StatusBar.makeStatusBarTransparent(this);
        dynamic_details_tv = findViewById(R.id.dynamic_details_tv);
        dynamic_details_return_img = findViewById(R.id.dynamic_details_return_img);
        dynamic_rv = findViewById(R.id.dynamic_details_rv);
        all_no_information = findViewById(R.id.dynamic_details_information_S);

        dynamicName = getIntent().getStringExtra("DynamicName");
        dynamic_details_tv.setText(dynamicName);

        dynamic_details_return_img.setOnClickListener(this);

        initData();
    }

    @SuppressLint("WrongConstant")
    private void initData() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        dynamic_rv.setLayoutManager(layoutManager);
        recyclerAdapter = new Dynamic2Adapter();
        recyclerAdapter.setDynamicClick(this);
        recyclerAdapter.setPingLun(this);
        recyclerAdapter.setFuZhi(this);

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<Dynamic2Bean> dynamicBean1 = fzbInterface.getDynamicBean(FinalContents.getUserID(), "", "1000");
        dynamicBean1.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Dynamic2Bean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Dynamic2Bean dynamicBean) {
                        dynamicBeanData = dynamicBean.getData();
                        list = dynamicBeanData.getRows();
                        if (list.size() != 0) {
                            all_no_information.setVisibility(View.GONE);
                            dynamic_rv.setVisibility(View.VISIBLE);
                            FinalContents.setTargetId(FinalContents.getProjectID());
//  TODO 图片查看
                            recyclerAdapter.setTuPian(new Dynamic2Adapter.TuPian() {
                                @Override
                                public void tp(int position) {
                                    Intent intent = new Intent(DynamicDetailsActivity.this, BigPhotoActivity.class);
                                    intent.putExtra("index", position);
                                    intent.putExtra("bigPhotoimg", list.get(position).getImg());
                                    startActivity(intent);
                                }
                            });
                            recyclerAdapter.setList(list);
                            recyclerAdapter.setBuiling(1);
                            dynamic_rv.setAdapter(recyclerAdapter);
                            recyclerAdapter.notifyDataSetChanged();
                        } else {
                            all_no_information.setVisibility(View.VISIBLE);
                            dynamic_rv.setVisibility(View.GONE);
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        all_no_information.setVisibility(View.VISIBLE);
                        dynamic_rv.setVisibility(View.GONE);
                        Log.i("MyCL", "BuildingDynamicActivity错误信息：" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @Override
    public void pingClick(int position) {
        FinalContents.setTargetId(list.get(position).getId());
        Intent intent = new Intent(DynamicDetailsActivity.this, MessageCommentActivity.class);
        intent.putExtra("headPortrait", list.get(position).getCreateBy().getPhoto());
        intent.putExtra("title", list.get(position).getCreateBy().getName());
        intent.putExtra("message", list.get(position).getContent());
        intent.putExtra("img", list.get(position).getImgUrl());
        startActivity(intent);
    }

    @Override
    public void FuZhi(int position) {
        if (list.get(position).getImgUrl().equals("")) {

        } else {
            lists = new ArrayList<>();
            //        TODO 文本复制
            ClipboardManager clip = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            clip.setText(list.get(position).getContent() + "");
            //        String imagename = list.get(position).getContent();
            //        TODO 图片保存到本地

            String UrlImage = list.get(position).getImgUrl();
            final String[] a = list.get(position).getImgUrl().split("[|]");
            for (int i = 0; i < a.length; i++) {
                final int finalI = i;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        url = a[finalI];
                        imgURl = FinalContents.getImageUrl() + url;
                        mHandler.obtainMessage(SAVE_BEGIN).sendToTarget();
                        Bitmap bp = returnBitMap(imgURl);
                        saveImageToPhotos(DynamicDetailsActivity.this, bp);
                    }
                }).start();
            }
            ToastUtil.showToast(this,"复制成功");
            num = 0;

        }
    }

    @Override
    public void Click(final int position) {

        String phone = list.get(position).getAttaches().get(0).getPhone();
        if (phone.equals("")) {
            ToastUtil.showToast(DynamicDetailsActivity.this,"暂无电话信息，无法拨打");
        } else {
            final List<String> arrayList = new ArrayList<>();
            for (int i = 0; i < list.get(position).getAttaches().size(); i++) {
                arrayList.add(list.get(position).getAttaches().get(i).getName());
            }
            //      监听选中
            OptionsPickerView pvOptions = new OptionsPickerBuilder(DynamicDetailsActivity.this, new OnOptionsSelectListener() {
                @Override
                public void onOptionsSelect(int options1, int option2, int options3, View v) {
                    //               返回的分别是三个级别的选中位置
                    //              展示选中数据
                    Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + list.get(position).getAttaches().get(options1).getPhone()));//跳转到拨号界面，同时传递电话号码
                    startActivity(dialIntent);
                }
            })
                    .setSelectOptions(0)//设置选择第一个
                    .setOutSideCancelable(false)//点击背的地方不消失
                    .build();//创建
            //      把数据绑定到控件上面
            pvOptions.setPicker(arrayList);
            //      展示
            pvOptions.show();
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
        try {
            myFileUrl = new URL(url);
            HttpURLConnection conn;
            conn = (HttpURLConnection) myFileUrl.openConnection();
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    @SingleClick(1000)
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.dynamic_details_return_img:
                finish();
                break;
        }

    }
}

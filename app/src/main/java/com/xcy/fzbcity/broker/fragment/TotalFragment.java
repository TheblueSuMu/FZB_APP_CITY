package com.xcy.fzbcity.broker.fragment;

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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.xcy.fzbcity.all.utils.ToastUtil;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.api.Connector;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.fragment.AllFragment;
import com.xcy.fzbcity.all.modle.TotalBean;
import com.xcy.fzbcity.all.service.MyService;
import com.xcy.fzbcity.all.view.EconomicCircleParticularsActivity;
import com.xcy.fzbcity.broker.adapter.TotalAdapter;

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

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//TODO 经济圈全部
public class TotalFragment extends AllFragment implements TotalAdapter.EPinLun, TotalAdapter.EFuZi{


    private String imgURl;//图片的URL地址
    private static final int SAVE_SUCCESS = 0;//保存图片成功
    private static final int SAVE_FAILURE = 1;//保存图片失败
    private static final int SAVE_BEGIN = 2;//开始保存图片
    int countNum1 = 0;
    int countNum2 = 0;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SAVE_BEGIN:
                    if(countNum1 == 0){
                        ToastUtil.showToast(getContext(), "开始保存图片...");
                    }
                    countNum1++;
                    if(countNum1 == a.length){
                        countNum1 = 0;
                    }
//                    mSaveBtn.setClickable(false);
                    break;
                case SAVE_SUCCESS:
                    if(countNum2 == 0){
                        ToastUtil.showToast(getContext(), "图片保存成功,请到相册查找...");
                    }
                    countNum2++;
                    if(countNum2 == a.length){
                        countNum2 = 0;
                    }
//                    mSaveBtn.setClickable(true);
                    break;
                case SAVE_FAILURE:
                    ToastUtil.showToast(getContext(), "图片保存失败,请稍后再试...");
//                    mSaveBtn.setClickable(true);
                    break;
            }
        }
    };

    private View view;
    private RecyclerView totalRv;
//    EditText textView;

    private String economicUrl = "http://39.98.173.250:8080/fangfang/app/v1/ordinarySelect/economicCircleList?";
    Bitmap bitmap;
    private TotalAdapter recyclerAdapter1;
    private List<TotalBean.DataBean.RowsBean> hotlist;
    private int j;
    private int num;
    List<String> list;
    private String url;
    private ImageView all_no_information;
    private PtrClassicFrameLayout total_ptrclass;
    private String[] a;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.broker_fragment_total, container, false);
        initfvb();
        return view;
    }

    private void initfvb() {
        totalRv = view.findViewById(R.id.total_rv);
        total_ptrclass = view.findViewById(R.id.total_ptrclass);
        all_no_information = view.findViewById(R.id.all_no_information);
        total_ptrclass.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        total_ptrclass.refreshComplete();
                        total_ptrclass.setLastUpdateTimeKey("2017-2-10");
                        initView();
                    }
                }, 1000);
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }
        });
        initView();
    }

    @SuppressLint("WrongConstant")
    private void initView() {
        recyclerAdapter1 = new TotalAdapter();

        recyclerAdapter1.setePinLun(this);
        recyclerAdapter1.seteFuZi(this);

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<TotalBean> tBean = fzbInterface.getTBean(FinalContents.getUserID(), FinalContents.getCityID(), "1000");
        tBean.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TotalBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TotalBean totalBean) {
                        TotalBean.DataBean hotBeanData = totalBean.getData();
                        hotlist = hotBeanData.getRows();
                        if (hotlist.size() != 0) {
                            all_no_information.setVisibility(View.GONE);
                            totalRv.setVisibility(View.VISIBLE);
                            LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
                            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                            totalRv.setLayoutManager(layoutManager);
                            recyclerAdapter1.setList(hotlist);
                            totalRv.setAdapter(recyclerAdapter1);
                            recyclerAdapter1.notifyDataSetChanged();
                        }else {
                            all_no_information.setVisibility(View.VISIBLE);
                            totalRv.setVisibility(View.GONE);
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        all_no_information.setVisibility(View.VISIBLE);
                        totalRv.setVisibility(View.GONE);
                        Log.i("MyCL", "TotalFragment错误信息：" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    //TODO 评论
    @Override
    public void pl(int position) {
        FinalContents.setEconomicCircleID(hotlist.get(position).getId());
        Log.i("MyCL", "ID：" + hotlist.get(position).getId());
        Intent intent = new Intent(getContext(), EconomicCircleParticularsActivity.class);
        startActivity(intent);
    }

    @Override
    public void fz(int position) {
        list = new ArrayList<>();

        //        TODO 文本复制
        ClipboardManager clip = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
        clip.setText(hotlist.get(position).getContent() + "");
        if (hotlist.get(position).getImgUrl().equals("")) {

        }else {
            String UrlImage = hotlist.get(position).getImgUrl();
            a = hotlist.get(position).getImgUrl().split("[|]");
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
                        saveImageToPhotos(getContext(), bp);
                    }
                }).start();
            }
        }
        ToastUtil.showToast(getContext(), "复制成功");
        num = 0;
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

    @Override
    public void onResume() {
        super.onResume();
        if (Connector.isJJQ()) {
            initView();
            Connector.setJJQ(false);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        FinalContents.setEconomicCircleID("");
    }
}

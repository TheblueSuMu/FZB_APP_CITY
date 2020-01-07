package com.xcy.fzbcity.all.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.core.content.FileProvider;

import com.bumptech.glide.Glide;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.api.APKVersionCodeUtils;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.database.AppPackageBean;
import com.xcy.fzbcity.all.persente.SingleClick;
import com.xcy.fzbcity.all.persente.StatusBar;
import com.xcy.fzbcity.all.service.MyService;
import com.xcy.fzbcity.all.utils.CommonUtil;
import com.xcy.fzbcity.all.utils.ToastUtil;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AboutFZBActivity extends AllActivity implements View.OnClickListener {

    private RelativeLayout fzb_return;
    private ImageView fzb_fx;
    private RelativeLayout fzb_jc;
    private RelativeLayout fzb_mz;
    private Intent intent;
    private String panduan = "";

    TextView About_Version_NumBer;

    /**
     * 版本下载数据
     */
    //  上下文
//    private Context mContext;
    //  进度条
    private ProgressBar mProgressBar;
    //  对话框
    private Dialog mDownloadDialog;
    //  判断是否停止
    private boolean mIsCancel = false;
    //  进度
    private int mProgress;
    //  文件保存路径
    private String mSavePath;
    //  版本名称
    private String mVersion_name = FinalContents.getVersionNumBer();
    //  请求链接
    private String url = "http://test.fangzuobiao.com:88/fangfang/static/down/fangzuobiao.apk";
    private ImageView fzb_img;

    //https://download.dgstaticresources.net/fusion/android/app-c6-release.apk
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_fzb);
        init_No_Network();
    }

    private void init_No_Network() {
        boolean networkAvailable = CommonUtil.isNetworkAvailable(this);
        if (networkAvailable) {
            initView();
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

    private void initView() {

        StatusBar.makeStatusBarTransparent(this);

        fzb_return = findViewById(R.id.fzb_return);
        fzb_fx = findViewById(R.id.fzb_fx);
        fzb_jc = findViewById(R.id.fzb_jc);
        fzb_mz = findViewById(R.id.fzb_mz);
        fzb_img = findViewById(R.id.fzb_img);
        About_Version_NumBer = findViewById(R.id.About_Version_NumBer);

        About_Version_NumBer.setText("当前版本" + FinalContents.getVersionNumBer());

        Glide.with(AboutFZBActivity.this).load(FinalContents.getImageUrl() + "/fangfang/static/down/appTwoCode.png").into(fzb_img);

        fzb_return.setOnClickListener(this);
        fzb_fx.setOnClickListener(this);
        fzb_jc.setOnClickListener(this);
        fzb_mz.setOnClickListener(this);

    }

    @SingleClick(1000)
    @Override
    public void onClick(View view) {

        switch (view.getId()) {
//            TODO 返回上一层
            case R.id.fzb_return:
                finish();
                break;
//                TODO 分享
            case R.id.fzb_fx:
//                FinalContents.showShare("房app下载", FinalContents.getAdminUrl()+"/fangfang/static/down/index.html", "app下载",
//                        FinalContents.getImageUrl()+"/fangfang/static/common/images/logo.png", FinalContents.getAdminUrl()+"/fangfang/static/down/index.html", AboutFZBActivity.this);
                com.xcy.fzb.all.utils.Item_Share.initDaown(AboutFZBActivity.this,"房app下载", FinalContents.getAdminUrl()+"/fangfang/static/down/index.html", "app下载",
                        FinalContents.getImageUrl()+"/fangfang/static/common/images/logo.png", FinalContents.getAdminUrl()+"/fangfang/static/down/index.html");
                break;
//                TODO 检测版本
            case R.id.fzb_jc:
//                ToastUtil.showToast(AboutFZBActivity.this, "已是最新版本");
                initDaown();
//                showDownloadDialog();
                break;
//                TODO 免责声明
            case R.id.fzb_mz:
                intent = new Intent(AboutFZBActivity.this, DisclaimerActivity.class);
                startActivity(intent);
                break;
        }

    }

    private void initDaown() {
        String versionName = APKVersionCodeUtils.getVerName(this);
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        final Observable<AppPackageBean> appPackage = fzbInterface.getAppPackage("android", "com.xcy.fzbcity", versionName);
        appPackage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AppPackageBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(final AppPackageBean appPackageBean) {
//                        ToastUtil.showToast(AboutFZBActivity.this, appPackageBean.getData().getComment());
                        panduan = appPackageBean.getData().getIsUpgrade();
                        if (appPackageBean.getData().getIsUpgrade().equals("0")) {
                            ToastUtil.showToast(AboutFZBActivity.this,"当前版本已是最新版本");
                        } else if (appPackageBean.getData().getIsUpgrade().equals("1")) {
                            AlertDialog.Builder builder1 = new AlertDialog.Builder(AboutFZBActivity.this);
                            builder1.setTitle("提示");
                            builder1.setMessage("是否更新当前版本");
                            builder1.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                            builder1.setPositiveButton("更新", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    url = appPackageBean.getData().getAppurl();
                                    showDownloadDialog();
                                }
                            });
                            builder1.show();
                        } else if (appPackageBean.getData().getIsUpgrade().equals("2")) {
                            AlertDialog.Builder builder1 = new AlertDialog.Builder(AboutFZBActivity.this);
                            builder1.setTitle("提示");
                            builder1.setMessage("是否更新当前版本");
                            /*builder1.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    AllActivity.exit = true;
                                    finish();
                                }
                            });*/
                            builder1.setPositiveButton("更新", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    url = appPackageBean.getData().getAppurl();
                                    showDownloadDialog();
                                }
                            });
                            builder1.show();
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("版本更新", "错误信息：" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 显示正在下载对话框
     */
    protected void showDownloadDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(AboutFZBActivity.this);
        builder.setTitle("下载中");
        View view = LayoutInflater.from(AboutFZBActivity.this).inflate(R.layout.dialog_progress, null);
        mProgressBar = (ProgressBar) view.findViewById(R.id.id_progress);
        builder.setView(view);
        builder.setCancelable(false);
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (panduan.equals("2")) {
                    AllActivity.exit = true;
                    finish();
                } else {
                    // 隐藏当前对话框
                    dialog.dismiss();
                    // 设置下载状态为取消
                    mIsCancel = true;
                }

            }
        });

        mDownloadDialog = builder.create();
        mDownloadDialog.show();

        // 下载文件
        downloadAPK();
    }

    /**
     * 开启新线程下载apk文件
     */
    private void downloadAPK() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                        String sdPath = Environment.getExternalStorageDirectory() + "/";
//                        String sdPath = AboutFZBActivity.this.getCacheDir() + "/";
//                      文件保存路径
                        mSavePath = sdPath + "fzbcitydownload";

                        File dir = new File(mSavePath);
                        if (!dir.exists()) {
                            dir.mkdir();
                        }
                        // 下载文件
                        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
                        conn.connect();
                        InputStream is = conn.getInputStream();
                        int length = conn.getContentLength();

                        File apkFile = new File(mSavePath, mVersion_name);
                        FileOutputStream fos = new FileOutputStream(apkFile);

                        int count = 0;
                        byte[] buffer = new byte[4096];
                        while (!mIsCancel) {
                            int numread = is.read(buffer);
                            count += numread;
                            // 计算进度条的当前位置
                            mProgress = (int) (((float) count / length) * 100);
                            // 更新进度条
                            mUpdateProgressHandler.sendEmptyMessage(1);

                            // 下载完成
                            if (numread < 0) {
                                mUpdateProgressHandler.sendEmptyMessage(2);
                                break;
                            }
                            fos.write(buffer, 0, numread);
                        }
                        fos.close();
                        is.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 接收消息
     */
    private Handler mUpdateProgressHandler = new Handler() {
        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    // 设置进度条
                    mProgressBar.setProgress(mProgress);
                    break;
                case 2:
                    // 隐藏当前下载对话框
                    mDownloadDialog.dismiss();
                    // 安装 APK 文件
//                    File apkFile = new File(mSavePath, mVersion_name);
//                    installApk(AboutFZBActivity.this,apkFile);
                    installAPK();
            }
        }

        ;
    };

    /**
     * 下载到本地后执行安装
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    protected void installAPK() {

        //兼容8.0
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            boolean hasInstallPermission = AboutFZBActivity.this.getPackageManager().canRequestPackageInstalls();
//            if (!hasInstallPermission) {
////                ToastUtil.makeText(MyApplication.getContext(), MyApplication.getContext().getString(R.string.string_install_unknow_apk_note), false);
//                startInstallPermissionSettingActivity();
//                return;
//            }
//        }

        File apkFile = new File(mSavePath, mVersion_name);
        Intent intent = new Intent();
//跳转下载完成和打开页面
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//执行动作
        intent.setAction(Intent.ACTION_VIEW);
//执行的数据类型
        if (Build.VERSION.SDK_INT >= 24) { //判读版本是否在7.0以上
            //参数1 上下文, 参数2 Provider主机地址 和配置文件中保持一致   参数3  共享的文件
            Uri apkUri =
                    FileProvider.getUriForFile(AboutFZBActivity.this, "com.xcy.fzbcity.fileprovider", apkFile);
            //添加这一句表示对目标应用临时授权该Uri所代表的文件
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");
        }
        Log.e("TAG", "安装apk");
        AboutFZBActivity.this.startActivity(intent);

//        File apkFile = new File(mSavePath, mVersion_name);
//        if (!apkFile.exists()){
//            return;
//        }
////        Uri apkUri = FileProvider.getUriForFile(AboutFZBActivity.this, "com.zidian.qingframe.fileprovider", apkPath);
//        Intent intent = new Intent(Intent.ACTION_VIEW);
////      安装完成后，启动app（源码中少了这句话）
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        Uri uri = Uri.parse("file://" + apkFile.toString());
//        intent.setDataAndType(uri, "application/vnd.android.package-archive");
//        AboutFZBActivity.this.startActivity(intent);
//
//        startInstallPermissionSettingActivity();
//        String fileName = mSavePath;
//        Intent i = new Intent();
//        i.setAction(Intent.ACTION_VIEW);
//        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        i.setDataAndType(Uri.fromFile(new File(fileName) ), "application/vnd.android.package-archive");
//        startActivity(i);
    }

    /**
     *      * 跳转到设置-允许安装未知来源-页面
     *      
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void startInstallPermissionSettingActivity() {
        //注意这个是8.0新API
        Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        AboutFZBActivity.this.startActivity(intent);
    }

    //自动安装apk
    public static void installApk(Context context, File apkPath) {
        //提升文件读写权限
        String command = "chmod " + "777" + " " + apkPath.getPath();
        Runtime runtime = Runtime.getRuntime();
        try {
            runtime.exec(command);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //安装跳转
        Intent intent = new Intent(Intent.ACTION_VIEW);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //这块不要写错了，一定要是你自己Manifest注册的fileprovider
            Uri apkUri = FileProvider.getUriForFile(context, "com.zidian.qingframe.fileprovider", apkPath);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile(apkPath),
                    "application/vnd.android.package-archive");
        }
        context.startActivity(intent);
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

    /**
     * Bitmap转换成byte[]并且进行压缩,压缩到不大于maxkb
     *
     * @param bitmap
     * @param maxkb
     * @return
     */
    public static byte[] bitmap2Bytes(Bitmap bitmap, int maxkb) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, output);
        int options = 100;
        while (output.toByteArray().length > maxkb && options != 10) {
            output.reset(); //清空output
            bitmap.compress(Bitmap.CompressFormat.JPEG, options, output);//这里压缩options%，把压缩后的数据存放到output中
            options -= 10;
        }
        return output.toByteArray();
    }


}

package com.xcy.fzbcity.all.view;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.RelativeLayout;
import com.xcy.fzbcity.all.utils.ToastUtil;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;

import com.bumptech.glide.Glide;
import com.huantansheng.easyphotos.EasyPhotos;
import com.huantansheng.easyphotos.callback.SelectCallback;
import com.huantansheng.easyphotos.models.album.entity.Photo;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.adapter.GridViewAdapter;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.modle.AddPhotoBean;
import com.xcy.fzbcity.all.modle.AddPhotoBeanS;
import com.xcy.fzbcity.all.modle.FeedBackBean;
import com.xcy.fzbcity.all.persente.StatusBar;
import com.xcy.fzbcity.all.service.MyService;
import com.xcy.fzbcity.all.utils.CommonUtil;
import com.xcy.fzbcity.all.utils.GlideEngine;
import com.xcy.fzbcity.all.utils.ToastUtil;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//TODO 意见反馈
public class  FeedbackActivity extends AllActivity {

    RelativeLayout feedback_return;
    EditText feedback_editText;
    Button feedback_btn;
    private String message;

    private List<Object> mDatas;
    GridView mGridView;
    private GridViewAdapter adapter;

    private final String IMAGE_TYPE = "image/*";

    private final int IMAGE_CODE = 0;
    private Bitmap bm;
    StringBuffer stringBuffer = new StringBuffer();
    private StringBuffer imgurl;
    int sum = 0;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE"};
    //

    String isPhoto = "";
    private File file;
    private List<File> fileList = new ArrayList<>();
    private List<MultipartBody.Part> parts;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
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
            ToastUtil.showToast(this, "当前无网络，请检查网络后再进行登录");
        }
    }


    public void initView() {

        StatusBar.makeStatusBarTransparent(FeedbackActivity.this);

        mGridView = findViewById(R.id.gv_test);

        feedback_btn = findViewById(R.id.feedback_btn);
        feedback_editText = findViewById(R.id.feedback_editText);
        feedback_return = findViewById(R.id.feedback_return);
        //返回上一级的点击事件
        feedback_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //选择图片路径的点击事件


        View inflate = View.inflate(FeedbackActivity.this, R.layout.item_flowlayout, null);
        mDatas = new ArrayList<>();
        adapter = new GridViewAdapter(FeedbackActivity.this, mDatas);
        mGridView.setAdapter(adapter);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                if (mDatas.size() == 9) {
                    ToastUtil.showToast(FeedbackActivity.this, "图片最多九张");
                } else {
                    if (position == parent.getChildCount() - 1) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(FeedbackActivity.this);
                        builder.setTitle("请选择图片来源");
                        builder.setItems(new String[]{"相机", "相册"}, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (i == 0) {

                                    try {
                                        //检测是否有写的权限
                                        int permission = ActivityCompat.checkSelfPermission(FeedbackActivity.this,
                                                "android.permission.WRITE_EXTERNAL_STORAGE");
                                        if (permission != PackageManager.PERMISSION_GRANTED) {
                                            // 没有写的权限，去申请写的权限，
                                            ActivityCompat.requestPermissions(FeedbackActivity.this, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    String SDState = Environment.getExternalStorageState();
                                    if (SDState.equals(Environment.MEDIA_MOUNTED)) {
                                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);// "android.media.action.IMAGE_CAPTURE"
                                        /***
                                         * 需要说明一下，以下操作使用照相机拍照，拍照后的图片会存放在相册中的 这里使用的这种方式有一个好处就是获取的图片是拍照后的原图
                                         * 如果不实用ContentValues存放照片路径的话，拍照后获取的图片为缩略图不清晰
                                         */
                                        ContentValues values = new ContentValues();
                                        Uri photoUri = FeedbackActivity.this.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);


//TODO Uri图片转换成file类型的 start
                                        file = uri2File(photoUri);

                                        if (isPhoto.equals("")) {
                                            isPhoto = "拍照";
                                        }


//TODO Uri图片转换成file类型的 end
                                        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                                        startActivityForResult(intent, 1);
                                    } else {

                                    }
                                } else if (i == 1) {
//                                    Intent getAlbum = new Intent(Intent.ACTION_PICK);
//                                    getAlbum.setType(IMAGE_TYPE);
//                                    startActivityForResult(getAlbum, IMAGE_CODE);
                                    Log.i("url", "图片");
                                    int size = 9 - mDatas.size();
                                    EasyPhotos.createAlbum(FeedbackActivity.this, false, GlideEngine.getInstance())//参数说明：上下文，是否显示相机按钮，[配置Glide为图片加载引擎](https://github.com/HuanTanSheng/EasyPhotos/wiki/12-%E9%85%8D%E7%BD%AEImageEngine%EF%BC%8C%E6%94%AF%E6%8C%81%E6%89%80%E6%9C%89%E5%9B%BE%E7%89%87%E5%8A%A0%E8%BD%BD%E5%BA%93)
                                            .setCount(size)//参数说明：最大可选数，默认1
                                            .start(new SelectCallback() {
                                                @Override
                                                public void onResult(ArrayList<Photo> photos, boolean isOriginal) {
                                                    Log.i("url", "图片");
                                                    try {
                                                        Uri url = photos.get(0).uri;
                                                        String s = url.toString();
                                                        File file = uri2File(url);
                                                        fileList.clear();
                                                        for (int i = 0; i < photos.size(); i++) {
                                                            Uri uri = photos.get(i).uri;
                                                            File filel = uri2File(uri);
                                                            fileList.add(filel);
                                                        }

                                                        String[] filePathColumn = {MediaStore.Images.Media.DATA};
                                                        Cursor cursor = FeedbackActivity.this.getContentResolver().query(url, filePathColumn, null, null, null);
                                                        cursor.moveToFirst();
                                                        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                                                        String picPath = cursor.getString(columnIndex);
                                                        //转码
                                                        String encode = URLEncoder.encode(picPath, "utf-8");
                                                        cursor.close();
                                                        if (picPath.equals("")) return;
                                                        Log.e("123", "onActivityResult: 路径:--" + encode);
                                                        //上传图片
//                        addimg(encode, file);
                                                        Log.i("url", "图片：" + s + ".png");

                                                        MultipartBody.Builder mbuilder = new MultipartBody.Builder();
                                                        mbuilder.setType(MultipartBody.FORM);
                                                        for (int i = 0; i < fileList.size(); i++) {
                                                            mbuilder.addFormDataPart("files", fileList.get(i).getName(), RequestBody.create(MediaType.parse("image/*"), fileList.get(i)));
                                                        }
                                                        parts = mbuilder.build().parts();

                                                        Log.i("批量选择相册","进入前");

                                                        Retrofit.Builder builder = new Retrofit.Builder();
                                                        builder.baseUrl(FinalContents.getBaseUrl());
                                                        builder.addConverterFactory(GsonConverterFactory.create());
                                                        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
                                                        Retrofit build = builder.build();
                                                        MyService fzbInterface = build.create(MyService.class);
                                                        final Observable<AddPhotoBeanS> addPhoto = fzbInterface.getAddPhotoS(FinalContents.getUserID(), "zhuanyuan", parts);
                                                        addPhoto.subscribeOn(Schedulers.io())
                                                                .observeOn(AndroidSchedulers.mainThread())
                                                                .subscribe(new Observer<AddPhotoBeanS>() {
                                                                    @Override
                                                                    public void onSubscribe(Disposable d) {
                                                                        Log.i("批量选择相册","onSubscribe");
                                                                    }

                                                                    @Override
                                                                    public void onNext(AddPhotoBeanS addPhotoBean) {
                                                                        Log.i("批量选择相册","图片地址");
                                                                        for (int i = 0; i < addPhotoBean.getData().size(); ++i){
                                                                            Log.i("批量选择相册","图片地址：" + addPhotoBean.getData().get(i).getUrl());
                                                                            if (stringBuffer.length() == 0) {
                                                                                stringBuffer.append(addPhotoBean.getData().get(i).getUrl());
                                                                            } else {
                                                                                stringBuffer.append("|" + addPhotoBean.getData().get(i).getUrl());
                                                                            }
                                                                            mDatas.add(addPhotoBean.getData().get(i).getUrl());
                                                                            adapter.notifyDataSetChanged();
                                                                        }


                                                                        Log.i("图片上传", "数据上传" + addPhotoBean.getData().get(0).getUrl());
                                                                    }

                                                                    @Override
                                                                    public void onError(Throwable e) {
                                                                        Log.i("批量选择相册","经济圈发布图片上传错误信息：" + e.getMessage());
                                                                        Log.i("MyCL", "经济圈发布图片上传错误信息：" + e.getMessage());
                                                                    }

                                                                    @Override
                                                                    public void onComplete() {

                                                                    }
                                                                });

                                                    } catch (UnsupportedEncodingException e) {
                                                        e.printStackTrace();
                                                    }
                                                }
                                            });

                                }
                            }
                        });
                        builder.setPositiveButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                        AlertDialog show = builder.show();
                        show.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#334485"));
                        adapter = new GridViewAdapter(FeedbackActivity.this, mDatas);
                        mGridView.setAdapter(adapter);

                    } else {

                        Intent intent = new Intent(FeedbackActivity.this, BigPhotoActivity.class);
                        intent.putExtra("index", position);
                        intent.putExtra("bigPhotoimg", stringBuffer.toString());// -1  -1  -1
                        startActivity(intent);

//                        Log.i("MyCL","下标：" + position);
//                        ArrayList<String> listImage = new ArrayList<>();
//                        final String[] a = stringBuffer.toString().split("[|]");
//                        for (int i = 0; i < a.length; i++) {
//                            listImage.add(a[i]);
//                        }
//                        listImage.remove(position);
//                        stringBuffer.append(listImage);
//                        mDatas.remove(position);
//                        adapter.notifyDataSetChanged();
//                        Log.i("MyCL","stringBuffer：" + stringBuffer.toString());
//                        Log.i("MyCL","mDatas：" + mDatas.toString());
                    }
                }
            }
        });

        //TODO 提交按钮的点击事件
        feedback_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message = feedback_editText.getText().toString();

                if (message.equals("")) {
                    ToastUtil.showToast(FeedbackActivity.this, "原因不能为空");
                } else {
                    Retrofit.Builder builder = new Retrofit.Builder();
                    builder.baseUrl(FinalContents.getBaseUrl());
                    builder.addConverterFactory(GsonConverterFactory.create());
                    builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
                    Retrofit build = builder.build();
                    MyService fzbInterface = build.create(MyService.class);
                    final Observable<FeedBackBean> feedBack = fzbInterface.getFeedBack(message, stringBuffer.toString(), FinalContents.getUserID());
                    feedBack.subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Observer<FeedBackBean>() {
                                @Override
                                public void onSubscribe(Disposable d) {
                                }

                                @Override
                                public void onNext(FeedBackBean feedBackBean) {
                                    String msg = feedBackBean.getMsg();
                                    if (msg.equals("成功")) {
                                        ToastUtil.showToast(FeedbackActivity.this, "提交成功");
                                        finish();
                                    } else {
                                        ToastUtil.showToast(FeedbackActivity.this, "提交失败，请重新提交");
                                    }
                                }

                                @Override
                                public void onError(Throwable e) {
                                    Log.i("MyCL", "意见反馈错误信息" + e.getMessage());
                                }

                                @Override
                                public void onComplete() {

                                }
                            });
                }


            }
        });
    }

    //TODO 将Uri转换成File
    private File uri2File(Uri uri) {
        String img_path;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor actualimagecursor = managedQuery(uri, proj, null,
                null, null);
        if (actualimagecursor == null) {
            img_path = uri.getPath();
        } else {
            int actual_image_column_index = actualimagecursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            actualimagecursor.moveToFirst();
            img_path = actualimagecursor
                    .getString(actual_image_column_index);
        }
        File file = new File(img_path);
        return file;
    }

    //TODO 将bitmap转换成File
    public File saveFile(Bitmap bm, String fileName) throws IOException {//将Bitmap类型的图片转化成file类型，便于上传到服务器
        String path = Environment.getExternalStorageDirectory() + "/Ask";
        File dirFile = new File(path);
        if (!dirFile.exists()) {
            dirFile.mkdir();
        }
        File myCaptureFile = new File(path + fileName);
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
        bm.compress(Bitmap.CompressFormat.JPEG, 80, bos);
        bos.flush();
        bos.close();
        return myCaptureFile;

    }

    @Override
    protected void onRestart() {
        super.onRestart();

        if (isPhoto.equals("拍照")) {
            new Thread() {
                @Override
                public void run() {

                    Retrofit.Builder builder = new Retrofit.Builder();
                    builder.baseUrl(FinalContents.getBaseUrl());
                    builder.addConverterFactory(GsonConverterFactory.create());
                    builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
                    Retrofit build = builder.build();
                    MyService fzbInterface = build.create(MyService.class);

                    RequestBody requestBody = RequestBody.create(MediaType.parse("image/png"), file);

                    MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), requestBody);

                    Observable<AddPhotoBean> addPhoto = fzbInterface.getAddPhoto(FinalContents.getUserID(), "linkelist", part);
                    addPhoto.subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Observer<AddPhotoBean>() {
                                @Override
                                public void onSubscribe(Disposable d) {

                                }

                                @Override
                                public void onNext(AddPhotoBean addPhotoBean) {
                                    if (stringBuffer.length() == 0) {
                                        stringBuffer.append(addPhotoBean.getData().getUrl());
                                    } else {
                                        stringBuffer.append("|" + addPhotoBean.getData().getUrl());
                                    }
                                    mDatas.add(addPhotoBean.getData().getUrl());
                                    adapter.notifyDataSetChanged();
                                    isPhoto = "";
                                }

                                @Override
                                public void onError(Throwable e) {
                                }

                                @Override
                                public void onComplete() {

                                }
                            });
                }
            }.start();
        }

    }
}

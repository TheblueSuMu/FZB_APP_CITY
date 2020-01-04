package com.xcy.fzbcity.all.view;

import android.app.AlertDialog;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bumptech.glide.Glide;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.api.CityContents;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.modle.AddClientBean;
import com.xcy.fzbcity.all.modle.AddPhotoBean;
import com.xcy.fzbcity.all.persente.SingleClick;
import com.xcy.fzbcity.all.persente.StatusBar;
import com.xcy.fzbcity.all.service.MyService;
import com.xcy.fzbcity.all.utils.CommonUtil;
import com.xcy.fzbcity.all.utils.MatcherUtils;
import com.xcy.fzbcity.all.utils.ToastUtil;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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

public class MyClientAddActivity extends AllActivity implements View.OnClickListener {

    RelativeLayout client_add_return;
    ImageView client_add_img;
    ImageView client_add_bj;
    ImageView client_add_photo_img_1;
    ImageView client_add_photo_img_2;
    ImageView client_add_photo_img_3;
    ImageView client_add_photo_add;
    ImageView client_add_photo_delete_1;
    ImageView client_add_photo_delete_2;

    LinearLayout client_add_photo_ll_1;
    LinearLayout client_add_photo_ll_2;
    LinearLayout client_add_photo_ll_3;

    TextView client_add_name_tv;
    TextView client_add_photo_tv_1;
    TextView client_add_photo_tv_2;
    TextView client_add_photo_tv_3;

    EditText client_add_name_et;
    EditText client_add_photo_et_1;
    EditText client_add_photo_et_2;
    EditText client_add_photo_et_3;

    LinearLayout client_add_ll_1;
    LinearLayout client_add_ll_2;

    RelativeLayout client_add_rl_1;
    RelativeLayout client_add_rl_2;
    private AlertDialog.Builder builders;
    private String[] cities;

    String name1;
    String name2;
    String name3;

    Button client_add_btn;

    int addNum = 0;

    private final String IMAGE_TYPE = "image/*";

    private final int IMAGE_CODE = 0;
    private Bitmap bm;

    String imgUrl = "";

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE"};

    int ifimg = 0;

    Uri saveUri;
    private static final String FILE_PATH = "/sdcard/syscamera.jpg";
    private File file;

    int issex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_client_add);
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

    private void initView() {

        StatusBar.makeStatusBarTransparent(this);

        builders = new AlertDialog.Builder(MyClientAddActivity.this);
        cities = new String[]{"本人手机", "父母手机", "配偶手机", "子女手机"};

        client_add_return = findViewById(R.id.client_add_return);
        client_add_img = findViewById(R.id.client_add_img);
        client_add_bj = findViewById(R.id.client_add_bj);
        client_add_photo_img_1 = findViewById(R.id.client_add_photo_img_1);
        client_add_photo_img_2 = findViewById(R.id.client_add_photo_img_2);
        client_add_photo_img_3 = findViewById(R.id.client_add_photo_img_3);
        client_add_photo_add = findViewById(R.id.client_add_photo_add);
        client_add_photo_delete_1 = findViewById(R.id.client_add_photo_delete_1);
        client_add_photo_delete_2 = findViewById(R.id.client_add_photo_delete_2);
        client_add_name_tv = findViewById(R.id.client_add_name_tv);
        client_add_name_et = findViewById(R.id.client_add_name_et);
        client_add_photo_et_1 = findViewById(R.id.client_add_photo_et_1);
        client_add_photo_et_2 = findViewById(R.id.client_add_photo_et_2);
        client_add_photo_et_3 = findViewById(R.id.client_add_photo_et_3);
        client_add_btn = findViewById(R.id.client_add_btn);
        client_add_photo_ll_1 = findViewById(R.id.client_add_photo_ll_1);
        client_add_photo_ll_2 = findViewById(R.id.client_add_photo_ll_2);
        client_add_photo_ll_3 = findViewById(R.id.client_add_photo_ll_3);

        client_add_ll_1 = findViewById(R.id.client_add_ll_1);
        client_add_ll_2 = findViewById(R.id.client_add_ll_2);

        client_add_rl_1 = findViewById(R.id.client_add_rl_1);
        client_add_rl_2 = findViewById(R.id.client_add_rl_2);

        client_add_photo_tv_1 = findViewById(R.id.client_add_photo_tv_1);
        client_add_photo_tv_2 = findViewById(R.id.client_add_photo_tv_2);
        client_add_photo_tv_3 = findViewById(R.id.client_add_photo_tv_3);

        name1 = client_add_photo_tv_1.getText().toString();
        name2 = client_add_photo_tv_2.getText().toString();
        name3 = client_add_photo_tv_3.getText().toString();

        client_add_return.setOnClickListener(this);
        client_add_img.setOnClickListener(this);
        client_add_photo_img_1.setOnClickListener(this);
        client_add_photo_img_2.setOnClickListener(this);
        client_add_photo_img_3.setOnClickListener(this);
        client_add_photo_add.setOnClickListener(this);
        client_add_photo_delete_1.setOnClickListener(this);
        client_add_photo_delete_2.setOnClickListener(this);
        client_add_btn.setOnClickListener(this);
        client_add_photo_ll_1.setOnClickListener(this);
        client_add_photo_ll_2.setOnClickListener(this);
        client_add_photo_ll_3.setOnClickListener(this);

        saveUri = Uri.fromFile(new File(getExternalFilesDir(Environment.DIRECTORY_DCIM), "test.jpg"));
    }

    @SingleClick(1000)
    @Override
    public void onClick(View view) {


        switch (view.getId()) {

            case R.id.client_add_return:
                finish();
                break;
//                TODO 图片选择
            case R.id.client_add_img:
//                TODO 获取相册图片地址

                AlertDialog.Builder builder = new AlertDialog.Builder(MyClientAddActivity.this);
                builder.setTitle("请选择图片来源");
                builder.setItems(new String[]{"相机", "相册"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (i == 0) {
                            try {
                                //检测是否有写的权限
                                int permission = ActivityCompat.checkSelfPermission(MyClientAddActivity.this,
                                        "android.permission.WRITE_EXTERNAL_STORAGE");
                                if (permission != PackageManager.PERMISSION_GRANTED) {
                                    // 没有写的权限，去申请写的权限，会弹出对话框
                                    ActivityCompat.requestPermissions(MyClientAddActivity.this, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
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
                                Uri photoUri = MyClientAddActivity.this.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                                file = uri2File(photoUri);

                                if (ifimg == 0) {
                                    ifimg = 1;
                                }


//                                Glide.with(MyClientAddActivity.this).load(photoUri).into(client_add_img);
                                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                                startActivityForResult(intent, 1);
                            } else {

                            }
                        } else if (i == 1) {
                            Intent getAlbum = new Intent(Intent.ACTION_PICK);
                            getAlbum.setType(IMAGE_TYPE);
                            startActivityForResult(getAlbum, IMAGE_CODE);
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
                break;
//                TODO 编辑
            case R.id.client_add_btn:
                if (!MatcherUtils.isMobile(client_add_photo_et_1.getText().toString())) {
                    ToastUtil.showToast(this, "请输入正确的手机号");
                    return;
                } else {
                    if (client_add_name_et.getText().toString().equals("")) {
                        ToastUtil.showToast(MyClientAddActivity.this, "手机号至少填一个");
                    } else {
                        if (addNum == 0) {
                            addNum = 1;
                            initData();
                            addNum = 0;
                        }
                    }
                }

                break;
//                TODO 选择联系人
            case R.id.client_add_photo_ll_1:
                issex = 1;
                initSelectData();

//                builders.setItems(cities, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        client_add_photo_tv_1.setText(cities[which] + "");
//                        name1 = cities[which] + "";
//                    }
//                });
//                builders.show();
                break;
            case R.id.client_add_photo_ll_2:
                issex = 2;
                initSelectData();
//                builders.setItems(cities, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        client_add_photo_tv_2.setText(cities[which] + "");
//                        name2 = cities[which] + "";
//                    }
//                });
//                builders.show();
                break;
            case R.id.client_add_photo_ll_3:
                issex = 3;
                initSelectData();

//                builders.setItems(cities, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        client_add_photo_tv_3.setText(cities[which] + "");
//                        name3 = cities[which] + "";
//                    }
//                });
//                builders.show();
                break;
//                TODO 添加
            case R.id.client_add_photo_add:
                if (client_add_rl_1.getVisibility() == View.GONE) {
                    client_add_rl_1.setVisibility(View.VISIBLE);
                    client_add_ll_1.setVisibility(View.VISIBLE);
                } else if (client_add_rl_1.getVisibility() == View.VISIBLE && client_add_rl_2.getVisibility() == View.GONE) {
                    client_add_rl_2.setVisibility(View.VISIBLE);
                    client_add_ll_2.setVisibility(View.VISIBLE);
                } else if (client_add_rl_1.getVisibility() == View.VISIBLE && client_add_rl_2.getVisibility() == View.VISIBLE) {
                    ToastUtil.showToast(MyClientAddActivity.this, "最多添加三个联系人");
                }
                break;
//                TODO 删除
            case R.id.client_add_photo_delete_1:
                client_add_rl_1.setVisibility(View.GONE);
                client_add_ll_1.setVisibility(View.GONE);
                break;
            case R.id.client_add_photo_delete_2:
                client_add_rl_2.setVisibility(View.GONE);
                client_add_ll_2.setVisibility(View.GONE);
                break;
        }

    }

    private void initSelectData() {
        final List<String> list1 = new ArrayList<>();
        list1.add("本人手机");
        list1.add("父母手机");
        list1.add("配偶手机");
        list1.add("子女手机");
        OptionsPickerView pvOptions = new OptionsPickerBuilder(MyClientAddActivity.this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //              展示选中数据
                if (issex == 1) {
                    client_add_photo_tv_1.setText(list1.get(options1));
                    name1 = list1.get(options1);
                } else if (issex == 2) {
                    client_add_photo_tv_2.setText(list1.get(options1));
                    name2 = list1.get(options1);
                } else if (issex == 3) {
                    client_add_photo_tv_3.setText(list1.get(options1));
                    name3 = list1.get(options1);
                }
            }
        }).setSelectOptions(0)
                .setOutSideCancelable(false)//点击背的地方不消失
                .build();//创建
        //      把数据绑定到控件上面
        pvOptions.setPicker(list1);
        //      展示
        pvOptions.show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//TODO  获取相册图片地址
        if (resultCode != RESULT_OK) {        //此处的 RESULT_OK 是系统自定义得一个常量
            Log.e("MyCL", "ActivityResult resultCode error");
            return;
        }
        bm = null;
        //外界的程序访问ContentProvider所提供数据 可以通过ContentResolver接口

        ContentResolver resolver = getContentResolver();


        //此处的用于判断接收的Activity是不是你想要的那个

        if (requestCode == IMAGE_CODE) {

            try {

                Uri originalUri = data.getData();        //获得图片的uri

                bm = MediaStore.Images.Media.getBitmap(resolver, originalUri);        //显得到bitmap图片
                final File san = saveFile(bm, "tx.png");

                new Thread() {
                    @Override
                    public void run() {

                        Retrofit.Builder builder = new Retrofit.Builder();
                        builder.baseUrl(FinalContents.getBaseUrl());
                        builder.addConverterFactory(GsonConverterFactory.create());
                        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
                        Retrofit build = builder.build();
                        MyService fzbInterface = build.create(MyService.class);

                        RequestBody requestBody = RequestBody.create(MediaType.parse("image/png"), san);

                        MultipartBody.Part part = MultipartBody.Part.createFormData("file", san.getName(), requestBody);

                        Observable<AddPhotoBean> addPhoto = fzbInterface.getAddPhoto(FinalContents.getUserID(), "myClient", part);
                        addPhoto.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Observer<AddPhotoBean>() {
                                    @Override
                                    public void onSubscribe(Disposable d) {

                                    }

                                    @Override
                                    public void onNext(AddPhotoBean addPhotoBean) {
                                        imgUrl = addPhotoBean.getData().getUrl();
                                        Glide.with(MyClientAddActivity.this).load(FinalContents.getImageUrl() + imgUrl).into(client_add_img);

                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        Log.i("MyCL", "经济圈发布图片上传错误信息：" + e.getMessage());
                                    }

                                    @Override
                                    public void onComplete() {

                                    }
                                });
                    }
                }.start();


                String[] proj = {MediaStore.Images.Media.DATA};


                //好像是android多媒体数据库的封装接口，具体的看Android文档

                Cursor cursor = managedQuery(originalUri, proj, null, null, null);

                //按我个人理解 这个是获得用户选择的图片的索引值

                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

                //将光标移至开头 ，这个很重要，不小心很容易引起越界

                cursor.moveToFirst();

                //最后根据索引值获取图片路径

                String path = cursor.getString(column_index);
            } catch (IOException e) {

                Log.e("MyCL", e.toString());

            }

        }
    }

    private void initData() {
        if (client_add_rl_1.getVisibility() == View.VISIBLE && client_add_rl_2.getVisibility() == View.GONE) {
            if (client_add_photo_et_1.getText().length() != 11 || client_add_photo_et_2.getText().length() != 11) {
                ToastUtil.showToast(MyClientAddActivity.this, "请检查手机号是否是11位");
                addNum = 0;
            } else {
                Retrofit.Builder builder = new Retrofit.Builder();
                builder.baseUrl(FinalContents.getBaseUrl());
                builder.addConverterFactory(GsonConverterFactory.create());
                builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
                Retrofit build = builder.build();
                MyService fzbInterface = build.create(MyService.class);
                Log.i("返回数据","1:" + client_add_name_et.getText().toString());
                Log.i("返回数据","2:" + imgUrl);
                Log.i("返回数据","3:" + name1);
                Log.i("返回数据","4:" + client_add_photo_et_1.getText().toString());
                Log.i("返回数据","5:" + name2);
                Log.i("返回数据","6:" + client_add_photo_et_2.getText().toString());
                Log.i("返回数据","7:" + name3);
                Log.i("返回数据","8:" + client_add_photo_et_3.getText().toString());
                Log.i("返回数据","9:" + FinalContents.getUserID());
                Observable<AddClientBean> addClient = fzbInterface.getAddClient(client_add_name_et.getText().toString(), imgUrl, name1, client_add_photo_et_1.getText().toString()
                        , name2, client_add_photo_et_2.getText().toString(), name3, client_add_photo_et_3.getText().toString(), FinalContents.getUserID());
                addClient.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<AddClientBean>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(AddClientBean addClientBean) {
                                String msg = addClientBean.getMsg();
                                if (addClientBean.getData().getStatus().equals("1")) {
                                    ToastUtil.showToast(MyClientAddActivity.this, addClientBean.getData().getMessage());
                                    CityContents.setAddClient("1");
                                    finish();
                                } else {
                                    ToastUtil.showToast(MyClientAddActivity.this, addClientBean.getData().getMessage());
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.i("MyCL", "我的客户添加客户错误信息：" + e.getMessage());
                                ToastUtil.showToast(MyClientAddActivity.this, "");
                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            }
        } else if (client_add_rl_1.getVisibility() == View.VISIBLE && client_add_rl_2.getVisibility() == View.VISIBLE) {
            if (client_add_photo_et_1.getText().length() != 11 || client_add_photo_et_2.getText().length() != 11 || client_add_photo_et_3.getText().length() != 11) {
                ToastUtil.showToast(MyClientAddActivity.this, "请检查手机号是否是11位");
                addNum = 0;
            } else {
                Retrofit.Builder builder = new Retrofit.Builder();
                builder.baseUrl(FinalContents.getBaseUrl());
                builder.addConverterFactory(GsonConverterFactory.create());
                builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
                Retrofit build = builder.build();
                MyService fzbInterface = build.create(MyService.class);
                Observable<AddClientBean> addClient = fzbInterface.getAddClient(client_add_name_et.getText().toString(), imgUrl, name1, client_add_photo_et_1.getText().toString(), name2, client_add_photo_et_2.getText().toString(), name3, client_add_photo_et_3.getText().toString(), FinalContents.getUserID());
                addClient.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<AddClientBean>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(AddClientBean addClientBean) {
                                String msg = addClientBean.getMsg();
                                Log.i("返回数据", "数据：" + addClientBean.getData());
                                if (addClientBean.getData().getStatus().equals("1")) {
                                    ToastUtil.showToast(MyClientAddActivity.this, addClientBean.getData().getMessage());
                                    CityContents.setAddClient("1");
                                    finish();
                                } else {
                                    ToastUtil.showToast(MyClientAddActivity.this, addClientBean.getData().getMessage());
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.i("MyCL", "我的客户添加客户错误信息：" + e.getMessage());
                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            }
        } else {
            if (client_add_photo_et_1.getText().length() != 11) {
                ToastUtil.showToast(MyClientAddActivity.this, "请检查手机号是否是11位");
                addNum = 0;
            } else {
                Retrofit.Builder builder = new Retrofit.Builder();
                builder.baseUrl(FinalContents.getBaseUrl());
                builder.addConverterFactory(GsonConverterFactory.create());
                builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
                Retrofit build = builder.build();
                MyService fzbInterface = build.create(MyService.class);

                Log.i("返回数据","1:" + client_add_name_et.getText().toString());
                Log.i("返回数据","2:" + imgUrl);
                Log.i("返回数据","3:" + name1);
                Log.i("返回数据","4:" + client_add_photo_et_1.getText().toString());
                Log.i("返回数据","5:" + name2);
                Log.i("返回数据","6:" + client_add_photo_et_2.getText().toString());
                Log.i("返回数据","7:" + name3);
                Log.i("返回数据","8:" + client_add_photo_et_3.getText().toString());
                Log.i("返回数据","9:" + FinalContents.getUserID());

                Observable<AddClientBean> addClient = fzbInterface.getAddClient(client_add_name_et.getText().toString(), imgUrl, name1, client_add_photo_et_1.getText().toString(), name2, client_add_photo_et_2.getText().toString(), name3, client_add_photo_et_3.getText().toString(), FinalContents.getUserID());
                addClient.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<AddClientBean>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(AddClientBean addClientBean) {
                                String msg = addClientBean.getMsg();
                                Log.i("返回数据", "数据：" + addClientBean.getData());
                                if (addClientBean.getData().getStatus().equals("1")) {
                                    ToastUtil.showToast(MyClientAddActivity.this, addClientBean.getData().getMessage());
                                    CityContents.setAddClient("1");
                                    finish();
                                } else {
                                    ToastUtil.showToast(MyClientAddActivity.this, addClientBean.getData().getMessage());
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.i("MyCL", "我的客户添加客户错误信息：" + e.getMessage());
                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            }
        }

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
    protected void onResume() {
        super.onResume();

        if (ifimg == 1) {
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

                    Observable<AddPhotoBean> addPhoto = fzbInterface.getAddPhoto(FinalContents.getUserID(), "myClient", part);
                    addPhoto.subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Observer<AddPhotoBean>() {
                                @Override
                                public void onSubscribe(Disposable d) {

                                }

                                @Override
                                public void onNext(AddPhotoBean addPhotoBean) {
                                    imgUrl = addPhotoBean.getData().getUrl();
                                    Glide.with(MyClientAddActivity.this).load(FinalContents.getImageUrl() + imgUrl).into(client_add_img);
                                    ifimg = 0;
                                }

                                @Override
                                public void onError(Throwable e) {
                                    Log.i("MyCL", "经济圈发布图片上传错误信息：" + e.getMessage());
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

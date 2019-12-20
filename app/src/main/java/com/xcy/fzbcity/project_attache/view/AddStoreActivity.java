package com.xcy.fzbcity.project_attache.view;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.lljjcoder.citypickerview.widget.CityPicker;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.modle.AddPhotoBean;
import com.xcy.fzbcity.all.modle.AddStoreBean;
import com.xcy.fzbcity.all.modle.AddStoreNumBean;
import com.xcy.fzbcity.all.modle.ChangeAddress;
import com.xcy.fzbcity.all.modle.StoreChangeBean;
import com.xcy.fzbcity.all.persente.OkHttpPost;
import com.xcy.fzbcity.all.persente.SingleClick;
import com.xcy.fzbcity.all.persente.StatusBar;
import com.xcy.fzbcity.all.service.MyService;
import com.xcy.fzbcity.all.utils.CommonUtil;
import com.xcy.fzbcity.all.utils.ToastUtil;
import com.xcy.fzbcity.all.view.AllActivity;
import com.xcy.fzbcity.all.view.TestMapActivity;

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

//TODO 添加门店
public class AddStoreActivity extends AllActivity implements View.OnClickListener {

    RelativeLayout add_broker_return;
    ImageView add_broker_img1;
    ImageView add_broker_img2;

    TextView add_broker_tv1;
    TextView add_broker_tvs;
    TextView add_broker_tv3;
    TextView add_broker_tv4;

    TextView add_broker_tv2;
    EditText add_broker_et2;
    TextView add_broker_et3;
    EditText add_broker_et4;

    Button add_broker_btn;

    RadioButton add_broker_rb1;
    RadioButton add_broker_rb2;
    RadioButton add_broker_rb3;
    RadioButton add_broker_rb4;

    RelativeLayout add_broker_rl1;
    RelativeLayout add_store_rl2;
    RelativeLayout add_broker_rl2;
    RelativeLayout add_store_rl4;
    private Intent intent;

    RadioGroup add_broker_rg;

    int sum = 0;
    Bitmap bm;
    static int ifsearch = 0;
    private StringBuffer imgurl;
    private final String IMAGE_TYPE = "image/*";
    private final int IMAGE_CODE = 0;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE"};
    private String url1 = "";
    private String url2 = "";
    private int flag;
    private StoreChangeBean.DataBean.StoreManageBeanX storeManage;
    private File files;

    int isPhoto = 0;
    String myLocation = "";
    private String state = "";

    int isnum = 0;
    private String getLatitude = "";
    private String getLongitude = "";
    private int GPS_REQUEST_CODE = 10;
    int isOne = 0;

    int ifStart = 0;
    private String s1;
    private String s2;
    private String s3;
    private String s;
    private String s4;
    private String s5;
    private String s6 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_attache_activity_add_store);
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
            ToastUtil.showLongToast(AddStoreActivity.this, "当前无网络，请检查网络后再进行登录");
        }
    }

    private void initView() {
        StatusBar.makeStatusBarTransparent(this);

        add_broker_return = findViewById(R.id.add_broker_return);
        add_broker_img1 = findViewById(R.id.add_broker_img1);
        add_broker_tvs = findViewById(R.id.add_broker_tvs);
        add_broker_img2 = findViewById(R.id.add_broker_img2);
        add_broker_tv1 = findViewById(R.id.add_broker_tv1);
        add_broker_tv2 = findViewById(R.id.add_broker_tv2);
        add_broker_et2 = findViewById(R.id.add_broker_et2);
        add_broker_et3 = findViewById(R.id.add_broker_et3);
        add_broker_et4 = findViewById(R.id.add_broker_et4);
        add_broker_btn = findViewById(R.id.add_broker_btn);
        add_broker_rb1 = findViewById(R.id.add_broker_rb1);
        add_broker_rb2 = findViewById(R.id.add_broker_rb2);
        add_broker_rb3 = findViewById(R.id.add_broker_rb3);
        add_broker_rb4 = findViewById(R.id.add_broker_rb4);
        add_broker_rl1 = findViewById(R.id.add_broker_rl1);
        add_store_rl2 = findViewById(R.id.add_store_rl2);
        add_broker_rl2 = findViewById(R.id.add_broker_rl2);
        add_broker_tv3 = findViewById(R.id.add_broker_tv3);
        add_broker_rg = findViewById(R.id.add_broker_rg);
        add_store_rl4 = findViewById(R.id.add_store_rl4);
        add_broker_tv4 = findViewById(R.id.add_broker_tv4);

        add_broker_return.setOnClickListener(this);
        add_broker_img1.setOnClickListener(this);
        add_broker_img2.setOnClickListener(this);
        add_broker_btn.setOnClickListener(this);
        add_broker_rl1.setOnClickListener(this);
        add_store_rl2.setOnClickListener(this);
        add_broker_rl2.setOnClickListener(this);
        add_store_rl4.setOnClickListener(this);

        add_broker_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.add_broker_rb2) {
                    add_broker_rl2.setVisibility(View.VISIBLE);
                } else {
                    add_broker_rl2.setVisibility(View.GONE);
                }
            }
        });

        initData();

    }

    private void initData() {


        if (FinalContents.getStoreChange().equals("修改")) {

            add_broker_tvs.setText("修改门店");

            add_broker_rb3.setVisibility(View.VISIBLE);
            add_broker_rb4.setVisibility(View.VISIBLE);

            Retrofit.Builder builder = new Retrofit.Builder();
            builder.baseUrl(FinalContents.getBaseUrl());
            builder.addConverterFactory(GsonConverterFactory.create());
            builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
            Retrofit build = builder.build();
            MyService fzbInterface = build.create(MyService.class);
            Observable<StoreChangeBean> storeChangeBean = fzbInterface.getStoreChangeBean(FinalContents.getUserID(), FinalContents.getStoreId());
            storeChangeBean.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<StoreChangeBean>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(StoreChangeBean storeChangeBean) {
                            storeManage = storeChangeBean.getData().getStoreManage();
                            add_broker_tv1.setText(storeChangeBean.getData().getStoreManage().getStoreNum());
                            add_broker_tv2.setText(storeChangeBean.getData().getStoreManage().getCompany().getCompanyName());
                            add_broker_et2.setText(storeChangeBean.getData().getStoreManage().getStoreName());
                            add_broker_et3.setText(storeChangeBean.getData().getStoreManage().getArea());
                            add_broker_et4.setText(storeChangeBean.getData().getStoreManage().getAddress());
                            if (storeChangeBean.getData().getStoreManage().getState().equals("1")) {
                                add_broker_tv3.setText("签约");
                                state = "1";
                            } else if (storeChangeBean.getData().getStoreManage().getState().equals("2")) {
                                add_broker_tv3.setText("装机");
                                state = "2";
                            } else if (storeChangeBean.getData().getStoreManage().getState().equals("3")) {
                                add_broker_tv3.setText("培训");
                                state = "3";
                            } else if (storeChangeBean.getData().getStoreManage().getState().equals("4")) {
                                add_broker_tv3.setText("维护");
                                state = "4";
                            }
                            if (storeChangeBean.getData().getStoreManage().getStoreRise().equals("")) {

                            } else {
                                Glide.with(AddStoreActivity.this).load(FinalContents.getImageUrl() + storeChangeBean.getData().getStoreManage().getStoreRise()).into(add_broker_img1);
                            }
                            if (storeChangeBean.getData().getStoreManage().getStoreImg().equals("")) {

                            } else {
                                Glide.with(AddStoreActivity.this).load(FinalContents.getImageUrl() + storeChangeBean.getData().getStoreManage().getStoreImg()).into(add_broker_img2);
                            }

                            FinalContents.setCompanyManageId(storeManage.getCompany().getId());
                            if (ifStart == 0) {
                                Log.i("修改门店", "ifStart");
                                url1 = storeChangeBean.getData().getStoreManage().getStoreRise();
                                url2 = storeChangeBean.getData().getStoreManage().getStoreImg();
                                ifStart = 1;
                            }
                            if (storeChangeBean.getData().getStoreManage().getFlag().equals("1")) {
                                add_broker_rb2.setChecked(true);
                                add_broker_rl2.setVisibility(View.VISIBLE);
                            } else if (storeChangeBean.getData().getStoreManage().getFlag().equals("0")) {
                                add_broker_rb1.setChecked(true);
                                add_broker_rl2.setVisibility(View.GONE);
                            } else if (storeChangeBean.getData().getStoreManage().getFlag().equals("2")) {
                                add_broker_rb3.setChecked(true);
                                add_broker_rl2.setVisibility(View.GONE);
                            } else if (storeChangeBean.getData().getStoreManage().getFlag().equals("3")) {
                                add_broker_rb4.setChecked(true);
                                add_broker_rl2.setVisibility(View.GONE);
                            }
                            if (storeChangeBean.getData().getStoreManage().getLocation().equals("")) {

                            } else {
                                add_broker_tv4.setText(storeChangeBean.getData().getStoreManage().getLocation());
                            }
                            myLocation = storeChangeBean.getData().getStoreManage().getLocation();

                            FinalContents.setAddtype2(storeChangeBean.getData().getStoreManage().getCompany().getCompanyName());

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        } else {
            add_broker_tvs.setText("添加门店");
            add_broker_rb3.setVisibility(View.GONE);
            add_broker_rb4.setVisibility(View.GONE);
            if (isOne == 0) {
                String storeUrl = FinalContents.getBaseUrl() + "commissionerUpdate/setStoreNum?userId=" + FinalContents.getUserID();
                OkHttpPost okHttpPost = new OkHttpPost(storeUrl);
                String post = okHttpPost.post();
                Gson gson = new Gson();
                AddStoreNumBean addStoreNumBean = gson.fromJson(post, AddStoreNumBean.class);
                String storeNum = addStoreNumBean.getData().getStoreNum();
                add_broker_tv1.setText(storeNum);
                isOne = 1;
            } else {

            }

        }

    }


    @SingleClick(1000)
    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.add_broker_return:
                FinalContents.setStoreChange("");
                FinalContents.setMyAddType("");
                finish();
                break;
            case R.id.add_broker_img1:
                FinalContents.setImage1("0");
                initDataImg1();
                break;
            case R.id.add_broker_img2:
                FinalContents.setImage1("1");
                initDataImg2();
                break;
            case R.id.add_broker_rl1:
                intent = new Intent(AddStoreActivity.this, StoreListActivity.class);
                FinalContents.setMyAddType("公司");
                startActivity(intent);
                break;
            case R.id.add_store_rl2:
                getAddress();
                break;
            case R.id.add_broker_btn:
                initDatas();
                break;
            case R.id.add_broker_rl2:
                initState();
                break;
            case R.id.add_store_rl4:
                boolean locServiceEnable = isLocServiceEnable(AddStoreActivity.this);
                if (locServiceEnable == true) {
                    Log.i("MyCL", "定位服务已开启");
                    isnum = 1;
                    Intent intent = new Intent(AddStoreActivity.this, TestMapActivity.class);
                    intent.putExtra("La", getLatitude);
                    intent.putExtra("Lo", getLongitude);
                    startActivityForResult(intent, 1);
                } else {
                    Log.i("MyCL", "定位服务未开启");
                    Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivityForResult(intent, GPS_REQUEST_CODE);
                }
                break;

        }

    }

    public static boolean isLocServiceEnable(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        boolean gps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean network = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        return gps || network;
    }

    private void initState() {

        final List<String> list = new ArrayList<>();
        list.add("签约");
        list.add("装机");
        list.add("培训");
        list.add("维护");


        OptionsPickerView pvOptions = new OptionsPickerBuilder(AddStoreActivity.this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //              展示选中数据
                add_broker_tv3.setText(list.get(options1));
                if (options1 == 0) {
                    state = "1";
                } else if (options1 == 1) {
                    state = "2";
                } else if (options1 == 2) {
                    state = "3";
                } else if (options1 == 3) {
                    state = "4";
                }

            }
        })
                .setSelectOptions(0)
                .setOutSideCancelable(false)//点击背的地方不消失
                .build();//创建
        //      把数据绑定到控件上面
        pvOptions.setPicker(list);
        //      展示
        pvOptions.show();

    }

    private void initDataImg1() {

        AlertDialog.Builder builder = new AlertDialog.Builder(AddStoreActivity.this);
        builder.setTitle("请选择图片来源");
        builder.setItems(new String[]{"相机", "相册"}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == 0) {

                    try {
                        //检测是否有写的权限
                        int permission = ActivityCompat.checkSelfPermission(AddStoreActivity.this,
                                "android.permission.WRITE_EXTERNAL_STORAGE");
                        if (permission != PackageManager.PERMISSION_GRANTED) {
                            // 没有写的权限，去申请写的权限，
                            ActivityCompat.requestPermissions(AddStoreActivity.this, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
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
                        Uri photoUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
//TODO Uri图片转换成file类型的 start
                        files = uri2File(photoUri);

                        isPhoto = 1;
//TODO Uri图片转换成file类型的 end

                        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                        startActivityForResult(intent, 1);
                    } else {

                    }
                } else if (i == 1) {
                    Intent getAlbum = new Intent(Intent.ACTION_GET_CONTENT);
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

    }

    private void initDataImg2() {
        AlertDialog.Builder builder = new AlertDialog.Builder(AddStoreActivity.this);
        builder.setTitle("请选择图片来源");
        builder.setItems(new String[]{"相机", "相册"}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == 0) {

                    try {
                        //检测是否有写的权限
                        int permission = ActivityCompat.checkSelfPermission(AddStoreActivity.this,
                                "android.permission.WRITE_EXTERNAL_STORAGE");
                        if (permission != PackageManager.PERMISSION_GRANTED) {
                            // 没有写的权限，去申请写的权限，
                            ActivityCompat.requestPermissions(AddStoreActivity.this, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
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
                        Uri photoUri = AddStoreActivity.this.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
//TODO Uri图片转换成file类型的 start
                        files = uri2File(photoUri);

                        if (isPhoto == 0) {
                            isPhoto = 1;
                        }


//TODO Uri图片转换成file类型的 end

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

    }

    private void initDatas() {

        if (FinalContents.getStoreChange().equals("")) {
            s1 = add_broker_et2.getText().toString();
            s2 = add_broker_et3.getText().toString();
            s3 = add_broker_et4.getText().toString();
            s = add_broker_tv1.getText().toString();
            s4 = add_broker_tv2.getText().toString();
            s5 = add_broker_tv4.getText().toString();
            s6 = add_broker_tv3.getText().toString();
            if (add_broker_rb1.isChecked()) {
                flag = 0;
            }
            if (add_broker_rb2.isChecked()) {
                flag = 1;
            }
            if (add_broker_rb3.isChecked()) {
                flag = 2;
            }
            if (add_broker_rb4.isChecked()) {
                flag = 3;
            }
            if (s1.equals("") || s2.equals("") || s3.equals("") || s4.equals("") || s5.equals("")) {
                ToastUtil.showLongToast(AddStoreActivity.this, "带*号的数据请填写完整");
            } else {
                if (add_broker_rl2.getVisibility() == View.VISIBLE) {
                    if (s6.equals("")) {
                        ToastUtil.showLongToast(AddStoreActivity.this, "带*号的数据请填写完整");
                    } else {
                        initAddData1();
                    }
                } else {
                    initAddData1();
                }
            }
        } else if (FinalContents.getStoreChange().equals("修改")) {

            s1 = add_broker_et2.getText().toString();
            s2 = add_broker_et3.getText().toString();
            s3 = add_broker_et4.getText().toString();
            s = add_broker_tv1.getText().toString();
            s4 = add_broker_tv2.getText().toString();
            s5 = add_broker_tv4.getText().toString();
            s6 = add_broker_tv3.getText().toString();

            if (add_broker_rb1.isChecked()) {
                flag = 0;
            }
            if (add_broker_rb2.isChecked()) {
                flag = 1;
            }
            if (add_broker_rb3.isChecked()) {
                flag = 2;
            }
            if (add_broker_rb4.isChecked()) {
                flag = 3;
            }
            if (getLongitude.equals("")) {

            } else {
                myLocation = (getLongitude + "," + getLatitude);
            }
            if (s1.equals("") || s2.equals("") || s3.equals("") || s4.equals("") || s5.equals("")) {
                Toast.makeText(AddStoreActivity.this, "带*号的数据请填写完整", Toast.LENGTH_SHORT).show();
            } else {
                if (add_broker_rl2.getVisibility() == View.VISIBLE) {
                    if (s6.equals("")) {
                        Toast.makeText(AddStoreActivity.this, "带*号的数据请填写完整", Toast.LENGTH_SHORT).show();
                    } else {
                        initAddData2();
                    }
                } else {
                    initAddData2();
                }
            }

        }


    }

    private void initAddData2() {

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<AddStoreBean> addStoreBean;
        Log.i("修改门店", "storeManage.getId()：" + storeManage.getId());
        Log.i("修改门店", "s：" + s);
        Log.i("修改门店", "s1：" + s1);
        Log.i("修改门店", "s2：" + s2);
        Log.i("修改门店", "s3：" + s3);
        Log.i("修改门店", "myLocation：" + myLocation);
        Log.i("修改门店", "url1：" + url1);
        Log.i("修改门店", "url2：" + url2);
        Log.i("修改门店", "flag：" + flag);
        Log.i("修改门店", "state：" + state);
        Log.i("修改门店", " FinalContents.getCompanyManageId()：" + FinalContents.getCompanyManageId());
        Log.i("修改门店", " FinalContents.getUserID()：" + FinalContents.getUserID());
        if (add_broker_rl2.getVisibility() == View.VISIBLE) {
            addStoreBean = fzbInterface.getAddStoreBean(storeManage.getId(), s, s1, s2, s3, myLocation, url1, url2, flag + "", state, FinalContents.getCompanyManageId(), FinalContents.getUserID());
        } else {
            addStoreBean = fzbInterface.getAddStoreBean(storeManage.getId(), s, s1, s2, s3, myLocation, url1, url2, flag + "", "", FinalContents.getCompanyManageId(), FinalContents.getUserID());
        }
        addStoreBean.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AddStoreBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(AddStoreBean addStoreBean) {
                        if (addStoreBean.getData().getMessage().equals("保存成功")) {
                            ToastUtil.showLongToast(AddStoreActivity.this, addStoreBean.getData().getMessage());
                            finish();
                            FinalContents.setStoreChange("");
                        } else {
                            ToastUtil.showLongToast(AddStoreActivity.this, addStoreBean.getData().getMessage());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("门店","门店添加错误信息：" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private void initAddData1() {

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<AddStoreBean> addStoreBean;
//        Log.i("修改门店", "storeManage.getId()：" + storeManage.getId());
        Log.i("修改门店", "s：" + s);
        Log.i("修改门店", "s1：" + s1);
        Log.i("修改门店", "s2：" + s2);
        Log.i("修改门店", "s3：" + s3);
        Log.i("修改门店", "myLocation：" + getLongitude + "," + getLatitude);
        Log.i("修改门店", "url1：" + url1);
        Log.i("修改门店", "url2：" + url2);
        Log.i("修改门店", "flag：" + flag);
        Log.i("修改门店", "state：" + state);
        Log.i("修改门店", " FinalContents.getCompanyManageId()：" + FinalContents.getCompanyManageId());
        Log.i("修改门店", " FinalContents.getUserID()：" + FinalContents.getUserID());
        if (add_broker_rl2.getVisibility() == View.VISIBLE) {
            addStoreBean = fzbInterface.getAddStoreBean("", s, s1, s2, s3, (getLongitude + "," + getLatitude), url1, url2, flag + "", state, FinalContents.getCompanyManageId(), FinalContents.getUserID());
        } else {
            addStoreBean = fzbInterface.getAddStoreBean("", s, s1, s2, s3, (getLongitude + "," + getLatitude), url1, url2, flag + "", "", FinalContents.getCompanyManageId(), FinalContents.getUserID());
        }
        addStoreBean.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AddStoreBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(AddStoreBean addStoreBean) {
                        if (addStoreBean.getData().getMessage().equals("保存成功")) {
                            ToastUtil.showLongToast(AddStoreActivity.this, addStoreBean.getData().getMessage());
                            finish();
                            FinalContents.setStoreChange("");
                        } else {
                            ToastUtil.showLongToast(AddStoreActivity.this, addStoreBean.getData().getMessage());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("门店","门店添加错误信息：" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    /*TODO 添加市/区*/
    private void getAddress() {

        CityPicker cityPicker = new CityPicker.Builder(AddStoreActivity.this)
                .textSize(14)
                .title("地址选择")
                .titleBackgroundColor("#FFFFFF")
                .confirTextColor("#696969")
                .cancelTextColor("#696969")
                .province("江苏省")
                .city("常州市")
                .district("天宁区")
                .textColor(Color.parseColor("#000000"))
                .provinceCyclic(true)
                .cityCyclic(false)
                .districtCyclic(false)
                .visibleItemsCount(7)
                .itemPadding(10)
                .onlyShowProvinceAndCity(false)
                .build();
        cityPicker.show();

        //监听事件，获取结果
        cityPicker.setOnCityItemClickListener(new CityPicker.OnCityItemClickListener() {
            @Override
            public void onSelected(String... citySelected) {
                //省份
                String province = citySelected[0];
                //城市
                String city = citySelected[1];
                //区县（如果设定了两级联动，那么该项返回空）
                String district = citySelected[2];
                //邮编
                String code = citySelected[3];
                //为展示区赋值
                add_broker_et3.setText(province + "/" + city.trim() + "/" + district.trim());
            }
        });

    }

    //TODO 从相册获取图片
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

//TODO  获取相册图片地址
        if (resultCode != RESULT_OK) {        //此处的 RESULT_OK 是系统自定义得一个常量
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
//                if (ifsearch == 1) {

//                } else {
//                    Glide.with(AddStoreActivity.this).load(bm).into(add_broker_img2);
//                }
                final File san = saveFile(bm, sum + ".png");
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
                        Observable<AddPhotoBean> addPhoto = fzbInterface.getAddPhoto(FinalContents.getUserID(), "zhuanyuan", part);
                        addPhoto.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Observer<AddPhotoBean>() {
                                    @Override
                                    public void onSubscribe(Disposable d) {

                                    }

                                    @Override
                                    public void onNext(AddPhotoBean addPhotoBean) {
                                        if (FinalContents.getImage1().equals("0")) {
                                            url1 = addPhotoBean.getData().getUrl();
                                            Log.i("修改门店", "url1：" + url1);
                                            Log.i("修改门店", "url2：" + url2);
                                            Glide.with(AddStoreActivity.this).load(FinalContents.getImageUrl() + url1).into(add_broker_img1);
                                        } else if (FinalContents.getImage1().equals("1")) {
                                            url2 = addPhotoBean.getData().getUrl();
                                            Log.i("修改门店", "url1：" + url1);
                                            Log.i("修改门店", "url2：" + url2);
                                            Glide.with(AddStoreActivity.this).load(FinalContents.getImageUrl() + url2).into(add_broker_img2);
                                        }
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


        if (isnum == 1) {
            if (requestCode == 1 && resultCode == RESULT_OK) {
                add_broker_tv4.setText(data.getStringExtra("getLongitude") + "," + data.getStringExtra("getLatitude"));
            }

            getLatitude = data.getStringExtra("getLatitude");
            getLongitude = data.getStringExtra("getLongitude");


            StringBuffer stringBuffer1 = new StringBuffer();
            StringBuffer stringBuffer2 = new StringBuffer();

            StringBuffer append1 = stringBuffer1.append(getLatitude);
            StringBuffer append2 = stringBuffer2.append(getLongitude);

            Retrofit.Builder builder = new Retrofit.Builder();
            builder.baseUrl(FinalContents.getBaseUrl());
            builder.addConverterFactory(GsonConverterFactory.create());
            builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
            Retrofit build = builder.build();
            MyService fzbInterface = build.create(MyService.class);

            Observable<ChangeAddress> changeAddress = fzbInterface.getChangeAddress(getLongitude, getLatitude);
            changeAddress.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<ChangeAddress>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(ChangeAddress changeAddress) {

                            add_broker_et4.setText(changeAddress.getData().getValue());

                        }

                        @Override
                        public void onError(Throwable e) {

//                            ToastUtil.showToast(AddStoreActivity.this,"请重新定位详情地址");
                            Log.i("经纬度转坐标", "经纬度转坐标错误信息：" + e.getMessage());

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }

        isnum = 0;

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

//        initData();

        add_broker_tv2.setText(FinalContents.getAddtype2());

        if (isPhoto == 1) {

            new Thread() {
                @Override
                public void run() {

                    Retrofit.Builder builder = new Retrofit.Builder();
                    builder.baseUrl(FinalContents.getBaseUrl());
                    builder.addConverterFactory(GsonConverterFactory.create());
                    builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
                    Retrofit build = builder.build();
                    MyService fzbInterface = build.create(MyService.class);
                    RequestBody requestBody = RequestBody.create(MediaType.parse("image/png/jpg"), files);
                    MultipartBody.Part part = MultipartBody.Part.createFormData("file", files.getName(), requestBody);
                    final Observable<AddPhotoBean> addPhoto = fzbInterface.getAddPhoto(FinalContents.getUserID(), "cesda", part);
                    addPhoto.subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Observer<AddPhotoBean>() {
                                @Override
                                public void onSubscribe(Disposable d) {

                                }

                                @Override
                                public void onNext(AddPhotoBean addPhotoBean) {
                                    if (FinalContents.getImage1().equals("0")) {
                                        url1 = addPhotoBean.getData().getUrl();
                                        Glide.with(AddStoreActivity.this).load(FinalContents.getImageUrl() + url1).into(add_broker_img1);
                                    } else if (FinalContents.getImage1().equals("1")) {
                                        url2 = addPhotoBean.getData().getUrl();
                                        Glide.with(AddStoreActivity.this).load(FinalContents.getImageUrl() + url2).into(add_broker_img2);
                                    }
                                    isPhoto = 0;
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

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideKeyboard(v, ev)) {
                boolean res = hideKeyboard(v.getWindowToken());
                if (res) {
                    //隐藏了输入法，则不再分发事件
                    return true;
                }
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时则不能隐藏
     *
     * @param v
     * @param event
     * @return
     */
    private boolean isShouldHideKeyboard(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0],
                    top = l[1],
                    bottom = top + v.getHeight(),
                    right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击EditText的事件，忽略它。
                return false;
            } else {
                return true;
            }
        }
        // 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditText上，和用户用轨迹球选择其他的焦点
        return false;
    }

    /**
     * 获取InputMethodManager，隐藏软键盘
     *
     * @param token
     */
    private boolean hideKeyboard(IBinder token) {
        if (token != null) {
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            return im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
        return false;
    }
}

package com.xcy.fzbcity.all.view;

import android.annotation.SuppressLint;
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
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.xcy.fzbcity.all.utils.ToastUtil;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bumptech.glide.Glide;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.api.ProjectProgressApi;
import com.xcy.fzbcity.all.modle.AddPhotoBean;
import com.xcy.fzbcity.all.modle.LandBean;
import com.xcy.fzbcity.all.persente.SingleClick;
import com.xcy.fzbcity.all.persente.StatusBar;
import com.xcy.fzbcity.all.service.MyService;
import com.xcy.fzbcity.all.utils.CommonUtil;
import com.xcy.fzbcity.all.utils.ToastUtil;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

//TODO 申请登岛
public class ToApplyForAnIslandActivity extends AllActivity implements View.OnClickListener {

    TextView to_apply_for_an_island_tv1;
    TextView to_apply_for_an_island_tv2;
    TextView to_apply_for_an_island_tv3;
    TextView to_apply_for_an_island_tv4;

    RelativeLayout to_apply_for_an_island_return;
    ImageView to_apply_for_an_island_img;

    EditText to_apply_for_an_island_et1;
    EditText to_apply_for_an_island_et2;

    RelativeLayout to_apply_for_an_island_rl1;
    RelativeLayout to_apply_for_an_island_rl2;

    LinearLayout to_apply_for_an_island_ll2;

    Button to_apply_for_an_island_btn;
    private Intent intent;

    int isPhoto = 0;
    private final String IMAGE_TYPE = "image/*";
    private final int IMAGE_CODE = 0;
    private Bitmap bm;
    //    TODO 上传服务器图片地址
    String imgUrl = "";
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE"};
    private File file;
    private boolean enabled;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_apply_for_an_island);
        init_No_Network();
    }

    private void init_No_Network(){
        boolean networkAvailable = CommonUtil.isNetworkAvailable(this);
        if (networkAvailable) {
            if (ProjectProgressApi.getComplemented().equals("0")) {

            } else if (ProjectProgressApi.getComplemented().equals("1")) {
                initComplemented();
            }


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

    @SuppressLint("WrongViewCast")
    private void initView() {

        StatusBar.makeStatusBarTransparent(this);

        to_apply_for_an_island_tv1 = findViewById(R.id.to_apply_for_an_island_tv1);
        to_apply_for_an_island_tv2 = findViewById(R.id.to_apply_for_an_island_tv2);
        to_apply_for_an_island_tv3 = findViewById(R.id.to_apply_for_an_island_tv3);
        to_apply_for_an_island_tv4 = findViewById(R.id.to_apply_for_an_island_tv4);

        to_apply_for_an_island_return = findViewById(R.id.to_apply_for_an_island_return);
        to_apply_for_an_island_img = findViewById(R.id.to_apply_for_an_island_img);

        to_apply_for_an_island_et1 = findViewById(R.id.to_apply_for_an_island_et1);
        to_apply_for_an_island_et2 = findViewById(R.id.to_apply_for_an_island_et2);

        to_apply_for_an_island_rl1 = findViewById(R.id.to_apply_for_an_island_rl1);
        to_apply_for_an_island_rl2 = findViewById(R.id.to_apply_for_an_island_rl2);

        to_apply_for_an_island_ll2 = findViewById(R.id.to_apply_for_an_island_ll2);

        to_apply_for_an_island_btn = findViewById(R.id.to_apply_for_an_island_btn);
        to_apply_for_an_island_return.setOnClickListener(this);
        to_apply_for_an_island_img.setOnClickListener(this);
        to_apply_for_an_island_rl1.setOnClickListener(this);
        to_apply_for_an_island_rl2.setOnClickListener(this);
        to_apply_for_an_island_btn.setOnClickListener(this);

        if (FinalContents.getProjectType().equals("2")) {
            //  TODO 海外
            to_apply_for_an_island_ll2.setVisibility(View.VISIBLE);
            to_apply_for_an_island_img.setBackgroundResource(R.mipmap.wx_hz);
        } else if (FinalContents.getProjectType().equals("3")) {
            //  TODO 旅居
            to_apply_for_an_island_ll2.setVisibility(View.GONE);
            to_apply_for_an_island_img.setBackgroundResource(R.mipmap.wx_sfz);
        }

        to_apply_for_an_island_tv1.setText(ProjectProgressApi.getCustomerName());
        to_apply_for_an_island_et1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 18) {
                    int age = getAge(to_apply_for_an_island_et1.getText().toString());
                    to_apply_for_an_island_tv3.setText(age + "");
                } else {
                    to_apply_for_an_island_tv3.setText("");
                }
            }
        });
    }

    @SingleClick(1000)
    @Override
    public void onClick(View view) {

        switch (view.getId()) {
// TODO 返回上一層
            case R.id.to_apply_for_an_island_return:
                finish();
                break;
// TODO 請選擇性別
            case R.id.to_apply_for_an_island_rl1:
                if(enabled){
                    List<String> list = new ArrayList<>();
                    list.add("男");
                    list.add("女");
                    initSelect(list, to_apply_for_an_island_tv2);
                }
                break;
// TODO 客戶基本信息描摹
            case R.id.to_apply_for_an_island_rl2:
                ProjectProgressApi.setField("0");
                intent = new Intent(ToApplyForAnIslandActivity.this, EssentialInformationActivity.class);
                startActivity(intent);
                break;
//TODO 下一步
            case R.id.to_apply_for_an_island_btn:
                if (!to_apply_for_an_island_et1.getText().toString().equals("")) {
                    if (to_apply_for_an_island_tv2.getText().toString().equals("男") || to_apply_for_an_island_tv2.getText().toString().equals("女")) {
                        if (!imgUrl.equals("")) {
                            if (!to_apply_for_an_island_tv3.getText().toString().equals("")) {
                                if (FinalContents.getProjectType().equals("2")) {
                                    //  TODO 海外
                                    if (!to_apply_for_an_island_et2.getText().toString().equals("")) {
                                        intent = new Intent(ToApplyForAnIslandActivity.this, ToApplyForAnlsland2Activity.class);
                                        ProjectProgressApi.setGender(to_apply_for_an_island_tv2.getText().toString());        //      TODO    性别
                                        ProjectProgressApi.setIdNumber(to_apply_for_an_island_et1.getText().toString());        //      TODO    身份证号码
                                        ProjectProgressApi.setAge(to_apply_for_an_island_tv3.getText().toString());        //      TODO    年龄
                                        ProjectProgressApi.setPassportNumber(to_apply_for_an_island_et2.getText().toString());        //      TODO    护照号码
                                        ProjectProgressApi.setPassportimg(imgUrl);        //      TODO    身份证或护照 照片
                                        startActivity(intent);
                                    } else {
                                        ToastUtil.showToast(this, "请输入护照编号");
                                    }
                                } else if (FinalContents.getProjectType().equals("3")) {
                                    //  TODO 旅居
                                    intent = new Intent(ToApplyForAnIslandActivity.this, ToApplyForAnlsland2Activity.class);
                                    ProjectProgressApi.setGender(to_apply_for_an_island_tv2.getText().toString());        //      TODO    性别
                                    ProjectProgressApi.setIdNumber(to_apply_for_an_island_et1.getText().toString());        //      TODO    身份证号码
                                    ProjectProgressApi.setAge(to_apply_for_an_island_tv3.getText().toString());        //      TODO    年龄
                                    ProjectProgressApi.setPassportNumber(to_apply_for_an_island_et2.getText().toString());        //      TODO    护照号码
                                    ProjectProgressApi.setPassportimg(imgUrl);        //      TODO    身份证或护照 照片
                                    startActivity(intent);
                                } else if (FinalContents.getProjectType().equals("1")) {
                                    //  TODO 城市
                                    intent = new Intent(ToApplyForAnIslandActivity.this, ToApplyForAnlsland2Activity.class);
                                    ProjectProgressApi.setGender(to_apply_for_an_island_tv2.getText().toString());        //      TODO    性别
                                    ProjectProgressApi.setIdNumber(to_apply_for_an_island_et1.getText().toString());        //      TODO    身份证号码
                                    ProjectProgressApi.setAge(to_apply_for_an_island_tv3.getText().toString());        //      TODO    年龄
                                    ProjectProgressApi.setPassportNumber(to_apply_for_an_island_et2.getText().toString());        //      TODO    护照号码
                                    ProjectProgressApi.setPassportimg(imgUrl);        //      TODO    身份证或护照 照片
                                    startActivity(intent);
                                }

                            } else {
                                ToastUtil.showToast(this, "请输入年龄");
                            }
                        }else {
                            ToastUtil.showToast(this, "请上传照片");
                        }
                    } else {
                        ToastUtil.showToast(this, "请选择性别");
                    }
                } else {
                    ToastUtil.showToast(this, "请输入身份证");
                }
                break;
            //TODO 图片
            case R.id.to_apply_for_an_island_img:
                if(enabled){
                    initAlot();
                }
                break;
        }

    }

    //    TODO 照片选择
    private void initAlot() {

        AlertDialog.Builder builder = new AlertDialog.Builder(ToApplyForAnIslandActivity.this);
        builder.setTitle("请选择图片来源");
        builder.setItems(new String[]{"相机", "相册"}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == 0) {
                    try {
                        //检测是否有写的权限
                        int permission = ActivityCompat.checkSelfPermission(ToApplyForAnIslandActivity.this,
                                "android.permission.WRITE_EXTERNAL_STORAGE");
                        if (permission != PackageManager.PERMISSION_GRANTED) {
                            // 没有写的权限，去申请写的权限，会弹出对话框
                            ActivityCompat.requestPermissions(ToApplyForAnIslandActivity.this, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
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
                        Uri photoUri = ToApplyForAnIslandActivity.this.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                        Log.i("MyCL", "图片路径：" + photoUri);
                        file = uri2File(photoUri);

                        isPhoto = 1;

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

    //由出生日期获得年龄
    public static int getAge(String number) {
        String num = number.substring(6, 14);
        Calendar cal = Calendar.getInstance();
        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH);
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);

        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        try {
            cal.setTime(format.parse(num));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

        int age = yearNow - yearBirth;

        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth) age--;
            } else {
                age--;
            }
        }
        return age;
    }

    //选择器
    private void initSelect(final List<String> list, final TextView textView) {
        //      监听选中
        hideInput();
        OptionsPickerView pvOptions = new OptionsPickerBuilder(ToApplyForAnIslandActivity.this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //               返回的分别是三个级别的选中位置
                //              展示选中数据
                textView.setText(list.get(options1));
            }
        })
                .setSelectOptions(0)//设置选择第一个
                .setOutSideCancelable(false)//点击背的地方不消失
                .build();//创建
        //      把数据绑定到控件上面
        pvOptions.setPicker(list);
        //      展示
        pvOptions.show();
    }

    private void initComplemented() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<LandBean> clientFragmentBean = fzbInterface.getLand(FinalContents.getUserID(), FinalContents.getPreparationId());
        clientFragmentBean.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LandBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LandBean landBean) {

                        if (landBean.getData().getAuditStatus().equals("0")) {
                            to_apply_for_an_island_et1.setEnabled(true);
                            to_apply_for_an_island_et2.setEnabled(true);
                            enabled = true;
                        } else if (landBean.getData().getAuditStatus().equals("1") || landBean.getData().getAuditStatus().equals("3")) {
                            to_apply_for_an_island_et1.setEnabled(false);
                            to_apply_for_an_island_et2.setEnabled(false);
                            enabled = false;
                        }

                        to_apply_for_an_island_tv1.setText(landBean.getData().getFfServerNewspaperPreparation().getCustomerName());
                        to_apply_for_an_island_tv2.setText(landBean.getData().getGender());
                        to_apply_for_an_island_et1.setText(landBean.getData().getIdNumber());
                        to_apply_for_an_island_tv3.setText(landBean.getData().getAge());
                        to_apply_for_an_island_et2.setText(landBean.getData().getPassportNumber());
                        imgUrl = landBean.getData().getPassportImg();
                        Glide.with(ToApplyForAnIslandActivity.this).load(FinalContents.getImageUrl() + landBean.getData().getPassportImg()).into(to_apply_for_an_island_img);
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

    //TODO 获取相册图片
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

                Log.i("MyCL", "图片uri" + originalUri);
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
                        Log.i("MyCL", "addPhoto：" + addPhoto.toString());
                        addPhoto.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Observer<AddPhotoBean>() {
                                    @Override
                                    public void onSubscribe(Disposable d) {

                                    }

                                    @Override
                                    public void onNext(AddPhotoBean addPhotoBean) {
                                        imgUrl = addPhotoBean.getData().getUrl();
                                        Glide.with(ToApplyForAnIslandActivity.this).load(FinalContents.getImageUrl() + imgUrl).into(to_apply_for_an_island_img);
                                        to_apply_for_an_island_tv4.setVisibility(View.GONE);
                                        Log.i("MyCL", "解析完成后图片路径：" + imgUrl);

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
                Log.i("MyCL", "图片路径：" + path);
            } catch (IOException e) {

                Log.e("MyCL", e.toString());

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
    protected void onRestart() {
        super.onRestart();
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

                    RequestBody requestBody = RequestBody.create(MediaType.parse("image/png"), file);

                    MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), requestBody);

                    Observable<AddPhotoBean> addPhoto = fzbInterface.getAddPhoto(FinalContents.getUserID(), "myClient", part);
                    Log.i("MyCL", "addPhoto：" + addPhoto.toString());
                    addPhoto.subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Observer<AddPhotoBean>() {
                                @Override
                                public void onSubscribe(Disposable d) {

                                }

                                @Override
                                public void onNext(AddPhotoBean addPhotoBean) {
                                    imgUrl = addPhotoBean.getData().getUrl();
                                    Log.i("MyCL", "解析完成后图片路径：" + imgUrl);
                                    Glide.with(ToApplyForAnIslandActivity.this).load(FinalContents.getImageUrl() + imgUrl).into(to_apply_for_an_island_img);
                                    to_apply_for_an_island_tv4.setVisibility(View.GONE);
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
    protected void onResume() {
        super.onResume();
        if (FinalContents.getDengDao().equals("1")) {
            finish();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        FinalContents.setDengDao("0");
    }

    /**
     * 隐藏键盘
     */
    protected void hideInput() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        View v = getWindow().peekDecorView();
        if (null != v) {
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }

}

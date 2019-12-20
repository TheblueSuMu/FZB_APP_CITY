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
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.api.ProjectProgressApi;
import com.xcy.fzbcity.all.database.ColleagueBean;
import com.xcy.fzbcity.all.database.FieldBean;
import com.xcy.fzbcity.all.modle.AddPhotoBean;
import com.xcy.fzbcity.all.modle.LandSaveBean;
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

//TODO 同行人员
public class FieldActivity extends AllActivity implements View.OnClickListener {

    RelativeLayout field_return;
    ImageView field_img;

    Button field_btn;

    TextView field_tv1;
    TextView field_tv2;

    EditText field_et1;
    EditText field_et2;
    EditText field_et3;
    EditText field_et4;
    EditText field_et5;

    RelativeLayout field_rll;
    RelativeLayout field_rl2;
    private String et1;
    private String et2;
    private String et3;
    private String et4;
    private String et5;
    private String sex;
    private FieldBean fieldBean = new FieldBean();
    private FieldBean fieldBean2 = new FieldBean();
    private List<FieldBean> list = new ArrayList<>();

    //  TODO    图片
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
    private LinearLayout field_ll;
    //  TODO    图片

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_field);
        init_No_Network();
    }

    private void init_No_Network(){
        boolean networkAvailable = CommonUtil.isNetworkAvailable(this);
        if (networkAvailable) {
            initView();
            if (ProjectProgressApi.getComplemented().equals("1")) {
                initData();
            } else if (ProjectProgressApi.getComplemented().equals("0")){
                field_et1.setText(ProjectProgressApi.getFieldBean().getFullName());
                if (ProjectProgressApi.getFieldBean().getGender() != null) {
                    if (ProjectProgressApi.getFieldBean().getGender().equals("")) {
                        field_tv1.setText("请选择性别");
                    }else {
                        field_tv1.setText(ProjectProgressApi.getFieldBean().getGender());
                    }
                }
                field_et2.setText(ProjectProgressApi.getFieldBean().getRelation());
                field_et3.setText(ProjectProgressApi.getFieldBean().getPhone());
                field_et4.setText(ProjectProgressApi.getFieldBean().getIdNumber());
                field_et5.setText(ProjectProgressApi.getFieldBean().getPassportNumber());
            }
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

        field_return = findViewById(R.id.field_return);

        field_img = findViewById(R.id.field_img);

        field_btn = findViewById(R.id.field_btn);

        field_tv1 = findViewById(R.id.field_tv1);
        field_tv2 = findViewById(R.id.field_tv2);

        field_et1 = findViewById(R.id.field_et1);
        field_et2 = findViewById(R.id.field_et2);
        field_et3 = findViewById(R.id.field_et3);
        field_et4 = findViewById(R.id.field_et4);
        field_et5 = findViewById(R.id.field_et5);

        field_rll = findViewById(R.id.field_rl1);
        field_rl2 = findViewById(R.id.field_rl2);
        field_ll = findViewById(R.id.field_ll);

        if (FinalContents.getProjectType().equals("2")) {
            //  TODO 海外
            field_ll.setVisibility(View.VISIBLE);
            field_img.setImageResource(R.mipmap.wx_hz);
        } else if (FinalContents.getProjectType().equals("3")) {
            //  TODO 旅居
            field_ll.setVisibility(View.GONE);
            field_img.setImageResource(R.mipmap.wx_sfz);
        }

        field_return.setOnClickListener(this);
        field_tv1.setOnClickListener(this);
        field_rll.setOnClickListener(this);
        field_rl2.setOnClickListener(this);
        field_btn.setOnClickListener(this);
        field_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    @SingleClick(1000)
    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            //            TODO 选择性别
            case R.id.field_tv1:
                initGender();
                break;
            //            TODO 添加图片
            case R.id.field_rl2:
                initAlot();
                break;
            //            TODO 客户基本信息描摹
            case R.id.field_rl1:
                ProjectProgressApi.setField("1");
                Intent intent = new Intent(FieldActivity.this,EssentialInformationActivity.class);
                startActivity(intent);
                break;
            //            TODO 完成
            case R.id.field_btn:

                if (!MatcherUtils.isMobile(field_et3.getText().toString())) {
                    ToastUtil.showToast(this, "请输入正确的手机号");
                    return;
                } else {
                    if (field_et1.getText().toString().equals("")) {
                        ToastUtil.showToast(this, "请输入客户名");
                        return;
                    }

                    if (field_tv1.getText().toString().equals("")) {
                        ToastUtil.showToast(this, "请输入性别");
                        return;
                    }

                    if (field_et2.getText().toString().equals("")) {
                        ToastUtil.showToast(this, "请输入关系");
                        return;
                    }


                    if (field_et3.getText().toString().equals("")) {
                        ToastUtil.showToast(this, "请输入手机号");
                        return;
                    }

                    if (field_et4.getText().toString().equals("")) {
                        ToastUtil.showToast(this, "请输入身份证号码");
                        return;
                    }

                    if (FinalContents.getProjectType().equals("2")) {
                        if (field_et5.getText().toString().equals("")) {
                            ToastUtil.showToast(this, "请输入护照号码");
                            return;
                        }
                    }

                    if (imgUrl.equals("")) {
                        ToastUtil.showToast(this, "请选择照片");
                        return;
                    }

                    fieldBean = new FieldBean();
                    ProjectProgressApi.setChongfu("不重复");
                    fieldBean.setFullName(field_et1.getText().toString());      //  TODO    客户名
                    fieldBean.setGender(field_tv1.getText().toString());      //  TODO    性别
                    fieldBean.setRelation(field_et2.getText().toString());      //  TODO    关系
                    fieldBean.setPhone(field_et3.getText().toString());      //  TODO    手机号
                    fieldBean.setIdNumber(field_et4.getText().toString());      //  TODO    身份证号码
                    fieldBean.setPassportNumber(field_et5.getText().toString());      //  TODO    护照号码
                    fieldBean.setPassportImg(imgUrl);      //  TODO    照片地址

                    if (ProjectProgressApi.getFieldBean().getCity() != null) {
                        if (ProjectProgressApi.getFieldBean().getCity().equals("")) {

                        }else {
                            fieldBean.setCity(ProjectProgressApi.getFieldBean().getCity());
                            fieldBean.setOccupation(ProjectProgressApi.getFieldBean().getOccupation());
                            fieldBean.setFocus(ProjectProgressApi.getFieldBean().getFocus());
                            fieldBean.setIntentionalBuilding(ProjectProgressApi.getFieldBean().getIntentionalBuilding());
                            fieldBean.setPaymentMethod(ProjectProgressApi.getFieldBean().getPaymentMethod());
                            fieldBean.setHasDecision(ProjectProgressApi.getFieldBean().getHasDecision());
                            fieldBean.setResistance(ProjectProgressApi.getFieldBean().getResistance());
                            fieldBean.setObjective(ProjectProgressApi.getFieldBean().getObjective());
                            fieldBean.setIdealArea(ProjectProgressApi.getFieldBean().getIdealArea());
                        }
                    }

                    Log.i("同行人", "基本描摹信息："+fieldBean);
                    Log.i("同行人", "基本描摹信息："+fieldBean.getCity());
                    Log.i("同行人", "基本描摹信息："+fieldBean.getObjective());

                    ProjectProgressApi.setFieldBean(fieldBean);
                    ProjectProgressApi.setField("1");
                    if (ProjectProgressApi.getComplemented().equals("1")) {
                        initViewData();
                    }else if (ProjectProgressApi.getComplemented().equals("0")) {
                        fieldBean = ProjectProgressApi.getFieldBean();
                        list.add(fieldBean);
                        ProjectProgressApi.setFieldBeanList(list);
                    }
                    finish();
                }
                break;
        }

    }

    private void initGender(){
        final List<String> stringList = new ArrayList<>();
        stringList.add("男");
        stringList.add("女");
        //      监听选中
        OptionsPickerView pvOptions = new OptionsPickerBuilder(FieldActivity.this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //               返回的分别是三个级别的选中位置
                //              展示选中数据
                field_tv1.setText(stringList.get(options1));
            }
        })
                .setSelectOptions(0)//设置选择第一个
                .setOutSideCancelable(false)//点击背的地方不消失
                .build();//创建

        //      把数据绑定到控件上面
        pvOptions.setPicker(stringList);
        //      展示
        pvOptions.show();
    }

    private void initViewData(){
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<LandSaveBean> userMessage = fzbInterface.getColleagueUpdate(ProjectProgressApi.getFieldID(),ProjectProgressApi.getLandingId(),FinalContents.getPreparationId(),field_et1.getText().toString(),field_et3.getText().toString(),field_et4.getText().toString(),field_et5.getText().toString(),"",field_tv1.getText().toString(),field_et2.getText().toString(),fieldBean.getCity(),fieldBean.getOccupation(),fieldBean.getFocus(),fieldBean.getIntentionalBuilding(),fieldBean.getPaymentMethod(),fieldBean.getHasDecision(),fieldBean.getResistance(),fieldBean.getObjective(),fieldBean.getIdealArea(),FinalContents.getUserID());
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LandSaveBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @SuppressLint("WrongConstant")
                    @Override
                    public void onNext(LandSaveBean landSaveBean) {
                        ToastUtil.showToast(FieldActivity.this, landSaveBean.getData().getMessage());
                        finish();
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtil.showToast(FieldActivity.this, "请确定您的输入信息是否完整");
                        Log.i("列表数据获取错误","错误"+e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initData(){
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<ColleagueBean> clientFragmentBean = fzbInterface.getColleague(FinalContents.getUserID(), ProjectProgressApi.getFieldID());
        clientFragmentBean.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ColleagueBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ColleagueBean colleagueBean) {
                        field_et1.setText(colleagueBean.getData().getFullName());
                        field_tv1.setText(colleagueBean.getData().getGender());
                        field_et2.setText(colleagueBean.getData().getRelation());
                        field_et3.setText(colleagueBean.getData().getPhone());
                        field_et4.setText(colleagueBean.getData().getIdNumber());
                        field_et5.setText(colleagueBean.getData().getPassportNumber());
                        imgUrl = colleagueBean.getData().getPassportImg();
                        Glide.with(FieldActivity.this).load(FinalContents.getImageUrl() + colleagueBean.getData().getPassportImg()).into(field_img);
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

    //    TODO 照片选择
    private void initAlot() {

        AlertDialog.Builder builder = new AlertDialog.Builder(FieldActivity.this);
        builder.setTitle("请选择图片来源");
        builder.setItems(new String[]{"相机", "相册"}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == 0) {
                    try {
                        //检测是否有写的权限
                        int permission = ActivityCompat.checkSelfPermission(FieldActivity.this,
                                "android.permission.WRITE_EXTERNAL_STORAGE");
                        if (permission != PackageManager.PERMISSION_GRANTED) {
                            // 没有写的权限，去申请写的权限，会弹出对话框
                            ActivityCompat.requestPermissions(FieldActivity.this, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
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
                        Uri photoUri = FieldActivity.this.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
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

    //TODO 获取相册图片
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
                                        Glide.with(FieldActivity.this).load(FinalContents.getImageUrl() + imgUrl).into(field_img);
                                        field_tv2.setVisibility(View.GONE);

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
                    addPhoto.subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Observer<AddPhotoBean>() {
                                @Override
                                public void onSubscribe(Disposable d) {

                                }

                                @Override
                                public void onNext(AddPhotoBean addPhotoBean) {
                                    imgUrl = addPhotoBean.getData().getUrl();
                                    Glide.with(FieldActivity.this).load(FinalContents.getImageUrl() + imgUrl).into(field_img);
                                    field_tv2.setVisibility(View.GONE);
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
        fieldBean2 = ProjectProgressApi.getFieldBean();

        fieldBean2.setFullName(field_et1.getText().toString());      //  TODO    客户名
        fieldBean2.setGender(field_tv1.getText().toString());      //  TODO    性别
        fieldBean2.setRelation(field_et2.getText().toString());      //  TODO    关系
        fieldBean2.setPhone(field_et3.getText().toString());      //  TODO    手机号
        fieldBean2.setIdNumber(field_et4.getText().toString());      //  TODO    身份证号码
        fieldBean2.setPassportNumber(field_et5.getText().toString());      //  TODO    护照号码
        fieldBean2.setPassportImg(imgUrl);      //  TODO    照片地址

        if (fieldBean.getCity() != null) {
            if (fieldBean.getCity().equals("")) {

            }else {
                fieldBean2.setCity(fieldBean.getCity());
                fieldBean2.setOccupation(fieldBean.getOccupation());
                fieldBean2.setFocus(fieldBean.getFocus());
                fieldBean2.setIntentionalBuilding(fieldBean.getIntentionalBuilding());
                fieldBean2.setPaymentMethod(fieldBean.getPaymentMethod());
                fieldBean2.setHasDecision(fieldBean.getHasDecision());
                fieldBean2.setResistance(fieldBean.getResistance());
                fieldBean2.setObjective(fieldBean.getObjective());
                fieldBean2.setIdealArea(fieldBean.getIdealArea());
            }
        }


        ProjectProgressApi.setFieldBean(fieldBean2);
        Log.i("同行人", "基本描摹信息："+fieldBean2);
        Log.i("同行人", "基本描摹信息："+fieldBean2.getCity());
        Log.i("同行人", "基本描摹信息："+fieldBean2.getObjective());

    }
}

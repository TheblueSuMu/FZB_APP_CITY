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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.adapter.FieldAdapter;
import com.xcy.fzbcity.all.adapter.FieldBeanListAdapter;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.api.ProjectProgressApi;
import com.xcy.fzbcity.all.database.FieldBean;
import com.xcy.fzbcity.all.modle.AddPhotoBean;
import com.xcy.fzbcity.all.modle.GetLandLineBean;
import com.xcy.fzbcity.all.modle.GetLandLineTimeBean;
import com.xcy.fzbcity.all.modle.LandBean;
import com.xcy.fzbcity.all.modle.LandSaveBean;
import com.xcy.fzbcity.all.persente.MyLinearLayoutManager;
import com.xcy.fzbcity.all.persente.SingleClick;
import com.xcy.fzbcity.all.persente.StatusBar;
import com.xcy.fzbcity.all.service.MyService;
import com.xcy.fzbcity.all.utils.CommonUtil;
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

//TODO 申请登岛2
public class ToApplyForAnlsland2Activity extends AllActivity implements View.OnClickListener {

    RelativeLayout to_apply_for_an_island2_return;
    ImageView to_apply_for_an_island2_img2;
    ImageView to_apply_for_an_island2_img3;

    RelativeLayout to_apply_for_an_island2_rl1;
    RelativeLayout to_apply_for_an_island2_rl2;
    RelativeLayout to_apply_for_an_island2_rl3;
    RelativeLayout to_apply_for_an_island2_rl4;

    LinearLayout to_apply_for_an_island2_ll1;

    RecyclerView to_apply_for_an_island2_rv;

    TextView to_apply_for_an_island2_tv1;
    TextView to_apply_for_an_island2_tv3;
    TextView to_apply_for_an_island2_tv4;

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

    Button to_apply_for_an_island2_btn;
    private TextView getlandLine;
    private TextView getlandLinetime;
    private int index = 0;
    private String routeid = "";
    private String routeid2 = "";

    private String isColleague = "0";
    private int colleagueUserNum = 0;
    private String passportImg = "";
    private String route = "";
    private String isLandTime = "";
    private String isPay = "0";
    private String sumCost = "";
    private String landingImg = "";
    private String occupation = "";
    private String focus = "";
    private List<FieldBean> list = new ArrayList<>();
    private LinearLayout to_apply_for_an_island2_linear;

    int ifnum1 = 0;
    int ifnum2 = 0;
    int ifnum3 = 0;
    private String fieldbeanlist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_apply_for_anlsland2);
        init_No_Network();
    }

    private void init_No_Network() {
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

    private void initView() {

        StatusBar.makeStatusBarTransparent(this);
        to_apply_for_an_island2_linear = findViewById(R.id.to_apply_for_an_island2_linear);
        to_apply_for_an_island2_return = findViewById(R.id.to_apply_for_an_island2_return);
        to_apply_for_an_island2_img2 = findViewById(R.id.to_apply_for_an_island2_img2);
        to_apply_for_an_island2_img3 = findViewById(R.id.to_apply_for_an_island2_img3);
        to_apply_for_an_island2_rl1 = findViewById(R.id.to_apply_for_an_island2_rl1);
        to_apply_for_an_island2_rl2 = findViewById(R.id.to_apply_for_an_island2_rl2);
        to_apply_for_an_island2_rl3 = findViewById(R.id.to_apply_for_an_island2_rl3);
        to_apply_for_an_island2_rl4 = findViewById(R.id.to_apply_for_an_island2_rl4);

        to_apply_for_an_island2_ll1 = findViewById(R.id.to_apply_for_an_island2_ll1);
        to_apply_for_an_island2_rv = findViewById(R.id.to_apply_for_an_island2_rv);

        to_apply_for_an_island2_tv1 = findViewById(R.id.to_apply_for_an_island2_tv1);
        to_apply_for_an_island2_tv3 = findViewById(R.id.to_apply_for_an_island2_tv3);
        to_apply_for_an_island2_tv4 = findViewById(R.id.to_apply_for_an_island2_tv4);
        to_apply_for_an_island2_btn = findViewById(R.id.to_apply_for_an_island2_btn);
        getlandLine = findViewById(R.id.getlandLine);
        getlandLinetime = findViewById(R.id.getlandLinetime);

        to_apply_for_an_island2_return.setOnClickListener(this);
        to_apply_for_an_island2_img2.setOnClickListener(this);
        to_apply_for_an_island2_rl1.setOnClickListener(this);
        to_apply_for_an_island2_rl2.setOnClickListener(this);
        to_apply_for_an_island2_rl3.setOnClickListener(this);
        to_apply_for_an_island2_rl4.setOnClickListener(this);
        to_apply_for_an_island2_btn.setOnClickListener(this);
        to_apply_for_an_island2_return.setOnClickListener(new View.OnClickListener() {
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
            //            TODO 添加同行人员
            case R.id.to_apply_for_an_island2_img2:
                ProjectProgressApi.setIndex(999999);
                ProjectProgressApi.setFieldBean(new FieldBean());
                ProjectProgressApi.setChongfu("重复");
                Intent intent = new Intent(ToApplyForAnlsland2Activity.this, FieldActivity.class);
                startActivity(intent);
                break;
            //            TODO 选择路线
            case R.id.to_apply_for_an_island2_rl1:
                if (ifnum1 == 0) {
                    ifnum1 = 1;
                    initGetLandLine();
                    ifnum1 = 0;
                }
                break;
            //            TODO 登岛时间
            case R.id.to_apply_for_an_island2_rl2:
                if (ifnum2 == 0) {
                    ifnum2 = 1;
                    initGetLandLineTime();
                    ifnum2 = 0;
                }
                break;
            //            TODO 添加图片
            case R.id.to_apply_for_an_island2_rl3:
                ProjectProgressApi.setChongfu("重复");
                initAlot();
                break;
            //            TODO 完成
            case R.id.to_apply_for_an_island2_btn:
                if (ifnum3 == 0) {
                    ifnum3 = 1;
                    ProjectProgressApi.setChongfu("重复");
                    if (ProjectProgressApi.getComplemented().equals("0")) {
                        if (!to_apply_for_an_island2_tv1.getText().toString().equals("")) {
                            initPostReport();
                        } else {
                            ToastUtil.showToast(ToApplyForAnlsland2Activity.this, "请选择登岛路线时间，如果没有请及时联系管理员");
                        }
                    } else if (ProjectProgressApi.getComplemented().equals("1")) {
                        initlandUpdate();
                    }
                    ifnum3 = 0;
                }
                break;
            //            TODO 编辑
            case R.id.to_apply_for_an_island2_rl4:
                ProjectProgressApi.setIndex(999999);
                ProjectProgressApi.setFieldBean(new FieldBean());
                Log.i("我们不一样", "差不多");
                Intent intent2 = new Intent(ToApplyForAnlsland2Activity.this, FieldActivity.class);
                startActivity(intent2);
                break;
        }

    }

    //    TODO 照片选择
    private void initAlot() {

        AlertDialog.Builder builder = new AlertDialog.Builder(ToApplyForAnlsland2Activity.this);
        builder.setTitle("请选择图片来源");
        builder.setItems(new String[]{"相机", "相册"}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == 0) {
                    try {
                        //检测是否有写的权限
                        int permission = ActivityCompat.checkSelfPermission(ToApplyForAnlsland2Activity.this,
                                "android.permission.WRITE_EXTERNAL_STORAGE");
                        if (permission != PackageManager.PERMISSION_GRANTED) {
                            // 没有写的权限，去申请写的权限，会弹出对话框
                            ActivityCompat.requestPermissions(ToApplyForAnlsland2Activity.this, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
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
                        Uri photoUri = ToApplyForAnlsland2Activity.this.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
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

    //            TODO 查询登岛路线
    private void initGetLandLine() {
        Log.i("项目路线", "项目ID1：" + FinalContents.getProjectID());
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<GetLandLineBean> clientFragment = fzbInterface.getlandLine(FinalContents.getUserID(), FinalContents.getProjectID());
        clientFragment.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GetLandLineBean>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GetLandLineBean getLandLineBean) {
                        List<GetLandLineBean.DataBean> list = getLandLineBean.getData();
                        if (list.size() != 0) {
                            initSelect(list, getlandLine);
                        }else {
                            ToastUtil.showLongToast(ToApplyForAnlsland2Activity.this,"当前项目暂无路线");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("MyCL", "我的客户（失效）错误信息" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //查询登岛路线选择器
    private void initSelect(final List<GetLandLineBean.DataBean> list, final TextView textView) {
        //      监听选中
        OptionsPickerView pvOptions = new OptionsPickerBuilder(ToApplyForAnlsland2Activity.this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //               返回的分别是三个级别的选中位置
                //              展示选中数据
                textView.setText(list.get(options1).getRouteName());
                routeid2 = list.get(options1).getId();
                route = list.get(options1).getRouteName();
            }
        })
                .setSelectOptions(0)//设置选择第一个
                .setOutSideCancelable(false)//点击背的地方不消失
                .build();//创建

        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            stringList.add(list.get(i).getRouteName());
        }

        //      把数据绑定到控件上面
        pvOptions.setPicker(stringList);
        //      展示
        pvOptions.show();
    }

    //            TODO 登岛路线时间
    private void initGetLandLineTime() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<GetLandLineTimeBean> clientFragment = fzbInterface.getlandLinetime(routeid2,FinalContents.getProjectID());
        clientFragment.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GetLandLineTimeBean>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GetLandLineTimeBean getLandLineTimeBean) {
                        List<GetLandLineTimeBean.DataBean> list = getLandLineTimeBean.getData();
                        if (list.size() != 0) {
                            initSelect2(list, getlandLinetime);
                        }else {
                            ToastUtil.showLongToast(ToApplyForAnlsland2Activity.this,"当前项目暂无路线时间");
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("MyCL", "我的客户（失效）错误信息" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //登岛路线时间选择器
    private void initSelect2(final List<GetLandLineTimeBean.DataBean> list, final TextView textView) {
        //      监听选中
        OptionsPickerView pvOptions = new OptionsPickerBuilder(ToApplyForAnlsland2Activity.this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //               返回的分别是三个级别的选中位置
                //              展示选中数据
                textView.setText(list.get(options1).getStartEndTime());
                routeid = list.get(options1).getId();
                isLandTime = list.get(options1).getStartEndTime();
                to_apply_for_an_island2_tv1.setText(list.get(options1).getExpenses());
                to_apply_for_an_island2_tv3.setText(list.get(options1).getExpenses());
            }
        })
                .setSelectOptions(0)//设置选择第一个
                .setOutSideCancelable(false)//点击背的地方不消失
                .build();//创建
        //      把数据绑定到控件上面
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            stringList.add(list.get(i).getStartEndTime());
        }
        pvOptions.setPicker(stringList);
        //      展示
        pvOptions.show();
    }

    //            TODO 请求登岛报备
    private void initPostReport() {

        Log.i("成交数据", "1：" + FinalContents.getProjectID());
        Log.i("成交数据", "2：" + FinalContents.getPreparationId());
        Log.i("成交数据", "3：" + FinalContents.getCustomerID());
        Log.i("成交数据", "4：" + FinalContents.getCommissionId());
        Log.i("成交数据", "5：" + FinalContents.getUserID());
        Log.i("成交数据", "6：" + routeid);

        ProjectProgressApi.setFieldBeanList(list);

        Gson gson = new Gson();
        fieldbeanlist = gson.toJson(ProjectProgressApi.getFieldBeanList());

        if (list.size() != 0) {
            isColleague = "1";
        }
        Log.i("成交数据", "基本描摹信息：" + fieldbeanlist);
        Log.i("同行人", "基本描摹信息：" + fieldbeanlist);
        Log.i("同行人", "基本描摹信息："+list.size());

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<LandSaveBean> userMessage = fzbInterface.getIslandSave(FinalContents.getPreparationId(), routeid, FinalContents.getProjectType(), isColleague, list.size() + "", ProjectProgressApi.getIdNumber(), ProjectProgressApi.getAge(), ProjectProgressApi.getGender(), ProjectProgressApi.getPassportNumber(), ProjectProgressApi.getPassportimg(), route, isLandTime, isPay, to_apply_for_an_island2_tv3.getText().toString(), imgUrl,ProjectProgressApi.getCustomerCity(), ProjectProgressApi.getCustomerOccupation()+"", ProjectProgressApi.getCustomerFocus()+"",ProjectProgressApi.getCustomerIntentionalBuilding()+"", ProjectProgressApi.getCustomerPaymentMethod()+"", ProjectProgressApi.getCustomerHasDecision()+"",ProjectProgressApi.getCustomerResistance()+"",ProjectProgressApi.getCustomerObjective()+"",ProjectProgressApi.getCustomerAuditStatus()+"", FinalContents.getUserID(), fieldbeanlist, ProjectProgressApi.getCustomerName());
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LandSaveBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @SuppressLint("WrongConstant")
                    @Override
                    public void onNext(LandSaveBean landSaveBean) {
                        ToastUtil.showToast(ToApplyForAnlsland2Activity.this, landSaveBean.getData().getMessage());
                        FinalContents.setTiaozhuang("登岛成功");
                        FinalContents.setDengDao("1");
                        finish();
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtil.showToast(ToApplyForAnlsland2Activity.this, "请确定您的输入信息是否完整");
                        Log.i("列表数据获取错误", "错误" + e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //            TODO 补全信息 请求登岛报备
    private void initlandUpdate() {

        Log.i("补全信息","申请登岛ID："+ProjectProgressApi.getID());
        Log.i("补全信息","报备id："+FinalContents.getPreparationId());
        Log.i("补全信息","routeId："+routeid);
        Log.i("补全信息","用户ID："+FinalContents.getUserID());


        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<LandSaveBean> userMessage = fzbInterface.getlandUpdate(ProjectProgressApi.getID(), FinalContents.getPreparationId(), ProjectProgressApi.getGender(), ProjectProgressApi.getIdNumber(), ProjectProgressApi.getAge(), ProjectProgressApi.getPassportNumber(), ProjectProgressApi.getPassportimg(), route, routeid, colleagueUserNum, sumCost, landingImg, ProjectProgressApi.getCustomerCity(), ProjectProgressApi.getCustomerOccupation(), ProjectProgressApi.getCustomerFocus(), ProjectProgressApi.getCustomerIntentionalBuilding(), ProjectProgressApi.getCustomerPaymentMethod(), ProjectProgressApi.getCustomerHasDecision(), ProjectProgressApi.getCustomerResistance(), ProjectProgressApi.getCustomerObjective(), ProjectProgressApi.getCustomerAuditStatus()+"", FinalContents.getUserID());
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LandSaveBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @SuppressLint("WrongConstant")
                    @Override
                    public void onNext(LandSaveBean landSaveBean) {
                        ToastUtil.showToast(ToApplyForAnlsland2Activity.this, landSaveBean.getData().getMessage());
                        FinalContents.setTiaozhuang("登岛成功");
                        FinalContents.setDengDao("1");
                        finish();
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtil.showToast(ToApplyForAnlsland2Activity.this, "请确定您的输入信息是否完整");
                        Log.i("列表数据获取错误", "错误" + e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //            TODO 补全信息 数据请求
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
                        ProjectProgressApi.setID(landBean.getData().getId());

                        getlandLine.setText(landBean.getData().getRoute());
                        getlandLinetime.setText(landBean.getData().getIslandTime());
                        to_apply_for_an_island2_tv1.setText(landBean.getData().getRoutetime().getExpenses());
                        to_apply_for_an_island2_tv3.setText(landBean.getData().getSumCost());
                        Log.i("图片", FinalContents.getImageUrl() + landBean.getData().getLandingImg());
                        if (landBean.getData().getLandingImg().equals("")) {

                        } else {
                            Glide.with(ToApplyForAnlsland2Activity.this).load(FinalContents.getImageUrl() + landBean.getData().getLandingImg()).into(to_apply_for_an_island2_img3);
                        }
                        routeid = landBean.getData().getRouteId();
                        if (landBean.getData().getColleagues().size() == 0) {
                            to_apply_for_an_island2_ll1.setVisibility(View.GONE);
                            to_apply_for_an_island2_rl4.setVisibility(View.GONE);
                            to_apply_for_an_island2_linear.setVisibility(View.GONE);
                        } else {
                            to_apply_for_an_island2_ll1.setVisibility(View.VISIBLE);
                            to_apply_for_an_island2_rl4.setVisibility(View.GONE);
                            colleagueUserNum = Integer.parseInt(landBean.getData().getColleagueUserNum());
                            MyLinearLayoutManager layoutManager = new MyLinearLayoutManager(ToApplyForAnlsland2Activity.this);
                            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                            to_apply_for_an_island2_rv.setLayoutManager(layoutManager);
                            FieldAdapter fieldAdapter = new FieldAdapter(landBean.getData().getColleagues());
                            to_apply_for_an_island2_rv.setAdapter(fieldAdapter);
                            fieldAdapter.notifyDataSetChanged();
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
                                        Glide.with(ToApplyForAnlsland2Activity.this).load(FinalContents.getImageUrl() + imgUrl).into(to_apply_for_an_island2_img3);
                                        to_apply_for_an_island2_tv4.setVisibility(View.GONE);
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
                                    Glide.with(ToApplyForAnlsland2Activity.this).load(FinalContents.getImageUrl() + imgUrl).into(to_apply_for_an_island2_img3);
                                    to_apply_for_an_island2_tv4.setVisibility(View.GONE);
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

        if (ProjectProgressApi.getFieldBeanList() != null) {
            if (ProjectProgressApi.getFieldBeanList().size() != 0) {
                to_apply_for_an_island2_ll1.setVisibility(View.VISIBLE);

                if (ProjectProgressApi.getIndex() != 999999) {
                    index = ProjectProgressApi.getIndex();
                    list.set(index, ProjectProgressApi.getFieldBean());
                } else {
                    if (ProjectProgressApi.getChongfu().equals("不重复")) {
                        list.add(ProjectProgressApi.getFieldBean());
                    }
                }

                Log.i("同行人", "基本描摹信息："+ProjectProgressApi.getFieldBean());
                Log.i("同行人", "基本描摹信息："+ProjectProgressApi.getFieldBean().getCity());
                Log.i("同行人", "基本描摹信息："+ProjectProgressApi.getFieldBean().getObjective());
                Log.i("同行人", "基本描摹信息："+ProjectProgressApi.getFieldBeanList().get(0).getCity());
                Log.i("同行人", "基本描摹信息："+ProjectProgressApi.getFieldBeanList().get(0).getObjective());

                if (to_apply_for_an_island2_tv1.getText().toString().equals("")) {
                } else {
                    int expenses = Integer.parseInt(to_apply_for_an_island2_tv1.getText().toString());
                    sumCost = "" + expenses * (list.size() + 1);
                    to_apply_for_an_island2_tv3.setText(sumCost);
                }

                MyLinearLayoutManager layoutManager = new MyLinearLayoutManager(ToApplyForAnlsland2Activity.this);
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                to_apply_for_an_island2_rv.setLayoutManager(layoutManager);
                FieldBeanListAdapter fieldAdapter = new FieldBeanListAdapter(list);
                to_apply_for_an_island2_rv.setAdapter(fieldAdapter);
                fieldAdapter.notifyDataSetChanged();
                fieldAdapter.setOnItemClickListener(new FieldBeanListAdapter.OnItemClickLisenter() {
                    @Override
                    public void onItemClick(int postion) {
                        ProjectProgressApi.setFieldID(list.get(postion).getId());
                        ProjectProgressApi.setLandingId(list.get(postion).getLandingId());
                        ProjectProgressApi.setIndex(postion);
                        ProjectProgressApi.setFieldBean(list.get(postion));
                        Intent intent = new Intent(ToApplyForAnlsland2Activity.this, FieldActivity.class);
                        startActivity(intent);
                    }
                });
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        ProjectProgressApi.setChongfu("1");
    }

}

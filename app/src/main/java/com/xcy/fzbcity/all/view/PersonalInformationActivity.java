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
import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.api.CityContents;
import com.xcy.fzbcity.all.api.Connector;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.modle.AddPhotoBean;
import com.xcy.fzbcity.all.modle.ChangeSexBean;
import com.xcy.fzbcity.all.modle.GWDataBean;
import com.xcy.fzbcity.all.modle.PersonalPhotoBean;
import com.xcy.fzbcity.all.modle.UserBean;
import com.xcy.fzbcity.all.modle.UserMessageBean;
import com.xcy.fzbcity.all.modle.ZYDataBean;
import com.xcy.fzbcity.all.modle.ZhangBingDataBean;
import com.xcy.fzbcity.all.persente.OkHttpPost;
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

import butterknife.ButterKnife;
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


//TODO 个人信息
public class PersonalInformationActivity extends AllActivity implements View.OnClickListener {

    RelativeLayout personalReturnImg;
    RelativeLayout personalRl1;
    RelativeLayout personalRl2;
    RelativeLayout personalRl3;
    RelativeLayout personalRl4;
    RelativeLayout personalRl5;
    RelativeLayout personalRl6;
    RelativeLayout information_rl;

    LinearLayout information_ll;

    ImageView personal_photo;
    TextView personal_name;
    TextView personal_sex;
    TextView personal_phone;
    TextView personal_city;
    TextView personal_store;
    TextView personal_identity;
    EditText personal_et_name;


    private final String IMAGE_TYPE = "image/*";
    private final int IMAGE_CODE = 0;
    private Bitmap bm;

    private Intent intent;

    String imgStr = "";
    String isPhoto = "";


    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE"};
    private String s = "";
    private File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_information);
        init_No_Network();
    }

    private void init_No_Network() {
        boolean networkAvailable = CommonUtil.isNetworkAvailable(this);
        if (networkAvailable) {
            ButterKnife.bind(this);

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

//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        StatusBar.makeStatusBarTransparent(PersonalInformationActivity.this);

        information_ll = findViewById(R.id.information_ll);
        personalReturnImg = findViewById(R.id.personal_return_img);
        personalRl1 = findViewById(R.id.personal_rl_1);
        personalRl2 = findViewById(R.id.personal_rl_2);
        personalRl3 = findViewById(R.id.personal_rl_3);
        personalRl4 = findViewById(R.id.personal_rl_4);
        personalRl5 = findViewById(R.id.personal_rl_5);
        personalRl6 = findViewById(R.id.personal_rl_6);
        information_rl = findViewById(R.id.information_rl);

        personal_photo = findViewById(R.id.personal_photo);
        personal_name = findViewById(R.id.personal_name);
        personal_sex = findViewById(R.id.personal_sex);
        personal_phone = findViewById(R.id.personal_phone);
        personal_city = findViewById(R.id.personal_city);
        personal_store = findViewById(R.id.personal_store);
        personal_identity = findViewById(R.id.personal_identity);
        personal_et_name = findViewById(R.id.personal_et_name);

        personalReturnImg.setOnClickListener(this);
        personalRl1.setOnClickListener(this);
        personalRl2.setOnClickListener(this);
        personalRl3.setOnClickListener(this);
        personalRl4.setOnClickListener(this);
        personalRl5.setOnClickListener(this);
        personalRl6.setOnClickListener(this);


        if (FinalContents.getIdentity().equals("1") || FinalContents.getIdentity().equals("2") || FinalContents.getIdentity().equals("3") || FinalContents.getIdentity().equals("4") || FinalContents.getIdentity().equals("5") || FinalContents.getIdentity().equals("7")|| FinalContents.getIdentity().equals("8")|| FinalContents.getIdentity().equals("9")) {
            initData();
        } else if (FinalContents.getIdentity().equals("60")) {
            initDataTDZ();
        } else if (FinalContents.getIdentity().equals("61")) {
            initDataTDZ();
        } else if (FinalContents.getIdentity().equals("62")) {
            initDataTDZ();
        } else if (FinalContents.getIdentity().equals("63")) {
            initDataTDZ();
        } else {
            initData();
        }



        if (FinalContents.getIdentity().equals("1") || FinalContents.getIdentity().equals("2") || FinalContents.getIdentity().equals("3")) {

        } else if (FinalContents.getIdentity().equals("4")  || FinalContents.getIdentity().equals("7")) {

        } else if (FinalContents.getIdentity().equals("5")) {

        } else if (FinalContents.getIdentity().equals("60")) {

        } else if (FinalContents.getIdentity().equals("61")) {

        } else if (FinalContents.getIdentity().equals("62")) {

        } else if (FinalContents.getIdentity().equals("63")) {

        }

    }

    private void initData() {
        if (FinalContents.getIdentity().equals("1") || FinalContents.getIdentity().equals("2") || FinalContents.getIdentity().equals("3")) {

            UserMessageBean userMessageBean = Connector.getUserMessageBean();

            information_rl.setVisibility(View.VISIBLE);
            information_ll.setVisibility(View.VISIBLE);
            UserMessageBean.DataBean data = userMessageBean.getData();
            Glide.with(PersonalInformationActivity.this).load(FinalContents.getImageUrl() + data.getPhoto()).into(personal_photo);
            personal_name.setText(data.getName());
            personal_identity.setText("经纪人");
            personal_city.setText(data.getCity());
            if (data.getSex().equals("")) {
                personal_sex.setText("男");
                s = "男";
            } else {
                personal_sex.setText(data.getSex());
                s = data.getSex();
            }
            personal_phone.setText(data.getPhone());

            UserMessageBean.DataBean.StoreManageBean storeManage = data.getStoreManage();
            if(storeManage.getStoreName().equals("")){
                information_rl.setVisibility(View.GONE);
                information_ll.setVisibility(View.GONE);
            }else {
                personal_store.setText(storeManage.getStoreName());
            }

        } else if (FinalContents.getIdentity().equals("4")) {
            UserBean userMessageBean = Connector.getUserBean();

            UserBean.DataBean data = userMessageBean.getData();
            Glide.with(PersonalInformationActivity.this).load(FinalContents.getImageUrl() + data.getPhoto()).into(personal_photo);
            personal_name.setText(data.getName());
            personal_identity.setText("专案");
            personal_city.setText(data.getCity());
            if (data.getSex().equals("")) {
                personal_sex.setText("男");
                s = "男";
            } else {
                personal_sex.setText(data.getSex());
                s = data.getSex();
            }
            personal_phone.setText(data.getPhone());
            s = data.getSex();
            String storeManage = data.getStoreManage();
            personal_store.setText(storeManage);

        } else if (FinalContents.getIdentity().equals("5") || FinalContents.getIdentity().equals("8") || FinalContents.getIdentity().equals("9")) {
            ZYDataBean userMessageBean = Connector.getZyDataBean();

            ZYDataBean.DataBean data = userMessageBean.getData();
            Glide.with(PersonalInformationActivity.this).load(FinalContents.getImageUrl() + data.getPhoto()).into(personal_photo);
            personal_name.setText(data.getName());
            if (data.getIdentity().equals("5")) {
                personal_identity.setText("专员");
            } else if (data.getIdentity().equals("7")) {
                personal_identity.setText("导购");
            }else if (data.getIdentity().equals("8")) {
                personal_identity.setText("经理");
            }else if (data.getIdentity().equals("9")) {
                personal_identity.setText("总监");
            }
            personal_city.setText(data.getCity());
            if (data.getSex().equals("")) {
                personal_sex.setText("男");
                s = "男";
            } else {
                personal_sex.setText(data.getSex());
                s = data.getSex();
            }
            personal_phone.setText(data.getPhone());
            s = data.getSex();
            String storeManage = data.getStoreManage();
            personal_store.setText(storeManage);
        } else if (FinalContents.getIdentity().equals("7")) {
            UserBean userMessageBean = Connector.getUserBean();

            UserBean.DataBean data = userMessageBean.getData();

            Glide.with(PersonalInformationActivity.this).load(FinalContents.getImageUrl() + data.getPhoto()).into(personal_photo);
            personal_name.setText(data.getName());
            if (data.getIdentity().equals("5")) {
                personal_identity.setText("专员");
            } else if (data.getIdentity().equals("7")) {
                personal_identity.setText("导购");
            }
            personal_city.setText(data.getCity());
            if (data.getSex().equals("")) {
                personal_sex.setText("男");
                s = "男";
            } else {
                personal_sex.setText(data.getSex());
                s = data.getSex();
            }
            personal_phone.setText(data.getPhone());
            s = data.getSex();
            String storeManage = data.getStoreManage();
            personal_store.setText(storeManage);
        }
    }

    private void initDataTDZ() {
        if (FinalContents.getIdentity().equals("60")) {

            ZhangBingDataBean userMessageBean = Connector.getZhangBingDataBean();

            if (userMessageBean.getData().getSysUser().getPhoto().equals("")) {
                Glide.with(PersonalInformationActivity.this).load(FinalContents.getImageUrl() + userMessageBean.getData().getSysUser().getManager().getPhoto()).into(personal_photo);
            } else {
                Glide.with(PersonalInformationActivity.this).load(FinalContents.getImageUrl() + userMessageBean.getData().getSysUser().getPhoto()).into(personal_photo);
            }

            personal_name.setText(userMessageBean.getData().getSysUser().getName());
            if (userMessageBean.getData().getSysUser().getIdentity().equals("60")) {
                personal_identity.setText("团队长");
            } else if (userMessageBean.getData().getSysUser().getIdentity().equals("61")) {
                personal_identity.setText("销售");
            } else if (userMessageBean.getData().getSysUser().getIdentity().equals("62")) {
                personal_identity.setText("顾问");
            } else if (userMessageBean.getData().getSysUser().getIdentity().equals("63")) {
                personal_identity.setText("团助");
            }

            personal_city.setText(userMessageBean.getData().getSysUser().getCity());
            if (userMessageBean.getData().getSysUser().getSex().equals("")) {
                personal_sex.setText("男");
                s = "男";
            } else {
                personal_sex.setText(userMessageBean.getData().getSysUser().getSex());
                s = userMessageBean.getData().getSysUser().getSex();
            }
            personal_phone.setText(userMessageBean.getData().getSysUser().getPhone());
            s = userMessageBean.getData().getSysUser().getSex();
            personal_store.setText(userMessageBean.getData().getSysUser().getStoreManage());

        } else if (FinalContents.getIdentity().equals("61") || FinalContents.getIdentity().equals("62")) {
            GWDataBean userMessageBean = Connector.getGwDataBean();

            if (userMessageBean.getData().getSysUser().getPhoto().equals("")) {
                Glide.with(PersonalInformationActivity.this).load(FinalContents.getImageUrl() + userMessageBean.getData().getSysUser().getManager().getPhoto()).into(personal_photo);
            } else {
                Glide.with(PersonalInformationActivity.this).load(FinalContents.getImageUrl() + userMessageBean.getData().getSysUser().getPhoto()).into(personal_photo);
            }


            personal_name.setText(userMessageBean.getData().getSysUser().getName());
            if (userMessageBean.getData().getSysUser().getIdentity().equals("60")) {
                personal_identity.setText("团队长");
            } else if (userMessageBean.getData().getSysUser().getIdentity().equals("61")) {
                personal_identity.setText("销售");
            } else if (userMessageBean.getData().getSysUser().getIdentity().equals("62")) {
                personal_identity.setText("顾问");
            } else if (userMessageBean.getData().getSysUser().getIdentity().equals("63")) {
                personal_identity.setText("团助");
            }

            personal_city.setText(userMessageBean.getData().getSysUser().getCity());
            if (userMessageBean.getData().getSysUser().getSex().equals("")) {
                personal_sex.setText("男");
                s = "男";
            } else {
                personal_sex.setText(userMessageBean.getData().getSysUser().getSex());
                s = userMessageBean.getData().getSysUser().getSex();
            }
            personal_phone.setText(userMessageBean.getData().getSysUser().getPhone());
            s = userMessageBean.getData().getSysUser().getSex();
            personal_store.setText(userMessageBean.getData().getSysUser().getStoreManage());

        } else if (FinalContents.getIdentity().equals("63")) {

            UserBean userMessageBean = Connector.getUserBean();

            if (userMessageBean.getData().getPhoto().equals("")) {
                Glide.with(PersonalInformationActivity.this).load(FinalContents.getImageUrl() + userMessageBean.getData().getPhoto()).into(personal_photo);
            } else {
                Glide.with(PersonalInformationActivity.this).load(FinalContents.getImageUrl() + userMessageBean.getData().getPhoto()).into(personal_photo);
            }

            personal_name.setText(userMessageBean.getData().getName());
            if (userMessageBean.getData().getIdentity().equals("60")) {
                personal_identity.setText("团队长");
            } else if (userMessageBean.getData().getIdentity().equals("61")) {
                personal_identity.setText("销售");
            } else if (userMessageBean.getData().getIdentity().equals("62")) {
                personal_identity.setText("顾问");
            } else if (userMessageBean.getData().getIdentity().equals("63")) {
                personal_identity.setText("团助");
            }

            personal_city.setText(userMessageBean.getData().getCity());
            if (userMessageBean.getData().getSex().equals("")) {
                personal_sex.setText("男");
                s = "男";
            } else {
                personal_sex.setText(userMessageBean.getData().getSex());
                s = userMessageBean.getData().getSex();
            }
            personal_phone.setText(userMessageBean.getData().getPhone());
            s = userMessageBean.getData().getSex();
            personal_store.setText(userMessageBean.getData().getStoreManage());
        }

    }

    @SingleClick(1000)
    @Override
    public void onClick(View view) {

        int id = view.getId();
        switch (id) {
//返回
            case R.id.personal_return_img:
                finish();
                break;
//头像
            case R.id.personal_rl_1:
                //   弹出框
                AlertDialog.Builder builder = new AlertDialog.Builder(PersonalInformationActivity.this);
                builder.setTitle("请选择图片来源");
                builder.setItems(new String[]{"相机", "相册"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (i == 0) {
                            try {
                                //检测是否有写的权限
                                int permission = ActivityCompat.checkSelfPermission(PersonalInformationActivity.this,
                                        "android.permission.WRITE_EXTERNAL_STORAGE");
                                if (permission != PackageManager.PERMISSION_GRANTED) {
                                    // 没有写的权限，去申请写的权限，会弹出对话框
                                    ActivityCompat.requestPermissions(PersonalInformationActivity.this, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
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
                                Uri photoUri = PersonalInformationActivity.this.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                                file = uri2File(photoUri);
//                                TODO 提交头像
                                if (isPhoto.equals("")) {
                                    isPhoto = "拍照";
                                }
                                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                                startActivityForResult(intent, 1);
                            }else {

                            }
                            ToastUtil.showToast(PersonalInformationActivity.this, "相机");
                        } else if (i == 1) {
                            Intent getAlbum = new Intent(Intent.ACTION_PICK);
                            getAlbum.setType(IMAGE_TYPE);
                            startActivityForResult(getAlbum, IMAGE_CODE);
                            ToastUtil.showToast(PersonalInformationActivity.this, "相册");
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
//昵称
            case R.id.personal_rl_2:
                intent = new Intent(PersonalInformationActivity.this, NickNameActivity.class);
                startActivity(intent);
                break;
//性别
            case R.id.personal_rl_3:


                initRatioByOwnerId();

                break;
//手机号
            case R.id.personal_rl_4:
                String s = personal_phone.getText().toString();
                if (s.equals("")) {
                    Intent intent1 = new Intent(PersonalInformationActivity.this, BindingPhoneActivity.class);
                    startActivity(intent1);
                } else {
                    Intent intent2 = new Intent(PersonalInformationActivity.this, ChangePhoneActivity.class);
                    startActivity(intent2);
                }
                break;
//修改密码
            case R.id.personal_rl_5:
                intent = new Intent(this, SetPasswordActivity.class);
                startActivity(intent);
                finish();
                break;
//第三方账号设置
            case R.id.personal_rl_6:
                CityContents.setPhone(personal_phone.getText().toString());
                intent = new Intent(this, ToLoginActivity.class);
                startActivity(intent);
//                ToastUtil.showToast(PersonalInformationActivity.this, "暂无功能");
                break;
        }
    }

    private void initRatioByOwnerId() {

        final List<String> list1 = new ArrayList<>();
        list1.add("男");
        list1.add("女");
        OptionsPickerView pvOptions = new OptionsPickerBuilder(PersonalInformationActivity.this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //              展示选中数据
                String url = FinalContents.getImageUrl() + "/fangfang/app/v1/commonUpdate/updateSex?" + "userId=" + FinalContents.getUserID() + "&sex=" + list1.get(options1);
//              修改性别成功
                OkHttpPost okHttpPost = new OkHttpPost(url);
                String data = okHttpPost.post();
                Gson gson = new Gson();
                ChangeSexBean changeSexBean = gson.fromJson(data, ChangeSexBean.class);
                ChangeSexBean.DataBean data1 = changeSexBean.getData();
                if (data1.getMessage().equals("修改性别成功")) {
                    ToastUtil.showToast(PersonalInformationActivity.this, data1.getMessage());
                    personal_sex.setText(list1.get(options1));
                    if (FinalContents.getIdentity().equals("1") || FinalContents.getIdentity().equals("2") || FinalContents.getIdentity().equals("3")) {
                        UserMessageBean userMessageBean = Connector.getUserMessageBean();
                        UserMessageBean.DataBean dataBean = userMessageBean.getData();
                        dataBean.setSex(personal_sex.getText().toString());
                        userMessageBean.setData(dataBean);
                        Connector.setUserMessageBean(userMessageBean);
                    } else if (FinalContents.getIdentity().equals("4")  || FinalContents.getIdentity().equals("7")) {
                        UserBean userMessageBean = Connector.getUserBean();
                        UserBean.DataBean dataBean = userMessageBean.getData();
                        dataBean.setName(personal_sex.getText().toString());
                        userMessageBean.setData(dataBean);
                        Connector.setUserBean(userMessageBean);
                    } else if (FinalContents.getIdentity().equals("5")) {
                        ZYDataBean userMessageBean = Connector.getZyDataBean();
                        ZYDataBean.DataBean dataBean = userMessageBean.getData();
                        dataBean.setName(personal_sex.getText().toString());
                        userMessageBean.setData(dataBean);
                        Connector.setZyDataBean(userMessageBean);
                    } else if (FinalContents.getIdentity().equals("60")) {
                        ZhangBingDataBean userMessageBean = Connector.getZhangBingDataBean();
                        ZhangBingDataBean.DataBean dataBean = userMessageBean.getData();
                        ZhangBingDataBean.DataBean.SysUserBean sysUserBean = dataBean.getSysUser();
                        sysUserBean.setName(personal_sex.getText().toString());
                        dataBean.setSysUser(sysUserBean);
                        userMessageBean.setData(dataBean);
                        Connector.setZhangBingDataBean(userMessageBean);
                    } else if (FinalContents.getIdentity().equals("61")) {
                        GWDataBean userMessageBean = Connector.getGwDataBean();
                        GWDataBean.DataBean dataBean = userMessageBean.getData();
                        GWDataBean.DataBean.SysUserBean sysUserBean = dataBean.getSysUser();
                        sysUserBean.setName(personal_sex.getText().toString());
                        dataBean.setSysUser(sysUserBean);
                        userMessageBean.setData(dataBean);
                        Connector.setGwDataBean(userMessageBean);
                    } else if (FinalContents.getIdentity().equals("62")) {
                        GWDataBean userMessageBean = Connector.getGwDataBean();
                        GWDataBean.DataBean dataBean = userMessageBean.getData();
                        GWDataBean.DataBean.SysUserBean sysUserBean = dataBean.getSysUser();
                        sysUserBean.setName(personal_sex.getText().toString());
                        dataBean.setSysUser(sysUserBean);
                        userMessageBean.setData(dataBean);
                        Connector.setGwDataBean(userMessageBean);
                    } else if (FinalContents.getIdentity().equals("63")) {
                        UserBean userMessageBean = Connector.getUserBean();
                        UserBean.DataBean dataBean = userMessageBean.getData();
                        dataBean.setName(personal_sex.getText().toString());
                        userMessageBean.setData(dataBean);
                        Connector.setUserBean(userMessageBean);
                    }
                } else {
                    ToastUtil.showToast(PersonalInformationActivity.this, "性别修改失败");
                }


            }
        })
                .setOutSideCancelable(false)//点击背的地方不消失
                .build();//创建
        String s2 = personal_sex.getText().toString();
        if (s2.equals("男")) {
            pvOptions.setSelectOptions(0);//设置选择第一个
        } else if (s2.equals("女")) {
            pvOptions.setSelectOptions(1);
        } else {
            pvOptions.setSelectOptions(0);
        }
        //      把数据绑定到控件上面
        pvOptions.setPicker(list1);
        //      展示
        pvOptions.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable final Intent data) {
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
//                                TODO 提交头像
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

                        Observable<AddPhotoBean> addPhoto = fzbInterface.getAddPhoto(FinalContents.getUserID(), "p", part);
                        addPhoto.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Observer<AddPhotoBean>() {
                                    @Override
                                    public void onSubscribe(Disposable d) {

                                    }

                                    @Override
                                    public void onNext(AddPhotoBean addPhotoBean) {
                                        imgStr = addPhotoBean.getData().getUrl();
                                        Glide.with(PersonalInformationActivity.this).load(FinalContents.getImageUrl() + imgStr).into(personal_photo);

                                        if (FinalContents.getIdentity().equals("1") || FinalContents.getIdentity().equals("2") || FinalContents.getIdentity().equals("3")) {
                                            UserMessageBean userMessageBean = Connector.getUserMessageBean();
                                            UserMessageBean.DataBean dataBean = userMessageBean.getData();
                                            dataBean.setPhoto(imgStr);
                                            userMessageBean.setData(dataBean);
                                            Connector.setUserMessageBean(userMessageBean);
                                        } else if (FinalContents.getIdentity().equals("4")  || FinalContents.getIdentity().equals("7")) {
                                            UserBean userMessageBean = Connector.getUserBean();
                                            UserBean.DataBean dataBean = userMessageBean.getData();
                                            dataBean.setPhoto(imgStr);
                                            userMessageBean.setData(dataBean);
                                            Connector.setUserBean(userMessageBean);
                                        } else if (FinalContents.getIdentity().equals("5")) {
                                            ZYDataBean userMessageBean = Connector.getZyDataBean();
                                            ZYDataBean.DataBean dataBean = userMessageBean.getData();
                                            dataBean.setPhoto(imgStr);
                                            userMessageBean.setData(dataBean);
                                            Connector.setZyDataBean(userMessageBean);
                                        } else if (FinalContents.getIdentity().equals("60")) {
                                            ZhangBingDataBean userMessageBean = Connector.getZhangBingDataBean();
                                            ZhangBingDataBean.DataBean dataBean = userMessageBean.getData();
                                            ZhangBingDataBean.DataBean.SysUserBean sysUserBean = dataBean.getSysUser();
                                            sysUserBean.setPhoto(imgStr);
                                            dataBean.setSysUser(sysUserBean);
                                            userMessageBean.setData(dataBean);
                                            Connector.setZhangBingDataBean(userMessageBean);
                                        } else if (FinalContents.getIdentity().equals("61")) {
                                            GWDataBean userMessageBean = Connector.getGwDataBean();
                                            GWDataBean.DataBean dataBean = userMessageBean.getData();
                                            GWDataBean.DataBean.SysUserBean sysUserBean = dataBean.getSysUser();
                                            sysUserBean.setPhoto(imgStr);
                                            dataBean.setSysUser(sysUserBean);
                                            userMessageBean.setData(dataBean);
                                            Connector.setGwDataBean(userMessageBean);
                                        } else if (FinalContents.getIdentity().equals("62")) {
                                            GWDataBean userMessageBean = Connector.getGwDataBean();
                                            GWDataBean.DataBean dataBean = userMessageBean.getData();
                                            GWDataBean.DataBean.SysUserBean sysUserBean = dataBean.getSysUser();
                                            sysUserBean.setPhoto(imgStr);
                                            dataBean.setSysUser(sysUserBean);
                                            userMessageBean.setData(dataBean);
                                            Connector.setGwDataBean(userMessageBean);
                                        } else if (FinalContents.getIdentity().equals("63")) {
                                            UserBean userMessageBean = Connector.getUserBean();
                                            UserBean.DataBean dataBean = userMessageBean.getData();
                                            dataBean.setPhoto(imgStr);
                                            userMessageBean.setData(dataBean);
                                            Connector.setUserBean(userMessageBean);
                                        }

                                        Retrofit.Builder builder = new Retrofit.Builder();
                                        builder.baseUrl(FinalContents.getBaseUrl());
                                        builder.addConverterFactory(GsonConverterFactory.create());
                                        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
                                        Retrofit build = builder.build();
                                        MyService fzbInterface = build.create(MyService.class);
                                        Observable<PersonalPhotoBean> personalPhoto = fzbInterface.getPersonalPhoto(FinalContents.getUserID(), imgStr);
                                        personalPhoto.subscribeOn(Schedulers.io())
                                                .observeOn(AndroidSchedulers.mainThread())
                                                .subscribe(new Observer<PersonalPhotoBean>() {
                                                    @Override
                                                    public void onSubscribe(Disposable d) {

                                                    }

                                                    @Override
                                                    public void onNext(PersonalPhotoBean personalPhotoBean) {
                                                        if (personalPhotoBean.getData().getStatus().equals("1")) {
                                                            ToastUtil.showToast(PersonalInformationActivity.this, personalPhotoBean.getData().getMessage());
                                                        } else {
                                                            ToastUtil.showToast(PersonalInformationActivity.this, personalPhotoBean.getData().getMessage());
                                                        }

                                                    }

                                                    @Override
                                                    public void onError(Throwable e) {
                                                        Log.i("MyCL", "头像提交错误信息：" + e.getMessage());
                                                    }

                                                    @Override
                                                    public void onComplete() {

                                                    }
                                                });

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

                    Observable<AddPhotoBean> addPhoto = fzbInterface.getAddPhoto(FinalContents.getUserID(), "p", part);
                    addPhoto.subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Observer<AddPhotoBean>() {
                                @Override
                                public void onSubscribe(Disposable d) {

                                }

                                @Override
                                public void onNext(AddPhotoBean addPhotoBean) {
                                    imgStr = addPhotoBean.getData().getUrl();
                                    Glide.with(PersonalInformationActivity.this).load(FinalContents.getImageUrl() + imgStr).into(personal_photo);
                                    Retrofit.Builder builder = new Retrofit.Builder();
                                    builder.baseUrl(FinalContents.getBaseUrl());
                                    builder.addConverterFactory(GsonConverterFactory.create());
                                    builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
                                    Retrofit build = builder.build();
                                    MyService fzbInterface = build.create(MyService.class);
                                    Observable<PersonalPhotoBean> personalPhoto = fzbInterface.getPersonalPhoto(FinalContents.getUserID(), imgStr);
                                    personalPhoto.subscribeOn(Schedulers.io())
                                            .observeOn(AndroidSchedulers.mainThread())
                                            .subscribe(new Observer<PersonalPhotoBean>() {
                                                @Override
                                                public void onSubscribe(Disposable d) {

                                                }

                                                @Override
                                                public void onNext(PersonalPhotoBean personalPhotoBean) {
                                                    if (personalPhotoBean.getData().getStatus().equals("1")) {
                                                        ToastUtil.showToast(PersonalInformationActivity.this, personalPhotoBean.getData().getMessage());
                                                        isPhoto = "";
                                                        if (FinalContents.getIdentity().equals("1") || FinalContents.getIdentity().equals("2") || FinalContents.getIdentity().equals("3")) {
                                                            UserMessageBean userMessageBean = Connector.getUserMessageBean();
                                                            UserMessageBean.DataBean dataBean = userMessageBean.getData();
                                                            dataBean.setPhoto(imgStr);
                                                            userMessageBean.setData(dataBean);
                                                            Connector.setUserMessageBean(userMessageBean);
                                                        } else if (FinalContents.getIdentity().equals("4")  || FinalContents.getIdentity().equals("7")) {
                                                            UserBean userMessageBean = Connector.getUserBean();
                                                            UserBean.DataBean dataBean = userMessageBean.getData();
                                                            dataBean.setPhoto(imgStr);
                                                            userMessageBean.setData(dataBean);
                                                            Connector.setUserBean(userMessageBean);
                                                        } else if (FinalContents.getIdentity().equals("5")) {
                                                            ZYDataBean userMessageBean = Connector.getZyDataBean();
                                                            ZYDataBean.DataBean dataBean = userMessageBean.getData();
                                                            dataBean.setPhoto(imgStr);
                                                            userMessageBean.setData(dataBean);
                                                            Connector.setZyDataBean(userMessageBean);
                                                        } else if (FinalContents.getIdentity().equals("60")) {
                                                            ZhangBingDataBean userMessageBean = Connector.getZhangBingDataBean();
                                                            ZhangBingDataBean.DataBean dataBean = userMessageBean.getData();
                                                            ZhangBingDataBean.DataBean.SysUserBean sysUserBean = dataBean.getSysUser();
                                                            sysUserBean.setPhoto(imgStr);
                                                            dataBean.setSysUser(sysUserBean);
                                                            userMessageBean.setData(dataBean);
                                                            Connector.setZhangBingDataBean(userMessageBean);
                                                        } else if (FinalContents.getIdentity().equals("61")) {
                                                            GWDataBean userMessageBean = Connector.getGwDataBean();
                                                            GWDataBean.DataBean dataBean = userMessageBean.getData();
                                                            GWDataBean.DataBean.SysUserBean sysUserBean = dataBean.getSysUser();
                                                            sysUserBean.setPhoto(imgStr);
                                                            dataBean.setSysUser(sysUserBean);
                                                            userMessageBean.setData(dataBean);
                                                            Connector.setGwDataBean(userMessageBean);
                                                        } else if (FinalContents.getIdentity().equals("62")) {
                                                            GWDataBean userMessageBean = Connector.getGwDataBean();
                                                            GWDataBean.DataBean dataBean = userMessageBean.getData();
                                                            GWDataBean.DataBean.SysUserBean sysUserBean = dataBean.getSysUser();
                                                            sysUserBean.setPhoto(imgStr);
                                                            dataBean.setSysUser(sysUserBean);
                                                            userMessageBean.setData(dataBean);
                                                            Connector.setGwDataBean(userMessageBean);
                                                        } else if (FinalContents.getIdentity().equals("63")) {
                                                            UserBean userMessageBean = Connector.getUserBean();
                                                            UserBean.DataBean dataBean = userMessageBean.getData();
                                                            dataBean.setPhoto(imgStr);
                                                            userMessageBean.setData(dataBean);
                                                            Connector.setUserBean(userMessageBean);
                                                        }
                                                    } else {
                                                        ToastUtil.showToast(PersonalInformationActivity.this, personalPhotoBean.getData().getMessage());
                                                    }

                                                }

                                                @Override
                                                public void onError(Throwable e) {
                                                    Log.i("MyCL", "头像提交错误信息：" + e.getMessage());
                                                }

                                                @Override
                                                public void onComplete() {

                                                }
                                            });
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
        }else {
            if (FinalContents.getIdentity().equals("1") || FinalContents.getIdentity().equals("2") || FinalContents.getIdentity().equals("3") || FinalContents.getIdentity().equals("4") || FinalContents.getIdentity().equals("5") || FinalContents.getIdentity().equals("7") || FinalContents.getIdentity().equals("8") || FinalContents.getIdentity().equals("9")) {
                initData();
            } else if (FinalContents.getIdentity().equals("60")) {
                initDataTDZ();
            } else if (FinalContents.getIdentity().equals("61")) {
                initDataTDZ();
            } else if (FinalContents.getIdentity().equals("62")) {
                initDataTDZ();
            } else if (FinalContents.getIdentity().equals("63")) {
                initDataTDZ();
            } else {
                initData();
            }
        }

    }
}

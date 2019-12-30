package com.xcy.fzbcity.all.view;

import android.Manifest;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.mcxtzhang.indexlib.IndexBar.widget.IndexBar;
import com.mcxtzhang.indexlib.suspension.SuspensionDecoration;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.adapter.PhoneAdapter;
import com.xcy.fzbcity.all.api.CityContents;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.database.LinkmanBean;
import com.xcy.fzbcity.all.modle.AddPhoneBean;
import com.xcy.fzbcity.all.modle.PhoneDto;
import com.xcy.fzbcity.all.persente.ContactModel;
import com.xcy.fzbcity.all.persente.DividerItemDecoration;
import com.xcy.fzbcity.all.persente.StatusBar;
import com.xcy.fzbcity.all.service.MyService;
import com.xcy.fzbcity.all.utils.PhoneUtil;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PhoneActivity extends AppCompatActivity {


    private TextView all_activity_phone_cancle;
    private EditText all_activity_phone_search;
    private TextView all_activity_phone_ensure;
    private RecyclerView all_activity_phone_rv;
    private ImageView all_activity_phone_no_information;
    private List<PhoneDto> phoneDtos;
    private CheckBox all_activity_phone_checkbox_all;
    private TextView all_activity_phone_checkbox_all_number;
    private PhoneAdapter phoneAdapter;
    private List<ContactModel> mContactModels;
    private List<PhoneDto> jsonList = new ArrayList<>();
    private String fieldbeanlist = "";
    private static final int REQUEST_EXTERNAL_STORAGE = 2;
    private static String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE",
            "android.permission.READ_CONTACTS",
            "android.permission.ACCESS_FINE_LOCATION",
            "android.permission.CAMERA"
    };

    /**
     * 右侧边栏导航区域
     */
    private IndexBar all_activity_side_bar;

    /**
     * 显示指示器DialogText
     */
    private TextView all_activity_indexbar;
    private List<LinkmanBean> list = new ArrayList<>();
    private LinearLayoutManager linearLayoutManager;
    private SuspensionDecoration mDecoration;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_activity_phone);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);      //  TODO    始终竖屏
        StatusBar.makeStatusBarTransparent(this);
        if (Build.VERSION.SDK_INT >= 23) {
            //判断是否有权限
            int permission = ActivityCompat.checkSelfPermission(PhoneActivity.this, "android.permission.READ_CONTACTS");
            if (permission != PackageManager.PERMISSION_GRANTED) {
                Log.i("权限获取","未开启读取通讯录权限");
                ActivityCompat.requestPermissions(PhoneActivity.this, new String[]{Manifest.permission.READ_CONTACTS}, 1);
            } else {
                //已经开启权限
                Log.i("权限获取","已开启读取通讯录权限");
            }

            int permission1 = ActivityCompat.checkSelfPermission(PhoneActivity.this, "android.permission.WRITE_EXTERNAL_STORAGE");
            if (permission1 != PackageManager.PERMISSION_GRANTED) {// 没有写的权限，去申请写的权限，
                ActivityCompat.requestPermissions(PhoneActivity.this, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
                Log.i("权限获取","1未开启读取通讯录权限");
            }else {
                //已经开启权限
                Log.i("权限获取","1已开启读取通讯录权限");
            }
        }
        initfvb();
    }

    private void initfvb(){
        all_activity_phone_cancle = findViewById(R.id.all_activity_phone_cancle);
        all_activity_phone_search = findViewById(R.id.all_activity_phone_search);
        all_activity_phone_ensure = findViewById(R.id.all_activity_phone_ensure);
        all_activity_phone_rv = findViewById(R.id.all_activity_phone_Rv);
        all_activity_phone_no_information = findViewById(R.id.all_activity_phone_no_information);
        all_activity_phone_checkbox_all = findViewById(R.id.all_activity_phone_checkbox_all);
        all_activity_phone_checkbox_all_number = findViewById(R.id.all_activity_phone_checkbox_all_number);
        all_activity_side_bar = findViewById(R.id.all_activity_side_bar);
        all_activity_indexbar = findViewById(R.id.all_activity_indexbar);
        linearLayoutManager = new LinearLayoutManager(PhoneActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        all_activity_phone_rv.setLayoutManager(linearLayoutManager);
        all_activity_phone_rv.addItemDecoration(mDecoration = new SuspensionDecoration(PhoneActivity.this, list));
        //如果add两个，那么按照先后顺序，依次渲染。
        all_activity_phone_rv.addItemDecoration(new DividerItemDecoration(PhoneActivity.this, DividerItemDecoration.VERTICAL_LIST));
        //indexbar初始化
        all_activity_side_bar.setmPressedShowTextView(all_activity_indexbar)//设置HintTextView
                .setNeedRealIndex(true)//设置需要真实的索引
                .setmLayoutManager(linearLayoutManager);//设置RecyclerView的LayoutManager

        mContactModels = new ArrayList<>();
        initClick();
        initViews();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults != null) {
            if (grantResults.length != 0) {
                switch (requestCode) {
                    case 1://刚才的识别码
                        try {
                            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {//用户同意权限,执行我们的操作
                                Log.i("权限获取","开启读取通讯录权限");
                            } else {//用户拒绝之后,当然我们也可以弹出一个窗口,直接跳转到系统设置页面
                                //                            ToastUtil.showLongToast(AllActivity.this, "未开启读取通讯录权限,请手动到设置去开启读取通讯录权限");
                                Log.i("权限获取","未开启读取通讯录权限,请手动到设置去开启读取通讯录权限");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 2://刚才的识别码
                        try {
                            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {//用户同意权限,执行我们的操作
                            } else {//用户拒绝之后,当然我们也可以弹出一个窗口,直接跳转到系统设置页面
                                //                            ToastUtil.showLongToast(AllActivity.this, "未开启存储权限,请手动到设置去开启权限");
                                Log.i("权限获取","未开启存储权限,请手动到设置去开启权限");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    default:
                        break;
                }
            }
        }
    }

    private void initClick(){

        all_activity_phone_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        all_activity_phone_ensure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (phoneAdapter.getList() != null) {
                    phoneDtos = phoneAdapter.getList();
                    for (int i = 0;i < phoneDtos.size();i++){
                        if (phoneDtos.get(i).isStatus()) {
                            //  TODO    导入通讯录
                            jsonList.add(phoneDtos.get(i));
                            Log.i("每次","添加显示的数据"+phoneDtos.get(i).getName());
                        }
                        Log.i("每次","显示的数据"+phoneDtos.get(i).getName());
                        if (i == phoneDtos.size()-1) {
                            Log.i("每次","最后显示的数据"+phoneDtos.get(i).getName());
                            if (jsonList.size() != 0) {
                                initadd();
                            }else {
                                finish();
                            }
                        }
                    }
                }
            }
        });
    }


    private void initViews() {
        PhoneUtil phoneUtil = new PhoneUtil(PhoneActivity.this);
        phoneDtos = phoneUtil.getPhone();

        if (phoneDtos.size() != 0) {

            for (int i = 0; i < phoneDtos.size(); i++){
                list.add(new LinkmanBean(phoneDtos.get(i).getName()+"","",phoneDtos.get(i).getTelPhone()+""));
            }

            phoneAdapter = new PhoneAdapter(PhoneActivity.this,list);
            all_activity_phone_rv.setAdapter(phoneAdapter);
            phoneAdapter.setList(phoneDtos);
            phoneAdapter.notifyDataSetChanged();

            all_activity_side_bar.setmSourceDatas(list)//设置数据
                    .invalidate();
            mDecoration.setmDatas(list);

            phoneAdapter.setItemOnClick(new PhoneAdapter.ItemOnClick() {
                  @Override
                  public void itemClick(int position) {
                      Log.i("数据对比","1客户名"+list.get(position).getCity());
                      int size = 0;
                      phoneDtos = phoneAdapter.getList();
                      all_activity_phone_checkbox_all.setChecked(false);
                      all_activity_phone_checkbox_all.setText("全选");
                      for (int i = 0;i < phoneDtos.size();i++){
                          if (phoneDtos.get(i).isStatus()) {
                              size++;
                              all_activity_phone_checkbox_all_number.setText("已选"+size);
                          }
                      }
                      if (size == 0) {
                          all_activity_phone_checkbox_all_number.setText("已选0");
                      } else if (size == phoneDtos.size()) {
                          all_activity_phone_checkbox_all.setChecked(true);
                          all_activity_phone_checkbox_all.setText("取消全选");
                          phoneAdapter.setAll("1");
                          phoneAdapter.notifyDataSetChanged();
                      }
                  }
              }
            );


            all_activity_phone_checkbox_all.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (all_activity_phone_checkbox_all.getText().toString().equals("取消全选")) {
                        all_activity_phone_checkbox_all.setText("全选");
                        all_activity_phone_checkbox_all_number.setText("已选0");
                        phoneAdapter.setAll("2");
                        phoneAdapter.notifyDataSetChanged();
                    }else if (all_activity_phone_checkbox_all.getText().toString().equals("全选")){
                        all_activity_phone_checkbox_all.setText("取消全选");
                        all_activity_phone_checkbox_all_number.setText("已选"+phoneDtos.size());
                        phoneAdapter.setAll("1");
                        phoneAdapter.notifyDataSetChanged();
                    }
                }
            });
        }
    }

    private void initadd(){
        Gson gson = new Gson();
        fieldbeanlist = gson.toJson(jsonList);

        Log.i("MyCL","fieldbeanlist:" + fieldbeanlist);

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<AddPhoneBean> addClient = fzbInterface.getCustomerImport(fieldbeanlist,FinalContents.getUserID());
        addClient.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AddPhoneBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(AddPhoneBean addPhoneBean) {
                        String msg = addPhoneBean.getData().getMessage();
                        if (msg.equals("添加成功")) {
                            Toast.makeText(PhoneActivity.this, addPhoneBean.getData().getMessage(), Toast.LENGTH_SHORT).show();
                            CityContents.setAddClient("1");
                            finish();
                        } else {
                            Toast.makeText(PhoneActivity.this, addPhoneBean.getData().getMessage(), Toast.LENGTH_SHORT).show();
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

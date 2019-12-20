package com.xcy.fzbcity.all.view;

import android.Manifest;
import android.app.AppOpsManager;
import android.content.Context;
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
import com.xcy.fzbcity.all.service.MyService;
import com.xcy.fzbcity.all.utils.PhoneUtil;
import com.xcy.fzbcity.all.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PhoneActivity extends AllActivity{


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
        initfvb();
    }

    private void initfvb(){
        havaReadContacts(PhoneActivity.this,"READ_CONTACTS ");
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

    /**
     * 判断是否拥有联系人读取权限
     *
     * @param context
     * @return
     * XXX代表权限字符串，比如 READ_CONTACTS 通讯录读取权限
     */
    public boolean havaReadContacts(Context context, String xxx) {

        boolean have = false;

        ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_CONTACTS);
        if (Build.VERSION.SDK_INT >= 23) {
            AppOpsManager appOpsManager = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
            int checkOp = appOpsManager.checkOp(AppOpsManager.OPSTR_READ_CONTACTS, android.os.Process.myUid(), context.getPackageName());
            Log.e("通讯录权限", "checkOp:" + checkOp);
            ToastUtil.showLongToast(context,"AppOpsManager.MODE_ALLOWED ："+checkOp);
            switch (checkOp) {
                case AppOpsManager.MODE_ALLOWED:
                    Log.e("通讯录权限", "AppOpsManager.MODE_ALLOWED ：有权限");
                    ToastUtil.showLongToast(context,"AppOpsManager.MODE_ALLOWED ：有权限");
                    have = true;
                    break;
                case AppOpsManager.MODE_IGNORED:
                    Log.e("通讯录权限", "AppOpsManager.MODE_IGNORED：被禁止了");
                    ToastUtil.showLongToast(context,"AppOpsManager.MODE_ALLOWED ：被禁止了");
                    have = false;
                    break;
                case AppOpsManager.MODE_DEFAULT:
                    Log.e("通讯录权限", "AppOpsManager.MODE_DEFAULT");
                    ToastUtil.showLongToast(context,"AppOpsManager.MODE_ALLOWED ：默认");
                    break;
                case AppOpsManager.MODE_ERRORED:
                    Log.e("通讯录权限", "AppOpsManager.MODE_ERRORED：出错了");
                    ToastUtil.showLongToast(context,"AppOpsManager.MODE_ALLOWED ：出错了");
                    have = false;
                    break;
                case 4:
                    Log.e("通讯录权限", "AppOpsManager.OTHER：权限需要询问");
                    ToastUtil.showLongToast(context,"AppOpsManager.MODE_ALLOWED ：权限需要询问");
                    have = false;
                    break;
            }
        } else {
            have = ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_CONTACTS)
                    == PackageManager.PERMISSION_GRANTED;
        }
        return have;
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

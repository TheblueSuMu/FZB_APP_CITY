package com.xcy.fzbcity.all.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.modle.NickNameBean;
import com.xcy.fzbcity.all.persente.SingleClick;
import com.xcy.fzbcity.all.persente.StatusBar;
import com.xcy.fzbcity.all.service.MyService;
import com.xcy.fzbcity.all.utils.CommonUtil;
import com.xcy.fzbcity.all.utils.ToastUtil;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class SetPasswordActivity extends AllActivity implements View.OnClickListener {

    private RelativeLayout set_return;
    private TextView set_wc;
    private EditText set_j_password;
    private EditText set_x_password;
    private EditText set_x_again_password;
    private TextView set_z_password;
    private String msg;
    private String message;
    private String j;
    private String x;
    private String x_again = "";
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_password);
        init_No_Network();
    }

    private void init_No_Network(){
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

        set_return = findViewById(R.id.set_return);
        set_wc = findViewById(R.id.set_wc);
        set_j_password = findViewById(R.id.set_j_password);//旧密码
        set_x_password = findViewById(R.id.set_x_password);//新密码
        set_x_again_password = findViewById(R.id.set_x_again_password);//再次输入的新密码
        set_z_password = findViewById(R.id.set_z_password);

        set_return.setOnClickListener(this);
        set_wc.setOnClickListener(this);
        set_z_password.setOnClickListener(this);

    }
    @SingleClick(1000)
    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            //TODO 返回上一级
            case R.id.set_return:
                intent = new Intent(SetPasswordActivity.this, PersonalInformationActivity.class);
                startActivity(intent);
                finish();
                break;
            //TODO 完成
            case R.id.set_wc:

                j = set_j_password.getText().toString();
                x = set_x_password.getText().toString();
                x_again = set_x_again_password.getText().toString();
                if (j.equals("")) {
                    ToastUtil.showToast(SetPasswordActivity.this, "请输入要更改的旧密码");
                    return;
                }

                if (x.equals("")) {
                    ToastUtil.showToast(SetPasswordActivity.this, "请输入要更改的新密码");
                    return;
                }

                if (x_again.equals("")) {
                    ToastUtil.showToast(SetPasswordActivity.this, "请输入确认要更改的新密码");
                    return;
                }

                if (x.equals(x_again)) {
                    Retrofit.Builder builder = new Retrofit.Builder();
                    builder.baseUrl(FinalContents.getBaseUrl());
                    builder.addConverterFactory(GsonConverterFactory.create());
                    builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
                    Retrofit build = builder.build();
                    MyService fzbInterface = build.create(MyService.class);
                    Observable<NickNameBean> addPhoto = fzbInterface.getUpdatePassword(FinalContents.getUserID(), j, x_again);
                    Log.i("MyCL", "addPhoto：" + addPhoto.toString());
                    addPhoto.subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Observer<NickNameBean>() {
                                @Override
                                public void onSubscribe(Disposable d) {

                                }

                                @Override
                                public void onNext(NickNameBean nickNameBean) {
                                    NickNameBean.DataBean data1 = nickNameBean.getData();
                                    ToastUtil.showToast(SetPasswordActivity.this, data1.getMessage());
                                    if (data1.getMessage().equals("密码修改成功，请牢记！")) {
                                        finish();
                                        intent = new Intent(SetPasswordActivity.this, PersonalInformationActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                }

                                @Override
                                public void onError(Throwable e) {
                                    Log.i("MyCL", "修改密码错误信息：" + e.getMessage());
                                }

                                @Override
                                public void onComplete() {

                                }
                            });
                } else {
                    ToastUtil.showToast(this, "新密码两次不一致,请重新输入");
                }

                break;
            //TODO 找回密码
            case R.id.set_z_password:
                finish();
                Intent intent = new Intent(SetPasswordActivity.this,ForgetActivity.class);
                startActivity(intent);
                break;
        }

    }
}

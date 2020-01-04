package com.xcy.fzbcity.broker.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import com.xcy.fzbcity.all.utils.ToastUtil;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.github.androidprogresslayout.ProgressLayout;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.fragment.HomeFragment;
import com.xcy.fzbcity.all.fragment.MessageFragment;
import com.xcy.fzbcity.all.persente.StatusBar;
import com.xcy.fzbcity.all.utils.CommonUtil;
import com.xcy.fzbcity.all.utils.ToastUtil;
import com.xcy.fzbcity.all.utils.VirturlUtil;
import com.xcy.fzbcity.all.view.AllActivity;
import com.xcy.fzbcity.all.view.ReportActivity;
import com.xcy.fzbcity.broker.fragment.DFragment;
import com.xcy.fzbcity.broker.fragment.EFragment;

public class Broker_MainActivity extends AllActivity implements View.OnClickListener,HomeFragment.FragmentInteraction {

    private RadioButton button_home;
    private RadioButton button_message;
    private RadioButton button_backup;
    private RadioButton button_economics;
    private RadioButton button_me;
    private ImageView img_backup;
    private ProgressLayout progressLayout;
    FragmentManager manager = getSupportFragmentManager();
    FragmentTransaction transaction = manager.beginTransaction();

    HomeFragment home_fragment = new HomeFragment();
    MessageFragment message_fragment = new MessageFragment();
    DFragment dFragment = new DFragment();
    EFragment eFragment = new EFragment();


    //定义一个变量，来标识是否退出
    private static boolean isExit=false;

    @SuppressLint("HandlerLeak")
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg){
            super.handleMessage(msg);
            isExit=false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.broker_activity_main);
        StatusBar.makeStatusBarTransparent(this);
        FinalContents.setZhuanAn("0");
        init();
        initfvb();
    }

    private void init(){
        VirturlUtil.assistActivity(findViewById(android.R.id.content));
        init_No_Network();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        transaction.add(R.id.main_framelayout,home_fragment);
        transaction.add(R.id.main_framelayout,message_fragment);
        transaction.add(R.id.main_framelayout,dFragment);
        transaction.add(R.id.main_framelayout,eFragment);


        transaction.show(home_fragment);
        transaction.hide(message_fragment);
        transaction.hide(dFragment);
        transaction.hide(eFragment);
        transaction.commit();
    }

    // 3.2 +实现接口，实现回调
    @Override
    public void process(String str) {
        if (str != null) {
            if (str.equals("0")) {
                init_No_Network();
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
//                MessageFragment messageFragment = new MessageFragment();
                message_fragment.setType("2");
//                transaction.replace(R.id.main_framelayout,messageFragment);

                transaction.hide(home_fragment);
                transaction.hide(dFragment);
                transaction.show(message_fragment);
                transaction.hide(eFragment);

                transaction.commit();
                button_message.setChecked(true);
            } else if (str.equals("2")) {
                init_No_Network();
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
//                MessageFragment messageFragment = new MessageFragment();
                message_fragment.setType("3");
//                transaction.replace(R.id.main_framelayout,messageFragment);

                transaction.hide(home_fragment);
                transaction.hide(dFragment);
                transaction.show(message_fragment);
                transaction.hide(eFragment);

                transaction.commit();
                button_message.setChecked(true);
            } else if (str.equals("5")) {
                init_No_Network();
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
//                MessageFragment messageFragment = new MessageFragment();
                message_fragment.setType("4");
//                transaction.replace(R.id.main_framelayout,messageFragment);

                transaction.hide(home_fragment);
                transaction.hide(dFragment);
                transaction.show(message_fragment);
                transaction.hide(eFragment);

                transaction.commit();
                button_message.setChecked(true);
            }
        }

    }

    private void initfvb() {
        button_home = findViewById(R.id.button_home);
        button_message = findViewById(R.id.button_message);
        button_backup = findViewById(R.id.button_backup);
        button_economics = findViewById(R.id.button_economics);
        button_me = findViewById(R.id.button_me);
        img_backup = findViewById(R.id.img_backup);
        img_backup.setImageResource(R.mipmap.z13);
        img_backup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (FinalContents.getCityID().equals(FinalContents.getOldCityId())) {
                    Intent intent = new Intent(Broker_MainActivity.this, ReportActivity.class);
                    startActivity(intent);
                }else {
                    ToastUtil.showLongToast(Broker_MainActivity.this, "该城市不是您的主营城市，请切换到您的主营城市后再报备客户");
                }
            }
        });

        click();
    }

    public void click() {
        button_home.setOnClickListener(this);
        button_message.setOnClickListener(this);
        button_backup.setOnClickListener(this);
        button_economics.setOnClickListener(this);
        button_me.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        switch (view.getId()) {
            case R.id.button_home:
                init_No_Network();
//                HomeFragment home_fragment = new HomeFragment();
//                transaction.replace(R.id.main_framelayout, home_fragment);
                transaction.show(home_fragment);
                transaction.hide(message_fragment);
                transaction.hide(dFragment);
                transaction.hide(eFragment);
                break;
            case R.id.button_message:
                init_No_Network();

//                transaction.replace(R.id.main_framelayout, message_fragment);
                transaction.hide(home_fragment);
                transaction.show(message_fragment);
                transaction.hide(dFragment);
                transaction.hide(eFragment);
                break;
            case R.id.button_backup:
                if (FinalContents.getCityID().equals(FinalContents.getOldCityId())) {
                    ToastUtil.showToast(this, "");
                }else {
                    Intent intent = new Intent(Broker_MainActivity.this, ReportActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.button_economics:
                init_No_Network();

//                transaction.replace(R.id.main_framelayout, dFragment);
                transaction.hide(home_fragment);
                transaction.hide(message_fragment);
                transaction.show(dFragment);
                transaction.hide(eFragment);
                break;
            case R.id.button_me:
                init_No_Network();

//                transaction.replace(R.id.main_framelayout, eFragment);
                transaction.hide(home_fragment);
                transaction.hide(message_fragment);
                transaction.hide(dFragment);
                transaction.show(eFragment);
                break;
        }
        transaction.commit();
    }


    private void init_No_Network() {
        boolean networkAvailable = CommonUtil.isNetworkAvailable(this);
        if (networkAvailable) {

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

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("MyCL","进入了Broker_MainActivity中onResume方法中");

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if(keyCode==KeyEvent.KEYCODE_BACK){
            exit();
            return false;
        }
        return super.onKeyDown(keyCode,event);
    }

    private void exit(){
        if(!isExit){
            isExit=true;
            ToastUtil.showLongToast(getApplicationContext(),"再按一次返回键，退出程序");
            //利用handler延迟发送更改状态信息
            handler.sendEmptyMessageDelayed(0,2000);
        }
        else{
            AllActivity.exit = true;
            finish();
            System.exit(0);
        }
    }
}

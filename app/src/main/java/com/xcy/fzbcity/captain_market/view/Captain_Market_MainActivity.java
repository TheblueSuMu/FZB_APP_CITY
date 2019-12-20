package com.xcy.fzbcity.captain_market.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.github.androidprogresslayout.ProgressLayout;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.fragment.MessageFragment;
import com.xcy.fzbcity.all.persente.SingleClick;
import com.xcy.fzbcity.all.persente.StatusBar;
import com.xcy.fzbcity.all.utils.CommonUtil;
import com.xcy.fzbcity.all.utils.ToastUtil;
import com.xcy.fzbcity.all.view.AllActivity;
import com.xcy.fzbcity.all.view.ReportActivity;
import com.xcy.fzbcity.captain_market.fragment.MeFragment;
import com.xcy.fzbcity.captain_team.fragment.Captain_Team_MyClientFragment;
import com.xcy.fzbcity.captain_team.fragment.ProjectFragment;

public class Captain_Market_MainActivity extends AllActivity implements View.OnClickListener, ProjectFragment.FragmentInteraction {
    private RadioButton button_home;
    private RadioButton button_message;
    private RadioButton button_backup;
    private RadioButton button_economics;
    private RadioButton button_me;
    private ImageView img_backup;
    private ProgressLayout progressLayout;


    ProjectFragment projectFragment = new ProjectFragment();
    Captain_Team_MyClientFragment myClientFragment = new Captain_Team_MyClientFragment();
    MessageFragment messageFragment = new MessageFragment();
    MeFragment meFragment = new MeFragment();

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
        setContentView(R.layout.captain_market_activity_main);
        StatusBar.makeStatusBarTransparent(this);
        FinalContents.setDengLu("销售");
        FinalContents.setZhuanAn("0");
        FinalContents.setQuanceng("1");
        initfvb();

    }

    private void init_No_Network(){
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

    // 3.2 +实现接口，实现回调
    @Override
    public void process(String str) {
        if (str != null) {
            if (str.equals("0")) {
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
//                MessageFragment messageFragment = new MessageFragment();
                messageFragment.setType("2");
//                transaction.replace(R.id.main_framelayout,messageFragment);

                transaction.hide(projectFragment);
                transaction.hide(myClientFragment);
                transaction.show(messageFragment);
                transaction.hide(meFragment);

                transaction.commit();
                button_message.setChecked(true);
            } else if (str.equals("2")) {
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
//                MessageFragment messageFragment = new MessageFragment();
                messageFragment.setType("3");
//                transaction.replace(R.id.main_framelayout,messageFragment);

                transaction.hide(projectFragment);
                transaction.hide(myClientFragment);
                transaction.show(messageFragment);
                transaction.hide(meFragment);

                transaction.commit();
                button_message.setChecked(true);
            } else if (str.equals("5")) {
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
//                MessageFragment messageFragment = new MessageFragment();
                messageFragment.setType("4");
//                transaction.replace(R.id.main_framelayout,messageFragment);

                transaction.hide(projectFragment);
                transaction.hide(myClientFragment);
                transaction.show(messageFragment);
                transaction.hide(meFragment);

                transaction.commit();
                button_message.setChecked(true);
            }
        }
    }

    private void initfvb(){
        init_No_Network();
        button_home = findViewById(R.id.button_home);
        button_message = findViewById(R.id.button_message);
        button_backup = findViewById(R.id.button_backup);
        button_economics = findViewById(R.id.button_economics);
        button_me = findViewById(R.id.button_me);
        img_backup = findViewById(R.id.img_backup);
        img_backup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (FinalContents.getCityID().equals(FinalContents.getOldCityId())) {
                    Intent intent = new Intent(Captain_Market_MainActivity.this, ReportActivity.class);
                    startActivity(intent);
                }else {
                    ToastUtil.showLongToast(Captain_Market_MainActivity.this, "该城市不是您的主营城市，请切换到您的主营城市后再报备客户");
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

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        transaction.add(R.id.main_framelayout,projectFragment);
        transaction.add(R.id.main_framelayout,myClientFragment);
        transaction.add(R.id.main_framelayout,messageFragment);
        transaction.add(R.id.main_framelayout,meFragment);

        transaction.show(projectFragment);
        transaction.hide(myClientFragment);
        transaction.hide(messageFragment);
        transaction.hide(meFragment);

        transaction.commit();
    }

    @SingleClick(1000)
    @Override
    public void onClick(View view) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        switch (view.getId()) {
            case R.id.button_economics:
                init_No_Network();
                FinalContents.setMySelf("1");
                FinalContents.setQuanceng("1");
                FinalContents.setAgentId(FinalContents.getUserID());

                transaction.hide(projectFragment);
                transaction.show(myClientFragment);
                transaction.hide(messageFragment);
                transaction.hide(meFragment);
//                transaction.replace(R.id.main_framelayout,myClientFragment);
                break;
            case R.id.button_home:
                init_No_Network();

                transaction.show(projectFragment);
                transaction.hide(myClientFragment);
                transaction.hide(messageFragment);
                transaction.hide(meFragment);
//                transaction.replace(R.id.main_framelayout,projectFragment);
                break;
            case R.id.button_message:
                init_No_Network();

                transaction.hide(projectFragment);
                transaction.hide(myClientFragment);
                transaction.show(messageFragment);
                transaction.hide(meFragment);
//                transaction.replace(R.id.main_framelayout,messageFragment);
                break;
            case R.id.button_me:
                init_No_Network();

                transaction.hide(projectFragment);
                transaction.hide(myClientFragment);
                transaction.hide(messageFragment);
                transaction.show(meFragment);
//                transaction.replace(R.id.main_framelayout,meFragment);
                break;
        }
        transaction.commit();
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

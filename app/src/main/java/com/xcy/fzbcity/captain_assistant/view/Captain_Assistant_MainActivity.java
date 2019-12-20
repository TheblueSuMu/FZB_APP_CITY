package com.xcy.fzbcity.captain_assistant.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.github.androidprogresslayout.ProgressLayout;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.fragment.MessageFragment;
import com.xcy.fzbcity.all.persente.SingleClick;
import com.xcy.fzbcity.all.persente.StatusBar;
import com.xcy.fzbcity.all.utils.CommonUtil;
import com.xcy.fzbcity.all.utils.ToastUtil;
import com.xcy.fzbcity.all.view.AllActivity;
import com.xcy.fzbcity.captain_assistant.fragment.AssistantHomeFragment;
import com.xcy.fzbcity.captain_assistant.fragment.AssistantMeFragment;
import com.xcy.fzbcity.captain_assistant.fragment.TeamFragment;

import me.jessyan.autosize.AutoSizeConfig;
import me.jessyan.autosize.internal.CustomAdapt;

public class Captain_Assistant_MainActivity extends AllActivity implements View.OnClickListener, CustomAdapt, AssistantHomeFragment.FragmentInteraction, AssistantMeFragment.FragmentInteraction {

    private RadioButton home;
    private RadioButton project;
    private RadioButton message;
    private RadioButton me;
    private ProgressLayout progressLayout;

    AssistantHomeFragment projectFragment = new AssistantHomeFragment();
    TeamFragment teamFragment = new TeamFragment();
    MessageFragment messageFragment = new MessageFragment();
    AssistantMeFragment meFragment = new AssistantMeFragment();

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
        AutoSizeConfig.getInstance()
                .getExternalAdaptManager()
                .addCancelAdaptOfActivity(Captain_Assistant_MainActivity.class);
        setContentView(R.layout.activity_captain__assistant__main);
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

    private void initfvb() {
        init_No_Network();
        StatusBar.makeStatusBarTransparent(this);

        home = findViewById(R.id.team_main_home);
        project = findViewById(R.id.team_main_project);
        message = findViewById(R.id.team_main_message);
        me = findViewById(R.id.team_main_me);

        home.setOnClickListener(this);
        project.setOnClickListener(this);
        message.setOnClickListener(this);
        me.setOnClickListener(this);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        transaction.add(R.id.team_main_framelayout,projectFragment);
        transaction.add(R.id.team_main_framelayout,teamFragment);
        transaction.add(R.id.team_main_framelayout,messageFragment);
        transaction.add(R.id.team_main_framelayout,meFragment);

        transaction.show(projectFragment);
        transaction.hide(teamFragment);
        transaction.hide(messageFragment);
        transaction.hide(meFragment);

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
                messageFragment.setType("2");
//                transaction.replace(R.id.team_main_framelayout,messageFragment);

                transaction.hide(projectFragment);
                transaction.hide(teamFragment);
                transaction.show(messageFragment);
                transaction.hide(meFragment);

                transaction.commit();
                message.setChecked(true);
            } else if (str.equals("2")) {
                init_No_Network();
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
//                MessageFragment messageFragment = new MessageFragment();
                messageFragment.setType("3");
//                transaction.replace(R.id.team_main_framelayout,messageFragment);

                transaction.hide(projectFragment);
                transaction.hide(teamFragment);
                transaction.show(messageFragment);
                transaction.hide(meFragment);

                transaction.commit();
                message.setChecked(true);
            } else if (str.equals("5")) {
                init_No_Network();
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
//                MessageFragment messageFragment = new MessageFragment();
                messageFragment.setType("4");
//                transaction.replace(R.id.team_main_framelayout,messageFragment);

                transaction.hide(projectFragment);
                transaction.hide(teamFragment);
                transaction.show(messageFragment);
                transaction.hide(meFragment);

                transaction.commit();
                message.setChecked(true);
            } else if (str.equals("63")) {
                init_No_Network();
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
//                TeamFragment teamFragment = new TeamFragment();
//                transaction.replace(R.id.team_main_framelayout, teamFragment);

                transaction.hide(projectFragment);
                transaction.show(teamFragment);
                transaction.hide(messageFragment);
                transaction.hide(meFragment);

                transaction.commit();
                project.setChecked(true);
            }
        }
    }

    @SingleClick(1000)
    @Override
    public void onClick(View view) {

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        switch (view.getId()) {
            case R.id.team_main_home:
                init_No_Network();
                transaction.show(projectFragment);
                transaction.hide(teamFragment);
                transaction.hide(messageFragment);
                transaction.hide(meFragment);
//                transaction.replace(R.id.team_main_framelayout, projectFragment);
                break;
            case R.id.team_main_project:
                init_No_Network();
                transaction.hide(projectFragment);
                transaction.show(teamFragment);
                transaction.hide(messageFragment);
                transaction.hide(meFragment);
//                transaction.replace(R.id.team_main_framelayout, teamFragment);
                break;
            case R.id.team_main_message:
                init_No_Network();
                transaction.hide(projectFragment);
                transaction.hide(teamFragment);
                transaction.show(messageFragment);
                transaction.hide(meFragment);
//                transaction.replace(R.id.team_main_framelayout, messageFragment);
                break;
            case R.id.team_main_me:
                init_No_Network();
                transaction.hide(projectFragment);
                transaction.hide(teamFragment);
                transaction.hide(messageFragment);
                transaction.show(meFragment);
//                transaction.replace(R.id.team_main_framelayout, meFragment);
                break;
        }
        transaction.commit();
    }

    @Override
    public boolean isBaseOnWidth() {
        return false;
    }

    @Override
    public float getSizeInDp() {
        return 667;
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
            ToastUtil.showToast(getApplicationContext(),"再按一次返回键，退出程序");
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
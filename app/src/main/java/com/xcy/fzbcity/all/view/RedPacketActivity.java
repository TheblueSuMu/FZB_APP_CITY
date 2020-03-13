package com.xcy.fzbcity.all.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.api.RedEnvelopesAllTalk;
import com.xcy.fzbcity.all.modle.CheckRedbagPayBean;
import com.xcy.fzbcity.all.modle.CheckRedbagPayProjectBean;
import com.xcy.fzbcity.all.modle.CodeBean;
import com.xcy.fzbcity.all.modle.RechargeRedbagBean;
import com.xcy.fzbcity.all.service.MyService;
import com.xcy.fzbcity.all.utils.CountDownTimerUtils;
import com.xcy.fzbcity.all.utils.ToastUtil;

import cn.sharesdk.wechat.utils.WXMediaMessage;
import cn.sharesdk.wechat.utils.WXTextObject;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RedPacketActivity extends AllActivity implements View.OnClickListener {


    private RelativeLayout red_packet_return;
    private TextView red_packet_record;
    private EditText red_packet_unit_price_et;
    private EditText red_packet_number_et;
    private EditText red_packet_people_et;
    private TextView red_packet_check_hint;
    private TextView red_packet_check_text;
    private Button red_packet_pay;
    private boolean switchover = true;
    private RelativeLayout red_packet_list_relative0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_red_packet);
        initfvb();
    }

    private void initfvb(){
        red_packet_return = findViewById(R.id.red_packet_return);
        red_packet_record = findViewById(R.id.red_packet_record);
        red_packet_unit_price_et = findViewById(R.id.red_packet_unit_price_et);
        red_packet_number_et = findViewById(R.id.red_packet_number_et);
        red_packet_people_et = findViewById(R.id.red_packet_people_et);
        red_packet_check_hint = findViewById(R.id.red_packet_check_hint);
        red_packet_check_text = findViewById(R.id.red_packet_check_text);
        red_packet_pay = findViewById(R.id.red_packet_pay);
        red_packet_list_relative0 = findViewById(R.id.red_packet_list_relative0);

        //可以合并一起写为：
        red_packet_check_text.getPaint().setFlags(Paint. UNDERLINE_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);

        red_packet_return.setOnClickListener(this);
        red_packet_record.setOnClickListener(this);
        red_packet_unit_price_et.setOnClickListener(this);
        red_packet_number_et.setOnClickListener(this);
        red_packet_people_et.setOnClickListener(this);
        red_packet_check_text.setOnClickListener(this);
        red_packet_pay.setOnClickListener(this);
        red_packet_list_relative0.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.red_packet_return:
                //  TODO    返回
                finish();
                break;
            case R.id.red_packet_record:
                //  TODO    红包记录
                Intent intent = new Intent(RedPacketActivity.this,RedPacketRecordActivity.class);
                startActivity(intent);
                break;
            case R.id.red_packet_unit_price_et:
                //  TODO    单个金额
                break;
            case R.id.red_packet_number_et:
                //  TODO    红包个数
                break;
            case R.id.red_packet_people_et:
                //  TODO    助力人数
                break;
            case R.id.red_packet_check_text:
                //  TODO    红包推项目与红包推店的切换
                if (switchover) {
                    red_packet_check_hint.setText("当前为红包推项目，");
                    red_packet_check_text.setText("改为红包推店");
                    red_packet_list_relative0.setVisibility(View.VISIBLE);
                    switchover = false;
                }else {
                    red_packet_check_hint.setText("当前为红包推店，");
                    red_packet_check_text.setText("改为红包推项目");
                    red_packet_list_relative0.setVisibility(View.GONE);
                    switchover = true;
                }
                break;
            case R.id.red_packet_pay:
                //  TODO    支付按钮
                initPayDetection();
                break;
            case R.id.red_packet_list_relative0:
                //  TODO    推广项目按钮
                break;
        }
    }

    //      TODO    检查店铺或者项目是否已发过红包
    private void initPayDetection(){
        Log.i("检查店铺或者项目是否已发过红包", "FinalContents.getUserID():"+FinalContents.getUserID());
        Log.i("检查店铺或者项目是否已发过红包", "FinalContents.getProjectID():"+FinalContents.getProjectID());
        Log.i("检查店铺或者项目是否已发过红包", "RedEnvelopesAllTalk.getWebshopId():"+RedEnvelopesAllTalk.getWebshopId());

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        final Observable<CheckRedbagPayProjectBean> code = fzbInterface.getCheckRedbagPayProject(FinalContents.getUserID(),"", RedEnvelopesAllTalk.getWebshopId());
        code.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CheckRedbagPayProjectBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CheckRedbagPayProjectBean checkRedbagPayProjectBean) {
                        if (checkRedbagPayProjectBean.getData().getMessageCode().equals("0")) {
                            initPay();
                        }else {
                            ToastUtil.showLongToast(RedPacketActivity.this,checkRedbagPayProjectBean.getData().getMessage());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("检查店铺或者项目是否已发过红包", "检查店铺或者项目是否已发过红包错误信息:" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    //      TODO    红包下单接口
    private void initPay(){
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        final Observable<RechargeRedbagBean> code = fzbInterface.getRechargeRedbag(FinalContents.getUserID(),"", ""+RedEnvelopesAllTalk.getWebshopId(),FinalContents.getCityID(),"2.95","1","1");
        code.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RechargeRedbagBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RechargeRedbagBean rechargeRedbagBean) {
                        rechargeRedbagBean.getData().getAppid();//你的微信appid
                        rechargeRedbagBean.getData().getDevice_info();
                        rechargeRedbagBean.getData().getErr_code();
                        rechargeRedbagBean.getData().getErr_code_des();
                        rechargeRedbagBean.getData().getMch_id();
                        rechargeRedbagBean.getData().getNonce_str();//随机字符串
                        rechargeRedbagBean.getData().getPrepay_id();//预支付交易会话ID
                        rechargeRedbagBean.getData().getResult_code();
                        rechargeRedbagBean.getData().getReturn_code();
                        rechargeRedbagBean.getData().getReturn_msg();
                        rechargeRedbagBean.getData().getSign();//签名
                        rechargeRedbagBean.getData().getTrade_type();
                        rechargeRedbagBean.getData().getTimestamp();

                        final IWXAPI msgApi = WXAPIFactory.createWXAPI(RedPacketActivity.this, null);// 将该app注册到微信
                        msgApi.registerApp("wxf9a42b48a61cfd62");
                        PayReq req = new PayReq();
                        req.appId = rechargeRedbagBean.getData().getAppid();//你的微信appid
                        req.partnerId = rechargeRedbagBean.getData().getMch_id();//商户号
                        req.prepayId = rechargeRedbagBean.getData().getPrepay_id();//预支付交易会话ID
                        req.nonceStr = rechargeRedbagBean.getData().getNonce_str();//随机字符串
                        req.timeStamp = rechargeRedbagBean.getData().getTimestamp();//时间戳
                        req.packageValue = "Sign=WXPay";//扩展字段,这里固定填写Sign=WXPay
                        req.sign = rechargeRedbagBean.getData().getSign();//签名
                        msgApi.sendReq(req);

                        Log.i("微信支付参数","你的微信appid"+rechargeRedbagBean.getData().getAppid());
                        Log.i("微信支付参数","商户号"+rechargeRedbagBean.getData().getMch_id());
                        Log.i("微信支付参数","预支付交易会话ID"+rechargeRedbagBean.getData().getPrepay_id());
                        Log.i("微信支付参数","随机字符串"+rechargeRedbagBean.getData().getNonce_str());
                        Log.i("微信支付参数","时间戳"+rechargeRedbagBean.getData().getTimestamp());
                        Log.i("微信支付参数","签名"+rechargeRedbagBean.getData().getSign());

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("红包下单接口", "红包下单接口错误信息:" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }


    public void onResp(BaseResp resp){
        if(resp.getType()== ConstantsAPI.COMMAND_PAY_BY_WX){
            Log.d("支付","onPayFinish,errCode="+resp.errCode);
            if (resp.errCode == 0) {
                initCheckRedbagPay();
            } else{
                ToastUtil.showLongToast(RedPacketActivity.this,"支付失败或取消");
            }
        }
    }

    //      TODO    红包查单接口
    private void initCheckRedbagPay(){
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        final Observable<CheckRedbagPayBean> code = fzbInterface.getCheckRedbagPay(FinalContents.getUserID(),"");
        code.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CheckRedbagPayBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CheckRedbagPayBean checkRedbagPayBean) {
                        if (checkRedbagPayBean.getData().getMessageCode().equals("1")) {
                            ToastUtil.showLongToast(RedPacketActivity.this,"支付成功");
                        } else {
                            ToastUtil.showLongToast(RedPacketActivity.this,"支付失败");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("红包查单接口", "红包查单接口错误信息:" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}

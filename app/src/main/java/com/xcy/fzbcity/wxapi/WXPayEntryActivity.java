package com.xcy.fzbcity.wxapi;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.xcy.fzbcity.all.api.RedEnvelopesAllTalk;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {
	
	private static final String TAG = "MicroMsg.SDKSample.WXPayEntryActivity";
	
    private IWXAPI api;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
   	    api = WXAPIFactory.createWXAPI(this, "wxf9a42b48a61cfd62");
        api.handleIntent(getIntent(), this);
    }

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		setIntent(intent);
        api.handleIntent(intent, this);
	}

	@Override
	public void onReq(BaseReq req) {
		Log.d("微信支付", "onPayFinish, 进入");
	}

	@Override
	public void onResp(BaseResp resp) {
		if(resp.getType()== ConstantsAPI.COMMAND_PAY_BY_WX){
			Log.d("微信支付", "onPayFinish, errCode = " + resp.errCode);
			RedEnvelopesAllTalk.setErrCode(resp.errCode);
			finish();
		}

	}
}
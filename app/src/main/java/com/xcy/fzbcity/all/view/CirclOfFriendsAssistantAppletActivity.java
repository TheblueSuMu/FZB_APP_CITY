package com.xcy.fzbcity.all.view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXMiniProgramObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.adapter.HousingSupermarketAdapter;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.api.RedEnvelopesAllTalk;
import com.xcy.fzbcity.all.modle.AppletWechatImageBean;
import com.xcy.fzbcity.all.modle.SupermarketBean;
import com.xcy.fzbcity.all.persente.BitmapUtils;
import com.xcy.fzbcity.all.persente.ImageDispose;
import com.xcy.fzbcity.all.service.MyService;

import java.io.ByteArrayOutputStream;
import java.io.File;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.onekeyshare.ShareContentCustomizeCallback;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static mapsdkvi.com.gdi.bgl.android.java.EnvDrawText.bmp;


public class CirclOfFriendsAssistantAppletActivity extends AllActivity implements View.OnClickListener {

    private RelativeLayout circle_of_friends_assistant_applet_return;
    private ImageView circle_of_friends_assistant_applet_background;
    private TextView circle_of_friends_assistant_applet_name;
    private ImageView circle_of_friends_assistant_applet_image;
    private Button circle_of_friends_assistant_applet_generalize;
    private ScrollView circle_of_friends_assistant_applet_scrollview;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_of_friends_assistant_applet);
        initfvb();
    }

    private void initfvb(){
        circle_of_friends_assistant_applet_return = findViewById(R.id.circle_of_friends_assistant_applet_return);
        circle_of_friends_assistant_applet_background = findViewById(R.id.circle_of_friends_assistant_applet_background);
        circle_of_friends_assistant_applet_name = findViewById(R.id.circle_of_friends_assistant_applet_name);
        circle_of_friends_assistant_applet_image = findViewById(R.id.circle_of_friends_assistant_applet_image);
        circle_of_friends_assistant_applet_generalize = findViewById(R.id.circle_of_friends_assistant_applet_generalize);
        circle_of_friends_assistant_applet_scrollview = findViewById(R.id.circle_of_friends_assistant_applet_scrollview);

        circle_of_friends_assistant_applet_return.setOnClickListener(this);
        circle_of_friends_assistant_applet_generalize.setOnClickListener(this);

        Glide.with(CirclOfFriendsAssistantAppletActivity.this).load(FinalContents.getImageUrl() + RedEnvelopesAllTalk.getAppletImage()).into(circle_of_friends_assistant_applet_background);
        circle_of_friends_assistant_applet_name.setText(RedEnvelopesAllTalk.getUserName()+"  "+RedEnvelopesAllTalk.getUserPhone());
        initData();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.circle_of_friends_assistant_applet_return :
                //      TODO    返回
                finish();
                break;
            case R.id.circle_of_friends_assistant_applet_generalize :
                //      TODO    推广按钮
                showShare();
                break;

        }
    }

    private void showShare() {
        final IWXAPI msgApi = WXAPIFactory.createWXAPI(CirclOfFriendsAssistantAppletActivity.this, null);// 将该app注册到微信
        msgApi.registerApp("wxf9a42b48a61cfd62");
        WXMiniProgramObject miniProgram = new WXMiniProgramObject();
        miniProgram.miniprogramType = WXMiniProgramObject.MINIPTOGRAM_TYPE_RELEASE;// 正式版:0，测试版:1，体验版:2
        miniProgram.webpageUrl="http://www.qq.com";//自定义
        miniProgram.userName="gh_f55c0afa4b0b";//小程序端提供参数
        miniProgram.path="pages/project/index?agentId="+FinalContents.getUserID();//小程序端提供参数
        WXMediaMessage mediaMessage = new WXMediaMessage(miniProgram);
        mediaMessage.title = "cgw miniProgram";//自定义
        mediaMessage.description = "this is miniProgram's description";//自定义
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.logo_city, null);
//        imageZoom(scrollViewScreenShot(circle_of_friends_assistant_applet_scrollview))
        mediaMessage.thumbData = ImageDispose.Bitmap2Bytes(BitmapUtils.scaleBitmap(bitmap, 2));
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = "";
        req.scene = SendMessageToWX.Req.WXSceneSession;
        req.message = mediaMessage;
        msgApi.sendReq(req);
    }

    private Bitmap imageZoom(Bitmap bitMap) {
        //图片允许最大空间   单位：KB
        double maxSize =100.00;
        //将bitmap放至数组中，意在bitmap的大小（与实际读取的原文件要大）
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitMap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] b = baos.toByteArray();
        System.out.println("XXX压缩后byte.length " + b.length);
        //将字节换成KB
        double mid = b.length/1024;
        //判断bitmap占用空间是否大于允许最大空间  如果大于则压缩 小于则不压缩
        if (mid > maxSize) {
            //获取bitmap大小 是允许最大大小的多少倍
            double i = mid / maxSize;
            //开始压缩  此处用到平方根 将宽带和高度压缩掉对应的平方根倍 （1.保持刻度和高度和原bitmap比率一致，压缩后也达到了最大大小占用空间的大小）
            bitMap = zoomImage(bitMap, bitMap.getWidth() / Math.sqrt(i),
                    bitMap.getHeight() / Math.sqrt(i));
        }
        return  bitMap;
    }



    /***
     * 图片的缩放方法
     *
     * @param bgimage
     *            ：源图片资源
     * @param newWidth
     *            ：缩放后宽度
     * @param newHeight
     *            ：缩放后高度
     * @return
     */
    public static Bitmap zoomImage(Bitmap bgimage, double newWidth,
                                   double newHeight) {
        // 获取这个图片的宽和高
        float width = bgimage.getWidth();
        float height = bgimage.getHeight();
        // 创建操作图片用的matrix对象
        Matrix matrix = new Matrix();
        // 计算宽高缩放率
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // 缩放图片动作
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap bitmap = Bitmap.createBitmap(bgimage, 0, 0, (int) width, (int) height, matrix, true);
        return bitmap;
    }

    public static byte[] bitmap2Bytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        return baos.toByteArray();
    }

    public static Bitmap RGB_565(Bitmap bitmap) {
        byte[] bytes = bitmap2Bytes(bitmap);
        System.out.println("XXX压缩前byte.length " + bytes.length);
        BitmapFactory.Options options2 = new BitmapFactory.Options();
        options2.inPreferredConfig = Bitmap.Config.RGB_565;
        Bitmap bitmap1 = BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options2);
        byte[] bytes1 = bitmap2Bytes(bitmap1);
        System.out.println("XXX压缩后byte.length " + bytes1.length);
        return bitmap1;
    }

    /**
     * 获取scrollview的截屏
     */
    public static Bitmap scrollViewScreenShot(ScrollView scrollView) {
        int h = 0;
        Bitmap bitmap = null;
        for (int i = 0; i < scrollView.getChildCount(); i++) {
            h += scrollView.getChildAt(i).getHeight();
            scrollView.getChildAt(i).setBackgroundColor(Color.parseColor("#ffffff"));
        }
        bitmap = Bitmap.createBitmap(scrollView.getWidth(), h, Bitmap.Config.RGB_565);
        final Canvas canvas = new Canvas(bitmap);
        scrollView.draw(canvas);
        return bitmap;
    }

    //      TODO    推广获客
    private void initData(){
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        final Observable<AppletWechatImageBean> code = fzbInterface.getAppletWechatImage(FinalContents.getUserID());
        code.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AppletWechatImageBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(AppletWechatImageBean appletWechatImageBean) {
                        Glide.with(CirclOfFriendsAssistantAppletActivity.this).load(appletWechatImageBean.getData().getQrFilePath()).into(circle_of_friends_assistant_applet_image);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("推广获客", "推广获客错误信息:" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}

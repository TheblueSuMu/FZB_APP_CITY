package com.xcy.fzbcity.all.fragment;


import android.annotation.SuppressLint;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.xcy.fzbcity.all.utils.ToastUtil;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.adapter.Dynamic2Adapter;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.api.NewlyIncreased;
import com.xcy.fzbcity.all.modle.Dynamic2Bean;
import com.xcy.fzbcity.all.persente.StatusBar;
import com.xcy.fzbcity.all.service.MyService;
import com.xcy.fzbcity.all.utils.ToastUtil;
import com.xcy.fzbcity.all.view.BigPhotoActivity;
import com.xcy.fzbcity.all.view.MessageCommentActivity;
import com.xcy.fzbcity.all.view.ProjectDetails;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
//TODO 楼盘动态
public class DynamicFragment extends Fragment {
    PtrClassicFrameLayout ptrClassicFrameLayout;
    private RecyclerView recyclerView;
    private List<Dynamic2Bean.DataBean.RowsBean> list = new ArrayList<>();
    private Dynamic2Adapter recyclerAdapter;
    private String imgUrl;
    private TextView textView;
    Bitmap bitmap;

    private int j;
    private int num = -1;
    List<String> lists;
    private String imgURl;//图片的URL地址
    private static final int SAVE_SUCCESS = 0;//保存图片成功
    private static final int SAVE_FAILURE = 1;//保存图片失败
    private static final int SAVE_BEGIN = 2;//开始保存图片
    int countNum1 = 0;
    int countNum2 = 0;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SAVE_BEGIN:
                    if(countNum1 == 0){
                        ToastUtil.showToast(getContext(), "开始保存图片...");
                    }
                    countNum1++;
                    if(countNum1 == a.length){
                        countNum1 = 0;
                    }
//                    mSaveBtn.setClickable(false);
                    break;
                case SAVE_SUCCESS:
                    if(countNum2 == 0){
                        ToastUtil.showToast(getContext(), "图片保存成功,请到相册查找...");
                    }
                    countNum2++;
                    Log.i("图片保存","countNum2：" + countNum2);
                    Log.i("图片保存","a.length：" + a.length);
                    if(countNum2 == a.length){
                        countNum2 = 0;
                    }
//                    mSaveB
                    num = 1;
//                    mSaveBtn.setClickable(true);
                    break;
                case SAVE_FAILURE:
                    ToastUtil.showToast(getContext(), "图片保存失败,请稍后再试...");
//                    mSaveBtn.setClickable(true);
                    break;
            }
        }
    };
    private View view;
    private String url;
    private ImageView all_no_information;
    private String[] a;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        StatusBar.makeStatusBarTransparent(getActivity());

        view = inflater.inflate(R.layout.fragment_dynamic, container, false);


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        recyclerView = view.findViewById(R.id.dynamic_rv);
        all_no_information = view.findViewById(R.id.all_no_information);
        textView = view.findViewById(R.id.dynamic_text);
        ptrClassicFrameLayout = (PtrClassicFrameLayout) getActivity().findViewById(R.id.store_house_ptr_frame_11);
        ptrClassicFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ptrClassicFrameLayout.refreshComplete();
                        ptrClassicFrameLayout.setLastUpdateTimeKey("2017-2-10");
                        initView();
                    }
                }, 1000);
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }
        });
        initView();
    }

    @SuppressLint("WrongConstant")
    private void initView() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Log.i("刷新","FinalContents.getCityID()");
        Observable<Dynamic2Bean> userMessage = fzbInterface.getDynamicBeanList2(FinalContents.getCityID(),FinalContents.getUserID(), "1000");
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Dynamic2Bean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @SuppressLint("WrongConstant")
                    @Override
                    public void onNext(Dynamic2Bean dynamicBean) {
                        Dynamic2Bean.DataBean dynamicBeanData = dynamicBean.getData();
                        list = dynamicBeanData.getRows();
                        Log.i("楼盘动态列表数据加载", "次数" );
                        if (list.size() != 0) {
                            all_no_information.setVisibility(View.GONE);
                            recyclerView.setVisibility(View.VISIBLE);
                            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerAdapter = new Dynamic2Adapter();
                            recyclerAdapter.setList(list);
                            //    TODO 联系
                            recyclerAdapter.setDynamicClick(new Dynamic2Adapter.LianxiClick() {
                                @Override
                                public void Click(final int position) {
                                    String phone = list.get(position).getAttaches().get(0).getPhone();
                                    if (phone.equals("")) {
                                        ToastUtil.showLongToast(getContext(),"暂无电话信息，无法拨打");
                                    } else {
                                        final List<String> arrayList = new ArrayList<>();
                                        for (int i = 0; i < list.get(position).getAttaches().size(); i++) {
                                            arrayList.add(list.get(position).getAttaches().get(i).getName());
                                        }
                                        //      监听选中
                                        OptionsPickerView pvOptions = new OptionsPickerBuilder(view.getContext(), new OnOptionsSelectListener() {
                                            @Override
                                            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                                                //               返回的分别是三个级别的选中位置
                                                //              展示选中数据
                                                Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + list.get(position).getAttaches().get(options1).getPhone()));//跳转到拨号界面，同时传递电话号码
                                                startActivity(dialIntent);
                                            }
                                        })
                                                .setSelectOptions(0)//设置选择第一个
                                                .setOutSideCancelable(false)//点击背的地方不消失
                                                .build();//创建
                                        //      把数据绑定到控件上面
                                        pvOptions.setPicker(arrayList);
                                        //      展示
                                        pvOptions.show();
                                    }
                                }
                            });

                            //    TODO 评论
                            recyclerAdapter.setPingLun(new Dynamic2Adapter.PingLun() {
                                @Override
                                public void pingClick(int position) {
                                    FinalContents.setTargetId(list.get(position).getId());
                                    Log.i("MyCL", "ID：" + list.get(position).getId());
                                    Intent intent = new Intent(getContext(), MessageCommentActivity.class);
                                    intent.putExtra("headPortrait", list.get(position).getCreateBy().getPhoto());
                                    intent.putExtra("title", list.get(position).getCreateBy().getName());
                                    intent.putExtra("time", list.get(position).getCreateDate());
                                    intent.putExtra("message", list.get(position).getContent());
                                    intent.putExtra("img", list.get(position).getImgUrl());
                                    intent.putExtra("isLike", list.get(position).getIsLike());
                                    startActivity(intent);
                                }
                            });

                            //    TODO 一键复制
                            recyclerAdapter.setFuZhi(new Dynamic2Adapter.FuZhi() {
                                @Override
                                public void FuZhi(int position) {
                                    lists = new ArrayList<>();
                                    //        TODO 文本复制
                                    ClipboardManager clip = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                                    clip.setText(list.get(position).getContent() + "");
                                    //        String imagename = list.get(position).getContent();
                                    //        TODO 图片保存到本地

                                    Log.i("打印图片", "图片：" + list.get(position).getImgUrl());
                                    if (list.get(position).getImgUrl().equals("")) {

                                    } else {
                                        String UrlImage = list.get(position).getImgUrl();
                                        a = list.get(position).getImgUrl().split("[|]");
                                        for (int i = 0; i < a.length; i++) {
                                            Log.i("分割图片", "图片：" + a[i]);
                                            final int finalI = i;
                                            new Thread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    url = a[finalI];
                                                    imgURl = FinalContents.getImageUrl() + url;
                                                    Log.i("分割图片", "图片151：" + imgURl);
                                                    mHandler.obtainMessage(SAVE_BEGIN).sendToTarget();
                                                    Bitmap bp = returnBitMap(imgURl);
                                                    Log.i("MyCL", "bp：" + bp);
                                                    saveImageToPhotos(getContext(), bp);
                                                }
                                            }).start();
                                        }
                                    //        TODO 图片保存到本地
                                    }
                                    ToastUtil.showLongToast(getContext(),"复制成功");
                                }
                            });

                            //  TODO 项目跳转
                            recyclerAdapter.setXiangMu(new Dynamic2Adapter.XiangMu() {
                                @Override
                                public void xm(int position) {
                                    FinalContents.setProjectID(list.get(position).getProject().getId());
                                    Intent intent = new Intent(getContext(), ProjectDetails.class);
                                    startActivity(intent);
                                }
                            });

                            //  TODO 图片查看
                            recyclerAdapter.setTuPian(new Dynamic2Adapter.TuPian() {
                                @Override
                                public void tp(int position) {
                                    Intent intent = new Intent(getContext(), BigPhotoActivity.class);
                                    intent.putExtra("index", position);
                                    intent.putExtra("bigPhotoimg", list.get(position).getImg());
                                    startActivity(intent);
                                }
                            });
                            recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
                                //用来标记是否正在向最后一个滑动
                                boolean isSlidingToLast = false;

                                @Override
                                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                                    super.onScrollStateChanged(recyclerView, newState);
                                    LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                                    // 当不滚动时
                                    if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                                        //获取最后一个完全显示的ItemPosition
                                        int lastVisibleItem = manager.findLastCompletelyVisibleItemPosition();
                                        int totalItemCount = manager.getItemCount();

                                        // 判断是否滚动到底部，并且是向右滚动
                                        if (lastVisibleItem == (totalItemCount - 1) && isSlidingToLast) {
                                            //加载更多功能的代码
                                            textView.setVisibility(View.VISIBLE);
                                        }
                                    } else {
                                        textView.setVisibility(View.GONE);
                                    }
                                }

                                @Override
                                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                                    super.onScrolled(recyclerView, dx, dy);
                                    //dx用来判断横向滑动方向，dy用来判断纵向滑动方向
                                    if (dx > 0) {
                                        //大于0表示正在向右滚动
                                        isSlidingToLast = true;
                                    } else {
                                        //小于等于0表示停止或向左滚动
                                        isSlidingToLast = false;
                                    }
                                }
                            });
                            recyclerView.setAdapter(recyclerAdapter);
                            recyclerAdapter.notifyDataSetChanged();
                        } else {
                            recyclerView.setVisibility(View.GONE);
                            all_no_information.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        recyclerView.setVisibility(View.GONE);
                        all_no_information.setVisibility(View.VISIBLE);
                        Log.i("楼盘动态列表数据获取错误", "错误" + e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!NewlyIncreased.isTest()) {
            initView();
            NewlyIncreased.setTest(true);
        }
    }

    /**
     * 保存二维码到本地相册
     */
    private void saveImageToPhotos(Context context, Bitmap bmp) {
        // 首先保存图片

        Log.i("分割图片", "图片151：" + bmp);

        File appDir = new File(Environment.getExternalStorageDirectory(), "Boohee");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = System.currentTimeMillis() + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 其次把文件插入到系统图库
//        try {
//            MediaStore.Images.Media.insertImage(context.getContentResolver(),
//                    file.getAbsolutePath(), fileName, null);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//            mHandler.obtainMessage(SAVE_FAILURE).sendToTarget();
//            return;
//        }
        // 最后通知图库更新
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri uri = Uri.fromFile(file);
        intent.setData(uri);
        context.sendBroadcast(intent);
        mHandler.obtainMessage(SAVE_SUCCESS).sendToTarget();
    }

    /**
     * 将URL转化成bitmap形式
     *
     * @param url
     * @return bitmap type
     */
    public final static Bitmap returnBitMap(String url) {
        URL myFileUrl;
        Bitmap bitmap = null;
        Log.i("MyCL", "1");
        try {
            myFileUrl = new URL(url);
            Log.i("MyCL", "2");
            HttpURLConnection conn;
            Log.i("MyCL", "3");
            conn = (HttpURLConnection) myFileUrl.openConnection();
            Log.i("MyCL", "4");
            conn.setDoInput(true);
            Log.i("MyCL", "5");
            conn.connect();
            Log.i("MyCL", "6");
            InputStream is = conn.getInputStream();
            Log.i("MyCL", "7");
            bitmap = BitmapFactory.decodeStream(is);
            Log.i("MyCL", "8");
        } catch (MalformedURLException e) {
            Log.i("MyCL", "9");
            e.printStackTrace();
        } catch (IOException e) {
            Log.i("MyCL", "10");
            e.printStackTrace();
        }
        Log.i("MyCL", "11");
        return bitmap;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this))//加上判断
            EventBus.getDefault().unregister(this);
    }

    @Override
    public void onStart() {
        super.onStart();

        if(!EventBus.getDefault().isRegistered(this)){//加上判断
            EventBus.getDefault().register(this);
        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN, priority = 100, sticky = false) //在ui线程执行，优先级为100
    public void onEvent(String nam) {
        if(nam.equals("切换")){
            Log.i("刷新","切换");
            initView();
        }
    }

}

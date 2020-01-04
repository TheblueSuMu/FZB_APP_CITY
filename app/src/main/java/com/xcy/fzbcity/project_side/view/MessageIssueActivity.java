package com.xcy.fzbcity.project_side.view;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.xcy.fzbcity.all.utils.ToastUtil;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.adapter.GridViewAdapter;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.api.NewlyIncreased;
import com.xcy.fzbcity.all.modle.AddPhotoBean;
import com.xcy.fzbcity.all.modle.MessageIssueBean;
import com.xcy.fzbcity.all.persente.StatusBar;
import com.xcy.fzbcity.all.service.MyService;
import com.xcy.fzbcity.all.utils.CommonUtil;
import com.xcy.fzbcity.all.utils.ToastUtil;
import com.xcy.fzbcity.all.view.AllActivity;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//TODO 消息发布
public class MessageIssueActivity extends AllActivity {


    TextView message_issue_qx;
    Button message_issue_fb;
    EditText message_issue_message;
    GridView message_issue_gv;

    RelativeLayout message_issue_rl1;
    RelativeLayout message_issue_rl2;

    TextView message_issue_tv1;
    TextView message_issue_tv2;

    private List<Object> mDatas;
    private GridViewAdapter adapter;
    String myImage = "";
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE"};

    private Bitmap bm;
    int i = 0;
    private final String IMAGE_TYPE = "image/*";

    private final int IMAGE_CODE = 0;
    StringBuffer stringBuffer = new StringBuffer();
    private String projectID = "";
    private String title;
    private String imgUrl;
    private String message;

    int isPhoto = 0;
    private File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_side_activity_message_issue);
        init_No_Network();
    }

    private void init_No_Network() {
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

        message_issue_qx = findViewById(R.id.message_issue_qx);
        message_issue_fb = findViewById(R.id.message_issue_fb);
        message_issue_message = findViewById(R.id.message_issue_message);
        message_issue_gv = findViewById(R.id.message_issue_gv);
        message_issue_rl1 = findViewById(R.id.message_issue_rl1);
        message_issue_rl2 = findViewById(R.id.message_issue_rl2);
        message_issue_tv1 = findViewById(R.id.message_issue_tv1);
        message_issue_tv2 = findViewById(R.id.message_issue_tv2);

        initData();

        message_issue_qx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FinalContents.setProjectID("");
                FinalContents.setProjectName("");
                finish();
            }
        });

        message_issue_fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (message_issue_message.getText().toString().equals("") && stringBuffer.toString().equals("")) {
                    ToastUtil.showToast(MessageIssueActivity.this, "请输入要发布的内容或图片");
                } else {
                    if (projectID.equals("")) {
                        ToastUtil.showToast(MessageIssueActivity.this, "请选择要发布的项目");
                    } else {
                        initFB();
                    }
                }
            }
        });

        message_issue_rl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initRL1();
            }
        });

        message_issue_rl2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initProject();
            }
        });
    }

    //TODO 项目选择
    private void initProject() {
        Intent intent = new Intent(MessageIssueActivity.this, MyProjectActivity.class);
        FinalContents.setMessageIssueNum("1");
        startActivity(intent);
    }

    //TODO 发布操作
    private void initFB() {
        message = message_issue_message.getText().toString();
        imgUrl = stringBuffer.toString();
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(FinalContents.getBaseUrl());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit build = builder.build();
        MyService fzbInterface = build.create(MyService.class);
        Observable<MessageIssueBean> userMessage = fzbInterface.getAddHousesDynamic(message_issue_tv2.getText().toString(), message, imgUrl, FinalContents.getUserID(), projectID);
        userMessage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MessageIssueBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @SuppressLint("WrongConstant")
                    @Override
                    public void onNext(MessageIssueBean messageIssueBean) {
                        if (messageIssueBean.getMsg().equals("成功")) {
                            ToastUtil.showToast(MessageIssueActivity.this, messageIssueBean.getMsg());
                            finish();
                            FinalContents.setProjectID("");
                            FinalContents.setProjectName("");
                            NewlyIncreased.setTest(false);
                        } else {
                            ToastUtil.showToast(MessageIssueActivity.this, messageIssueBean.getMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("发布楼盘动态数据获取错误", "错误" + e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    //TODO 楼盘动态/开盘通知/变价通知
    private void initRL1() {
        final List<String> list = new ArrayList<>();
        list.add("楼盘动态");
        list.add("变价通知");
        list.add("开盘通知");

//      监听选中
        OptionsPickerView pvOptions = new OptionsPickerBuilder(MessageIssueActivity.this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //               返回的分别是三个级别的选中位置
                //              展示选中数据
                message_issue_tv1.setText(list.get(options1));
            }
        })
                .setSelectOptions(0)//设置选择第一个
                .setOutSideCancelable(false)//点击背的地方不消失
                .build();//创建
        //      把数据绑定到控件上面
        pvOptions.setPicker(list);
        //      展示
        pvOptions.show();
    }

    //    TODO 照片选择操作
    private void initData() {

        View inflate = View.inflate(MessageIssueActivity.this, R.layout.item_flowlayout, null);
        mDatas = new ArrayList<>();
        adapter = new GridViewAdapter(MessageIssueActivity.this, mDatas);
        message_issue_gv.setAdapter(adapter);

        message_issue_gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                if (position == parent.getChildCount() - 1) {

                    if (mDatas.size() == 9) {
                        ToastUtil.showToast(MessageIssueActivity.this, "图片最多九张");
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(MessageIssueActivity.this);
                        builder.setTitle("请选择图片来源");
                        builder.setItems(new String[]{"相机", "相册"}, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (i == 0) {

                                    try {
                                        //检测是否有写的权限
                                        int permission = ActivityCompat.checkSelfPermission(MessageIssueActivity.this,
                                                "android.permission.WRITE_EXTERNAL_STORAGE");
                                        if (permission != PackageManager.PERMISSION_GRANTED) {
                                            // 没有写的权限，去申请写的权限，
                                            ActivityCompat.requestPermissions(MessageIssueActivity.this, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    String SDState = Environment.getExternalStorageState();
                                    if (SDState.equals(Environment.MEDIA_MOUNTED)) {
                                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);// "android.media.action.IMAGE_CAPTURE"
                                        /***
                                         * 需要说明一下，以下操作使用照相机拍照，拍照后的图片会存放在相册中的 这里使用的这种方式有一个好处就是获取的图片是拍照后的原图
                                         * 如果不实用ContentValues存放照片路径的话，拍照后获取的图片为缩略图不清晰
                                         */
                                        ContentValues values = new ContentValues();
                                        Uri photoUri = MessageIssueActivity.this.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);


//TODO Uri图片转换成file类型的 start
                                        file = uri2File(photoUri);

                                        isPhoto = 1;

//TODO Uri图片转换成file类型的 end
                                        intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, photoUri);
                                        startActivityForResult(intent, 1);
                                    } else {

                                    }
                                } else if (i == 1) {
                                    Intent getAlbum = new Intent(Intent.ACTION_PICK);
                                    getAlbum.setType(IMAGE_TYPE);
                                    startActivityForResult(getAlbum, IMAGE_CODE);
                                }
                            }
                        });
                        builder.setPositiveButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                        AlertDialog show = builder.show();
                        show.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#334485"));
                        adapter = new GridViewAdapter(MessageIssueActivity.this, mDatas);
                        message_issue_gv.setAdapter(adapter);

                    }

                }
            }
        });

    }

    //TODO  获取相册图片地址
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        i++;
        if (resultCode != RESULT_OK) {        //此处的 RESULT_OK 是系统自定义得一个常量
            return;
        }
        bm = null;
        //外界的程序访问ContentProvider所提供数据 可以通过ContentResolver接口

        ContentResolver resolver = getContentResolver();


        //此处的用于判断接收的Activity是不是你想要的那个

        if (requestCode == IMAGE_CODE) {

            try {

                Uri originalUri = data.getData();        //获得图片的uri
                bm = MediaStore.Images.Media.getBitmap(resolver, originalUri);        //显得到bitmap图片
//TODO bitmap图片转换成file类型的 start

                long l = System.currentTimeMillis();

                final File san = saveFile(bm, "" + l + ".png");
                new Thread() {
                    @Override
                    public void run() {
                        Retrofit.Builder builder = new Retrofit.Builder();
                        builder.baseUrl(FinalContents.getBaseUrl());
                        builder.addConverterFactory(GsonConverterFactory.create());
                        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
                        Retrofit build = builder.build();
                        MyService fzbInterface = build.create(MyService.class);

                        RequestBody requestBody = RequestBody.create(MediaType.parse("image/png"), san);

                        MultipartBody.Part part = MultipartBody.Part.createFormData("file", san.getName(), requestBody);

                        Observable<AddPhotoBean> addPhoto = fzbInterface.getAddPhoto(FinalContents.getUserID(), "messageissue", part);
                        addPhoto.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Observer<AddPhotoBean>() {
                                    @Override
                                    public void onSubscribe(Disposable d) {

                                    }

                                    @Override
                                    public void onNext(AddPhotoBean addPhotoBean) {
                                        if (stringBuffer.length() == 0) {
                                            stringBuffer.append(addPhotoBean.getData().getUrl());
                                        } else {
                                            stringBuffer.append("|" + addPhotoBean.getData().getUrl());
                                        }
                                        myImage = addPhotoBean.getData().getUrl();
                                        mDatas.add(myImage);
                                        adapter.notifyDataSetChanged();
                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        Log.i("MyCL", "经济圈发布图片上传错误信息：" + e.getMessage());
                                    }

                                    @Override
                                    public void onComplete() {

                                    }
                                });
                    }
                }.start();
////TODO bitmap图片转换成file类型的 end
                String[] proj = {MediaStore.Images.Media.DATA};


                //好像是android多媒体数据库的封装接口，具体的看Android文档

                Cursor cursor = managedQuery(originalUri, proj, null, null, null);

                //按我个人理解 这个是获得用户选择的图片的索引值

                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

                //将光标移至开头 ，这个很重要，不小心很容易引起越界

                cursor.moveToFirst();

                //最后根据索引值获取图片路径

                String path = cursor.getString(column_index);
            } catch (IOException e) {
                Log.e("MyCL", e.toString());
            }

        }
    }

    //TODO 将Uri转换成File
    private File uri2File(Uri uri) {
        String img_path;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor actualimagecursor = managedQuery(uri, proj, null,
                null, null);
        if (actualimagecursor == null) {
            img_path = uri.getPath();
        } else {
            int actual_image_column_index = actualimagecursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            actualimagecursor.moveToFirst();
            img_path = actualimagecursor
                    .getString(actual_image_column_index);
        }
        File file = new File(img_path);
        return file;
    }

    //TODO 将bitmap转换成File
    public File saveFile(Bitmap bm, String fileName) throws IOException {//将Bitmap类型的图片转化成file类型，便于上传到服务器
        String path = Environment.getExternalStorageDirectory() + "/Ask";
        File dirFile = new File(path);
        if (!dirFile.exists()) {
            dirFile.mkdir();
        }
        File myCaptureFile = new File(path + fileName);
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
        bm.compress(Bitmap.CompressFormat.JPEG, 80, bos);
        bos.flush();
        bos.close();
        return myCaptureFile;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (FinalContents.getMessageIssueNum().equals("1")) {
            projectID = FinalContents.getProjectID();
            title = FinalContents.getProjectName();
            message_issue_tv2.setText(title);
            FinalContents.setMessageIssueNum("0");
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        if (isPhoto == 1) {
            new Thread() {
                @Override
                public void run() {

                    Retrofit.Builder builder = new Retrofit.Builder();
                    builder.baseUrl(FinalContents.getBaseUrl());
                    builder.addConverterFactory(GsonConverterFactory.create());
                    builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
                    Retrofit build = builder.build();
                    MyService fzbInterface = build.create(MyService.class);

                    RequestBody requestBody = RequestBody.create(MediaType.parse("image/png"), file);

                    MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), requestBody);

                    Observable<AddPhotoBean> addPhoto = fzbInterface.getAddPhoto(FinalContents.getUserID(), "messageissue", part);
                    addPhoto.subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Observer<AddPhotoBean>() {
                                @Override
                                public void onSubscribe(Disposable d) {

                                }

                                @Override
                                public void onNext(AddPhotoBean addPhotoBean) {
                                    if (stringBuffer.length() == 0) {
                                        stringBuffer.append(addPhotoBean.getData().getUrl());
                                    } else {
                                        stringBuffer.append("|" + addPhotoBean.getData().getUrl());
                                    }
                                    myImage = addPhotoBean.getData().getUrl();
                                    mDatas.add(myImage);

                                    adapter.notifyDataSetChanged();
                                    isPhoto = 0;
                                }

                                @Override
                                public void onError(Throwable e) {
                                    Log.i("MyCL", "经济圈发布图片上传错误信息：" + e.getMessage());
                                }

                                @Override
                                public void onComplete() {

                                }
                            });
                }
            }.start();
        }

    }
}

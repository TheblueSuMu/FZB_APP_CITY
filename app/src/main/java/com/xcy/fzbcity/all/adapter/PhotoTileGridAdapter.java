package com.xcy.fzbcity.all.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import com.xcy.fzbcity.all.utils.ToastUtil;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.modle.PhotoBean;
import com.xcy.fzbcity.all.view.BannerPhotoActivity;

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

public class PhotoTileGridAdapter extends RecyclerView.Adapter<PhotoTileGridAdapter.ViewHolder> {

    private List<PhotoBean.DataBean.DataListBean> list;
    private Context context;
    private List<String> array = new ArrayList<>();
    private String url = "";
    private int index = -1;
    private int num = -1;
    private String imgURl;//图片的URL地址
    private static final int SAVE_SUCCESS = 0;//保存图片成功
    private static final int SAVE_FAILURE = 1;//保存图片失败
    private static final int SAVE_BEGIN = 2;//开始保存图片
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SAVE_BEGIN:
                    ToastUtil.showToast(context, "开始保存图片...");
//                    mSaveBtn.setClickable(false);
                    break;
                case SAVE_SUCCESS:
                    ToastUtil.showToast(context, "图片保存成功,请到相册查找...");
                    num = 1;
//                    mSaveBtn.setClickable(true);
                    break;
                case SAVE_FAILURE:
                    ToastUtil.showToast(context, "图片保存失败,请稍后再试...");
//                    mSaveBtn.setClickable(true);
                    break;
            }
        }
    };

    public void setArray(List<String> array) {
        this.array = array;
    }

    public PhotoTileGridAdapter(List<PhotoBean.DataBean.DataListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.photo_tile_item_grid,
                viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        context = viewGroup.getContext();
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        Glide.with(context).load(FinalContents.getImageUrl() + list.get(i).getImgPath()).into(viewHolder.imageAvatar);
        viewHolder.imageAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                url = FinalContents.getImageUrl() + list.get(i).getImgPath();
                for (int posotion = 0; posotion < array.size(); posotion++) {
                    if (array.get(posotion).equals(url)) {
                        index = posotion;
                        Log.i("下标", "index￥：" + index);
                        Log.i("下标", "图片地址：" + url);
                        Log.i("下标", "列表图片地址：" + array.get(posotion));
                    }
                }
                Intent intent = new Intent(context, BannerPhotoActivity.class);
                intent.putExtra("index", index);
                context.startActivity(intent);
            }
        });
        viewHolder.imageAvatar.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                View myView = LayoutInflater.from(context).inflate(R.layout.bottom_popwindow, null);
                builder.setView(myView);

                Button btn_take_photo = myView.findViewById(R.id.btn_take_photo);
                Button btn_take_photo_S = myView.findViewById(R.id.btn_take_photo_S);

                final AlertDialog dialog = builder.show();
                dialog.getWindow().setBackgroundDrawableResource(R.drawable.report_shape_s);
                dialog.setCanceledOnTouchOutside(true);
                Window window = dialog.getWindow();
                window.setGravity(Gravity.BOTTOM);

                btn_take_photo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        url = FinalContents.getImageUrl() + list.get(i).getImgPath();
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                imgURl = url;
                                Log.i("分割图片", "图片151：" + imgURl);
                                mHandler.obtainMessage(SAVE_BEGIN).sendToTarget();
                                Bitmap bp = returnBitMap(imgURl);
                                Log.i("MyCL", "bp：" + bp);
                                saveImageToPhotos(context, bp);
                            }
                        }).start();
                        dialog.dismiss();
                    }
                });
                btn_take_photo_S.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageAvatar;

        public ViewHolder(View itemView) {
            super(itemView);
            //注意这里可能需要import com.example.lenovo.myrecyclerview.R; 才能使用R.id
            imageAvatar = (ImageView) itemView.findViewById(R.id.photo_tile_item_grid_pic);
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

}

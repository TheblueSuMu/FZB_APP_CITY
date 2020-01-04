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

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.utils.ToastUtil;
import com.xcy.fzbcity.all.view.BigPhotoActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {
    private List<String> list;
    private Context context;
    private String ImageUrl = "";

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

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public ImageAdapter(List<String> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image_adapter, parent, false);
        context = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        Glide.with(context).load(FinalContents.getImageUrl() + list.get(position)).into(holder.item_image_adapter_img);
        if (ImageUrl.equals("")) {
        } else {
            holder.item_image_adapter_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, BigPhotoActivity.class);
                    intent.putExtra("index", position);
                    intent.putExtra("bigPhotoimg", ImageUrl);
                    context.startActivity(intent);
                }
            });
            holder.item_image_adapter_img.setOnLongClickListener(new View.OnLongClickListener() {
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
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    imgURl = FinalContents.getImageUrl() + list.get(position);
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

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView item_image_adapter_img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item_image_adapter_img = itemView.findViewById(R.id.item_image_adapter_img);
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

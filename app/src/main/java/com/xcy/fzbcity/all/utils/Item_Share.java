package com.xcy.fzb.all.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.view.AboutFZBActivity;

import cn.sharesdk.wechat.favorite.WechatFavorite;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;
import cn.sharesdk.wechat.utils.WechatClientNotExistException;

public class Item_Share {

    public static void initDaown(Context context,String title, String titleUrl, String text, String imagePath, String url) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View myView = LayoutInflater.from(context).inflate(R.layout.item_share, null);
        builder.setView(myView);

        RelativeLayout item_share_1 = myView.findViewById(R.id.item_share_1);
        RelativeLayout item_share_2 = myView.findViewById(R.id.item_share_2);
        RelativeLayout item_share_3 = myView.findViewById(R.id.item_share_3);
        Button item_share_delete = myView.findViewById(R.id.item_share_delete);

        final AlertDialog dialog = builder.show();
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.report_shape_s);
        dialog.setCanceledOnTouchOutside(true);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);

        item_share_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(context, "微信好友", Toast.LENGTH_SHORT).show();
                FinalContents.showShare(Wechat.NAME,title, titleUrl, text, imagePath, url, context);

                dialog.dismiss();
            }
        });
        item_share_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(context, "朋友圈", Toast.LENGTH_SHORT).show();
                FinalContents.showShare(WechatMoments.NAME,title, titleUrl, text, imagePath, url, context);

                dialog.dismiss();
            }
        });
        item_share_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(context, "微信收藏", Toast.LENGTH_SHORT).show();
                FinalContents.showShare(WechatFavorite.NAME,title, titleUrl, text, imagePath, url, context);
                dialog.dismiss();
            }
        });
        item_share_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(context, "取消", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

    }

}

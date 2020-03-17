package com.netease.nim.uikit.business.session.viewholder;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;
import com.netease.nim.uikit.R;
import com.netease.nim.uikit.common.ui.recyclerview.adapter.BaseMultiItemFetchLoadAdapter;
import com.netease.nim.uikit.common.util.sys.ScreenUtil;
import com.netease.nim.uikit.impl.NimUIKitImpl;
import com.netease.nimlib.sdk.msg.attachment.LocationAttachment;

/**
 * Created by zhoujianghua on 2015/8/7.
 */
public class MsgViewHolderLocation extends MsgViewHolderBase {

    public MsgViewHolderLocation(BaseMultiItemFetchLoadAdapter adapter) {
        super(adapter);
    }


    public MapView message_item_location_map;
    public AMap aMap = null;
    private UiSettings mUiSettings;//定义一个UiSettings对象
    private MarkerOptions markerOption;

    public TextView message_item_location_address_one;
    public TextView message_item_location_address_two;

    public RelativeLayout message_item_location_rl;
    LinearLayout message_item_location_ll;

    @Override
    public int getContentResId() {
        return R.layout.nim_message_item_location;
    }

    @Override
    public void inflateContentView() {
//        mapView = (MsgThumbImageView) view.findViewById(R.id.message_item_location_image);
//        addressText = (TextView) view.findViewById(R.id.message_item_location_address);
        message_item_location_map = (MapView) view.findViewById(R.id.message_item_location_map);
        message_item_location_address_one = (TextView) view.findViewById(R.id.message_item_location_address_one);
        message_item_location_address_two = (TextView) view.findViewById(R.id.message_item_location_address_two);
        message_item_location_rl = (RelativeLayout) view.findViewById(R.id.message_item_location_rl);
        message_item_location_ll = (LinearLayout) view.findViewById(R.id.message_item_location_ll);
    }

    @Override
    public void bindContentView() {
        final LocationAttachment location = (LocationAttachment) message.getAttachment();
//        addressText.setText(location.getAddress());

        message_item_location_address_one.setText(new StringBuffer(location.getAddress()).substring(location.getAddress().lastIndexOf("|") + 1));
        message_item_location_address_two.setText(new StringBuffer(location.getAddress()).substring(0, location.getAddress().lastIndexOf("|")));

        message_item_location_map.onCreate(Bundle.EMPTY);
//初始化地图控制器对象

        if (aMap == null) {
            aMap = message_item_location_map.getMap();
        }

        mUiSettings = aMap.getUiSettings();//实例化UiSettings类对象
        mUiSettings.setZoomControlsEnabled(false);
        mUiSettings.setCompassEnabled(false);
        mUiSettings.setMyLocationButtonEnabled(false);
        mUiSettings.setScaleControlsEnabled(false);

        mUiSettings.setZoomGesturesEnabled(false);
        mUiSettings.setScrollGesturesEnabled(false);
        mUiSettings.setRotateGesturesEnabled(false);
        mUiSettings.setTiltGesturesEnabled(false);
        mUiSettings.setGestureScaleByMapCenter(false);

        CameraUpdate mCameraUpdate = CameraUpdateFactory.newCameraPosition(new CameraPosition(new LatLng(location.getLatitude(),location.getLongitude()), 15, 0, 0));
        aMap.moveCamera(mCameraUpdate);
//
        markerOption = new MarkerOptions();
        markerOption.position(new LatLng(location.getLatitude(),location.getLongitude()));
//
        markerOption.draggable(false);//设置Marker可拖动
//
        markerOption.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                .decodeResource(context.getResources(), R.drawable.pin_red2x)));
//        // 将Marker设置为贴地显示，可以双指下拉地图查看效果
        markerOption.setFlat(true);//设置marker平贴地图效果
        aMap.addMarker(markerOption);

        message_item_location_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (NimUIKitImpl.getLocationProvider() != null) {
                    LocationAttachment location = (LocationAttachment) message.getAttachment();
                    NimUIKitImpl.getLocationProvider().openMap(context, location.getLongitude(), location.getLatitude(), location.getAddress());
                }
            }
        });

        aMap.setOnMapClickListener(new AMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                if (NimUIKitImpl.getLocationProvider() != null) {
                    LocationAttachment location = (LocationAttachment) message.getAttachment();
                    NimUIKitImpl.getLocationProvider().openMap(context, location.getLongitude(), location.getLatitude(), location.getAddress());
                }
            }
        });

    }

    @Override
    public void onItemClick() {
        Log.i("即时通讯地图","onItemClick");
        if (NimUIKitImpl.getLocationProvider() != null) {
            LocationAttachment location = (LocationAttachment) message.getAttachment();
            NimUIKitImpl.getLocationProvider().openMap(context, location.getLongitude(), location.getLatitude(), location.getAddress());
        }
    }

    public static int getLocationDefEdge() {
        return (int) (0.5 * ScreenUtil.screenWidth);
    }

    @Override
    protected int rightBackground() {
        return 0;
    }
}

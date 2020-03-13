package com.xcy.fzbcity.all.utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.maps.Projection;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.xcy.fzbcity.R;

import java.util.ArrayList;

public class MyMarkerCluster implements GeocodeSearch.OnGeocodeSearchListener {

    private Activity activity;
    private MarkerOptions options;
    private ArrayList<MarkerOptions> includeMarkers;
    private LatLngBounds bounds;// 创建区域
    private String district;
    private GeocodeSearch geocoderSearch;
    private RegeocodeQuery query;
    private double lat;
    private double lng;
    private int size;
    private View view;
    private int carNum1;
    private int resourceId1;
    private int ifCount = 0;
    private TextView carNumTextView;
    private RelativeLayout myCarLayout;

    /**
     * @param activity
     * @param firstMarkers
     * @param projection
     * @param gridSize     区域大小参数
     */
    public MyMarkerCluster(Activity activity, MarkerOptions firstMarkers,
                           Projection projection, int gridSize) {
        // TODO Auto-generated constructor stub
        // this.options = firstMarkers;
        options = new MarkerOptions();
        this.activity = activity;
        Point point = projection.toScreenLocation(firstMarkers.getPosition());
        Point southwestPoint = new Point(point.x - gridSize, point.y + gridSize);
        Point northeastPoint = new Point(point.x + gridSize, point.y - gridSize);
        bounds = new LatLngBounds(
                projection.fromScreenLocation(southwestPoint),
                projection.fromScreenLocation(northeastPoint));
        options.anchor(0.5f, 0.5f)
                .position(firstMarkers.getPosition())
                .icon(firstMarkers.getIcon())
                .snippet(firstMarkers.getSnippet());
        includeMarkers = new ArrayList<MarkerOptions>();
        includeMarkers.add(firstMarkers);
    }

    /**
     * 添加marker
     */
    public void addMarker(MarkerOptions markerOptions) {
        includeMarkers.add(markerOptions);// 添加到列表中
    }

    /**
     * 设置聚合点的中心位置以及图标
     */
    public void setpositionAndIcon() {
        size = includeMarkers.size();

        if (size == 1) {
            return;
        }
        lat = 0.0;
        lng = 0.0;

        String snippet = "";
        for (MarkerOptions op : includeMarkers) {
            lat += op.getPosition().latitude;
            lng += op.getPosition().longitude;
            snippet += op.getTitle() + "\n";
        }


        options.position(new LatLng(lat / size, lng / size));// 设置中心位置为聚集点的平均位置
        options.title("聚合点");
        options.snippet(snippet);

        int iconType = size / 10;

//        geocoderSearch = new GeocodeSearch(activity);
//        geocoderSearch.setOnGeocodeSearchListener(this);
//        query = new RegeocodeQuery(new LatLonPoint(lat / size, lng / size), 200, GeocodeSearch.AMAP);
//        geocoderSearch.getFromLocationAsyn(query);

        switch (iconType) {
            case 0:
                options.icon(BitmapDescriptorFactory
                        .fromBitmap(getViewBitmap(getView(size,
                                R.mipmap.blackimg))));
                break;
            case 1:
                options.icon(BitmapDescriptorFactory
                        .fromBitmap(getViewBitmap(getView(size,
                                R.mipmap.blackimg))));
                break;
            case 2:
                options.icon(BitmapDescriptorFactory
                        .fromBitmap(getViewBitmap(getView(size,
                                R.mipmap.blackimg))));
                break;
            case 3:
                options.icon(BitmapDescriptorFactory
                        .fromBitmap(getViewBitmap(getView(size,
                                R.mipmap.blackimg))));
                break;
            case 4:
                options.icon(BitmapDescriptorFactory
                        .fromBitmap(getViewBitmap(getView(size,
                                R.mipmap.blackimg))));
                break;
            default:
                options.icon(BitmapDescriptorFactory
                        .fromBitmap(getViewBitmap(getView(size,
                                R.mipmap.blackimg))));
                break;
        }
    }

    public View getView(int carNum, int resourceId) {

        carNum1 = carNum;
        resourceId1 = resourceId;

        Log.i("解析result获取地址描述信息", "getView");

        view = activity.getLayoutInflater().inflate(
                R.layout.my_car_cluster_view, null);
        carNumTextView = (TextView) view.findViewById(R.id.my_car_num);
        myCarLayout = (RelativeLayout) view
                .findViewById(R.id.my_car_bg);

        Log.i("解析result获取地址描述信息", "district:" + district);

        myCarLayout.setBackgroundResource(resourceId1);
        carNumTextView.setText(String.valueOf(carNum1));

        Log.i("解析result获取地址描述信息", "view:" + view);

        return view;
    }

    /**
     * 把一个view转化成bitmap对象
     */
    public static Bitmap getViewBitmap(View view) {
        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();
        return bitmap;
    }

    @Override
    public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {
        //解析result获取地址描述信息
        Log.i("解析result获取地址描述信息", "getFormatAddress:" + regeocodeResult.getRegeocodeAddress().getDistrict());
        district = regeocodeResult.getRegeocodeAddress().getDistrict();

//        options.icon(BitmapDescriptorFactory
//                .fromBitmap(getViewBitmap(getView(size,
//                        R.drawable.marker_cluster_60))));

    }

    @Override
    public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {

    }


    public LatLngBounds getBounds() {
        return bounds;
    }

    public MarkerOptions getOptions() {
        return options;
    }

    public void setOptions(MarkerOptions options) {
        this.options = options;
    }

}

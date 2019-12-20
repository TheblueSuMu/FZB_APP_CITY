/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */

package com.xcy.fzbcity.project_attache.adapter;


import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.model.LatLng;

/**
 * ClusterItem represents a marker on the map.
 * ClusterItem表示地图上的一个标记。
 */
public interface ClusterItem {

    /**
     * The position of this marker. This must always return the same value.
     * 这个标记的位置。它必须总是返回相同的值。
     */
    LatLng getPosition();

    BitmapDescriptor getBitmapDescriptor();
//
//    String getString();
}
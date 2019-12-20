/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */

package com.xcy.fzbcity.project_attache.adapter;


import com.baidu.mapapi.model.LatLng;

import java.util.Collection;

/**
 * A collection of ClusterItems that are nearby each other.
 * 彼此相邻的集群项集合。
 */
public interface Cluster<T extends ClusterItem> {
    LatLng getPosition();

    Collection<T> getItems();

    int getSize();
}
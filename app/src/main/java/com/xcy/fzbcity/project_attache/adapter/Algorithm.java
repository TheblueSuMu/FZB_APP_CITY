/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */

package com.xcy.fzbcity.project_attache.adapter;


import java.util.Collection;
import java.util.Set;

/**
 * Logic for computing clusters
 * 计算集群的逻辑
 */
public interface Algorithm<T extends ClusterItem> {
    void addItem(T item);

    void addItems(Collection<T> items);

    void clearItems();

    void removeItem(T item);

    Set<? extends Cluster<T>> getClusters(double zoom);

    Collection<T> getItems();
}
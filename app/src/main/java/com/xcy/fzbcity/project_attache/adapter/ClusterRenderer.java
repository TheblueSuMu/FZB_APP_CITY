/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */

package com.xcy.fzbcity.project_attache.adapter;


import java.util.Set;

/**
 * Renders clusters.
 * 呈现集群
 */
public interface ClusterRenderer<T extends ClusterItem> {

    /**
     * Called when the view needs to be updated because new clusters need to be displayed.
     * @param clusters the clusters to be displayed.
     * *在需要更新视图时调用，因为需要显示新的集群。
     * * @param集群将显示的集群。
     */
    void onClustersChanged(Set<? extends Cluster<T>> clusters);

    void setOnClusterClickListener(ClusterManager.OnClusterClickListener<T> listener);

    void setOnClusterInfoWindowClickListener(ClusterManager.OnClusterInfoWindowClickListener<T> listener);

    void setOnClusterItemClickListener(ClusterManager.OnClusterItemClickListener<T> listener);

    void setOnClusterItemInfoWindowClickListener(ClusterManager.OnClusterItemInfoWindowClickListener<T> listener);

    /**
     * Called when the view is added.
     * 在添加视图时调用。
     */
    void onAdd();

    /**
     * Called when the view is removed.
     * 在视图被移除时调用。
     */
    void onRemove();
}
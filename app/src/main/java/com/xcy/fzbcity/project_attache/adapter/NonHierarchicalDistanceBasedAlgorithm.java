/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */

package com.xcy.fzbcity.project_attache.adapter;

import com.baidu.mapapi.model.LatLng;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * A simple clustering algorithm with O(nlog n) performance. Resulting clusters are not
 * hierarchical.
 * <p/>
 * High level algorithm:<br>
 * 1. Iterate over items in the order they were added (candidate clusters).<br>
 * 2. Create a cluster with the center of the item. <br>
 * 3. Add all items that are within a certain distance to the cluster. <br>
 * 4. Move any items out of an existing cluster if they are closer to another cluster. <br>
 * 5. Remove those items from the list of candidate clusters.
 * <p/>
 * Clusters have the center of the first element (not the centroid of the items within it).
 *
 * *具有O(nlog n)性能的简单聚类算法。得到的集群不是
 * *等级。
 * * < p / >
 * *高级算法:<br>
 * * 1。按照条目被添加的顺序进行迭代(候选集群)。<br>
 * * 2。创建一个以项为中心的集群。< br >
 * * 3。添加与集群有一定距离的所有项。< br >
 * * 4。如果项目离另一个集群较近，则将它们移出现有集群。< br >
 * * 5。从候选集群列表中删除这些项。
 * * < p / >
 * *簇的中心是第一个元素(而不是其中元素的质心)。
 */
public class NonHierarchicalDistanceBasedAlgorithm<T extends ClusterItem> implements Algorithm<T> {
    public static final int MAX_DISTANCE_AT_ZOOM = 100; // essentially 100 dp.

    /**
     * Any modifications should be synchronized on mQuadTree.
     *
     * 任何修改都应该在mQuadTree上同步。
     */
    private final Collection<QuadItem<T>> mItems = new ArrayList<QuadItem<T>>();

    /**
     * Any modifications should be synchronized on mQuadTree.
     */
    private final PointQuadTree<QuadItem<T>> mQuadTree = new PointQuadTree<QuadItem<T>>(0, 1, 0, 1);

    private static final SphericalMercatorProjection PROJECTION = new SphericalMercatorProjection(1);

    @Override
    public void addItem(T item) {
        final QuadItem<T> quadItem = new QuadItem<T>(item);
        synchronized (mQuadTree) {
            mItems.add(quadItem);
            mQuadTree.add(quadItem);
        }
    }

    @Override
    public void addItems(Collection<T> items) {
        for (T item : items) {
            addItem(item);
        }
    }

    @Override
    public void clearItems() {
        synchronized (mQuadTree) {
            mItems.clear();
            mQuadTree.clear();
        }
    }

    @Override
    public void removeItem(T item) {
        // TODO: delegate QuadItem#hashCode and QuadItem#equals to its item.
        // TODO:将QuadItem#hashCode委托给它的item, QuadItem#等于它的item。
        throw new UnsupportedOperationException("NonHierarchicalDistanceBasedAlgorithm.remove not implemented");
    }

    /**
     *  cluster算法核心
     * @param zoom map的级别
     * @return
     */
    @Override
    public Set<? extends Cluster<T>> getClusters(double zoom) {
        final int discreteZoom = (int) zoom;

        final double zoomSpecificSpan = MAX_DISTANCE_AT_ZOOM / Math.pow(2, discreteZoom) / 256;

        final Set<QuadItem<T>> visitedCandidates = new HashSet<QuadItem<T>>();
        final Set<Cluster<T>> results = new HashSet<Cluster<T>>();
        final Map<QuadItem<T>, Double> distanceToCluster = new HashMap<QuadItem<T>, Double>();
        final Map<QuadItem<T>, StaticCluster<T>> itemToCluster =
                new HashMap<QuadItem<T>, StaticCluster<T>>();

        synchronized (mQuadTree) {
            for (QuadItem<T> candidate : mItems) {
                if (visitedCandidates.contains(candidate)) {
                    // Candidate is already part of another cluster.
                    // Candidate已经是另一个集群的一部分。
                    continue;
                }

                Bounds searchBounds = createBoundsFromSpan(candidate.getPoint(), zoomSpecificSpan);
                Collection<QuadItem<T>> clusterItems;
                // search 某边界范围内的clusterItems
                clusterItems = mQuadTree.search(searchBounds);
                if (clusterItems.size() == 1) {
                    // Only the current marker is in range. Just add the single item to the results.
                    //只有当前标记在范围内。只需将单个项添加到结果中。
                    results.add(candidate);
                    visitedCandidates.add(candidate);
                    distanceToCluster.put(candidate, 0d);
                    continue;
                }
                StaticCluster<T> cluster =
                        new StaticCluster<T>(candidate.mClusterItem.getPosition());
                results.add(cluster);

                for (QuadItem<T> clusterItem : clusterItems) {
                    Double existingDistance = distanceToCluster.get(clusterItem);
                    double distance = distanceSquared(clusterItem.getPoint(), candidate.getPoint());
                    if (existingDistance != null) {
                        // Item already belongs to another cluster. Check if it's closer to this cluster.
                        if (existingDistance < distance) {
                            continue;
                        }
                        // Move item to the closer cluster.
                        //将物品移动到更近的集群。
                        itemToCluster.get(clusterItem).remove(clusterItem.mClusterItem);
                    }
                    distanceToCluster.put(clusterItem, distance);
                    cluster.add(clusterItem.mClusterItem);
                    itemToCluster.put(clusterItem, cluster);
                }
                visitedCandidates.addAll(clusterItems);
            }
        }
        return results;
    }

    @Override
    public Collection<T> getItems() {
        final List<T> items = new ArrayList<T>();
        synchronized (mQuadTree) {
            for (QuadItem<T> quadItem : mItems) {
                items.add(quadItem.mClusterItem);
            }
        }
        return items;
    }

    private double distanceSquared(Point a, Point b) {
        return (a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y);
    }

    private Bounds createBoundsFromSpan(Point p, double span) {
        // TODO: Use a span that takes into account the visual size of the marker, not just its
        // TODO:使用的跨度要考虑到标记的视觉大小，而不仅仅是它的大小
        // LatLng.
        double halfSpan = span / 2;
        return new Bounds(
                p.x - halfSpan, p.x + halfSpan,
                p.y - halfSpan, p.y + halfSpan);
    }

    private static class QuadItem<T extends ClusterItem> implements PointQuadTree.Item, Cluster<T> {
        private final T mClusterItem;
        private final Point mPoint;
        private final LatLng mPosition;
        private Set<T> singletonSet;

        private QuadItem(T item) {
            mClusterItem = item;
            mPosition = item.getPosition();
            mPoint = PROJECTION.toPoint(mPosition);
            singletonSet = Collections.singleton(mClusterItem);
        }

        @Override
        public Point getPoint() {
            return mPoint;
        }

        @Override
        public LatLng getPosition() {
            return mPosition;
        }

        @Override
        public Set<T> getItems() {
            return singletonSet;
        }

        @Override
        public int getSize() {
            return 1;
        }
    }
}

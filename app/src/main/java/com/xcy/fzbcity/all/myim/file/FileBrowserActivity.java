package com.xcy.fzbcity.all.myim.file;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.netease.nim.uikit.api.wrapper.NimToolBarOptions;
import com.netease.nim.uikit.common.ToastHelper;
import com.netease.nim.uikit.common.activity.ToolBarOptions;
import com.netease.nim.uikit.common.activity.UI;
import com.netease.nim.uikit.common.adapter.TAdapterDelegate;
import com.netease.nim.uikit.common.adapter.TViewHolder;
import com.xcy.fzbcity.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.RequiresApi;

/**
 * 文件管理器
 * Created by hzxuwen on 2015/4/17.
 */
public class FileBrowserActivity extends UI implements TAdapterDelegate {
    // constant
    private static final String ROOT_PATH = Environment.getExternalStorageDirectory().getPath();
    public static final String EXTRA_DATA_PATH = "EXTRA_DATA_PATH";

    // data
    private ArrayList<String> names = null; //存储文件名称
    private ArrayList<String> paths = null; //存储文件路径

    // view
    private ListView fileListView;
    private List<FileBrowserAdapter.FileManagerItem> fileListItems = new ArrayList<>();

    public static void startActivityForResult(Activity activity, int reqCode) {
        Intent intent = new Intent();
        intent.setClass(activity, FileBrowserActivity.class);
        activity.startActivityForResult(intent, reqCode);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.file_browser_activity);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);      //  TODO    始终竖屏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().setStatusBarColor(Color.parseColor("#ff546da6"));

        ToolBarOptions options = new NimToolBarOptions();
        setToolBar(R.id.toolbar, options);

        findViews();
        showFileDir(ROOT_PATH);
    }

    private void findViews() {
        fileListView = findViewById(R.id.file_list);
    }

    /**
     * 显示文件列表
     *
     * @param path 根路径
     */
    private void showFileDir(String path) {
        names = new ArrayList<>();
        paths = new ArrayList<>();
        File file = new File(path);
        File[] files = null;
        try {
            files = file.listFiles();
        } catch (Exception e) {
            e.printStackTrace();
            ToastHelper.showToast(this, "获取文件列表失败");
        }
        //获取失败
        if (files == null || files.length == 0) {
            ToastHelper.showToast(this, "当前文件夹为空");
            return;
        }

        //如果当前目录不是根目录
        if (!ROOT_PATH.equals(path)) {
            names.add("@1");
            paths.add(ROOT_PATH);

            names.add("@2");
            paths.add(file.getParent());
        }
        //添加所有文件
        for (File f : files) {
            names.add(f.getName());
            paths.add(f.getPath());
        }

        fileListItems.clear();
        for (int i = 0; i < names.size(); i++) {
            fileListItems.add(new FileBrowserAdapter.FileManagerItem(names.get(i), paths.get(i)));
        }


        fileListView.setItemsCanFocus(true);
        fileListView.setAdapter(new FileBrowserAdapter(this, fileListItems, this));
        fileListView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String path = fileListItems.get(position).getPath();

                File file = new File(path);
                // 文件存在并可读
                if (file.exists() && file.canRead()) {
                    if (file.isDirectory()) {
                        //显示子目录及文件
                        showFileDir(path);
                    } else {
                        //处理文件
                        selectFile(path);
                    }
                } else {
                    //没有权限
                    ToastHelper.showToast(FileBrowserActivity.this, R.string.no_permission);
                }
            }
        });
    }

    private void selectFile(String path) {
        Intent intent = new Intent();
        intent.putExtra(EXTRA_DATA_PATH, path);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    /**
     * *************** implements TAdapterDelegate ***************
     */
    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public Class<? extends TViewHolder> viewHolderAtPosition(int position) {
        return FileBrowserViewHolder.class;
    }

    @Override
    public boolean enabled(int position) {
        return true;
    }
}


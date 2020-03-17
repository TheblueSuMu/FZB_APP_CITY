package com.xcy.fzbcity.all.myim.project;

import android.content.Intent;
import android.widget.TextView;

import com.netease.nim.uikit.business.session.viewholder.MsgViewHolderBase;
import com.netease.nim.uikit.common.ui.recyclerview.adapter.BaseMultiItemFetchLoadAdapter;
import com.xcy.fzbcity.R;
import com.xcy.fzbcity.all.api.FinalContents;
import com.xcy.fzbcity.all.view.ProjectDetails;

public class ProjectMsgViewHolderCrops extends MsgViewHolderBase {

    private ProjectCustomAttachment attachment;

    TextView project_view_name;
    TextView project_view_type;
    TextView project_view_price;
    TextView project_view_square;

    public ProjectMsgViewHolderCrops(BaseMultiItemFetchLoadAdapter adapter) {
        super(adapter);

    }

    @Override
    public int getContentResId() {
        return R.layout.project_view;
    }

    @Override
    public void inflateContentView() {
        project_view_name = findViewById(R.id.project_view_name);
        project_view_type = findViewById(R.id.project_view_type);
        project_view_price = findViewById(R.id.project_view_price);
        project_view_square = findViewById(R.id.project_view_square);
    }

    @Override
    public void bindContentView() {

        attachment = (ProjectCustomAttachment) message.getAttachment();

        project_view_name.setText(attachment.getName());
        project_view_type.setText(attachment.getProjectType());
        project_view_price.setText(attachment.getPrice());
        project_view_square.setText(attachment.getSquare());


    }

    @Override
    public void onItemClick() {
        // app内打开浏览器,将网页URL传入，此处有误先别着急，往下看文档或者先注释

        Intent intent = new Intent(context, ProjectDetails.class);
        FinalContents.setProjectID(attachment.getPid());
        context.startActivity(intent);

    }

    @Override
    protected int rightBackground() {
        return 0;
    }
}

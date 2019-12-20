package com.xcy.fzbcity.all.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;

public class PtrClassicRefreshLayout  extends PtrClassicFrameLayout {

    private boolean disallowInterceptTouchEvent = false;

    public PtrClassicRefreshLayout(Context context) {
        super(context);
    }

    public PtrClassicRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PtrClassicRefreshLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void requestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        disallowInterceptTouchEvent = disallowIntercept;
        super.requestDisallowInterceptTouchEvent(disallowIntercept);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent e) {
        switch (e.getAction()) {
            case MotionEvent.ACTION_UP:
                //解除刷新的屏蔽
                this.requestDisallowInterceptTouchEvent(false);
                break;
        }

        if (disallowInterceptTouchEvent) {
            //事件向下分发给子控件，子控件会屏蔽掉父控件的刷新
            return dispatchTouchEventSupper(e);
        }

        return super.dispatchTouchEvent(e);
    }

}
